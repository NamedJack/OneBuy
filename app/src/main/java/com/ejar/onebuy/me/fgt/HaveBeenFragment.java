package com.ejar.onebuy.me.fgt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.LoginActivity;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.HaveBeenBean;
import com.ejar.onebuy.databinding.FgtJoinRecordBinding;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseBindingFragment;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.SPUtil;
import wang.wongxd.wquicklib.utils.SystemUtils;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/9.
 * <p>
 * 夺宝 已揭晓
 */

public class HaveBeenFragment extends BaseBindingFragment<FgtJoinRecordBinding> {
    public HaveBeenFragment() {
    }


    @Override
    public int setContent() {
        return R.layout.fgt_join_record;
    }
    private RvAdapter adapter;
    private String userId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(3)));
        userId = SPUtil.getInstance(getActivity()).getInfo("userId", "");

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getList(true);
            }
        }, bindingView.rv);

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });

        bindingView.srl.autoRefresh();
    }

    private int pageNo = 1, totalPage = 1;


    private void getList(final boolean isLoadMore) {
        if (isLoadMore) {
            if (pageNo > totalPage) {
                adapter.loadMoreEnd();
                return;
            }
        } else {
            pageNo = 1;
            totalPage = 1;
        }
        if (TextUtils.isEmpty(userId)) {
            TU.cT("登录失效 请重新登录");
            SystemUtils.cleanTask2Activity(getActivity(), LoginActivity.class);
            return;
        }

        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().haveBeen(App.token, userId + "", pageNo + "", "10"), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                bindingView.srl.finishRefresh();
                Logger.e(s);
                HaveBeenBean bean = new Gson().fromJson(s, HaveBeenBean.class);

                if (bean.getCode() != 200) {
                    if (isLoadMore) adapter.loadMoreFail();
                    else bindingView.srl.finishRefresh();
                    return;
                }

                totalPage = bean.getData().getTotalPage();
                if (bean.getData().getList() != null && bean.getData().getList().size() != 0)
                    ++pageNo;
                else {
                    if (isLoadMore) {
                        adapter.loadMoreEnd();
                    } else {
                        bindingView.srl.finishRefresh();
                    }
                    return;
                }
                if (isLoadMore) {
                    adapter.addData(bean.getData().getList());
                    adapter.loadMoreComplete();
                } else {
                    adapter.setNewData(bean.getData().getList());
                }


            }

            @Override
            public void onError(Throwable e) {
                if (isLoadMore) adapter.loadMoreFail();
                else bindingView.srl.finishRefresh();
            }
        }, getActivity(),false));
    }

    private class RvAdapter extends BaseQuickAdapter<HaveBeenBean.DataBean.ListBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_join_yet);
        }


        @Override
        protected void convert(BaseViewHolder helper, final HaveBeenBean.DataBean.ListBean item) {

            ImageView iv = helper.getView(R.id.iv_img);
            ImageLoader.roundImg(item.getProImg(),iv);

            helper.setText(R.id.tv_product_name, item.getShopName())
                    .setText(R.id.tv_issue, "期数: " + item.getDatenumber())
                    .setText(R.id.tv_join_time, item.getNum() + "")
            ;



            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/recent/detail").withString("id", item.getProId() + "")
                            .withString("goodsNo",item.getDatenumber()+"").navigation();
                }
            });
        }
    }
}

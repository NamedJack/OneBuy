package com.ejar.onebuy.me.fgt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.JoinRecordBean;
import com.ejar.onebuy.databinding.FgtJoinRecordBinding;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/9.
 * <p>
 * 全部夺宝记录
 */

public class JoinRecordFragment extends BaseBindingFragment<FgtJoinRecordBinding> {
    public JoinRecordFragment() {
    }

    private String userId;

    public static Fragment newInstance(String userId){
        Fragment fgt = new JoinRecordFragment();
        Bundle b= new Bundle();
        b.putString("userId",userId);
        fgt.setArguments(b);
        return fgt;
    }

    @Override
    public int setContent() {
        return R.layout.fgt_join_record;
    }

    private RvAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(3)));

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                getList(false);
//            }
//        }, bindingView.rv);

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
        if (TextUtils.isEmpty(userId)){
//            userId = App.user.getId() + "";
            userId =   SPUtil.getInstance(getActivity()).getInfo("userId",  "");
//            userId =   "";
        }


        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().fullRecord(App.token,userId, pageNo + "", "3"), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                bindingView.srl.finishRefresh();
                Logger.e(pageNo + "   totalPage  " + totalPage + " " );
                Logger.e(s);
                JoinRecordBean bean = null;
                try {
                    bean = new Gson().fromJson(s, JoinRecordBean.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

                if (bean.getCode() != 200) {
                    if (isLoadMore) adapter.loadMoreFail();
                    else bindingView.srl.finishRefresh();
                    return;
                }

                totalPage = bean.getData().getTotalPage();
                if (bean.getData().getUlist() != null && bean.getData().getUlist().size() != 0) {
                    ++pageNo;
                } else {
                    if (isLoadMore) {
                        adapter.loadMoreEnd();
                    } else {
                        bindingView.srl.finishRefresh();
                    }
                    return;
                }
                if (isLoadMore) {
                    adapter.addData(bean.getData().getUlist());
                    adapter.loadMoreComplete();
                } else {
                    adapter.setNewData(bean.getData().getUlist());
                }

            }

            @Override
            public void onError(Throwable e) {
                if (isLoadMore) adapter.loadMoreFail();
                else bindingView.srl.finishRefresh();
            }
        }, getActivity(),false));
    }

    private class RvAdapter extends BaseQuickAdapter<JoinRecordBean.DataBean.UlistBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_join_record);
        }


        @Override
        protected void convert(BaseViewHolder helper, final JoinRecordBean.DataBean.UlistBean item) {

            ImageView iv = helper.getView(R.id.iv_img);
            ImageLoader.roundImg(item.getProImg(),iv);

            helper.setText(R.id.tv_product_name, item.getShopName())
                    .setText(R.id.tv_issue, "期数: " + item.getGoodsNo())
                    .setText(R.id.tv_join_time, item.getNum() + "")

            ;


            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", item.getProId() + "").navigation();
                }
            });
        }
    }
}

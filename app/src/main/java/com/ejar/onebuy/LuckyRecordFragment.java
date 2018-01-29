package com.ejar.onebuy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.bean.me.LuckyRecordBean;
import com.ejar.onebuy.databinding.AtyLuckyRecordBinding;
import com.ejar.onebuy.me.SendShareActivity;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseBindingFragment;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/28.
 * <p>
 * TA 的个人中心的 幸运记录
 */

public class LuckyRecordFragment extends BaseBindingFragment<AtyLuckyRecordBinding> {
    private String userId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        adapter = new RvAdapter(new WeakReference<AppCompatActivity>((AppCompatActivity) getActivity()));
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

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                Logger.e(view.getId() + "");
                SendShareActivity.startPostActivity(getActivity(), new ArrayList<String>(), 3, currentGid + "", currentPid + "");
            }
        });
    }

    @Override
    public int setContent() {
        return R.layout.aty_lucky_record;
    }

    public static Fragment newInstance(String userId) {
        Fragment fgt = new LuckyRecordFragment();
        Bundle b = new Bundle();
        b.putString("userId", userId);
        fgt.setArguments(b);
        return fgt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getString("userId");
    }

    private RvAdapter adapter;


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

        Logger.e("userId  " + userId);
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().luckyRecord(App.token, userId, pageNo + "", "3"),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        bindingView.srl.finishRefresh();
                        Logger.e(s);
                        LuckyRecordBean bean = new Gson().fromJson(s, LuckyRecordBean.class);

                        if (bean.getCode() != 200) {
                            if (isLoadMore) adapter.loadMoreFail();
                            else bindingView.srl.finishRefresh();
                            return;
                        }

                        totalPage = bean.getData().getTotalPage();
                        if (bean.getData().getList() != null && bean.getData().getList().size() != 0) {
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

    private int currentGid, currentPid;

    private class RvAdapter extends BaseQuickAdapter<LuckyRecordBean.DataBean.ListBean, BaseViewHolder> {

        private WeakReference<AppCompatActivity> aty;

        public RvAdapter(WeakReference<AppCompatActivity> aty) {
            super(R.layout.item_rv_lucky_record);
            this.aty = aty;
        }


        @Override
        protected void convert(BaseViewHolder helper, final LuckyRecordBean.DataBean.ListBean item) {

            currentGid = item.getId();
            currentPid = item.getProId();
            ImageView iv = helper.getView(R.id.iv_img);
            ImageLoader.roundImg(item.getProImg(),iv);

            helper.setText(R.id.tv_name, item.getShopName())
                    .setText(R.id.tv_issue, "期数: " + item.getDatenumber())
                    .setText(R.id.tv_lucky_num, "幸运号码: " + item.getWinningNumber())
                    .setText(R.id.tv_need_people, "本期参与: " + item.getNum() + " 人次")
                    .setText(R.id.tv_time, "揭晓时间: " + item.getLotterytime() + "")
                    .addOnClickListener(R.id.tv_share);
            helper.setVisible(R.id.tv_use, false)
                    .setVisible(R.id.tv_share, false);
        }
    }
}

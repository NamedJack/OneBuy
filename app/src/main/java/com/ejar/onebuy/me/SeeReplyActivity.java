package com.ejar.onebuy.me;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.ReplyBean;
import com.ejar.onebuy.databinding.AtySeeReplyBinding;
import com.ejar.onebuy.util.DialogUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/18.
 * <p>
 * 查看回复
 */
@Route(path = "/me/seeReply")
public class SeeReplyActivity extends BaseHeaderBindingActivity<AtySeeReplyBinding> {

    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_see_reply);
        setToolBarLeftText("客服中心");
        setToolbarTitle("查看回复");

        initRecycleview();

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });
        bindingView.srl.autoRefresh();
    }


    private void initRecycleview() {
        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(2)));

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getList(true);
            }
        }, bindingView.rv);
    }

    private int pageNo = 1, totalPage = 1;

    public void getList(final boolean isLoadMore) {
        if (!isLoadMore) {
            pageNo = 1;
            totalPage = 1;
        } else {
            if (pageNo > totalPage) {
                adapter.loadMoreEnd();
                return;
            }
        }
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().seeReply(App.token, pageNo + "", "10"), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                addDisposable(disposable);
                bindingView.srl.finishRefresh();
                Logger.e(s);
                ReplyBean bean = new Gson().fromJson(s, ReplyBean.class);
                //data不对
                if (bean.getCode() != 200) {
                    TU.cT(bean.getMsg());
                    if (isLoadMore) adapter.loadMoreFail();
                    return;
                }

                totalPage = bean.getData().getTotalPage();

                //列表为空
                if (bean.getData().getList() != null && bean.getData().getList().size() != 0) {
                    ++pageNo;
                } else {
                    if (isLoadMore)
                        adapter.loadMoreEnd();
                    return;
                }

                if (isLoadMore) {
                    adapter.setNewData(bean.getData().getList());
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
        }, SeeReplyActivity.this,false));
    }


    private class RvAdapter extends BaseQuickAdapter<ReplyBean.DataBean.ListBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_service);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ReplyBean.DataBean.ListBean item) {
            helper.setText(R.id.tv, item.getLeContent());
            helper.setVisible(R.id.iv_hot, false);
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.showDialog(SeeReplyActivity.this,
                            item.getLeContent(), TextUtils.isEmpty(item.getReplyContent()) ? "暂无回复" : item.getReplyContent(), "我已知晓", "关闭", new DialogUtil.DialogListener() {
                                @Override
                                public void left(Dialog dialog) {

                                }

                                @Override
                                public void right(Dialog dialog) {

                                }
                            });
                }
            });
        }
    }
}

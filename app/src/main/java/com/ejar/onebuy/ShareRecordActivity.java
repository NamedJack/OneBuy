package com.ejar.onebuy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.bean.me.ShareRecordBean;
import com.ejar.onebuy.databinding.AtyShareRecordBinding;
import com.ejar.onebuy.util.ImageLoader;
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
import wang.wongxd.wquicklib.utils.glide.GlideRoundTransform;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/14.
 */
@Route(path = "/base/shareRecord")
public class ShareRecordActivity extends BaseHeaderBindingActivity<AtyShareRecordBinding> {
    @Autowired
    String id; //proId
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_share_record);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("返回");
        setToolbarTitle("晒单分享");


        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });

        adapter = new RvAdapter();

        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(2)));

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);
//        adapter.setEnableLoadMore(true);
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                getList(true);
//            }
//        }, bindingView.rv);

        getList(false);
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
        Logger.e(App.token);
        RetrofitUtil.toBaseIntercept(RetrofitImp.getHomeApi().shareRecordList(App.token, id, pageNo + "", "10"),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        Logger.e(s);

                        ShareRecordBean bean = new Gson().fromJson(s, ShareRecordBean.class);

                        if (bean.getCode() != 200) {
                            if (isLoadMore) adapter.loadMoreFail();
                            else bindingView.srl.finishRefresh();
                            return;
                        }

                        totalPage = bean.getData().getTotalPage();
                        if (bean.getData().getOlist() != null && bean.getData().getOlist().size() != 0) {
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
                            adapter.addData(bean.getData().getOlist());
                            adapter.loadMoreComplete();
                        } else {
                            adapter.setNewData(bean.getData().getOlist());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) adapter.loadMoreFail();
                        else
                            bindingView.srl.finishRefresh();
                    }
                }, ShareRecordActivity.this,false));
    }

    private static class RvAdapter extends BaseQuickAdapter<ShareRecordBean.DataBean.OlistBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_share_record);
        }
//

        @Override
        protected void convert(BaseViewHolder helper, final ShareRecordBean.DataBean.OlistBean item) {
            ImageView ivHeader = helper.getView(R.id.iv_header);
            ImageLoader.cicleImg(item.getHeadImg(),ivHeader);

            helper.setText(R.id.tv_nick_name, item.getUsername())
                    .setText(R.id.tv_time, item.getLotterytime())
                    .setText(R.id.tv_name, item.getShopname())
                    .setText(R.id.tv_issue, "期号:  "+item.getDatenumber()+"")
                    .setText(R.id.tv_content, item.getContent())
            ;

            ImageView iv1 = helper.getView(R.id.iv_1);

            ImageView iv2 = helper.getView(R.id.iv_2);

            ImageView iv3 = helper.getView(R.id.iv_3);

            String imgsString = item.getSurl();

            String[] imgs = imgsString.split(",");
            for (int i = 0, length = imgs.length; i < length; i++) {
                String img = "";
                ImageView iv = null;
                switch (i) {
                    case 0:
                        iv = iv1;
                        img = imgs[0];
                        break;

                    case 1:
                        iv = iv2;
                        img = imgs[1];
                        break;

                    case 2:
                        iv = iv3;
                        img = imgs[2];
                        break;
                }

                if (iv != null) {
                    Glide.with(ivHeader.getContext()).load(ApiServer.IMG_HOST + img).placeholder(R.drawable.placeholder)
                            .transform(new GlideRoundTransform(ivHeader.getContext()))
                            .into(iv);
                }
            }


            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/home/shareDetail").withObject("data", item).navigation();
                }
            });

        }
    }
}

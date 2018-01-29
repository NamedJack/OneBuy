package com.ejar.onebuy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.bean.me.ShareRecordBean;
import com.ejar.onebuy.bean.me.UnboxingBean;
import com.ejar.onebuy.databinding.AtyUnboxingBinding;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseBindingFragment;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.glide.GlideRoundTransform;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/28.
 * <p>
 * TA 的个人中心的 晒单记录
 */

public class ShareRecordFragemt extends BaseBindingFragment<AtyUnboxingBinding>{

    private RvAdapter adapter;

    private String userId;
    public static Fragment newInstance(String userId){
        Fragment fgt = new ShareRecordFragemt();
        Bundle b= new Bundle();
        b.putString("userId",userId);
        fgt.setArguments(b);
        return fgt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getString("userId");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });

        adapter = new RvAdapter(new WeakReference<AppCompatActivity>((AppCompatActivity) getActivity()));

        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(2)));

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);
        adapter.setEnableLoadMore(false);
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                getList(false);
//            }
//        }, bindingView.rv);

        getList(false);
    }

    @Override
    public int setContent() {
        return R.layout.aty_unboxing;
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
        Logger.e("userId  " + userId);
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().shareRecordList(App.token,userId, pageNo + "", "3"),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        Logger.e(s);

                        UnboxingBean bean = new Gson().fromJson(s, UnboxingBean.class);

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
                }, getActivity(),false));
    }

    private static class RvAdapter extends BaseQuickAdapter<UnboxingBean.DataBean.OlistBean, BaseViewHolder> {

        private WeakReference<AppCompatActivity> aty;

        public RvAdapter(WeakReference<AppCompatActivity> aty) {
            super(R.layout.item_rv_unboxing);
            this.aty = aty;
        }

        @Override
        protected void convert(BaseViewHolder helper, final UnboxingBean.DataBean.OlistBean item) {
            ImageView ivHeader = helper.getView(R.id.iv_header);
            ImageLoader.cicleImg(item.getHeadImg(), ivHeader);

            helper.setText(R.id.tv_time, item.getLotterytime())
                    .setText(R.id.tv_name, item.getShopname())
                    .setText(R.id.tv_issue, "期号:  " + item.getDatenumber() + "")
                    .setText(R.id.tv_content, item.getContent())
                    .addOnClickListener(R.id.tv_delete)
            ;

            ImageView iv1 = helper.getView(R.id.iv_1);

            ImageView iv2 = helper.getView(R.id.iv_2);

            ImageView iv3 = helper.getView(R.id.iv_3);

            final String imgsString = item.getSurl();

            final String[] imgs = imgsString.split(",");
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

            helper.setVisible(R.id.tv_delete, false);

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShareRecordBean.DataBean.OlistBean data = new ShareRecordBean.DataBean.OlistBean();
                    data.setContent(item.getContent());
                    data.setDatenumber(item.getDatenumber());
                    data.setExpectNum(item.getExpectNum());
                    data.setUsername(item.getUsername());
                    data.setSuntime(item.getSuntime());
                    data.setShopname(item.getShopname());
                    data.setWinningNumber(item.getWinningNumber());
                    data.setLotterytime(item.getLotterytime());
                    data.setSurl(item.getSurl());
                    data.setImg(item.getImg());
                    ARouter.getInstance().build("/home/shareDetail").withObject("data", data).navigation();
                }
            });
        }

    }
}

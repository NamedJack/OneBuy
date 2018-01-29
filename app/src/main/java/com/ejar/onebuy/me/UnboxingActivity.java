package com.ejar.onebuy.me;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.ShareRecordBean;
import com.ejar.onebuy.bean.me.UnboxingBean;
import com.ejar.onebuy.databinding.AtyUnboxingBinding;
import com.ejar.onebuy.util.DialogUtil;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.glide.GlideRoundTransform;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/10.
 * 晒单
 */
@Route(path = "/me/unboxing")
public class UnboxingActivity extends BaseHeaderBindingActivity<AtyUnboxingBinding> {


    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_unboxing);
        setToolBarLeftText("个人中心");
        setToolbarTitle("我的晒单");

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });

        adapter = new RvAdapter(new WeakReference<AppCompatActivity>(this));

        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(2)));

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getList(true);
            }
        }, bindingView.rv);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter1, View view, final int position) {
                Logger.e(String.valueOf(view.getId() == R.id.tv_delete) + " id  " + view.getId());

                if (view.getId() == R.id.tv_delete) {
                    DialogUtil.showDialog(UnboxingActivity.this, "确定删除吗？", "", "确定", "取消", new DialogUtil.DialogListener() {
                        @Override
                        public void left(Dialog dialog) {
                            RetrofitImp.getMeApi().deleteUnboxing(App.token, adapter.getData().get(position).getId() + "").subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<String>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            addDisposable(d);
                                        }

                                        @Override
                                        public void onNext(String s) {
                                            Logger.e(s);
                                            try {
                                                JSONObject j = new JSONObject(s);
                                                if (j.optInt("code") == 200) {
                                                    TU.cT("删除成功");
                                                    adapter.getData().remove(position);
                                                    adapter.notifyItemRemoved(position);
                                                } else
                                                    TU.cT(j.optString("删除失败"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            TU.cT("删除失败! " + e.getMessage());
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }

                        @Override
                        public void right(Dialog dialog) {

                        }
                    });
                }
            }
        });

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
        Logger.e(App.token + "  " + App.user.getId());
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().shareRecordList(App.token, App.user.getId() + "", pageNo + "", "10"),
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
                }, UnboxingActivity.this,false));
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

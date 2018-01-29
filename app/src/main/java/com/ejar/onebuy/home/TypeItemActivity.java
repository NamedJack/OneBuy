package com.ejar.onebuy.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.home.HomeInfoBean;
import com.ejar.onebuy.bean.home.JoinNowBean;
import com.ejar.onebuy.databinding.AtyTypeItemBinding;
import com.ejar.onebuy.home.fgt.JoinNowFragment;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.rx.RxBus;
import wang.wongxd.wquicklib.base.rx.RxEventCodeType;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/11.
 * <p>
 * 极速专区
 */

@Route(path = "/home/typeItem")
public class TypeItemActivity extends BaseHeaderBindingActivity<AtyTypeItemBinding> {
    @Autowired
    String title;
    @Autowired
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_type_item);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("首页");
        setToolbarTitle(title);


        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(3)));

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

        joinNow();
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

        Map<String, String> map = new HashMap<>();
        map.put("catalogID", id);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", "10");

        RetrofitUtil.toBaseIntercept(RetrofitImp.getHomeApi().HomeInfo(map, App.token),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        bindingView.srl.finishRefresh();
                        Logger.e(s);
                        HomeInfoBean bean = new Gson().fromJson(s, HomeInfoBean.class);

                        if (bean.getCode() != 200) {
                            if (isLoadMore) adapter.loadMoreFail();
                            else bindingView.srl.finishRefresh();
                            return;
                        }

                        totalPage = bean.getData().getTotalPage();
                        if (bean.getData().getProducts() != null && bean.getData().getProducts().size() != 0) {
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
                            adapter.addData(bean.getData().getProducts());
                            adapter.loadMoreComplete();
                        } else {
                            adapter.setNewData(bean.getData().getProducts());
                        }

                        bindingView.tvTip.setText("共 "+adapter.getData().size()+" 件商品");

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) adapter.loadMoreFail();
                        else bindingView.srl.finishRefresh();
                    }
                }, TypeItemActivity.this,false));
    }


    /**
     * 立即参与
     */
    private void joinNow() {
        //立即参与
        RxBus.getDefault().toObservable(RxEventCodeType.HOME_JOIN_NOW, String.class).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                RetrofitUtil.getStringInstance().create(HomeApi.class).joinNow(App.token, s).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                            @Override
                            public void onNext(String s, Disposable disposable) {
                                addDisposable(disposable);
                                JoinNowBean joinNowBean = new Gson().fromJson(s, JoinNowBean.class);
                                if (joinNowBean.getCode() == 200) {
                                    JoinNowFragment.showDialog(TypeItemActivity.this, joinNowBean.getData().getProduct(), new JoinNowFragment.JoinCallback() {
                                        @Override
                                        public void doRefresh() {
                                            bindingView.srl.autoRefresh();
                                        }
                                    });
                                } else
                                    TU.cT(joinNowBean.getMsg());
                            }

                            @Override
                            public void onError(Throwable e) {
                                TU.cT("参与失败！ " + e.getMessage());
                            }
                        }, TypeItemActivity.this));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private class RvAdapter extends BaseQuickAdapter<HomeInfoBean.DataBean.ProductsBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_speed_zone);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeInfoBean.DataBean.ProductsBean item) {
            ImageView iv = helper.getView(R.id.iv_img);
            Glide.with(iv.getContext()).load(ApiServer.IMG_HOST + item.getPicture()).placeholder(R.drawable.placeholder).into(iv);

            double tempProgress = (double) item.getAlreadyNum() / (double) item.getExpectNum();
            double progress = tempProgress * 100;
            ProgressBar pb = helper.getView(R.id.pb);
            pb.setProgress((int) progress);

            TextView tvJoin = helper.getView(R.id.tv_join_now);
            tvJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxBus.getDefault().post(RxEventCodeType.HOME_JOIN_NOW, item.getId() + "");
                }
            });

            helper.setText(R.id.tv_product_name, item.getName())
                    .setText(R.id.tv_need_people, "总需" + item.getExpectNum() + "人次")
                    .setText(R.id.tv_left, String.valueOf(item.getExpectNum() - item.getAlreadyNum()));

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", item.getId() + "").navigation();
                }
            });

        }
    }
}

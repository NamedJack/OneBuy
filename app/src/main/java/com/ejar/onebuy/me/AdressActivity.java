package com.ejar.onebuy.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.bean.me.AdressBean;
import com.ejar.onebuy.databinding.AtyAdressBinding;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/10.
 * 收货地址
 */

@Route(path = "/me/adress")
public class AdressActivity extends BaseHeaderBindingActivity<AtyAdressBinding> {

    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_adress);
        setToolBarLeftText("个人中心");
        setToolbarTitle("收货地址");
        setToolBarRightText("添加地址");
        setToolBarRightTextColor(getResources().getColor(R.color.appBlue));
        setToolBarRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/addAdress").withBoolean("isEdit", false).navigation();
            }
        });


        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList();
            }
        });


        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(3)));
        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);

        getList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    private void getList() {
        RetrofitUtil.getStringInstance().create(MeApi.class).getAdressList(App.token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        AdressBean adressBean = new Gson().fromJson(s, AdressBean.class);
                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        if (adressBean.getCode() != 200) {
                            return;
                        }

                        adapter.setNewData(adressBean.getData().getAddressList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        bindingView.srl.finishRefresh();
                    }
                }, AdressActivity.this,false));
    }

    private static class RvAdapter extends BaseQuickAdapter<AdressBean.DataBean.AddressListBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_adress);
        }


        @Override
        protected void convert(BaseViewHolder helper, final AdressBean.DataBean.AddressListBean item) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/me/addAdress").withBoolean("isEdit", true)
                            .withString("id",item.getId()+"")
                            .withObject("data", item)
                            .navigation();
                }
            });

            helper.setText(R.id.tv_name, item.getName())
                    .setText(R.id.tv_phone, item.getTel())
                    .setText(R.id.tv_location, item.getProvince() + item.getCity() + item.getArea() + item.getAddress());

        }
    }
}

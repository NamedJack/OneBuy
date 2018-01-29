package com.ejar.onebuy.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.home.GoodTypeBean;
import com.ejar.onebuy.databinding.AtyTypeViewBinding;
import com.google.gson.Gson;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;

/**
 * Created by wongxd on 2017/8/10.
 * 分类浏览
 */

@Route(path = "/home/typeView")
public class TypeViewActivity extends BaseHeaderBindingActivity<AtyTypeViewBinding> {

    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_type_view);
        setToolBarLeftText("首页");
        setToolbarTitle("分类浏览");

        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getTypeList();
    }

    private void getTypeList() {
       RetrofitUtil.toBaseIntercept(RetrofitImp.getHomeApi().getGoodType(App.token),
               new ProgressObserver<>(new ObserverOnNextListener<String>() {
                   @Override
                   public void onNext(String s, Disposable disposable) {
                       GoodTypeBean type = new Gson().fromJson(s, GoodTypeBean.class);
                       addDisposable(disposable);
                       if (type.getCode() != 200) return;
                       adapter.setNewData(type.getData().getCatalogs());
                   }

                   @Override
                   public void onError(Throwable e) {

                   }
               }, TypeViewActivity.this) );

    }

    private class RvAdapter extends BaseQuickAdapter<GoodTypeBean.DataBean.CatalogsBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_type);
        }


        @Override
        protected void convert(BaseViewHolder helper, final GoodTypeBean.DataBean.CatalogsBean item) {
            helper.setText(R.id.tv_type, item.getName());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/home/typeItem").withString("title", item.getName())
                            .withString("id", item.getId() + "")
                            .navigation()
                    ;
                }
            });
        }
    }
}

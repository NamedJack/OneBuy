package com.ejar.onebuy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.bean.PreviousLuckyBean;
import com.ejar.onebuy.databinding.AtyPreviousLuckyBinding;
import com.ejar.onebuy.home.HomeApi;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.TU;

/**
 * Created by wongxd on 2017/8/14.
 * 往期揭晓
 */

@Route(path = "/base/previousLucky")
public class AtyPreviousLucky extends BaseHeaderBindingActivity<AtyPreviousLuckyBinding> {

    @Autowired
    String id;
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_previous_lucky);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("返回");
        setToolbarTitle("往期揭晓");

        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getInfo();
            }
        });
        getInfo();
    }

    private void getInfo() {
        RetrofitUtil.toBaseIntercept(RetrofitUtil.getStringInstance().create(HomeApi.class).getPreviousLucky(App.token, id),
                new ProgressObserver<>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        PreviousLuckyBean dataBeanBaseResponse = new Gson().fromJson(s, PreviousLuckyBean.class);

                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        if (dataBeanBaseResponse.getCode() == 200) {
                            adapter.setNewData(dataBeanBaseResponse.getData().getWinBefore());
                        } else TU.cT(dataBeanBaseResponse.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT(e.getMessage());
                        bindingView.srl.finishRefresh();
                    }
                }, AtyPreviousLucky.this,false));


    }

    private static class RvAdapter extends BaseQuickAdapter<PreviousLuckyBean.DataBean.WinBeforeBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_previous_lucky);
        }

        @Override
        protected void convert(BaseViewHolder helper, final PreviousLuckyBean.DataBean.WinBeforeBean item) {
            ImageView iv = helper.getView(R.id.iv_header);
            ImageLoader.cicleImg(item.getUser().getHeadImg(), iv);

            helper.setText(R.id.tv_issuse, "期号:" + item.getDatenumber())
                    .setText(R.id.tv_time, "(揭晓时间:" + item.getLotterytime() + ")")
                    .setText(R.id.tv_people_name, item.getUser().getNiceName())
//                    .setText(R.id.tv_ip, "(" + item.getUser().getIpAddr() + " IP:" + item.getUser().getIp() + ")")
                    .setText(R.id.tv_people_id, "用户ID: " + item.getUser().getId() + "  (唯一不变标识)")
                    .setText(R.id.tv_lucky_num, item.getWinningNumber() + "")
                    .setText(R.id.tv_join_num, item.getUser().getJoinNumber() + "")
            ;

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        ARouter.getInstance().build("/base/itProfile")
                                .withString("userId", item.getUser().getId() + "")
                                .withString("userName", item.getUser().getNiceName())
                                .withString("userHead", item.getUser().getHeadImg())
                                .navigation();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

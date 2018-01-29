package com.ejar.onebuy.me;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.ServiceBean;
import com.ejar.onebuy.databinding.AtyServiceBinding;
import com.ejar.onebuy.util.DialogUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;


/**
 * Created by wongxd on 2017/8/10.
 */

@Route(path = "/me/service")
public class ServiceActivity extends BaseHeaderBindingActivity<AtyServiceBinding> {

    private RvAdapter adapter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_service);
        setToolBarLeftText("个人中心");
        setToolbarTitle("客服");

        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(2)));

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                getList(false);
            }
        });

        bindingView.srl.autoRefresh();

        bindingView.tvSeeReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/seeReply").navigation();
            }
        });

        bindingView.rvReported.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View vv = View.inflate(getApplicationContext(), R.layout.dialog_do_reported, null);
                final EditText editText = (EditText) vv.findViewById(R.id.et);
                Button btn = (Button) vv.findViewById(R.id.btn);

                dialog = new AlertDialog.Builder(ServiceActivity.this).setView(vv).show();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String msg = editText.getText().toString().trim();
                        if (TextUtils.isEmpty(msg)) {
                            TU.cT("请输入问题");
                            return;
                        }
                        doReported(msg);
                    }
                });
            }
        });
    }





    private void doReported(String msg) {
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().doReported(App.token, msg), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                addDisposable(disposable);
                Logger.e(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                   TU.cT(jsonObject.optString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (dialog != null && dialog.isShowing()) dialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                if (dialog != null && dialog.isShowing()) dialog.dismiss();
            }
        }, ServiceActivity.this));
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
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().getServiceList(App.token, pageNo + "", "10"), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                addDisposable(disposable);
                bindingView.srl.finishRefresh();
                Logger.e(s);
                ServiceBean bean = new Gson().fromJson(s, ServiceBean.class);
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
        }, ServiceActivity.this,false));
    }


    private class RvAdapter extends BaseQuickAdapter<ServiceBean.DataBean.ListBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_service);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ServiceBean.DataBean.ListBean item) {
            helper.setText(R.id.tv, item.getTitle());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.showDialog(ServiceActivity.this, item.getContent(), item.getContent(), "我已知晓", "关闭", new DialogUtil.DialogListener() {
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

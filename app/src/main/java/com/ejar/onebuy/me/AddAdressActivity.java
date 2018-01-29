package com.ejar.onebuy.me;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.bean.me.AdressBean;
import com.ejar.onebuy.databinding.AtyAddAdressBinding;
import com.ejar.onebuy.util.DialogUtil;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.SystemUtils;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.location.LocationPicker;

/**
 * Created by wongxd on 2017/8/10.
 * <p>
 * 添加  修改 地址
 */

@Route(path = "/me/addAdress")
public class AddAdressActivity extends BaseHeaderBindingActivity<AtyAddAdressBinding> {

    @Autowired
    boolean isEdit;
    @Autowired
    String id;
    @Autowired
    AdressBean.DataBean.AddressListBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_adress);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("地址列表");
        setToolbarTitle("添加地址");
        setToolBarRightText("保存");
        setToolBarRightTextColor(getResources().getColor(R.color.appBlue));


        bindingView.llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationPicker.initRegionPicker(new WeakReference<Activity>(AddAdressActivity.this), new LocationPicker.LocationCallback() {
                    @Override
                    public void getLocation(String location, String p, String c, String a) {
                        bindingView.tvLocation.setText(location);
                        province = p;
                        city = c;
                        area = a;
                    }
                }).show();
            }
        });

        if (isEdit) {
            province = data.getProvince();
            city = data.getCity();
            area = data.getArea();

            bindingView.etName.setText(data.getName());
            bindingView.etPhone.setText(data.getTel());
            bindingView.tvLocation.setText(province + city + area);
            bindingView.etDetailAdress.setText(data.getAddress());

            bindingView.cb.setChecked(data.getIsdefault() != 0);

            bindingView.tvDelete.setVisibility(View.VISIBLE);
            bindingView.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.showDialog(AddAdressActivity.this, "确定删除地址吗？", "", "确定", "取消", new DialogUtil.DialogListener() {
                        @Override
                        public void left(Dialog dialog) {
                            deleteAdress();
                            dialog.dismiss();
                        }

                        @Override
                        public void right(Dialog dialog) {
                            dialog.dismiss();
                        }
                    });

                }
            });

            setToolBarRightTextListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateAdress();
                }
            });
        } else {
            bindingView.tvDelete.setVisibility(View.GONE);
            setToolBarRightTextListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addAdress();
                }
            });
        }
    }

    private Map<String, String> params = new HashMap<>();
    private String province, city, area;

    private void addAdress() {

        String name = bindingView.etName.getText().toString().trim();
        String phone = bindingView.etPhone.getText().toString().trim();
        String detail = bindingView.etDetailAdress.getText().toString().trim();


        String alipay = bindingView.etAlipay.getText().toString().trim();
        String wechat = bindingView.etWechat.getText().toString().trim();

        if (TextUtils.isEmpty(alipay) && TextUtils.isEmpty(wechat)) {
            TU.cT("微信和支付宝至少输入一个");
            return;
        }


        if (SystemUtils.isHadEmptyText(name, phone, detail, city)) {
            TU.cT("请完善本页数据!");
            return;
        }

        params.clear();
        params.put("name", name);
        params.put("tel", phone);
        params.put("province", province);
        params.put("city", city);
        params.put("area", area);
        params.put("address", detail);
        params.put("isdefault", bindingView.cb.isChecked() ? "1" : "0");

        RetrofitUtil.getStringInstance().create(MeApi.class).addAdress(App.token, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 200) {
                                finish();
                            }
                            TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("添加地址失败");
                    }
                }, AddAdressActivity.this));
    }


    private void deleteAdress() {
        if (TextUtils.isEmpty(id)) return;
        RetrofitUtil.getStringInstance().create(MeApi.class).deleteAdress(App.token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 200) {
                                finish();
                            }
                            TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("删除地址失败");
                    }
                }, AddAdressActivity.this));
    }


    private void updateAdress() {

        String name = bindingView.etName.getText().toString().trim();
        String phone = bindingView.etPhone.getText().toString().trim();
        String detail = bindingView.etDetailAdress.getText().toString().trim();

        if (SystemUtils.isHadEmptyText(name, phone, detail, city)) {
            TU.cT("请完善本页数据!");
            return;
        }

        params.clear();
        params.put("id", id);
        params.put("name", name);
        params.put("tel", phone);
        params.put("province", province);
        params.put("city", city);
        params.put("area", area);
        params.put("address", detail);
        params.put("isdefault", bindingView.cb.isChecked() ? "1" : "0");

        RetrofitUtil.getStringInstance().create(MeApi.class).updateAdress(App.token, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 200) {
                                finish();
                            }
                            TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("编辑地址失败");
                    }
                }, AddAdressActivity.this));
    }
}

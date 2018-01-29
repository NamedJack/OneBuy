package com.ejar.onebuy;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.databinding.AtyRegisterBinding;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.TU;

@Route(path = "/base/register")
public class RegisterActivity extends BaseHeaderBindingActivity<AtyRegisterBinding> {

    CountDownTimer timer = new CountDownTimer(120 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            bindingView.tvGetVerifyCode.setText(millisUntilFinished / 1000 + "秒后重新获取");
            bindingView.tvGetVerifyCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            bindingView.tvGetVerifyCode.setText("获取验证码");
            bindingView.tvGetVerifyCode.setClickable(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_register);

        setToolBarLeftText("登录");
        setToolbarTitle("手机号注册");

        initView();
    }

    private void initView() {
        bindingView.cbSure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bindingView.btnSubmit.setBackgroundColor(Color.parseColor("#e21308"));
                    bindingView.btnSubmit.setClickable(true);
                } else {
                    bindingView.btnSubmit.setBackgroundColor(Color.GRAY);
                    bindingView.btnSubmit.setClickable(false);
                }
            }
        });

        bindingView.tvGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetVerifyCode();
            }
        });


        bindingView.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });

    }

    /**
     * 注册
     */
    private void doRegister() {
        String tel = bindingView.etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            TU.cT("请输入手机号！");
            return;
        }

        String code = bindingView.etCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            TU.cT("请输入验证码！");
            return;
        }

        RetrofitUtil.getStringInstance().create(ApiServer.class)
                .doRegister(tel, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String responseBody, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(responseBody);
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody);
                            if (jsonObject.optInt("code") == 100) {
                                TU.cT("注册成功,请登录");
                                finish();
                            } else TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }, RegisterActivity.this));
    }


    /**
     * 获取验证码
     */
    private void doGetVerifyCode() {
        String tel = bindingView.etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            TU.cT("请输入手机号");
            return;
        }
        RetrofitUtil.getStringInstance().create(ApiServer.class)
                .getRegisterCode(tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String responseBody, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(responseBody);
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody);
                            if (jsonObject.optInt("code") == 100) {
                                timer.start();
                                TU.cT(jsonObject.optString("data"));
                            } else TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }, RegisterActivity.this));

    }
}

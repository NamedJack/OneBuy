package com.ejar.onebuy;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.bean.LoginBean;
import com.ejar.onebuy.bean.UserLoginBean;
import com.ejar.onebuy.databinding.AtyLoginBinding;
import com.ejar.onebuy.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.SPUtil;
import wang.wongxd.wquicklib.utils.TU;

@Route(path = "/base/login")
public class LoginActivity extends BaseHeaderBindingActivity<AtyLoginBinding> {

    private AppCompatActivity thisActivity;
    private Context mContext;


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


    //wx appId


    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        api = App.wxApi;


        setContentView(R.layout.aty_login);

        thisActivity = this;
        mContext = this.getApplicationContext();

        setToolBarReturnVisble(false);
        setToolBarLeftText("取消");
        setToolbarTitle("登录");

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        api = null;
    }

    private void initView() {
        bindingView.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/base/register").navigation();
            }
        });

        bindingView.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });


        bindingView.tvGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoginCode();
            }
        });

        bindingView.llWechatLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxLogin();
            }
        });
    }


    public void wxLogin() {
//        Logger.e("点击" );
        if (!api.isWXAppInstalled()) {
            TU.cT("您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "onebuy_login";
//        Logger.e("点击222" );
        api.sendReq(req);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (App.wxCode != null) {
            doWXLogin(App.wxCode);
            App.wxCode = null;
        } else {

        }
    }

    private void doWXLogin(String wxCode) {
        RetrofitUtil.getStringInstance()
                .create(ApiServer.class)
                .doLogin(wxCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);
                        //{"code":200,"msg":"OK","data":{"msg":"三方登录请求成功","code":200,"token":"9ff52c145206f3897fff99d4b5bf2dac"}}
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") != 200) {
                                TU.cT("微信 登录失败!");
                            } else {
                                UserLoginBean userLoginBean = new Gson().fromJson(s, UserLoginBean.class);
                                App.token = userLoginBean.getData().getToken();
                                SPUtil.getInstance(getApplicationContext()).saveInfo("token", App.token);
                                SPUtil.getInstance(getApplicationContext()).saveInfo("cowUserId", userLoginBean.getData().getCowUserId() + "");
                                SPUtil.getInstance(LoginActivity.this).saveInfo("userId", userLoginBean.getData().getUserId() + "");
                                ARouter.getInstance().build("/base/atyMain").withTransition(R.anim.fadein, R.anim.fadeout).navigation();
                                LoginActivity.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("微信 登录失败!");
                    }
                }, LoginActivity.this));
    }

    /**
     * 登录
     */
    private void doLogin() {
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

        RetrofitUtil.getStringInstance().create(ApiServer.class).doLogin(tel, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 100) {
                                LoginBean loginBean = new Gson().fromJson(s, LoginBean.class);
                                App.token = loginBean.getData().getToken();
                                SPUtil.getInstance(getApplicationContext()).saveInfo("token", App.token);
                                ARouter.getInstance().build("/base/atyMain").withTransition(R.anim.fadein, R.anim.fadeout).navigation();
                                finish();
                            } else TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }, thisActivity));


    }

    /**
     * 获取登录验证码
     */
    private void getLoginCode() {

        String tel = bindingView.etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            TU.cT("请输入手机号！");
            return;
        }

        RetrofitUtil.getStringInstance().create(ApiServer.class).getLoginCode(tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
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
                }, thisActivity));

    }


}

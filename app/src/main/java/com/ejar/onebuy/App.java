package com.ejar.onebuy;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.bean.me.PersonBean;
import com.ejar.onebuy.wxapi.WXEntryActivity;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import common.pay.sdk.CommonPayConfig;
import wang.wongxd.wquicklib.base.WBaseApp;
import wang.wongxd.wquicklib.base.exception.CrashHandler;
import wang.wongxd.wquicklib.utils.location.LocationPicker;

import static com.ejar.onebuy.BuildConfig.LOG_DEBUG;

/**
 * Created by wongxd on 2017/8/3.
 */

public class App extends WBaseApp {
    public static String token;
    public static PersonBean.DataBean.UserBean user;
    public static IWXAPI wxApi;
    public static String openid;//微信登录授权成功
    public static String wxCode;//微信登录的code

    @Override
    public void onCreate() {
        super.onCreate();

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(this, WXEntryActivity.APP_ID, true);
        //将应用注册到微信
        api.registerApp(WXEntryActivity.APP_ID);

        App.wxApi = api;

        if (LOG_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            Logger.init().logLevel(LogLevel.FULL);
        } else {
            Logger.init().logLevel(LogLevel.NONE);
        }

        CrashHandler.getInstance().init(this, 2000);

        ARouter.init(this); // 尽可能早，推荐在Application中初始化


        new Thread(new Runnable() {
            @Override
            public void run() {
                LocationPicker.register(App.getInstance());
            }
        }).start();

        CommonPayConfig.WX_APP_ID = WXEntryActivity.APP_ID;
    }


}

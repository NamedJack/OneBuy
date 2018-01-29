package com.ejar.onebuy.wxapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.ejar.onebuy.App;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wang.wongxd.wquicklib.base.retrofit.BaseApiService;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.utils.TU;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
    public static boolean isShare = false;


    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    public static final String APP_ID = "wx629642ee5ca38b0f";
    public static final String SECRET = "218ffc85214e0c3899d4060c842417e9";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//         通过WXAPIFactory工厂，获取IWXAPI的实例
//        api = WXAPIFactory.createWXAPI(this, APP_ID, false);

        api = App.wxApi;

        // 将该app注册到微信
//        api.registerApp(APP_ID);


        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，
        // 则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        Toast.makeText(this, "微信请求BaseReq.getType = " + req.getType(), Toast.LENGTH_SHORT).show();
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
//                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            default:
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
//    @Override
//    public void onResp(BaseResp resp) {
//        String result = "";
//
//        Toast.makeText(this, "baseresp.getType = " + resp.getType(), Toast.LENGTH_SHORT).show();
//
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                result ="发送成功" ;
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                result ="发送取消" ;
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                result ="发送被拒绝" ;
//                break;
//            case BaseResp.ErrCode.ERR_UNSUPPORT:
//                result ="不支持错误" ;
//                break;
//            default:
//                result ="发送返回" ;
//                break;
//        }
//
//        TU.cT(result);
//    }


//    String code = "";
//    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    String startUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + SECRET +
            "&code=";
    String endUrl = "&grant_type=authorization_code";

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        Logger.e(resp.errCode + "" + resp.errStr);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if (isShare) TU.cT("微信 分享失败!   " + resp.errStr);
                else TU.cT("微信 登录失败!   " + resp.errStr);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (isShare) TU.cT("微信 取消分享！");
                else TU.cT("微信 取消登录!");
                break;
            case BaseResp.ErrCode.ERR_OK:
                if (!isShare) {
                    //拿到了微信返回的code,立马再去请求access_token
                    String code = ((SendAuth.Resp) resp).code;
                    //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                    Logger.e(code);
                    App.wxCode = code;

                    if (TextUtils.isEmpty(code) ) {
                        TU.cT("微信 登录CODE 获取失败");
                    } else {
                        TU.cT("微信 登录CODE 获取成功");
                    }
                } else {
                    TU.cT("微信分享成功");
                }
                break;

            default:
                TU.cT("微信 未知错误");
                break;
        }
        isShare = false;
        finish();
    }

    private ProgressDialog dialog;


    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(final String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://baidu.com")
                .client(new OkHttpClient())
                .build();

        BaseApiService service = retrofit.create(BaseApiService.class);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
        service.baseGet(startUrl + code + endUrl).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Logger.e(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String openid = jsonObject.optString("openid");
                    String access_token = jsonObject.optString("access_token");
                    if (TextUtils.isEmpty(openid) || TextUtils.isEmpty(access_token)) {
                        TU.cT("微信 登录失败");
                    } else {
                        openid = openid;
                        TU.cT("微信 登录成功");
//                        getUserMesg(access_token, openid);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    finish();
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.cancel();
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                TU.cT("微信登录失败");
                finish();
            }
        });


    }


    /**
     * 获取微信的个人信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        Logger.e("getUserMesg：" + path);


        RetrofitUtil.getStringInstance().create(BaseApiService.class).baseGet(path).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());

                    String nickname = jsonObject.optString("nickname");
                    int sex = jsonObject.optInt("sex"); //普通用户性别，1为男性，2为女性

                    String province = jsonObject.optString("province");
                    String city = jsonObject.optString("city");
                    String country = jsonObject.optString("country");
                    String headimgurl = jsonObject.optString("headimgurl");
                    String unionid = jsonObject.optString("unionid");


                    Logger.e("用户信息  " + nickname + "  " + city + "  " + "  " + headimgurl);

                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                TU.cT("获取用户信息失败");
                finish();
            }
        });


    }
}


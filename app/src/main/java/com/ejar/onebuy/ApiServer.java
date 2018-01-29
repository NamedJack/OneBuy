package com.ejar.onebuy;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wang.wongxd.wquicklib.base.retrofit.consts.Consts;

/**
 * Created by wongxd on 2017/8/4.
 */

public interface ApiServer {

    public static String IMG_HOST = Consts.IMG_HOST;

//    @Headers({"User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.2716.5 Safari/537.36"})

    /**
     * 注册获取验证码
     *
     * @return
     */
    @POST("front/getregCode")
    @FormUrlEncoded
    Observable<String> getRegisterCode(@Field("tel") String tel);


    /**
     * 注册
     */
    @POST("front/regUser")
    @FormUrlEncoded
    Observable<String> doRegister(@Field("tel") String tel, @Field("code") String token);


    /**
     * 登录获取验证码
     */
    @POST("front/getloginCode")
    @FormUrlEncoded
    Observable<String> getLoginCode(@Field("tel") String tel);


    /**
     * 登录
     */
    @POST("front/codelogin")
    @FormUrlEncoded
    Observable<String> doLogin(@Field("tel") String tel, @Field("code") String code);


    /**
     * 微信 登录
     *
     * @param code
     * @return
     */
    @POST("front/threeLogin")
    @FormUrlEncoded
    Observable<String> doLogin(@Field("code") String code);

}

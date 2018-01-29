package com.ejar.onebuy.me;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by wongxd on 2017/8/14.
 */

public interface MeApi {


    /**
     * 获取地址列表
     *
     * @param token
     * @return
     */
    @POST("front/userInfo")
    @FormUrlEncoded
    Observable<String> getUserInfo(@Field("token") String token);

    /**
     * 获取地址列表
     *
     * @param token
     * @return
     */
    @POST("front/address/list")
    @FormUrlEncoded
    Observable<String> getAdressList(@Field("token") String token);


    /**
     * 添加新地址
     *
     * @param token
     * @param map
     * @return
     */
    @POST("front/address/insert")
    @FormUrlEncoded
    Observable<String> addAdress(@Field("token") String token, @FieldMap Map<String, String> map);


    /**
     * 删除地址
     *
     * @param token
     * @param id
     * @return
     */
    @POST("front/address/deleteAddress")
    @FormUrlEncoded
    Observable<String> deleteAdress(@Field("token") String token, @Field("id") String id);


    /**
     * 编辑地址
     *
     * @param token
     * @param map
     * @return
     */
    @POST("front/address/updateAddress")
    @FormUrlEncoded
    Observable<String> updateAdress(@Field("token") String token, @FieldMap Map<String, String> map);


    /**
     * 获取客户帮助列表
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("front/Article/list")
    @FormUrlEncoded
    Observable<String> getServiceList(@Field("token") String token, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);


    /**
     * 问题反馈
     *
     * @param token
     * @param message
     * @return
     */
    @POST("front/leavMessage")
    @FormUrlEncoded
    Observable<String> doReported(@Field("token") String token, @Field("message") String message);


    /**
     * 查看回复
     *
     * @param token
     * @return
     */
    @POST("front/lookMessage")
    @FormUrlEncoded
    Observable<String> seeReply(@Field("token") String token, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);


    /**
     *   修个个人资料
     *
     * @param token
     * @return
     */
    @POST("front/updateuser")
    @FormUrlEncoded
    Observable<String> updateUserInfo(@Field("token") String token, @FieldMap Map<String, String> map);


    /**
     *   修个个人资料
     *
     * @return
     */
    @POST("front/updateuser")
    @Multipart
    Observable<String> updateUserHeader(@PartMap() Map<String, RequestBody> partMap
            , @Part() List<MultipartBody.Part> files
    );


    /**
     *   个人中心进行中
     *
     * @param token
     * @return
     */
    @POST("front/haveInHand")
    @FormUrlEncoded
    Observable<String> haveInHand(@Field("token") String token, @Field("userId") String userId
            , @Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize);


    /**
     *   个人中心已揭晓
     *
     * @param token
     * @return
     */
    @POST("front/haveBeen")
    @FormUrlEncoded
    Observable<String> haveBeen(@Field("token") String token, @Field("userId") String userId
            , @Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize);


    /**
     *   夺宝记录
     *
     * @param token
     * @return
     */
    @POST("front/fullRecord")
    @FormUrlEncoded
    Observable<String> fullRecord(@Field("token") String token, @Field("userId") String userId
            , @Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize);


    /**
     *   幸运记录
     *
     * @param token
     * @return
     */
    @POST("front/luckyRecord")
    @FormUrlEncoded
    Observable<String> luckyRecord(@Field("token") String token, @Field("userId") String userId
            , @Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize);


    /**
     * 点击去晒单
     *
     * @param token
     * @param id
     * @return
     */
    @POST("front/goTheSun")
    @FormUrlEncoded
    Observable<String> gotoUnboxing(@Field("token") String token, @Field("id") String id);

    /**
     * 发布晒单
     *
     * @return
     */
    @POST("front/oderInfo")
    Observable<String> unboxing(@Body RequestBody requestBody);


    /**
     * 晒单记录
     *
     * @return
     */
    @POST("front/lookOder")
    @FormUrlEncoded
    Observable<String> shareRecordList(@Field("token") String token,
                                       @Field("userId") String userId
            , @Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize);


    /**
     * 删除晒单
     *
     * @param token
     * @param id
     * @return
     */
    @POST("front/delOderSun")
    @FormUrlEncoded
    Observable<String> deleteUnboxing(@Field("token") String token, @Field("id") String id);
}

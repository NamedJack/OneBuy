package com.ejar.onebuy.home;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wongxd on 2017/8/11.
 */

public interface HomeApi {


    /**
     * 首页
     *
     * @return
     */
    @POST("front/product/list")
    @FormUrlEncoded
    Observable<String> HomeInfo(
            @FieldMap() Map<String, String> map,
            @Field("token") String token);


    /**
     * 首页轮播图及中奖信息
     *
     * @param token
     * @return
     */
    @POST("front/SowList/userNo")
    @FormUrlEncoded
    Observable<String> getHomeBanner(@Field("token") String token);


    /**
     * 商品分类
     *
     * @param token
     * @return
     */
    @POST("front/catalog/list")
    @FormUrlEncoded
    Observable<String> getGoodType(@Field("token") String token);


    /**
     * 商品详情
     *
     * @param token
     * @param id
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("front/product/queryById")
    @FormUrlEncoded
    Observable<String> getGoodDetail(@Field("token") String token, @Field("id") String id,
                                     @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);


    /**
     * 往期揭晓
     *
     * @param token
     * @param pId   商品id
     * @return
     */
    @POST("front/product/getWinBefore")
    @FormUrlEncoded
    Observable<String> getPreviousLucky(@Field("token") String token, @Field("productId") String pId);


    /**
     * 立即参与
     *
     * @param token
     * @param pId   商品id
     * @return
     */
    @POST("front/product/joinNow")
    @FormUrlEncoded
    Observable<String> joinNow(@Field("token") String token, @Field("productId") String pId);

    /**
     * 确认立即参与
     *
     * @return
     */
    @POST("front/product/confirmJoin")
    @FormUrlEncoded
    Observable<String> confirmJoinNow(@Field("token") String token, @Field("proId") String pId,
                                      @Field("personNum") String joinNum, @Field("frequency") String autoIssue);


    /**
     * 晒单记录
     *
     * @return
     */
    @POST("front/lookOder")
    @FormUrlEncoded
    Observable<String> shareRecordList(@Field("token") String token, @Field("proId") String pId
            , @Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize);
}

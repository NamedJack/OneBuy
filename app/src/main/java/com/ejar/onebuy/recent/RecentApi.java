package com.ejar.onebuy.recent;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wongxd on 2017/8/16.
 */

public interface RecentApi {

    /**
     * 最新揭晓列表
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("front/announce/list")
    @FormUrlEncoded
    Observable<String> getList(@Field("token") String token, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);


    @POST("front/announce/openWinner")
    @FormUrlEncoded
    Observable<String> openWinner(@Field("token") String token, @Field("id") String id);


    /**
     * 最新揭晓详情
     *
     * @param token
     * @param goodsNo  商品期数
     * @param proId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("front/announce/detail")
    @FormUrlEncoded
    Observable<String> getDetail(@Field("token") String token,
                                 @Field("goodsNo") String goodsNo,
                                 @Field("id") String proId,
                                 @Field("pageNo") String pageNo,
                                 @Field("state") String state,
                                 @Field("isOver") String isOver,
                                 @Field("pageSize") String pageSize);


    /**
     * 计算详情
     *
     * @return
     */
    @POST("front/announce/WinnerDetail")
    @FormUrlEncoded
    Observable<String> getCaculateDetail(@Field("token") String token, @Field("goodsNoId") String goodsNoId);
}

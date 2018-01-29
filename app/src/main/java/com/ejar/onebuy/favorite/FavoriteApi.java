package com.ejar.onebuy.favorite;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wongxd on 2017/8/17.
 */

public interface FavoriteApi {

    /**
     * 添加收藏
     *
     * @param token
     * @param proId
     * @return
     */
    @POST("front/userCollection")
    @FormUrlEncoded
    Observable<String> addCollection(@Field("token") String token, @Field("proId") String proId);


    /**
     * 取消收藏
     *
     * @param token
     * @param proId
     * @return
     */
    @POST("front/cancelCollection")
    @FormUrlEncoded
    Observable<String> cancelCollection(@Field("token") String token, @Field("proId") String proId);


    /**
     * 收藏列表
     *
     * @return
     */
    @POST("front/lookCollection")
    @FormUrlEncoded
    Observable<String> showCollection(@Field("token") String token, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);
}

package com.ejar.onebuy;

import com.ejar.onebuy.favorite.FavoriteApi;
import com.ejar.onebuy.home.HomeApi;
import com.ejar.onebuy.me.MeApi;
import com.ejar.onebuy.recent.RecentApi;

import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;

/**
 * Created by wongxd on 2017/8/15.
 */

public class RetrofitImp {

    public static  HomeApi getHomeApi(){
        return RetrofitUtil.getStringInstance().create(HomeApi.class);
    }

    public static RecentApi getRecentApi(){
        return RetrofitUtil.getStringInstance().create(RecentApi.class);
    }

    public static FavoriteApi getFavoriteApi(){
        return RetrofitUtil.getStringInstance().create(FavoriteApi.class);
    }

    public static  MeApi getMeApi(){
        return RetrofitUtil.getStringInstance().create(MeApi.class);
    }
}

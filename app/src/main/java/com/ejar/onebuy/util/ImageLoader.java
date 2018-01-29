package com.ejar.onebuy.util;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;

import wang.wongxd.wquicklib.utils.glide.GlideCircleTransform;
import wang.wongxd.wquicklib.utils.glide.GlideRoundTransform;

/**
 * Created by wongxd on 2017/8/24.
 */

public class ImageLoader {

    public static void cicleImg(String path, ImageView iv) {

        if (!TextUtils.isEmpty(path) && !path.contains("http://") && !path.contains("https://")) {
            path = ApiServer.IMG_HOST + path;
        }
        Glide.with(App.getInstance())
                .load(path)
                .transform(new GlideCircleTransform(App.getInstance()))
                .placeholder(R.drawable.placeholder)
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv);
    }


    public static void roundImg(String path, ImageView iv) {
        if (!TextUtils.isEmpty(path) && !path.contains("http://") && !path.contains("https://")) {
            path = ApiServer.IMG_HOST + path;
        }
        Glide.with(App.getInstance())
                .load(path)
                .transform(new GlideRoundTransform(App.getInstance()))
                .error(R.drawable.placeholder)
                .crossFade(500)
                .into(iv);
    }


    public static void Img(String path, ImageView iv) {
        if (!TextUtils.isEmpty(path) && !path.contains("http://") && !path.contains("https://")) {
            path = ApiServer.IMG_HOST + path;
        }
        Glide.with(App.getInstance())
                .load(path)
                .placeholder(R.drawable.placeholder)
                .crossFade(500)
                .into(iv);
    }
}

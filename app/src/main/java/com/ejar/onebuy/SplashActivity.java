package com.ejar.onebuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import wang.wongxd.wquicklib.utils.SPUtil;

/**
 * Created by wongxd on 2017/8/25.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_splash);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        Glide.with(this).load(R.drawable.img_splash).into(iv);
        App.token = SPUtil.getInstance(getApplicationContext()).getInfo("token", "");
        iv.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!TextUtils.isEmpty(App.token)) {
                    ARouter.getInstance().build("/base/atyMain").withTransition(R.anim.fadein, R.anim.fadeout).navigation();
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, AtyMainActivity.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        }, 1500);
    }
}

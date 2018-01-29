package com.ejar.onebuy.me;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.App;
import com.ejar.onebuy.LoginActivity;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtySettingBinding;
import com.ejar.onebuy.util.DialogUtil;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DataCleanManager;
import wang.wongxd.wquicklib.utils.SPUtil;
import wang.wongxd.wquicklib.utils.SystemUtils;

/**
 * Created by wongxd on 2017/8/9.
 */

@Route(path = "/me/setting")
public class SettingActivity extends BaseHeaderBindingActivity<AtySettingBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_setting);
        setToolBarLeftText("个人中心");
        setToolbarTitle("设置");


        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            bindingView.tvCache.setText(totalCacheSize);
            bindingView.llCleanCache.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DialogUtil.showDialog(SettingActivity.this, "确认清除缓存？", "", "取消", "确认", new DialogUtil.DialogListener() {
                        @Override
                        public void left(Dialog dialog) {
                            dialog.dismiss();
                        }

                        @Override
                        public void right(Dialog dialog) {
                            DataCleanManager.clearAllCache(SettingActivity.this);
                            try {
                                String totalCacheSize = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                                bindingView.tvCache.setText(totalCacheSize);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        bindingView.tvVersion.setText("v  "+getVersionName());

        bindingView.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showDialog(SettingActivity.this, "确认退出吗？", "", "取消", "确认", new DialogUtil.DialogListener() {
                    @Override
                    public void left(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void right(Dialog dialog) {
                        App.token = null;
                        SPUtil.getInstance(getApplicationContext()).cleanInfo("token");
                        SystemUtils.cleanTask2Activity(SettingActivity.this, LoginActivity.class);
                    }
                });

            }
        });
    }
}

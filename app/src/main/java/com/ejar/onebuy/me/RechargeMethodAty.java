package com.ejar.onebuy.me;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtyRechargeMethodBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import wang.wongxd.wquicklib.base.ui.BaseBindingActivity;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public class RechargeMethodAty extends BaseHeaderBindingActivity<AtyRechargeMethodBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_recharge_method);
        setToolBarLeftText("返回");
        setToolbarTitle("获取积分");
    }
}

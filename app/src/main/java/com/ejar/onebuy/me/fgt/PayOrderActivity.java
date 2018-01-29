package com.ejar.onebuy.me.fgt;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtyPayOrderBinding;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;

/**
 * Created by wongxd on 2017/8/10.
 * 订单支付
 */

@Route(path = "/me/payOrder")
public class PayOrderActivity extends BaseHeaderBindingActivity<AtyPayOrderBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_pay_order);
        setToolBarLeftText("充值");
        setToolbarTitle("支付订单");
    }
}

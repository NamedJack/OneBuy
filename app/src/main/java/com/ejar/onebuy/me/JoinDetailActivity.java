package com.ejar.onebuy.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtyJoinDetailBinding;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;

/**
 * Created by wongxd on 2017/8/21.
 * <p>
 * 夺宝详情
 */

@Route(path = "/me/joinDetail")
public class JoinDetailActivity extends BaseHeaderBindingActivity<AtyJoinDetailBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_join_detail);
        setToolBarLeftText("夺宝记录");
        setToolbarTitle("夺宝详情");

    }
}

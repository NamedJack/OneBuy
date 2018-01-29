package com.ejar.onebuy.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtyNiubiManagerBinding;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;

/**
 * Created by wongxd on 2017/8/9.
 */
@Route(path = "/me/niubimanager")
public class NiubiManagerActivity extends BaseHeaderBindingActivity<AtyNiubiManagerBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_niubi_manager);
        setToolBarLeftText("个人中心");
        setTitle("牛币管理");
    }
}

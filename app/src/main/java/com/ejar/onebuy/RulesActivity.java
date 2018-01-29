package com.ejar.onebuy;

import android.os.Bundle;
import android.view.View;

import com.ejar.onebuy.databinding.AtyRulesBinding;

import wang.wongxd.wquicklib.base.ui.BaseBindingActivity;

/**
 * Created by Administrator on 2017\12\4 0004.
 */

public class RulesActivity extends BaseBindingActivity<AtyRulesBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_rules);
        bindingView.ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

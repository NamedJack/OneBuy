package com.ejar.onebuy;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.databinding.AtyJoinDetailBinding;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;

/**
 * Created by wongxd on 2017/8/11.
 *
 * 夺宝详情
 */

@Route(path = "/base/joinDetail")
public class JoinDetailActivity extends BaseHeaderBindingActivity<AtyJoinDetailBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_join_detail);
        setToolBarLeftText("夺宝记录");
        setToolbarTitle("夺宝详情");
    }

    private class RvAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_join_detail);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}

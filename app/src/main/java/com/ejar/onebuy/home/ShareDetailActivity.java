package com.ejar.onebuy.home;

import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.R;
import com.ejar.onebuy.bean.me.ShareRecordBean;
import com.ejar.onebuy.databinding.AtyShareDetailBinding;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.TU;

/**
 * Created by wongxd on 2017/8/22.
 */

@Route(path = "/home/shareDetail")
public class ShareDetailActivity extends BaseHeaderBindingActivity<AtyShareDetailBinding> {
    @Autowired
    ShareRecordBean.DataBean.OlistBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_share_detail);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("晒单分享");
        setToolbarTitle("晒单详情");


        if (data == null) {
            TU.cT("数据错误");
            finish();
        }
        bindingView.tvUse.setText(data.getUsername());
        bindingView.tvTime.setText(data.getSuntime());
        bindingView.tvProductName.setText(data.getShopname());
        bindingView.tvIssue.setText(data.getDatenumber() + "");
        bindingView.tvNum.setText(data.getExpectNum() + "");
        bindingView.tvLuckyNum.setText("" + data.getWinningNumber());
        bindingView.tvLotterTime.setText(data.getLotterytime());
        bindingView.tvContent.setText(data.getContent());


        String imgsString = data.getSurl();

        String[] imgs = imgsString.split(",");
        for (int i = 0, length = imgs.length; i < length; i++) {
            String img = "";
            ImageView iv = null;
            switch (i) {
                case 0:
                    iv = bindingView.iv1;
                    img = imgs[0];
                    break;

                case 1:
                    iv = bindingView.iv2;
                    img = imgs[1];
                    break;

                case 2:
                    iv = bindingView.iv3;
                    img = imgs[2];
                    break;
                default:
                    iv = bindingView.iv1;
                    img = imgs[0];
                    break;
            }

            if (iv != null) {
                Glide.with(getApplicationContext()).load(ApiServer.IMG_HOST + img).placeholder(R.drawable.placeholder)
                        .into(iv);
            }
        }
    }
}

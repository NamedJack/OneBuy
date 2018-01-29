package com.ejar.onebuy;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.databinding.AtyImgAndTxtDetailBinding;
import com.orhanobut.logger.Logger;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.WebViewUtil;

/**
 * Created by wongxd on 2017/8/14.
 * 图文详情
 */

@Route(path = "/base/imgAndTxtDetail")
public class ImgAndTxtDetail extends BaseHeaderBindingActivity<AtyImgAndTxtDetailBinding> {

    @Autowired
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_img_and_txt_detail);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("返回");
        setToolbarTitle("图文详情");
//        json = json.replace("<img src=\"", "<img src=\"" + ApiServer.IMG_HOST.replace("/oneceIndiana",""));
        Logger.e("图文详情"+json);
        WebViewUtil.loadCNData(bindingView.webview, json);

    }
}

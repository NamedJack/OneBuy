package com.ejar.onebuy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.util.ImageLoader;

import wang.wongxd.wquicklib.base.exception.AppManager;
import wang.wongxd.wquicklib.base.retrofit.model.LuckyBean;
import wang.wongxd.wquicklib.utils.SystemUtils;

/**
 * Created by wongxd on 2017/8/25.
 */

@Route(path = "/base/prize")
public class PrizeActivity extends AppCompatActivity {

    @Autowired
    LuckyBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtils.backgroundAlpha(AppManager.getAppManager().currentActivity(), 0.5f);
        setContentView(R.layout.aty_prize);
        ARouter.getInstance().inject(this);
        RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        RelativeLayout rlContent = (RelativeLayout) findViewById(R.id.rl_content);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        TextView tv = (TextView) findViewById(R.id.tv);

        rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            ImageLoader.roundImg(data.getProductList().get(0).getPicture().split(",")[0], iv);
            tv.setText(data.getProductList().get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void finish() {
        SystemUtils.backgroundAlpha(AppManager.getAppManager().currentActivity(), 1f);
        super.finish();
    }


}

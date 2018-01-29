package com.ejar.onebuy.me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtyChargeBinding;
import com.ejar.onebuy.pay.ToPay;
import com.orhanobut.logger.Logger;

import common.pay.sdk.CommonPayConfig;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.TU;

/**
 * Created by wongxd on 2017/8/9.
 * 充值
 */

@Route(path = "/me/charge")
public class ChargeActivity extends BaseHeaderBindingActivity<AtyChargeBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_charge);

        setToolbarTitle("充值");
        setToolBarLeftText("个人中心");

        bindingView.llAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAliPay = true;
                bindingView.ivAlipayCheck.setImageResource(R.drawable.check);
                bindingView.ivWecahatCheck.setImageResource(0);
            }
        });

        bindingView.llWechatPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAliPay = false;
                bindingView.ivWecahatCheck.setImageResource(R.drawable.check);
                bindingView.ivAlipayCheck.setImageResource(0);
            }
        });


        bindingView.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAliPay) {
                    ToPay.ZfbPay(ChargeActivity.this, REQUEST_PAY_CODE);
                } else {
                    ToPay.WxPay(ChargeActivity.this, REQUEST_PAY_CODE);
                }
            }
        });


        bindingView.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bindingView.btn.setClickable(true);
                    bindingView.btn.setBackgroundColor(Color.RED);
                } else {
                    bindingView.btn.setClickable(false);
                    bindingView.btn.setBackgroundColor(Color.GRAY);
                }
            }
        });

        bindingView.cb.setChecked(false);
        bindingView.btn.setClickable(false);
        bindingView.btn.setBackgroundColor(Color.GRAY);


        initTv();
        bindingView.tv100.setTextColor(Color.RED);
    }

    private double money = 100.00;

    private void initTv() {
        bindingView.tv100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingView.tv100.setTextColor(Color.RED);
                bindingView.tv200.setTextColor(Color.GRAY);
                bindingView.tv500.setTextColor(Color.GRAY);
                bindingView.tv1000.setTextColor(Color.GRAY);
                money = 100.00;
            }
        });


        bindingView.tv200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingView.tv100.setTextColor(Color.GRAY);
                bindingView.tv200.setTextColor(Color.RED);
                bindingView.tv500.setTextColor(Color.GRAY);
                bindingView.tv1000.setTextColor(Color.GRAY);
                money = 200.00;
            }
        });


        bindingView.tv500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingView.tv100.setTextColor(Color.GRAY);
                bindingView.tv200.setTextColor(Color.GRAY);
                bindingView.tv500.setTextColor(Color.RED);
                bindingView.tv1000.setTextColor(Color.GRAY);
                money = 500.00;
            }
        });

        bindingView.tv1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingView.tv100.setTextColor(Color.GRAY);
                bindingView.tv200.setTextColor(Color.GRAY);
                bindingView.tv500.setTextColor(Color.GRAY);
                bindingView.tv1000.setTextColor(Color.RED);
                money = 1000.00;
            }
        });
    }

    private boolean isAliPay = true;

    private static final int REQUEST_PAY_CODE = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PAY_CODE:
                String toastHint = "支付模式:%s,结果描述:%s";
                String payModeDesc = "未知";
                String payRespCode = "unKnow";
                if (data != null) {
                    int payMode = data.getIntExtra(CommonPayConfig.INTENT_KEY_CUR_PAY_MODE, CommonPayConfig.PAY_MODE_WX);
                    payModeDesc = payMode == CommonPayConfig.PAY_MODE_ALIPAY ? "[支付宝]" : "[微信]";
                    payRespCode = data.getStringExtra(CommonPayConfig.INTENT_KEY_REAL_PAY_RESULT_STATUS_CODE);
                }
                String resultDesc = "支付失败";
                switch (resultCode) {
                    case CommonPayConfig.REQ_PAY_RESULT_CODE_OK:
                        resultDesc = "支付成功";
                        break;
                    case CommonPayConfig.REQ_PAY_RESULT_CODE_CANCEL:
                        resultDesc = "支付被取消了";
                        break;
                    case CommonPayConfig.REQ_PAY_RESULT_CODE_NO_WX:
                        resultDesc = "支付失败,未安装微信APP";
                        break;
                    case CommonPayConfig.REQ_PAY_RESULT_CODE_ERROR:
                        resultDesc = "支付失败";
                        break;
                }
                String payResultInfo = "支付模式:" + payModeDesc + "\n" +
                        "支付SDK的实际响应码：" + payRespCode + "\n" +
                        "结果描述：" + resultDesc;
                Logger.e(payResultInfo);
                TU.cT(payModeDesc + "  " + resultDesc);
                break;
        }
    }
}

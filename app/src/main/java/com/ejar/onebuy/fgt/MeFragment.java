package com.ejar.onebuy.fgt;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.PersonBean;
import com.ejar.onebuy.databinding.FgtMeBinding;
import com.ejar.onebuy.me.JoinRecordActivity;
import com.ejar.onebuy.util.CustomDialog;
import com.ejar.onebuy.util.DialogUtil;
import com.ejar.onebuy.util.ImageLoader;
import com.ejar.onebuy.util.ImageUtils;
import com.ejar.onebuy.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.ui.BaseBindingFragment;
import wang.wongxd.wquicklib.utils.SPUtil;

/**
 * Created by wongxd on 2017/8/4.
 */

public class MeFragment extends BaseBindingFragment<FgtMeBinding> {
    @Override
    public int setContent() {
        return R.layout.fgt_me;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        if (App.user == null || !isShowInfo) {
            getUserInfo();
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getUserInfo();
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (App.token != null) {
                getUserInfo();
            }
        }
    }

    private boolean isShowInfo = false;

    /**
     * 获取个人信息
     */
    private void getUserInfo() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("获取个人信息中");
        if (!isShowInfo)
            pd.show();
        RetrofitImp.getMeApi().getUserInfo(App.token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.e(s);
                        PersonBean person = new Gson().fromJson(s, PersonBean.class);
                        if (person.getCode() == 200) {
                            App.user = person.getData().getUser();
                            ImageLoader.cicleImg(App.user.getHeadImg(), bindingView.ivHeader);

                            bindingView.tvNickName.setText("昵称: " + App.user.getNiceName());
                            bindingView.tvId.setText("ID: " + App.user.getId());
                            bindingView.tvZeng.setText("返利积分:" + App.user.getIntegral());
                            bindingView.tvDuo.setText("积分:" + App.user.getWallet());
                            isShowInfo = true;
                        }
                        if (pd.isShowing()) pd.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (pd.isShowing()) pd.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {

        bindingView.ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/setting").navigation();
            }
        });
        bindingView.llAllRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/joinrecord").navigation();
            }
        });

        bindingView.llNiuCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/niubimanager").navigation();
            }
        });


        bindingView.tvCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
                Button copy = (Button) dialogView.findViewById(R.id.btn_cpoy);

                final CustomDialog dialog = new CustomDialog.Builder(getContext())
                        .View(dialogView)
                        .cancelable(true)

                        .gravity(Gravity.CENTER_VERTICAL)
                        .build();
                dialog.show();
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager manager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        manager.setText("一直牛");
                        Toast.makeText(getContext(), "复制成功", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

//             TU.cT("请关注 一直牛 微信公众号进行充值");
//                startActivity(new Intent(getActivity(), RechargeMethodAty.class));
            }
        });


        bindingView.ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/personinfo").navigation();
            }
        });

        bindingView.llUnboxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/unboxing").navigation();
            }
        });

        bindingView.llService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/service").navigation();
            }
        });


        bindingView.llAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/adress").navigation();
            }
        });

        bindingView.llLuckyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/luckyRecord").navigation();
            }
        });

        bindingView.llLuckyRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/joinrecord").withInt("type", 1).navigation();
            }
        });


        bindingView.llLuckyYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/joinrecord").withInt("type", 2).navigation();
            }
        });

        bindingView.llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showDialog((AppCompatActivity) getActivity(), "分享到微信", "", "确定", "取消", new DialogUtil.DialogListener() {
                    @Override
                    public void left(Dialog dialog) {
                        String cowUserId = SPUtil.getInstance(getActivity()).getInfo("cowUserId", "");
                        WXWebpageObject webpage = new WXWebpageObject();
                        webpage.webpageUrl = "http://www.yizhiniu.wang/yz/userEmpower?pid=" + cowUserId;
//                        WXTextObject textObj = new WXTextObject();
//                        textObj.text = "http://www.yizhiniu.wang/yz/share?userId=" + cowUserId ;


                        WXMediaMessage msg = new WXMediaMessage(webpage);
                        msg.title = "一直牛活动";
                        msg.description = "超值礼品任您挑，一次分享，天天拿返利";
//                        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.wchar_share_img);
//                        msg.thumbData = ImageUtils.bmpToByteArray(thumb, true);


                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("webPage"); // transaction 用于唯一标识
                        req.message = msg;
                        req.scene = SendMessageToWX.Req.WXSceneSession;


                        App.wxApi.sendReq(req);
                        WXEntryActivity.isShare = true;
                    }

                    @Override
                    public void right(Dialog dialog) {

                    }
                });
            }
        });

        if (App.user != null) {
            bindingView.tvZeng.setText("赠币:" + App.user.getIntegral());
            bindingView.tvDuo.setText("推广币:" + App.user.getWallet());
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            PersonBean.DataBean.UserBean userBean = App.user;
            ImageLoader.cicleImg(userBean.getHeadImg(), bindingView.ivHeader);

            bindingView.tvNickName.setText("昵称: " + userBean.getNiceName());
            bindingView.tvId.setText("ID: " + userBean.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

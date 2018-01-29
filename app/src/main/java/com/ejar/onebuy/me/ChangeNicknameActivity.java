package com.ejar.onebuy.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.databinding.AtyChangeNickNameBinding;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.TU;

/**
 * Created by wongxd on 2017/8/10
 * 修改昵称.
 */

@Route(path = "/me/changeNickname")
public class ChangeNicknameActivity extends BaseHeaderBindingActivity<AtyChangeNickNameBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_change_nick_name);
        setToolBarLeftText("个人资料");
        setToolbarTitle("修改昵称");
        setToolBarRightText("确认");
        setToolBarRightTextColor(getResources().getColor(R.color.appBlue));
        setToolBarRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doChange();
            }
        });
    }

    Map<String, String> params = new HashMap<>();

    private void doChange() {
        final String nickName = bindingView.et.getText().toString().trim();

        if (TextUtils.isEmpty(nickName)) {
            TU.cT("请输入新昵称");
            return;
        }


        params.clear();
        params.put("niceName", nickName);
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().updateUserInfo(App.token, params),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            TU.cT(jsonObject.optString("msg"));
                            if (jsonObject.optInt("code") == 200) {
                                App.user.setNiceName(nickName);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }, ChangeNicknameActivity.this));

    }


}

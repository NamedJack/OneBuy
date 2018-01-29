package com.ejar.onebuy.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.PersonBean;
import com.ejar.onebuy.databinding.AtyPersonInfoBinding;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.photo.TakeImgActivity;
import wang.wongxd.wquicklib.utils.SystemUtils;
import wang.wongxd.wquicklib.utils.TU;


/**
 * Created by wongxd on 2017/8/9.
 * <p>
 * 个人资料
 */

@Route(path = "/me/personinfo")
public class PersonInfoActivity extends BaseHeaderBindingActivity<AtyPersonInfoBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_person_info);

        setToolBarLeftText("个人中心");
        setToolbarTitle("个人资料");


        bindingView.llNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/me/changeNickname").navigation();

            }
        });

        bindingView.llHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PersonInfoActivity.this, TakeImgActivity.class), 101);
                SystemUtils.backgroundAlpha(PersonInfoActivity.this, 0.5f);
            }
        });

    }





    String imgPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SystemUtils.backgroundAlpha(this, 1f);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 101) {
                imgPath = data.getStringExtra("path");
                doChangeHeader(imgPath);
            }
        }
    }

    private void doChangeHeader(final String imgPath) {
        File file = new File(imgPath);

        // create a map of data to pass along
        RequestBody tokenBody = RequestBody.create(
                MediaType.parse("multipart/form-data"), App.token);


        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用 headImg
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        Map<String, RequestBody> map = new HashMap<>();
        map.put("token", tokenBody);

        List<MultipartBody.Part> files = new ArrayList<>();
        files.add(body);
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().updateUserHeader(map, files), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                Logger.e(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    TU.cT(jsonObject.optString("msg"));
                    if (jsonObject.optInt("code") == 200) {
                        ImageLoader.cicleImg(imgPath, bindingView.ivImg);
                        getUserInfo();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        }, PersonInfoActivity.this));


    }

    private void getUserInfo() {
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
                        }
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            PersonBean.DataBean.UserBean user = App.user;
            ImageLoader.cicleImg(user.getHeadImg(), bindingView.ivImg);
            bindingView.tvId.setText(user.getId() + "");
            bindingView.tvNickName.setText(user.getNiceName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

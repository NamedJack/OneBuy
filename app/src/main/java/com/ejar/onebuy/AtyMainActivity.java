package com.ejar.onebuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.databinding.AtyMainBinding;
import com.ejar.onebuy.fgt.FavoriteFragment;
import com.ejar.onebuy.fgt.HomeFragment;
import com.ejar.onebuy.fgt.MeFragment;
import com.ejar.onebuy.fgt.RecentFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.model.LuckyBean;
import wang.wongxd.wquicklib.base.rx.RxBus;
import wang.wongxd.wquicklib.base.rx.RxEventCodeType;
import wang.wongxd.wquicklib.base.ui.BaseBindingActivity;
import wang.wongxd.wquicklib.custom.switchIconView.SwitchIconView;
import wang.wongxd.wquicklib.utils.SystemUtils;
import wang.wongxd.wquicklib.utils.TU;

@Route(path = "/base/atyMain")
public class AtyMainActivity extends BaseBindingActivity<AtyMainBinding> {

    private List<SwitchIconView> switchIconViewList = new ArrayList<>();

    private HomeFragment homeFragment;
    private RecentFragment recentFragment;
    private FavoriteFragment favoriteFragment;
    private MeFragment meFragment;

    private Fragment currentFgt;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

        fManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        recentFragment = new RecentFragment();
        favoriteFragment = new FavoriteFragment();
        meFragment = new MeFragment();
        fManager.beginTransaction().add(R.id.fl_content, homeFragment).commit();
        currentFgt = homeFragment;

        iniView();

        RxBus.getDefault().toObservable(RxEventCodeType.BE_LUCKY, LuckyBean.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LuckyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LuckyBean luckyBean) {
                        Logger.e("中奖了 LuckyBean " + luckyBean.getProductList().get(0).getName());
                        ARouter.getInstance().build("/base/prize").withObject("data", luckyBean)
                                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .navigation();
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("中奖了  Throwable");
                    }

                    @Override
                    public void onComplete() {
                        TU.cT("中奖了  onComplete");
                    }
                });


        if (!TextUtils.isEmpty(App.token)) {
            bindingView.siHome.performClick();
        }
    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //将这一行注释掉，阻止activity保存fragment的状态
//        super.onSaveInstanceState(outState);
    }

    private void iniView() {
        //bottom
        switchIconViewList.add(bindingView.siHome);
        switchIconViewList.add(bindingView.siRecent);
        switchIconViewList.add(bindingView.siFavorite);
        switchIconViewList.add(bindingView.siMe);
        for (final SwitchIconView si : switchIconViewList) {
            si.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (SwitchIconView s : switchIconViewList) {
                        if (s == si) {
                            if (s.getId() != R.id.si_home && TextUtils.isEmpty(App.token)) {
                                SystemUtils.cleanTask2Activity(AtyMainActivity.this,LoginActivity.class);
                                return;
                            }
                            s.setIconEnabled(true);
                            if (s.getId() == R.id.si_home) {
                                switchFragment(currentFgt, homeFragment);
                            } else if (s.getId() == R.id.si_recent) {
                                switchFragment(currentFgt, recentFragment);
                            } else if (s.getId() == R.id.si_favorite) {
                                switchFragment(currentFgt, favoriteFragment);
                            } else if (s.getId() == R.id.si_me) {
                                switchFragment(currentFgt, meFragment);
                            }

                        } else {
                            s.setIconEnabled(false);
                        }
                    }
                }
            });
        }
    }


    /**
     * fragment的切换
     */
    public void switchFragment(Fragment from, Fragment to) {
        if (from == null || to == null)
            return;
        if (from.equals(to)) return;

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(from).add(R.id.fl_content, to).commit();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit();
        }
        currentFgt = to;
    }

}

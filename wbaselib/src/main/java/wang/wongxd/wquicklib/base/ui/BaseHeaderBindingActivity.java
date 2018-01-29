package wang.wongxd.wquicklib.base.ui;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.R;
import wang.wongxd.wquicklib.base.exception.AppManager;
import wang.wongxd.wquicklib.databinding.AtyBaseHeaderBinding;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.SystemBarHelper;


/**
 * Created by wongxd on 17/5/25.
 */
public class BaseHeaderBindingActivity<SV extends ViewDataBinding> extends AppCompatActivity {

    private AtyBaseHeaderBinding mBaseBinding;
    // 布局view
    protected SV bindingView;


    protected CompositeDisposable disposableList;

    protected void addDisposable(Disposable d) {
        if (null == disposableList) {
            disposableList = new CompositeDisposable();
        }
        disposableList.add(d);
    }


    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        disposableList = new CompositeDisposable();

        SystemBarHelper.tintStatusBar(this, Color.BLACK);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        disposableList.dispose();
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.aty_base_header, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        FrameLayout mContainer = (FrameLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        mBaseBinding.ivToolbarReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBaseBinding.tvToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected int getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            int packageCode = packageInfo.versionCode;
            return packageCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    protected String getVersionName() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            int packageCode = packageInfo.versionCode;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 以下 是对 toolbar 的操作
     */
    protected void setToolbarTitle(String title) {
        mBaseBinding.tvToolbarTitle.setText(title);
    }

    protected void setToolBarReturnVisble(boolean imgVisble) {
        mBaseBinding.ivToolbarReturn.setVisibility(imgVisble ? View.VISIBLE : View.GONE);
        if (!imgVisble) {
            mBaseBinding.tvToolbarLeft.setPadding(DensityUtil.dip2px(10), 0, 0, 0);
        }
    }

    protected void setToolBarLeftText(String s) {
        mBaseBinding.tvToolbarLeft.setText(s);
    }

    protected void setToolBarLeftTextListener(View.OnClickListener lis) {
        mBaseBinding.tvToolbarLeft.setOnClickListener(lis);
    }

    protected void setToolBarRightText(String s) {
        mBaseBinding.tvToolbarRight.setText(s);
    }

    protected void setToolBarRightTextColor(int color) {
        mBaseBinding.tvToolbarRight.setTextColor(color);
    }

    protected void setToolBarRightTextListener(View.OnClickListener lis) {
        mBaseBinding.tvToolbarRight.setOnClickListener(lis);
    }

    protected void setToolBarRightImg(int res) {
        mBaseBinding.ivToolbarRight.setImageResource(res);
    }

    protected void setToolBarRightImgListener(View.OnClickListener lis) {
        mBaseBinding.ivToolbarRight.setOnClickListener(lis);
    }

    protected FrameLayout getToolBar() {
        return mBaseBinding.flToolbar;
    }


}

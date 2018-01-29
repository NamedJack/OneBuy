package wang.wongxd.wquicklib.base.ui;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.R;


/**
 * 是没有title的Fragment
 */
public abstract class BaseBindingFragment<SV extends ViewDataBinding> extends Fragment {

    // 布局view
    protected SV bindingView;
    // fragment是否显示了
    protected boolean mIsVisible = false;
    private boolean isPrepared = false;
    protected FrameLayout mContainer;

    private boolean isFirst = true;

    protected CompositeDisposable disposableList;

    protected void addDisposable(Disposable d) {
        if (null == disposableList) {
            disposableList = new CompositeDisposable();
        }
        disposableList.add(d);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != disposableList) disposableList = null;
        disposableList = new CompositeDisposable();
    }

    @Override
    public void onStop() {
        disposableList.dispose();
        disposableList = null;
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(R.layout.fgt_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = (FrameLayout) ll.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        return ll;
    }


    protected boolean isCreated = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 此标记的改变请勿放在Fragment的onActivtyCreate方法中，此方法调用滞后于setUserVisibleHint的判断
         */
        isCreated = true;
    }

    /**
     * Viewpager装载Fragment时还有另一个坑，Viewpager的父容器（Activity或Fragment）在显隐状态改变时，
     * 如在Activity的onResume和onPause调用时，并不会主动通知Viewpager内的Fragment执行其应用的生命周期，故我们需要再增加手动强制调用两次
     */

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isCreated) {
            return;
        }

        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        onVisible();
    }

    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {
    }

    protected void onVisible() {
        if (isPrepared && mIsVisible) {
            isFirst = false;
            loadData();
        }
    }


    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 布局
     */
    public abstract int setContent();


}

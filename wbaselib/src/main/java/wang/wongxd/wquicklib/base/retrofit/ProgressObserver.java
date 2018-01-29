package wang.wongxd.wquicklib.base.retrofit;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.WBaseApp;

/**
 * Created by wongxd on 2017/8/2.
 * <p>
 * Observer 的封装  包含进度条对话框
 * <p>
 * 对外只暴露 关心的 onNext()
 */
public class ProgressObserver<T> implements ProgressCancelListener, Observer<T> {

    private ObserverOnNextListener<T> mListener;
    private WeakReference<Context> mContext;
    private ProgressDialogHandler mHandler;
    private boolean isShowDialog;

    public ProgressObserver(ObserverOnNextListener<T> listener, Activity context) {
        this(listener, context, true);
    }

    /**
     * @param listener
     * @param context
     * @param isShowDialog 默认显示
     */
    public ProgressObserver(ObserverOnNextListener<T> listener, Activity context, boolean isShowDialog) {
        this.mListener = listener;
        this.mContext = new WeakReference<Context>(context);
        this.isShowDialog = isShowDialog;
        mHandler = new ProgressDialogHandler(this.mContext, this, true);
    }

    private void showProgressDialog() {
        if (mHandler != null) {
            if (isShowDialog)
                mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
            else
                mHandler.obtainMessage(ProgressDialogHandler.UN_SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mHandler = null;
        }
    }


    @Override
    public void onError(Throwable e) {
        Logger.e("ProgressObserver  onError" + e);
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(WBaseApp.getInstance(), "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(WBaseApp.getInstance(), "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(WBaseApp.getInstance(), "RetrofitError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
        if (mListener != null) {
            mListener.onError(e);
        }
    }


    private Disposable disposable;

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
//        Logger.e("ProgressObserver  onNext");
        if (mListener != null) {
            try {
                mListener.onNext(t, disposable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
//        Logger.e("ProgressObserver  onComplete");
         dismissProgressDialog();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    /**
     * progress dialog 取消 同时 取消网络请求，并回掉到 onerror 去做处理
     */
    @Override
    public void onCancelProgress() {
//        Logger.e("ProgressObserver  onCancelProgress");
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (mListener != null) {
            mListener.onError(new Exception("用户取消请求"));
        }
    }
}
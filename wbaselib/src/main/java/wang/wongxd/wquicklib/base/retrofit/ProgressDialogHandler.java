package wang.wongxd.wquicklib.base.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by wongxd on 2017/8/2.
 * <p>
 * 进度条对话框 handler 处理进度条显示状态
 */
public class ProgressDialogHandler extends Handler {
    public static final int UN_SHOW_PROGRESS_DIALOG = 1;
    public static final int SHOW_PROGRESS_DIALOG = 2;
    public static final int DISMISS_PROGRESS_DIALOG = 3;

    private ProgressDialog pd;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private String title = "  精彩 稍后即来";

    /**
     *
     * @param weakReference
     * @param mProgressCancelListener
     * @param cancelable  默认可取消
     */
    public ProgressDialogHandler(WeakReference<Context> weakReference, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = weakReference.get();
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog(boolean isShow) {
        if (!isShow) return;
        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setMessage(title);
            pd.setCancelable(cancelable);

            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case UN_SHOW_PROGRESS_DIALOG:
                initProgressDialog(false);
                break;
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog(true);
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
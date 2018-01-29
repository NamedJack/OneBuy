package wang.wongxd.wquicklib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wongxd on 2017/8/15.
 * 软键盘相关
 * <p>
 * http://blog.csdn.net/mynameishuangshuai/article/details/51567357
 * <p>
 * <p>
 * <p>
 * 3.软键盘把某些布局挤上去了的情况
 * <p>
 * <activity
 * android:name=".activity.DetailActivity"
 * android:screenOrientation="portrait"
 * android:windowSoftInputMode="adjustPan"
 * android:theme="@style/AppTheme.NoActionBar.Translucent"/>
 * <p>
 * <p>
 * 主要就是 windowSoftInputMode 这个属性，其中2个比较重要的是 adjustPan 和 adjustResize
 * adjustPan 不会把底部的布局给挤上去，例如relateLayout 布局中 放到bottom 的布局
 * adjustResize 是自适应的，会把底部的挤上去。
 * 更详细的可以了解下 windowSoftInputMode 这个属性，好多大神的博客上都有说明，我这就不赘述了。
 */

public class KeyboardUtil {

    /**
     * @param aty
     * @param editText
     */
    public static void showKeyboard(WeakReference<Activity> aty, WeakReference<EditText> editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) aty.get().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText.get(), 0);
    }


    /**
     * 对于界面比较复杂的情况的时候，软键盘可能无法正常的弹出，需要延迟加载。即在界面加载完成之后，弹出软键盘
     *
     * @param editText
     */
    public static void showKeyboard(final WeakReference<EditText> editText) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText.get().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText.get(), 0);
            }
        }, 998);
    }

    /**
     * 关闭软键盘
     */
    public static void hideKeyboard(WeakReference<Activity> aty) {
        InputMethodManager imm = (InputMethodManager) aty.get().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(aty.get().getWindow().getDecorView().getWindowToken(), 0);
        }
    }


    /**
     * 我遇到过一种情况是，首页是scrollView 包裹的界面，滚动一段距离后进入下一个页面，
     * 会弹出软键盘，当关闭该界面的时候（即直接 finish()）,回到首页的时候，scrollView 不是原来的位置了。
     * 处理办法就是 在有软键盘弹出的页面，先关闭软键盘，再 finish（）界面。
     *
     * @param aty
     */
    public static void closeKeyboard(WeakReference<Activity> aty) {
        View view = aty.get().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) aty.get().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}

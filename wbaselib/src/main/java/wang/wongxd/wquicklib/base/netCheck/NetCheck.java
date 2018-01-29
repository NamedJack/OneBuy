package wang.wongxd.wquicklib.base.netCheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import wang.wongxd.wquicklib.base.WBaseApp;

/**
 * Created by wongxd on 2017/8/1.
 *
 * 网络检查工具
 */

public class NetCheck {

    /**
     * 手机网络
     */
    public static final int NET_DATA = 1;

    /**
     * wifi
     */
    public static final int NET_WIFI = 2;

    /**
     * 无网络
     */
    public static final int NO_NET = 0;
    /**
     * 网络状态  -1  未初始化   0--无网络  1--手机网络   2--wifi
     */
    private static volatile int netState = -1;

    public static int getNetState() {
        return netState;
    }

    private CheckNetReceive checkNetReceive;

    private NetCheck() {
        if (context == null) {
            throw new IllegalArgumentException("必须 register NetCheck");
        }
        checkNetReceive = new CheckNetReceive();
        //注册检查网络的广播
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(checkNetReceive, filter);
    }


    private static Context context;

    public static void register(Context ctx) {
        if (context != null) {
            Log.e("wongxd的网络监控", "  已经注册过了");
            return;
        }
        context = ctx;
        instance = new NetCheck();
    }

    private static volatile NetCheck instance;

    private static NetCheck getDefault() {
        if (null == instance) {
            synchronized (NetCheck.class) {
                if (null == instance) {
                    instance = new NetCheck();
                }
            }
        }
        return instance;
    }

    /***
     * 检查网络状态的广播
     */
    private static class CheckNetReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkInfo.State wifiState = null;
            NetworkInfo.State mobileState = null;
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED == mobileState) {
                // 手机网络连接成功
                netState = 1;
            } else if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED != mobileState) {
                // 手机没有任何的网络
                netState = 0;

            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
                // 无线网络连接成功
                netState = 2;
            }

            WBaseApp.NetState = netState;
        }


    }

}

package wang.wongxd.wquicklib.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import wang.wongxd.wquicklib.base.netCheck.NetCheck;
import wang.wongxd.wquicklib.utils.TU;

/**
 * Created by wongxd on 2017/8/1.
 */

public class WBaseApp extends Application {

    public static int NetState;

    private static WBaseApp instance;

    public static WBaseApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NetCheck.register(this);
        TU.register(this);

        //smartRefresh
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context);
            }

        });

        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
    }



}

package wang.wongxd.wquicklib.custom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wongxd on 2017/8/29.
 */

public class Search {

    // 创建 SingleThreadExecutor
    ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    Future mFuture;

    // 每当数据变化时调用
    void onDataChanged() {
        if (mFuture != null) {
            // 数据变化时，取消上一个任务
            mFuture.cancel(true);
        }
        // 执行异步任务
        mFuture = mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                // 根据resultDatas 更新UI


            }
        });
    }


}

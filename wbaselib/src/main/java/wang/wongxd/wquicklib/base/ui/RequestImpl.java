package wang.wongxd.wquicklib.base.ui;

import org.reactivestreams.Subscription;


/**
 * Created by wongxd on 2017/8/1.
 * <p>
 * 请求回调
 */

public interface RequestImpl {
    void loadSuccess(Object object);

    void loadFailed();

    void addSubscription(Subscription subscription);
}
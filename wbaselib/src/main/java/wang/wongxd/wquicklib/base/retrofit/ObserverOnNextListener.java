package wang.wongxd.wquicklib.base.retrofit;

import io.reactivex.disposables.Disposable;

/**
 * Created by wongxd on 2017/8/2.
 */

public interface ObserverOnNextListener<T> {
    void onNext(T t, Disposable disposable);

    void onError(Throwable e);
}
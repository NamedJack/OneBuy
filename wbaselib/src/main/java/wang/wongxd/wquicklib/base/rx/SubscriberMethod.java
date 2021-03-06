package wang.wongxd.wquicklib.base.rx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SubscriberMethod {
    public Method method;
    public ThreadMode threadMode;
    public Class<?> eventType;
    public Object subscriber;
    public int code;

    public SubscriberMethod(Object subscriber, Method method, Class<?> eventType, int code, ThreadMode threadMode) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
        this.subscriber = subscriber;
        this.code = code;
    }


    /**
     * 调用方法
     *
     * @param o 参数
     */
    public void invoke(Object o) {
        try {
            if (!Modifier.isPublic(method.getModifiers())) {
                method.setAccessible(true);  // not public method
            }
            method.invoke(subscriber, o);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
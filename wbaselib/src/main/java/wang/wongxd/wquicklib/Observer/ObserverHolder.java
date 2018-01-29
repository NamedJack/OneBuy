package wang.wongxd.wquicklib.Observer;

import java.util.HashMap;
import java.util.Map;

/**
 * 订阅模式管理类
 *
 * @author chengliang0315
 * @see <a>http://blog.csdn.net/chengliang0315/article/details/53381539</a>
 */
public class ObserverHolder {

    /**
     * 接收到信息
     */
    public static final int RECEIVER_MESSAGE = 1;


    private static ObserverHolder instance;

    private Map<String, EventObservable> observableList;

    private ObserverHolder() {
        observableList = new HashMap<>();
    }

    public static ObserverHolder getInstance() {
        if (instance == null) {
            synchronized (ObserverHolder.class) {
                if (instance == null) {
                    instance = new ObserverHolder();
                }
            }
        }
        return instance;
    }

    /**
     * 将消息接收者注册进来
     * (如果将Activity作为订阅者在此注册的时候，切记在onDestroy()里面移除注册，否则可能导致内存泄露)
     *
     * @param observer
     * @param type     什么的被观察者
     */
    public void register(String type, IObserver observer) {
        EventObservable observable = observableList.get(type);
        if (observable == null) {
            observable = new EventObservable();
            observableList.put(type, observable);
        }
        observable.addObserver(observer);
    }

    /**
     * 将消息接收者移除注册
     *
     * @param type 什么的被观察者
     */
    public void unregister(String type, IObserver observer) {
        EventObservable observable = observableList.get(type);
        if (observable == null) return;
        if (observable.countObservers() == 0) {
            observableList.remove(type);
            return;
        }
        observable.deleteObserver(observer);

    }

    /**
     * 给订阅者发生消息
     *
     * @param data
     */
    public void notifyObservers(String type, String data, int flag) {
        EventObservable observable = observableList.get(type);
        if (observable == null) {
            return;
        }
        observable.notifyObservers(data, flag);
    }


}

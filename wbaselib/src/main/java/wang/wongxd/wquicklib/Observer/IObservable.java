package wang.wongxd.wquicklib.Observer;

/**
 * 被观察者
 * @author chengliang0315
 * @see <a>http://blog.csdn.net/chengliang0315/article/details/53381539</a>
 */

public interface IObservable {

    /**
     * 向消息订阅队列添加订阅者
     */
    void deleteObserver(IObserver observer);

    /**
     * 删除指定订阅者
     */
    void addObserver(IObserver observer);

    /**
     * 通知消息订阅队列里的每一个订阅者
     * @param data  给订阅者传递的参数
     * @param flag 标示
     */
    void notifyObservers(Object data, int flag);

}

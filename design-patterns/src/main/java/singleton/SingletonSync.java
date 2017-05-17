package singleton;

public class SingletonSync {

    // 使用静态变量记录Singleton唯一的实例
    private static SingletonSync uniqueInstance;

    // 构造器声明为私有，只有Singleton类内才可以调用构造器
    private SingletonSync() {}

    // getInstance 实例化对象，提供全局访问点
    // 使用synchronized解决线程不安全的问题
    public static synchronized SingletonSync getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonSync();
        }
        return uniqueInstance;
    }
}

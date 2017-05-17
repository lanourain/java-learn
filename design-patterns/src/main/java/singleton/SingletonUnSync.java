package singleton;

// 线程不安全，会导致存在多个实例
public class SingletonUnSync {

    // 使用静态变量记录Singleton唯一的实例
    private static SingletonUnSync uniqueInstance;

    // 构造器声明为私有，只有Singleton类内才可以调用构造器
    private SingletonUnSync() {}

    // getInstance 实例化对象，提供全局访问点
    public static SingletonUnSync getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonUnSync();
        }
        return uniqueInstance;
    }
}

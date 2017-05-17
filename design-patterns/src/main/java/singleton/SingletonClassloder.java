package singleton;

public class SingletonClassloder {

    // 基于classloder避免多线程同步问题，类装载的时候就实例化
    private static SingletonClassloder instance = new SingletonClassloder();

    private SingletonClassloder() {
    }

    public static SingletonClassloder getInstance() {
        return instance;
    }
}

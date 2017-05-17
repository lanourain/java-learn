package singleton;

public class SingletonStaticClass {
    private static class SingletonHolder {
        private static final SingletonStaticClass INSTANCE = new SingletonStaticClass();
    }

    private SingletonStaticClass() {
    }

    // 只有在getInstance 被调用时，SingletonStaticClassHolder才会被装载，实现了延迟加载和线程安全
    public static final SingletonStaticClass getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

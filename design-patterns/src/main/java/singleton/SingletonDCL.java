package singleton;

public class SingletonDCL {
    private volatile static SingletonDCL singletonDCL;

    private SingletonDCL() {
    }

    public static SingletonDCL getSingletonDCL() {
        if (singletonDCL == null) {
            synchronized (SingletonDCL.class) {
                if (singletonDCL == null) {
                    singletonDCL = new SingletonDCL();
                }
            }
        }
        return singletonDCL;
    }
}

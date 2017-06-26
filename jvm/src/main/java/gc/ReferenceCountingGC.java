package gc;

/**
 * 循环引用
 * 主流虚拟机都不选择引用计数法进行gc
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    // 占用内存，以便gc日志中可以查看是否被回收
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC a = new ReferenceCountingGC();
        ReferenceCountingGC b = new ReferenceCountingGC();
        a.instance = b;
        b.instance = a;

        a = null;
        b = null;
        System.gc();
    }
}

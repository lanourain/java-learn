package memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试OOM
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    private static class OOMObject {}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        Long i = 0L;
        while (true) {
            list.add(new OOMObject());
            System.out.println(i++);
        }
    }
}

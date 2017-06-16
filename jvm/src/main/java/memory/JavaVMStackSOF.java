package memory;

/**
 * 测试SOF
 * VM Args：-Xss128k
 * 随着Xss的改变，溢出时的栈深度也会有改变
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + sof.stackLength);
            throw e;
        }

    }
}

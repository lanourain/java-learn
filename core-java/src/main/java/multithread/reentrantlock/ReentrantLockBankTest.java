package multithread.reentrantlock;

/**
 * 使用ReentrantLock 保证线程安全
 */
public class ReentrantLockBankTest {

    public static final int NACCOUNTS = 100;

    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        ReentrantLockBank b = new ReentrantLockBank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();

        }

    }
}

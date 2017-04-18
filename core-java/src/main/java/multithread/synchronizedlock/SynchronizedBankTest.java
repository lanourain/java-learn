package multithread.synchronizedlock;

/**
 * 使用synchronized实现锁
 */
public class SynchronizedBankTest {

    public static final int NACCOUNTS = 100;

    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        SynchronizedBank b = new SynchronizedBank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();

        }
    }
}

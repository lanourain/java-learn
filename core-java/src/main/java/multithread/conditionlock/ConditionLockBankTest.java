package multithread.conditionlock;

/**
 * 使用Condition 防止死锁发生
 */
public class ConditionLockBankTest {

    public static final int NACCOUNTS = 100;

    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        ConditionLockBank b = new ConditionLockBank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();

        }
    }
}

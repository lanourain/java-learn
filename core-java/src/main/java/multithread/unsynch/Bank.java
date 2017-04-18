package multithread.unsynch;

/**
 * bank类，提供转账的功能，并发对accounts更新读取会导致线程不安全，sum的值会变化
 */
public class Bank {
    private double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        // 由于更新accounts信息不是原子操作。分三步，加载accounts[to]信息到寄存器，增加account，写会导accounts[to]
        // 线程在执行过程中可能被剥夺运行权，过会儿才被唤醒。
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    }

    public double getTotalBalance() {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }

    public int site() {
        return accounts.length;
    }
}

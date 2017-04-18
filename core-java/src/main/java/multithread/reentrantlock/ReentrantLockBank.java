package multithread.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * bank类，在对accounts进行操作时，使用reentrantlock操作。
 */
public class ReentrantLockBank {
    private double[] accounts;

    private Lock bankLock = new ReentrantLock();

    public ReentrantLockBank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }

        //用ReentrantLock保护代码块，确保只有一个线程进入临界区
        //一个线程持有锁的时候，其他线程想要获取锁会被阻塞(尝试了下好像是WAITING状态？)
        bankLock.lock();
        try {
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            // 由于更新accounts信息不是原子操作。分三步，加载accounts[to]信息到寄存器，增加account，写会导accounts[to]
            // 线程在执行过程中可能被剥夺运行权，过会儿才被唤醒。
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        } finally {
            bankLock.unlock();
        }
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

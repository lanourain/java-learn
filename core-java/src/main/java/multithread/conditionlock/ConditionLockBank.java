package multithread.conditionlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * bank类，在对accounts进行操作时，使用condition防止死锁发生
 */
public class ConditionLockBank {
    private double[] accounts;

    private Lock bankLock = new ReentrantLock();

    private Condition sufficientFunds;

    public ConditionLockBank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
        //获取锁的条件对象
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) {
        //用ReentrantLock保护代码块，确保只有一个线程进入临界区
        //一个线程持有锁的时候，其他线程想要获取锁会被阻塞
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                //放弃锁，进入该条件的等待集。当锁可用的时候，该线程还是处于阻塞状态，要等其他线程调用了signalAll才会被激活
                sufficientFunds.await();
            }

            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            // 由于更新accounts信息不是原子操作。分三步，加载accounts[to]信息到寄存器，增加account，写会导accounts[to]
            // 线程在执行过程中可能被剥夺运行权，过会儿才被唤醒。
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            //激活等待集中的线程
            sufficientFunds.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

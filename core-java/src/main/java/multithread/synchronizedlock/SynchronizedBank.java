package multithread.synchronizedlock;

/**
 * bank类，使用Synchronized 内部锁
 */
public class SynchronizedBank {
    private double[] accounts;

    public SynchronizedBank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
    }

    // synchronized 内部锁，标记改方法是临界区
    //也可以用 synchronized(this) {} 包含对应的同步代码块
    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        while (accounts[from] < amount) {
            //相当于Condition 的await();
            wait();
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        // 由于更新accounts信息不是原子操作。分三步，加载accounts[to]信息到寄存器，增加account，写会导accounts[to]
        // 线程在执行过程中可能被剥夺运行权，过会儿才被唤醒。
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        //相当于Condition 的 signalAll
        //解除调用wait方法的线程的阻塞方法，只能在同步方法或者同步块内部调用
        notifyAll();
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

package multithread.unsynch;

/**
 * 转账线程，并发对account数据进行更新
 */
public class TransferRunnable implements Runnable {
    private Bank bank;

    private int fromAccount;

    private double maxAmmount;

    private int DELAY = 10;

    public TransferRunnable(Bank bank, int from, double maxAmmount) {
        this.bank = bank;
        this.fromAccount = from;
        this.maxAmmount = maxAmmount;
    }

    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.site() * Math.random());
                double amount = maxAmmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

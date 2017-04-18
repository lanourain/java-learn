package multithread;

public class SleepInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new SleepRunnable();
        //创建一个新线程，用于调用给定的run()方法
        Thread t = new Thread(r);
        //启动线程，引发run()的调用，该方法立刻返回，并且新线程将并行运行。
        t.start();
        Thread.sleep(5000);
        //线程阻塞，调用interrupt方法，会中断，并抛出 Interrupted Exception异常
        t.interrupt();
        System.out.printf("print main");
    }

    static class SleepRunnable implements Runnable {
        public void run() {
            int i = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("test runable " + i);
                ++i;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

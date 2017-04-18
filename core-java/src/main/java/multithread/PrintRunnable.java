package multithread;

public class PrintRunnable {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new MyRunnable();
        //创建一个新线程，用于调用给定的run()方法
        Thread t = new Thread(r);
        //启动线程，引发run()的调用，该方法立刻返回，并且新线程将并行运行。
        t.start();
        System.out.printf("print main");
    }

    static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("test runable ");

        }
    }
}

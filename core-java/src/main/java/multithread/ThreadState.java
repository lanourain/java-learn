package multithread;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new StateRunnable();
        Thread t = new Thread(r);

        // 状态 = NEW
        System.out.println(t.getState());

        t.start();
        // 状态 = RUNNABLE
        System.out.println(t.getState());

        Thread.sleep(10000);
        // 状态 = TERMINATED
        System.out.println(t.getState());
    }

    static class StateRunnable implements Runnable {
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


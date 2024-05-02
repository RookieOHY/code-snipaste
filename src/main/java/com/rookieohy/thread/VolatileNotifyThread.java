package com.rookieohy.thread;

/**
 *
 * @author 20413
 */
public class VolatileNotifyThread implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (!canceled && num <= 1000000) {
                if (num % 10 == 0) {
                    System.out.println(num + "是10的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        VolatileNotifyThread r = new VolatileNotifyThread();

        Thread thread = new Thread(r);

        thread.start();

        Thread.sleep(3000);

        r.canceled = true;

    }
}

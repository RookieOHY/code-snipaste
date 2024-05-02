package com.rookieohy.thread;

/**
 * @author 20413
 */
public class InterruptNotifyThread implements Runnable{
    @Override
    public void run() {
        // 打印0-999
        int count = 0;
        while (count < 1000 && !Thread.currentThread().isInterrupted()){
            System.out.println(count++);
        }
    }


    // 测试
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptNotifyThread());
        thread.start();
        Thread.sleep(5);
        // 发送一个中断通知(注意：具体什么时候中断、能不能成功中断)
        thread.interrupt();
    }

}

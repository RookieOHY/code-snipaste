package com.rookieohy.thread;

public class InterruptSleepThread {
    public static void main(String[] args) throws InterruptedException{
        Runnable runnable = ()->{
          int count = 0;
          while (!Thread.currentThread().isInterrupted() && count < 1000 ){
              System.out.println(count++);
              // 长睡眠可以响应中断信号
              try {
                  Thread.sleep(1000000);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5);
        // 发送一个中断通知(注意：具体什么时候中断、能不能成功中断)
        thread.interrupt();
        // Caused by: java.lang.InterruptedException: sleep interrupted

    }
}

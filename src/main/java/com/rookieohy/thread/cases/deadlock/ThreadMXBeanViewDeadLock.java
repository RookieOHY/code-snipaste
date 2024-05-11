package com.rookieohy.thread.cases.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadMXBeanViewDeadLock {
    // 2个对象
    private Object o1 = new Object();
    private Object o2 = new Object();
    // 方法1：先对o1加锁，内部对o2加锁
    public void m1() throws InterruptedException {
        synchronized (o1){
            Thread.sleep(500);
            System.out.println("m1的o1逻辑");
            synchronized (o2){
                System.out.println("m1的o2逻辑");
            }
        }
    }
    // 方法2：先对o2加锁，内部对o1加锁
    public void m2() throws InterruptedException {
        synchronized (o2){
            Thread.sleep(500);
            System.out.println("m2的o2逻辑");
            synchronized (o1){
                System.out.println("m2的o1逻辑");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadMXBeanViewDeadLock threadMXBeanViewDeadLock = new ThreadMXBeanViewDeadLock();

        new Thread(() -> {
            try {
                threadMXBeanViewDeadLock.m1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1").start();

        new Thread(() -> {
            try {
                threadMXBeanViewDeadLock.m2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t2").start();



        Thread.sleep(2000);

        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMxBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMxBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("线程id为"+threadInfo.getThreadId()+",线程名为" + threadInfo.getThreadName()+"的线程已经发生死锁，需要的锁正被线程"+threadInfo.getLockOwnerName()+"持有。");
            }

        }

    }
}

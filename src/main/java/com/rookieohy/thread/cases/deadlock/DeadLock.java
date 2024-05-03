package com.rookieohy.thread.cases.deadlock;

/**
 * 死锁案例
 * @author 20413
 */
public class DeadLock {
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
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        // 新建2个线程 一个执行m1 一个执行m2
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.m1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.m2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

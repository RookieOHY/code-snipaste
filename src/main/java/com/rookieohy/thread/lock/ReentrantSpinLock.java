package com.rookieohy.thread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个自旋+可重入锁
 */
public class ReentrantSpinLock {

    // 存储当前重复次数 因为加锁和释放锁都需要执行加和减
    private int count;
    // 存储重入的线程
    private AtomicReference<Thread> owner = new AtomicReference<>();

    // 加锁：加锁成功，count自增和设置当前下线程；加锁失败，自旋
    public void lock(){
        Thread tt = Thread.currentThread();
        if(tt == owner.get()){
            count++;
            return;
        }
        // 循环判断：owner是否为null，即当前无其他线程设置到owner,就设置tt
        while (!owner.compareAndSet(null,tt)){
            System.out.println("执行自旋中...");
        }
    }

    public void unlock(){
        Thread t = Thread.currentThread();
        //只有持有锁的线程才能解锁
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            } else {
                //此处无需CAS操作，因为没有竞争，因为只有线程持有者才能解锁
                owner.set(null);
            }

        }
    }


    public static void main(String[] args) {
        ReentrantSpinLock spinLock = new ReentrantSpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了了自旋锁");
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }

}

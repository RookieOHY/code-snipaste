package com.rookieohy.thread.cases.pcm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 实现生产者消费者
 * @author 20413
 */
public class ConditionImplementProducerConsumer {
    // 存放元素的队列
    private Queue queue;
    // 锁
    private ReentrantLock lock = new ReentrantLock();
    // 最大元素数量
    int max = 20;
    // 条件1：队列不为空
    private Condition notEmpty = lock.newCondition();
    // 条件2：队列还没满
    private Condition notFull = lock.newCondition();

    public ConditionImplementProducerConsumer(int max) {
        this.max = max;
        queue = new LinkedList();
    }

    //生产逻辑
    public void put(Object in) throws InterruptedException{
        // 加锁 满阻塞 执行生产 没空通知 释放锁
        lock.lock();
        try {
            while (queue.size() == max){
                notFull.await(); //!!!释放锁
            }
            queue.add(in);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }
    //消费逻辑
    public Object take() throws InterruptedException{
        // 加锁 空阻塞 执行消费 没满通知 释放锁
        lock.lock();
        try {
            while (queue.size() == 0){
                notEmpty.await(); //!!!释放锁
            }
            Object item = queue.remove();
            notFull.signalAll();
            return item;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionImplementProducerConsumer queue = new ConditionImplementProducerConsumer(5);

        // Runnable for producer
        Runnable producer1 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(100); // 模拟生产过程
                    Object item = "Item" + i;
                    queue.put(item);
                    System.out.println("P1: 生产了1个: " + item + ",当前大小："+queue.queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable producer2 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(150); // 模拟生产过程
                    Object item = "Item" + i;
                    queue.put(item);
                    System.out.println("P2: 生产了1个: " + item + ",当前大小："+queue.queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Runnable for consumer
        Runnable consumer1 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(200); // 模拟消费过程
                    Object item = queue.take();
                    System.out.println("C1: 消费了1个: " + item + ",当前大小："+queue.queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumer2 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(250); // 模拟消费过程
                    Object item = queue.take();
                    System.out.println("C2: 消费了1个: " + item + ",当前大小："+queue.queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Create threads
        Thread producerThread1 = new Thread(producer1);
        Thread producerThread2 = new Thread(producer2);
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);

        // Start threads
        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}

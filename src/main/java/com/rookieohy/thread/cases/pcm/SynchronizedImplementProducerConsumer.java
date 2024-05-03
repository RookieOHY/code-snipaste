package com.rookieohy.thread.cases.pcm;

import java.util.LinkedList;

/**
 * Synchronized+wait+notifyAll 实现
 */
public class SynchronizedImplementProducerConsumer {
    private int maxSize;
    private LinkedList<Object> storage;
    public SynchronizedImplementProducerConsumer(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }
    // 生产方法
    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize){
            wait();
        }
        storage.add(new Object());
        System.out.println("生产了一个");
        notifyAll();
    }
    // 消费方法
    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0){
            wait();
        }
        storage.remove();
        System.out.println("消费了一个");
        notifyAll();
    }

    public static void main(String[] args) {
        SynchronizedImplementProducerConsumer myBlockingQueue = new SynchronizedImplementProducerConsumer(10);
        Producer producer = new Producer(myBlockingQueue);
        Consumer consumer = new Consumer(myBlockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}


class Producer implements Runnable {
    private SynchronizedImplementProducerConsumer storage;
    public Producer(SynchronizedImplementProducerConsumer storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private SynchronizedImplementProducerConsumer storage;

    public Consumer(SynchronizedImplementProducerConsumer storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

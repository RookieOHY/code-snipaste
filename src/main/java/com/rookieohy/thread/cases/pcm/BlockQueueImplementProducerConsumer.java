package com.rookieohy.thread.cases.pcm;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 基于阻塞队列的api 生产者消费者模型
 * 注意：api已经实现
 */
public class BlockQueueImplementProducerConsumer {
    public static void main(String[] args) {
        // queue
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        // 消费者 和 生产者
        Runnable producer = ()->{
            while (true){
                try {
                    queue.put(new Object());
                    System.out.println("生产了1个，当前大小："+queue.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        // 尽量先启动若干个消费者 后启动若干消费者
        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer = ()->{
            while (true){
                try {
                    queue.take();
                    System.out.println("消费了1个，当前大小："+queue.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}

package com.rookieohy.virtualthread;



/**
 * @author 20413
 */
public class VirtualThread{
    public static void main(String[] args) throws InterruptedException {
        // 启动1个平台线程
        Thread.ofPlatform().start(()->{
            System.out.println("平台线程打印的内容:"+Thread.currentThread());
        });
        // 启动1个虚拟线程
        Thread vt = Thread.ofVirtual().start(() -> {
            System.out.println("虚拟线程打印的内容:"+ Thread.currentThread());
        });

        //！！！！！！！使用join（等待vt的执行结果后再执主线程）,如果不加该方法，上面的虚拟线程有可能没有输出
        vt.join();
        System.out.println("主线程");

    }
}
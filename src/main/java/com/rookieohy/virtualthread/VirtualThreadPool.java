package com.rookieohy.virtualthread;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 测试100w个虚拟线程大概会耗费多少个平台线程
 */
public class VirtualThreadPool {

    public static void main(String[] args)  {
        // 存储平台线程的非重复集合
        Set<String> pts = ConcurrentHashMap.newKeySet();
        // 新建虚拟线程池
        ExecutorService vtPool = Executors.newVirtualThreadPerTaskExecutor();
        // 新增100w
        IntStream.range(0,1_000_000).forEach(i-> vtPool.submit(()->{
           // task : 睡眠1s ，执行时获取当前虚拟线程对应的平台线程
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String threadFullName = Thread.currentThread().toString();
            String pftName = threadFullName.split("@")[1];
            // 加入Set
            pts.add(pftName);
            return i;
        }));
        System.out.println("100W虚拟线程耗费平台线程数：" + pts.size());
    }
}

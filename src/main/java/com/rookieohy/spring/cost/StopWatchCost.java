package com.rookieohy.spring.cost;

import org.springframework.util.StopWatch;

/**
 * 使用stopwatch计算方法执行耗时
 */
public class StopWatchCost {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        // start and stop ;gap as a task
        stopWatch.start();
        Thread.sleep(1000);
        stopWatch.stop();

        stopWatch.start();
        Thread.sleep(3000);
        stopWatch.stop();

        stopWatch.start();
        Thread.sleep(2000);
        stopWatch.stop();

        // 输出百分比
        System.out.println(stopWatch.prettyPrint());
    }
}

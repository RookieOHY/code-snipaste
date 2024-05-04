package com.rookieohy.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 测试 拆分合并线程池
 * @author 20413
 */
public class TestForkJoinPool {
    // 测试FbNum
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask<Integer> submit = forkJoinPool.submit(new FbNum(i));
            System.out.println("f("+i+")="+submit.get());
        }
    }
}




// 继承递归任务
class FbNum extends RecursiveTask<Integer> {
    int n ;

    public FbNum(int n) {
        this.n = n;
    }

    // 实现compute
    @Override
    protected Integer compute() {

        if(n<=1){
            return n;
        }
        // 拆分 和 合并结果
        FbNum subFb = new FbNum(n - 1);
        subFb.fork();

        FbNum subFb2 = new FbNum(n - 2);
        subFb2.fork();

        return subFb.join() + subFb2.join();
    }
}
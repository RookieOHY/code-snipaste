package com.rookieohy.thread;

import java.util.concurrent.*;

/**
 * @author 20413
 */
public class ImplentmentCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "实现callable接口的call方法（配合线程池submit方法）";
    }

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(1)) {
            Future<String> submit = executorService.submit(new ImplentmentCallable());
            System.out.println(submit.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

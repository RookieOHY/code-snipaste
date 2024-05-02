package com.rookieohy.thread;

/**
 * @author 20413
 */
public class ExtendThread extends Thread{
    @Override
    public void run() {
        // 任务
        System.out.println("继承Thread重写run方法");
    }

}

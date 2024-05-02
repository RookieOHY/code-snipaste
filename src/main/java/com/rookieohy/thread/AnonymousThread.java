package com.rookieohy.thread;

/**
 * @author 20413
 */
public class AnonymousThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类创建");
            }
        });
        thread.start();
    }
}

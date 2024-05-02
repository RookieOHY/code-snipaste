package com.rookieohy.thread;

public class LambdaThread {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Lambda方法创建")).start();
    }
}

package com.rookieohy.pattern.singleton;


/**
 * 双检查单例 + sync + volotale
 */
public class DoubleCheckSingleton {
    // 防止第一个线程初始化由于重排序（对象不为null但是属性为空），此时第二个线程
    // 直接返回，导致此时实际呗使用时不可用（实际上由于时机关系，没有初始化玩）
    private static volatile DoubleCheckSingleton singleton;
    private DoubleCheckSingleton(){

    }
    public static DoubleCheckSingleton getInstance(){
        // 首次判空：让线程可以直接返回，否则只能进入代码块才会返回
        if(singleton == null){
            synchronized (DoubleCheckSingleton.class){
                // 第二次判空：新建了2个对象
                if(singleton == null){
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        // 不为空
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(DoubleCheckSingleton.getInstance());
                }
            }).start();
        }
    }
}

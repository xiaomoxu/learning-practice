package com.home.basic.pattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoubleCheckSingleton {
    private static DoubleCheckSingleton _instance;

//    private static  volatile DoubleCheckSingleton _instance;


    /***
     * 事实上双重锁判定依然会出现线程问题:
     * 比如A线程进入此方法，判定instance == null,进入同步逻辑的时候，发现阻塞了，因为此时可能B线程正在执行同步代码块，这个时候B执行完了
     * 此时按道理 _instance应该是有值了，但是A线程不知道，所以A线程又执行了一次同步逻辑，再次覆盖了之前B生成的_instance对象
     *
     * 解决上面这个问题的办法 就是在设置 volatile 可见性声明，这意味着，B创建完对象之后，A再次进入同步块，会去主存再次获取_instance的值，发现此时有值了，那么就不
     * 会再次从创建对象
     *
     * 但是我们还是期望使用枚举模式去创建单例，因为枚举模式的默认构造方法只初始化一次，这样效率非常高
     *
     * @return
     */

    public static DoubleCheckSingleton getInstance() {
        if (_instance == null) {                // Single Checked
            synchronized (DoubleCheckSingleton.class) {
                if (_instance == null) {        // Double checked
                    _instance = new DoubleCheckSingleton();
                }
            }
        }
        return _instance;
    }

    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            cachedThreadPool.submit((Runnable) () -> {
                DoubleCheckSingleton obj1 = DoubleCheckSingleton.getInstance();
                System.out.println(obj1.getClass().hashCode());
            });
        }
    }
}

package com.home.basic.pattern.singleton;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用枚举的单例模式
 *
 * 最佳实践推荐使用这样模式创建单例，其他模式都会有不一致或重复创建单例实例的问题发生
 *
 * @author yzl
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class EnumSingleton {
    private EnumSingleton() {
    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton {
        INSTANCE;

        private EnumSingleton singleton;

        //JVM会保证此方法绝对只调用一次
        private Singleton() {
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return singleton;
        }
    }


    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            cachedThreadPool.submit((Runnable) () -> {
                EnumSingleton obj1 = EnumSingleton.getInstance();
                System.out.println(obj1.getClass().hashCode());
            });
        }
    }


}

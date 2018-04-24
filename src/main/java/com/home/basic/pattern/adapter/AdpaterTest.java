package com.home.basic.pattern.adapter;

public class AdpaterTest {
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.readOnly();
        adapter.orginalMethod();
        /***
         * 可以看出适配器接口既满足了新业务的扩展，又保留了遗留系统的接口的使用
         * 可以说适配器模式是对当前系统的一种补充式的设计模式
         * 适配器的设计模式分为 接口适配器模式 和 类适配器模式
         *
         * 类适配器模式 有点类似于静态代理模式，区别是静态代理模式 target  代理者 和 被代理者都保持一种相同的行为，也就是说接口一样
         * 但是适配器模式的target行为可以自定义
         */
    }
}

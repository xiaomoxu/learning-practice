package com.home.basic.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");

        //创建一个与代理对象相关联的InvocationHandler
        StuInvocationHandler stuHandler = new StuInvocationHandler<Person>();
        Person personProxy = (Person) stuHandler.bind(zhangsan);
        //代理执行上交班费的方法
        personProxy.giveMoney();
    }
}

package com.home.basic.kafka.common;

/**
 * @author Gary Russell
 * @since 2.2.1
 *
 */
public class Foo1 {

    private String foo;

    public Foo1() {
        super();
    }

    public Foo1(String foo) {
        this.foo = foo;
    }

    public String getFoo() {
        return this.foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "Foo1 [foo=" + this.foo + "]";
    }

}
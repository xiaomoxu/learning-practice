package com.home.basic.pattern.adapter;

public class Adapter extends Adaptee implements Target {
    @Override
    public void readOnly() {
        System.out.println("This is read only method for client!");
    }
}

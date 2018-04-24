package com.home.basic.container;

public class HashCode {
    public static void main(String[] args) {
        int hashcode = new String("h").hashCode();
        System.out.println(hashcode);
        System.out.println(hashcode >>> 16);
        System.out.println(hashcode ^ (hashcode >>> 16));
    }
}

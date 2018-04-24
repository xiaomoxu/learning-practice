package com.home.basic.container;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapApp {

    public void duplicateHashMapTest() {
        /**
         * 1.hashmap 可以允许key值为null
         * 2.可以重复put key值重复的 K-V ，但是每次都是后一次覆盖前一次的
         */
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("xxu", "xiaomoxu");
        hashMap.put("xxu", "wobao");
        hashMap.put(null, "xiejingwei");
        hashMap.put(null, "xiejingwei2");
        String value = hashMap.get("xxu");
        String keyNullValue = hashMap.get(null);
        System.out.println(value);
        System.out.println(keyNullValue);
    }

    public void duplicateHashTableTest() {
        /**
         * 很明显hashtable 不允许 放入key为null的 K-V
         * 可以重复put key值重复的 K-V ，但是每次都是后一次覆盖前一次的
         */
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("xxu", "xiaomoxu");
        hashtable.put("xxu", "wobao");
//        hashtable.put(null, "xiejingwei");
//        hashtable.put(null, "xiejingwei2");
        String value = hashtable.get("xxu");
//        String keyNullValue = hashtable.get(null);
        System.out.println(value);
//        System.out.println(keyNullValue);
    }

    public static void main(String[] args) {
//        new HashMapApp().duplicateHashMapTest();
        new HashMapApp().duplicateHashTableTest();
    }
}

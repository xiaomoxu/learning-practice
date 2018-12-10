package com.home.basic.concurrent;

public class WaitNotify {

//    public boolean pause = true;

    public Object lock = new Object();

    private Thread waitThread = new Thread() {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println("lock!");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("execute continue executing wait thread!");
        }
    };

    private Thread notifyThread = new Thread() {
        @Override
        public void run() {
            synchronized (lock) {
                lock.notify();
                System.out.println("unlock!");
            }
        }
    };

    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify();
        wn.waitThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wn.notifyThread.start();
    }
}

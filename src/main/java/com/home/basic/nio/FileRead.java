package com.home.basic.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileRead implements Runnable {
    private String filePath = "";

    public FileRead(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            FileChannel fileChannel = new FileInputStream(filePath).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int num = 0;
            int total = 0;
            while ((num = fileChannel.read(buffer)) > 0) {
//                System.out.println(Thread.currentThread().getName() + "read size :" + buffer.array().length);
                long position = fileChannel.position();
                System.out.println(position);
                total = total + buffer.array().length;
                buffer.clear();
            }
            System.out.println("finish! " + total);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\home\\common\\jdk-6u31-windows-x64.exe";
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new FileRead(filePath));
        executorService.submit(new FileRead(filePath));
    }
}

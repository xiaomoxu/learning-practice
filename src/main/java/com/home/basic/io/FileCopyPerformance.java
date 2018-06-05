package com.home.basic.io;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileCopyPerformance {
    public void copyFilebyOIO() {
        long start = System.currentTimeMillis();
        String originalFile = "Z:\\GHOST_WIN10X64Pro_14393_577\\windows.GHO";
        String destFile = "Z:\\GHOST_WIN10X64Pro_14393_577\\_windows.GHO";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File desFile = null;
        try {
            fis = new FileInputStream(originalFile);
            desFile = new File(destFile);
            if (!desFile.exists()) {
                boolean isCreate = desFile.createNewFile();
            }
            fos = new FileOutputStream(desFile);
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                fos.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                desFile.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("use time: " + (end - start) + " 毫秒");
    }

    public void copyFileByNIO() {
        long start = System.currentTimeMillis();
        String originalFile = "Z:\\GHOST_WIN10X64Pro_14393_577\\windows.GHO";
        String destFile = "Z:\\GHOST_WIN10X64Pro_14393_577\\_windows.GHO";
        File desFile = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            desFile = new File(destFile);
            if (!desFile.exists()) {
                boolean isCreate = desFile.createNewFile();
            }
            fileInputStream = new FileInputStream(originalFile);
            fileOutputStream = new FileOutputStream(destFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            desFile.deleteOnExit();
            long end = System.currentTimeMillis();
            System.out.println("use time: " + (end - start) + " 毫秒");
        }
    }

    public static void main(String artz[]) {
//        new FileCopyPerformance().copyFilebyOIO();
        new FileCopyPerformance().copyFileByNIO();
    }
}

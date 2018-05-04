package com.home.basic.sorting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Sort {


    //选择排序
    public static void SearchSort(int[] unsorted) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            for (int j = i + 1; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j]) {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }

    }

    //冒泡排序
    public static void BubbleSort(int[] unsorted) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            for (int j = 0; j < unsorted.length - 1 - i; j++) {
                if (unsorted[j] > unsorted[j + 1]) {
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j + 1];
                    unsorted[j + 1] = temp;
                }
            }
        }
    }


    //插入排序
    public static void InsertSort(int[] unsorted) {
        for (int i = 1; i < unsorted.length; i++) {
            int temp = unsorted[i];
            int j = i - 1;
            while (j >= 0 && temp < unsorted[j]) {
                unsorted[j + 1] = unsorted[j];
                j--;
            }
            unsorted[j + 1] = temp;
        }

    }

    //快速查找
    public static int quickSort(int[] numbers, int start, int end) {
        int temp = numbers[start]; //数组的第一个作为中轴
        while (start < end) {
            while (start < end && numbers[end] > temp) {
                end--;
            }
            numbers[start] = numbers[end];//比中轴小的记录移到低端
            while (start < end && numbers[start] < temp) {
                start++;
            }
            numbers[end] = numbers[start]; //比中轴大的记录移到高端
        }
        numbers[start] = temp; //中轴记录到尾
        return start; // 返回中轴的位置

    }


    //二分法查找
    public static int DoubleSearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }

        }
        return -1;

    }

    public static void OperateDoubleSearch() {
        int[] arr = new int[]{12, 23, 34, 45, 56, 67, 77, 89, 90};
        System.out.println(DoubleSearch(arr, 12));
        System.out.println(DoubleSearch(arr, 45));
        System.out.println(DoubleSearch(arr, 89));
    }


    //传入多个参数，都返回string
    public String getstring(Object object) {
        if (object == null) {
            return null;
        } else {
            return new String(object.toString());
        }

    }

    public void getString(Object... objects) {
        if (objects.length <= 0) {
            System.out.println("传入的参数为空！！！！！！！");
        } else {
            for (int i = 0; i < objects.length; i++) {
                String str = objects[i].toString();
                System.out.println("输出的字符串是：" + str);
                System.out.println(str.getClass().getName());
            }
        }
    }

    public <E> void gettoString(E... object) {
        for (int i = 0; i < object.length; i++) {
            System.out.println(object[i].toString());
        }
        for (E element : object) {
            System.out.println(element.toString());
        }
    }


    //将一个字符串反转
    public void reverseString(String str) {
        System.out.println("最初的 字符串是：" + str);
        String newstr = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(str.length() - 1 - i);
            newstr = newstr + c;
        }
        System.out.println("反转后的字符串是：" + newstr);

    }


    //复制文件
    public void CopyFile(String oldfilePath, String newfilePath) throws IOException {
        File oldfile = new File(oldfilePath);
        File newfile = new File(newfilePath);
        FileInputStream fis = new FileInputStream(oldfile);
        FileOutputStream fos = new FileOutputStream(newfile);
        int read = 0;
        while ((read = fis.read()) != -1) {
            fos.write(read);
            fos.flush();
        }
        fos.close();
        fis.close();
    }


    //将一句话的单词首字母大写
    public void UpperCase(String str) {
        char[] c = str.toCharArray();
        if (c[0] >= 'a' && c[0] <= 'z') {
            c[0] = (char) (c[0] - 32);
        }
        for (int i = 1; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i + 1] = (char) (c[i + 1] - 32);
            }
        }
        String UpperCaseStr = new String(c);
        System.out.println("将字符串单词首字母大写之后： " + UpperCaseStr);
    }


    //输入年月日，输出是这一年的第几天
    public static void getDayInYear(int year, int month, int day) {
        int counts = 0;
        int days = 0;
        if (year > 0 && month > 0 && month < 13 && day > 0 && day < 32) {
            for (int i = 0; i < month; i++) {
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        days = 30;
                        break;
                    case 2:
                        if ((year % 4 == 0 && year % 100 == 0) || (year % 400 == 0)) {
                            days = 29;
                        } else {
                            days = 28;
                        }
                        break;
                }
                counts = counts + days;
            }
            System.out.println("是这一年的第 " + counts);

        } else {
            System.err.println("参数年月日有问题！！！！！！");
        }
    }


    //将一段字符串中个字符的数量非农额打印出
    public void printCharSum() {
        int countNum = 0;
        int countWord = 0;
        int countBlank = 0;
        int countOther = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("请输入字符串！");
        String str = in.nextLine();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 0 && c[i] <= 9) {
                countNum++;
            } else if ((c[i] >= 'a' && c[i] <= 'z') || (c[i] >= 'A' && c[i] <= 'Z')) {
                countWord++;
            } else if (c[i] == ' ') {
                countBlank++;
            } else {
                countOther++;
            }
        }

        System.out.println("数字个数是 ： " + countNum);
        System.out.println("数字个数是 ： " + countWord);
        System.out.println("数字个数是 ： " + countBlank);
        System.out.println("数字个数是 ： " + countOther);

    }


    //计算出各个字符的个数
    public static void getCharNum(String[] args) {
        Set<Character> set = new HashSet<Character>();
        String str = "l love you !";
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        Iterator<Character> it = set.iterator();
        while (it.hasNext()) {
            Character c = (Character) it.next();
            int k = 0;
            for (int j = 0; j < str.length(); j++) {
                if (c.equals(str.charAt(j))) {
                    k++;
                }
            }
            System.out.println(c + "有" + k + "个");
        }
        //set.size();
    }


    //红绿灯
    public enum Signal {
        green, yellow, red
    }

    public static class trafficLight {
        public Signal signal = null;

        public Signal getSignalColor(Signal signal) throws InterruptedException {
            switch (signal) {
                case red:
                    Thread.sleep(5000);
                    signal = Signal.green;
                    break;
                case green:
                    Thread.sleep(5000);
                    signal = Signal.yellow;
                    break;
                case yellow:
                    Thread.sleep(5000);
                    signal = Signal.red;
                    break;

            }
            return signal;
        }

    }


    //递归阶乘
    public static long jiecheng(int i) {
        if (i == 0 || i == 1) {
            return 1;
        } else {
            return i * jiecheng(i - 1);
        }
    }

    public static long jiecheng1(int i) {
        int j = 1;
        if (i == 0 || i == 1) {
            return 1;
        } else {
            for (int k = i; k >= 2; k--) {
                j = j * i;
            }
        }
        return j;

    }

    //单例模式
    public static class Test {
        private Test() {
        }

        ;
        private static Test test;

        public static Test geTestInstance() {
            if (test == null) {
                test = new Test();
            }
            return test;
        }

    }


    //打印随机函数
    public void printRandom() {
        int[] numbers = {1, 0, 5, 4, 3, 42, 71, 25, 6};
        Random random = new Random();
        int newIndex = random.nextInt(numbers.length);
        System.out.println(numbers[newIndex - 1]);
    }

    public void printRandomforTime(int time) {
        long begin = System.currentTimeMillis();

        while (true) {
            printRandom();
            long end = System.currentTimeMillis();
            if ((end - begin) >= time * 1000) {
                System.out.println("运行了" + time + "秒之后结束运行");
                break;
            }

        }

    }


    //水仙花     百位的3次方 + 十位的3次方 + 个位的3次方 = 这个数
    public void printWaterFlower() {
        int a, b, c;
        for (int i = 100; i < 1000; i++) {
            a = i / 100;
            b = i % 100 / 10;
            c = i % 10;
            if ((a * a * a + b * b * b + c * c * c) == i) {
                System.out.println(i + "是一个水仙花数");
            }
        }

    }


    //乘法口诀
    public void chenfa() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.println(i + "*" + j + '=' + i * j + " ");
                if (i * j < 10) {
                    System.out.println(" ");
                }
            }
            System.out.println();
        }
    }


    //将1,2,3,4,5这5个数字可组成多少个无重复的四位数
    public void createNumbers() {
        int count = 0;
        for (int a = 1; a <= 5; a++) {
            for (int b = 1; b <= 5; b++) {
                for (int c = 1; c <= 5; c++) {
                    for (int d = 1; d <= 5; d++) {
                        if ((a != b) && (a != c) && (a != d) && (b != c) && (b != d) && (c != d)) {
                            count++;
                            System.out.println(a * 1000 + b * 100 + c * 10 + d);
                        }
                    }
                }
            }
        }
        System.out.println("可组成" + count + "个无重复的四位数");
    }


    /**
     * 判断两个文件的内容是否相同，文件名要用绝对路径
     *
     * @param fileName1 ：文件1的绝对路径
     * @param fileName2 ：文件2的绝对路径
     * @return 相同返回true，不相同返回false
     */
    public boolean isSameFile(String fileName1, String fileName2) {
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        try {
            fis1 = new FileInputStream(fileName1);
            fis2 = new FileInputStream(fileName2);

            int len1 = fis1.available();//返回总的字节数
            int len2 = fis2.available();

            if (len1 == len2) {//长度相同，则比较具体内容
                //建立两个字节缓冲区
                byte[] data1 = new byte[len1];
                byte[] data2 = new byte[len2];

                //分别将两个文件的内容读入缓冲区
                fis1.read(data1);
                fis2.read(data2);

                //依次比较文件中的每一个字节
                for (int i = 0; i < len1; i++) {
                    //只要有一个字节不同，两个文件就不一样
                    if (data1[i] != data2[i]) {
                        System.out.println("文件内容不一样");
                        return false;
                    }
                }
                System.out.println("两个文件完全相同");
                return true;
            } else {
                //长度不一样，文件肯定不同
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//关闭文件流，防止内存泄漏
            if (fis1 != null) {
                try {
                    fis1.close();
                } catch (IOException e) {
                    //忽略
                    e.printStackTrace();
                }
            }
            if (fis2 != null) {
                try {
                    fis2.close();
                } catch (IOException e) {
                    //忽略
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


}


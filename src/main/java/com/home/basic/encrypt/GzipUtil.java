package com.home.basic.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class GzipUtil {
    // 压缩
    public static byte[] compress(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(data);
        gzip.close();
        return out.toByteArray();//out.toString("ISO-8859-1");
    }

    public static byte[] compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        return compress(str.getBytes("utf-8"));
    }

    // 解压缩
    public static byte[] uncompress(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return data;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        gunzip.close();
        in.close();
        return out.toByteArray();
    }

    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        byte[] data = uncompress(str.getBytes("utf-8")); // ISO-8859-1
        return new String(data);
    }

    /**
     * @param @param  unZipfile
     * @param @param  destFile 指定读取文件，需要从压缩文件中读取文件内容的文件名
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: unZip
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public static String unZip(String unZipfile, String destFile) {// unZipfileName需要解压的zip文件名
        InputStream inputStream;
        String inData = null;
        try {
            // 生成一个zip的文件
            File f = new File(unZipfile);
            ZipFile zipFile = new ZipFile(f);

            // 遍历zipFile中所有的实体，并把他们解压出来
            ZipEntry entry = zipFile.getEntry(destFile);
            if (!entry.isDirectory()) {
                // 获取出该压缩实体的输入流
                inputStream = zipFile.getInputStream(entry);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] bys = new byte[4096];
                for (int p = -1; (p = inputStream.read(bys)) != -1; ) {
                    out.write(bys, 0, p);
                }
                inData = out.toString();
                out.close();
                inputStream.close();
            }
            zipFile.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return inData;
    }

    public static void main(String[] args) {
        String json = "ksjdflkjsdflskjdflsdfkjsdfsdfsdfsadfasdfasdfasdfasdfasfasdfasdfsadf";
        System.out.println(json.getBytes().length);
        try {
            byte[] buf = GzipUtil.compress(json);
            System.out.println(buf.length);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

//    File pdfFile = new File("C:\\Users\\xiaomo.xu\\workload\\projects\\" +
//            "github\\learning-practice\\src\\main\\java\\com\\home\\basic\\encrypt\\HR_1540275400153.pdf");
//        try {
//                byte[] buf = GzipUtil.compress(Files.readAllBytes(pdfFile.toPath()));
//
//                File fin = new File("C:\\Users\\xiaomo.xu\\workload\\projects\\github\\learning-practice\\" +
//                "src\\main\\java\\com\\home\\basic\\encrypt\\gzip-file");
//                FileChannel fcout = new RandomAccessFile(fin, "rws").getChannel();
//                ByteBuffer wBuffer = ByteBuffer.allocateDirect(buf.length);
//                fcout.write(wBuffer.wrap(buf), fcout.size());
//                if (fcout != null) {
//                fcout.close();
//                }
//                } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                }
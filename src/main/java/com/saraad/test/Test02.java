package com.saraad.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Title: Test02
 * @Package:com.saraad.test
 * @Description:
 * @author: saraad
 * @date: 2021/8/9 4:20 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Test02 {

    public static void main(String[] args) throws IOException {
        String path = "/Users/saraad/Desktop/sign.png";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String str = Base64.getEncoder().encodeToString(bytes);
        byte[] bytee = Base64.getEncoder().encode(bytes);
        System.out.println(str);
        System.out.println(bytee);

    }

    public static byte[] changeSize(int newWidth, int newHeight, byte[] ori) {
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(ori);

            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            Graphics graphics = tag.getGraphics();
            graphics.drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(tag);
            ImageIO.write(tag, "PNG", out);
            in.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static boolean changeSize(int newWidth, int newHeight, String path, String newPath) {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));

            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            Graphics graphics = tag.getGraphics();
            graphics.drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newPath));
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(tag);
            ImageIO.write(tag, "PNG", out);
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

//        if ("abc".equals(token)) {
//            AXUserInfoResponseDTO test = new AXUserInfoResponseDTO();
//            test.setUserId("C82B8B7127073FD9E05311016B0AFB9F");
//            test.setUserName("jiufang_test");
//            test.setIdentNo("310100199901011234");
//            test.setIdentTypeCode("0");
//            test.setMobilePhone("16621014396");
//            return test;
//        }

}

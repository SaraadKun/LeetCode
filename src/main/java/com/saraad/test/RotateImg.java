package com.saraad.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @Title: RotateImg
 * @Package:com.saraad.test
 * @Description:
 * @author: saraad
 * @date: 2021/9/9 10:18 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class RotateImg {
    /**
     * 创建任意角度的旋转图像
     *
     * @param image
     * @param theta
     * @param backgroundColor
     * @return
     */
    public BufferedImage rotateImage(BufferedImage image, double theta, Color backgroundColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        double angle = theta * Math.PI / 180; // 度转弧度
        double[] xCoords = getX(width / 2, height / 2, angle);
        double[] yCoords = getY(width / 2, height / 2, angle);
        int WIDTH = (int) (xCoords[3] - xCoords[0]);
        int HEIGHT = (int) (yCoords[3] - yCoords[0]);
        BufferedImage resultImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                int x = i - WIDTH / 2;
                int y = HEIGHT / 2 - j;
                double radius = Math.sqrt(x * x + y * y);
                double angle1;
                if (y > 0) {
                    angle1 = Math.acos(x / radius);
                } else {
                    angle1 = 2 * Math.PI - Math.acos(x / radius);
                }
                x = (int) (radius * Math.cos(angle1 - angle));
                y = (int) (radius * Math.sin(angle1 - angle));
                if (x < (width / 2) & x > -(width / 2) & y < (height / 2) & y > -(height / 2)) {
                    int rgb = image.getRGB(x + width / 2, height / 2 - y);
                    resultImage.setRGB(i, j, rgb);
                } else {
                    int rgb = ((0 & 0xff) << 24) | ((backgroundColor.getRed() & 0xff) << 16) | ((backgroundColor.getGreen() & 0xff) << 8)
                            | ((backgroundColor.getBlue() & 0xff));
                    resultImage.setRGB(i, j, rgb);
                }
            }
        }
        return resultImage;
    }

    // 获取四个角点旋转后Y方向坐标
    private double[] getY(int i, int j, double angle) {
        double results[] = new double[4];
        double radius = Math.sqrt(i * i + j * j);
        double angle1 = Math.asin(j / radius);
        results[0] = radius * Math.sin(angle1 + angle);
        results[1] = radius * Math.sin(Math.PI - angle1 + angle);
        results[2] = -results[0];
        results[3] = -results[1];
        Arrays.sort(results);
        return results;
    }

    // 获取四个角点旋转后X方向坐标
    private double[] getX(int i, int j, double angle) {
        double results[] = new double[4];
        double radius = Math.sqrt(i * i + j * j);
        double angle1 = Math.acos(i / radius);
        results[0] = radius * Math.cos(angle1 + angle);
        results[1] = radius * Math.cos(Math.PI - angle1 + angle);
        results[2] = -results[0];
        results[3] = -results[1];
        Arrays.sort(results);
        return results;
    }

    public static void main(String[] args) throws Exception {
        String oriPath = "/Users/saraad/Desktop/tmp/out/1.png";
        File input = new File(oriPath);
        File output = new File("/Users/saraad/Desktop/tmp/out/2.png");
        byte[] file = Files.readAllBytes(Paths.get(oriPath));
        ByteArrayInputStream in = new ByteArrayInputStream(file);
//        BufferedImage image = ImageIO.read(input);
        BufferedImage image = ImageIO.read(in);
        Color bgColor = new Color(255, 255, 255);
        BufferedImage result = new RotateImg().rotateImage(image, 90, bgColor);

        ImageIO.write(result, "png", output);
    }

}


package com.saraad.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Title: UDPSender
 * @Package:com.saraad.udp
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 4:26 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class UDPSender {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(); // 此类表示用来发送和接收数据报包的套接字。
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 键盘输入
        String line = null;
        while ((line = br.readLine()) != null) {
            byte[] bytes = line.getBytes();
            DatagramPacket dp0 = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 55550); // 数据包对象
//            DatagramPacket dp1 = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 55551); // 数据包对象
            socket.send(dp0);
//            socket.send(dp1);
            if ("end".equals(line)) { // 当输入end时结束发送
                break;
            }
        }
        socket.close();
    }
}

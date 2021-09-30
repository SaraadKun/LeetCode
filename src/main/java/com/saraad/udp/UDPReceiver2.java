package com.saraad.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Title: UDPReceiver2
 * @Package:com.saraad.udp
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 4:27 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class UDPReceiver2 {

    //port 55550
    public static void main(String[] args) throws IOException {
        int port = 55550;
        DatagramSocket socket = new DatagramSocket(port);
        byte bytes[] = new byte[2048];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length); // 建立数据包对象
        while (true) {
            socket.receive(dp); // 接受数据包
            byte[] data = dp.getData(); // 获取数据
            String str = new String(data, 0, dp.getLength());
            if ("end".equals(str)) {
                break;
            }
            System.out.println(str);
        }
        socket.close();
    }

}

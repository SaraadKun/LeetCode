package com.saraad.thread.demo;

import com.saraad.thread.util.ThreadUtil;

import java.io.*;
import java.net.Socket;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 29-06-2022 00:14
 */

public class SocketClientDemo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
//            ThreadUtil.sleep(10L);
            Socket socket = new Socket("localhost", 7777);
            OutputStream sos = socket.getOutputStream();
//            for (int j = 0; j < 2; j++) {
                sos.write(("client_" + i + " connected ").getBytes());
                sos.write(("client_" + i + " connected2").getBytes());
//            }
            sos.flush();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
//            while ((len = in.read(bytes)) != -1) {
//                outputStream.write(bytes, 0, len);
//            }
            int len = in.read(bytes);
            outputStream.write(bytes, 0, len);
            System.err.println(outputStream.toString());
//            SocketServerDemo.close(socket, out, in);
        }

    }
}

package com.saraad.thread.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 29-06-2022 00:01
 */

public class SocketServerDemo {
    static String SERVER_RES_FORMAT = "SERVER RECEIVE IP: %s, PORT: %s, MSG: %s";
    static ExecutorService pool = Executors.newFixedThreadPool(4);
    public static void main(String[] args) throws IOException {
        System.out.println("Server is running...");
        ServerSocket serverSocket = new ServerSocket(7777);
        serverSocket.setSoTimeout(60000);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            final Socket curSocket = socket;
            pool.submit(() -> {
                try {
                    System.err.println(curSocket.getInetAddress() + ": " + curSocket.getPort());
                    DataInputStream in;
    //                StringBuilder sb = new StringBuilder();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    in = new DataInputStream(curSocket.getInputStream());
                    int len = in.read(bytes);
                    out.write(bytes, 0, len);
//                    while ((i = in.read(bytes)) != -1) {
//                        out.write(bytes, 0, i);
//                    }
                    String msg = out.toString();
                    System.err.println(msg);

                    DataOutputStream oc = new DataOutputStream(curSocket.getOutputStream());
                    oc.write(String.format(SERVER_RES_FORMAT, curSocket.getInetAddress(), curSocket.getPort(), msg).getBytes());
                    oc.flush();
//                    close(curSocket, in, out, oc);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        serverSocket.close();
    }

    static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

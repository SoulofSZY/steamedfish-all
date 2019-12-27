package com.szy.lamda.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉<br>
 * 〈实现一个服务器应用，只简单要求能够同时服务多个客户端请求即可〉
 * IO/net 同步阻塞api
 *
 * 问题： Java 语言目前的线程实现是比较重量级的，启动或者销毁一个线程是有明显开销的，每个线程都有单独的线程栈等结构，需要占用非常明显的内存，所以，每一个 Client 启
 * 动一个线程似乎都有些浪费
 * 优化1： 线程池
 * 如果连接数并不是非常多，只有最多几百个连接的普通应用，这种模式往往可以工作的很好。但是，如果连接数量急剧上升，这种实现方式就无法很好地工作了，因为线程上下文切
 * 换开销会在高并发时变得很明显，这是同步阻塞方式的低扩展性劣势
 *
 * @author sunzhengyu
 * @create 2019/8/14
 * @since 1.0.0
 */
public class TestIO {

    static class DemoServer extends Thread {
        private ServerSocket serverSocket;

        private ExecutorService executor;

        public int getPort() {
            return serverSocket.getLocalPort();
        }

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(0);
                executor = Executors.newFixedThreadPool(8);
                while (true) {
                    Socket socket = serverSocket.accept();
                    RequestHandler requestHandler = new RequestHandler(socket);
                    // 方式1 每个client 一个线程
                    //requestHandler.start();
                    executor.execute(requestHandler);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        class RequestHandler extends Thread {
            private Socket socket;

            RequestHandler(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    out.println("hello world!");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DemoServer demoServer = new DemoServer();
        demoServer.start();

        try (Socket socket = new Socket(InetAddress.getLocalHost(), demoServer.getPort())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.lines().forEach(str -> System.out.println(str));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
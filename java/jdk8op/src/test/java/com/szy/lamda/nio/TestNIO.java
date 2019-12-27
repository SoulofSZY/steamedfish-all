package com.szy.lamda.nio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/8/14
 * @since 1.0.0
 */
public class TestNIO {


    static class NIOServer extends Thread {

        @Override
        public void run() {
            try (Selector selector = Selector.open(); ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
                serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
                serverSocket.configureBlocking(false);
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> itr = selectionKeys.iterator();
                    while (itr.hasNext()) {
                        SelectionKey key = itr.next();
                        sayHello((ServerSocketChannel) key.channel());
                        itr.remove();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sayHello(ServerSocketChannel serverSocket) throws IOException {
            try (SocketChannel client = serverSocket.accept()) {
                client.write(Charset.defaultCharset().encode("hello world!"));
            }
        }
    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer();
        server.start();

        try (Socket socket = new Socket(InetAddress.getLocalHost(), 8888)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.lines().forEach(str -> System.out.println(str));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
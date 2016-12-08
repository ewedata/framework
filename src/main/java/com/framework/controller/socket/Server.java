package com.framework.controller.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author LIUAOZ
 * @since 2016年12月8日 下午1:57:10
 * @version 1.0
 *
 */
// TODO 优化该服务类，使用工厂模式，创建不同类型的服务器
public class Server {

    public static void main(String[] args) {
        newIO();
    }

    public static void start(String serverType) {
        if (serverType == null) {
            start();
        } else {

        }
    }

    public static void start() {

    }

    public static void stop() {

    }

    public static void oldIO() {
        ServerSocket ss;
        Socket s;
        try {
            ss = new ServerSocket(8080);
            s = ss.accept();
            // 收到请求后使用socket进行通信，创建BufferedReader用于读数据
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = br.readLine();
            System.out.println("received from client: " + line);

            // 创建PrintWriter,用于发送数据
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("received data:" + line);
            pw.flush();

            pw.close();
            br.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // buffer/channel/selector/selectionKey //P43
    public static void newIO() {

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            Handler handler = new Handler(1024);

            while (true) {
                if (selector.select(3000) == 0) {
                    System.out.println("等待请求超时");
                    continue;
                }
                System.out.println("处理请求...");

                Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next();

                    if (key.isAcceptable()) {
                        handler.handleAccept(key);
                    }

                    if (key.isReadable()) {
                        handler.handleRead(key);
                    }
                    keyIter.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Handler {

        private int bufferSize = 1024;

        private String localCharset = "UTF-8";

        public Handler() {}

        public Handler(int bufferSize) {
            this(bufferSize, null);
        }

        public Handler(int bufferSize, String localCharset) {
            if (bufferSize > 0) this.bufferSize = bufferSize;
            if (localCharset != null) this.localCharset = localCharset;
        }

        public void handleAccept(SelectionKey key) throws IOException {
            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead(SelectionKey key) throws IOException {

            SocketChannel sc = (SocketChannel) key.channel();

            ByteBuffer buffer = (ByteBuffer) key.attachment();

            buffer.clear();

            if (sc.read(buffer) == -1) {
                sc.close();
            } else {
                buffer.flip();

                String receivedString =
                        Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                System.out.println("received from client: " + receivedString);

                String sendString = "received data: " + receivedString;

                buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));

                sc.write(buffer);

                sc.close();
            }
        }

    }

}

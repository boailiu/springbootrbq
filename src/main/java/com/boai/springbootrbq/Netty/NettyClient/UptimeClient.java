package com.boai.springbootrbq.Netty.NettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class UptimeClient {

    static final String HOST = "172.16.1.22";
    static final int PORT = 8989;

    static final int SIZE = 1024;

    static final int RECONNECT_DELAY = 5;

    private static final int READ_TIMEOUT = 10;

    private static final UptimeClientHandler handler = new UptimeClientHandler();
    private static final Bootstrap bs = new Bootstrap();

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        bs.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(HOST, PORT)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, 0, 0), handler);
                    }
                });
        bs.connect();
    }

    static void connect() {
        bs.connect().addListener((ChannelFutureListener) future -> {
            if (future.cause() != null) {
                handler.startTime = -1;
                handler.println("Failed to connect: " + future.cause());
            }
        });
    }

}

package com.boai.springbootrbq.Netty.Telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TelnetClient {

    static final String HOST = "127.0.0.1";
    static final int PORT = 8989;

    public static void main(String[] args) {
        final NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TelnetClientInitializer());
            //start the connection attmept
            final Channel channel = bootstrap.connect(HOST, PORT).sync().channel();

            ChannelFuture lastWriteFuture = null;
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                final String line = in.readLine();
                if (line == null) {
                    break;
                }

                lastWriteFuture = channel.writeAndFlush(line + "\r\n");
                if ("bye".equals(line.toLowerCase())) {
                    channel.closeFuture().sync();
                    break;
                }
            }

            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully();
        }

    }
}

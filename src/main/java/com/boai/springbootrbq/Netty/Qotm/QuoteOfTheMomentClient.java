package com.boai.springbootrbq.Netty.Qotm;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;

public class QuoteOfTheMomentClient {

    static final int PORT = 8989;

    public static void main(String[] args) {
        final NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, Boolean.TRUE)
                    .handler(new QuoteOfMomentClientHandler());

            final Channel channel = bootstrap.bind(0).sync().channel();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("QOTM?", CharsetUtil.UTF_8),
                                                     SocketUtils.socketAddress("255.255.255.255", PORT))).sync();

            if (!channel.closeFuture().await(50000)) {
                System.err.println("QOTM request time out");
            }
        } catch (Exception e) {
        } finally {
            group.shutdownGracefully();
        }
    }
}

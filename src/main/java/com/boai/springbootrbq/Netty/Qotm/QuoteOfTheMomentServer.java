package com.boai.springbootrbq.Netty.Qotm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class QuoteOfTheMomentServer {

    private static final int PORT = 8989;

    public static void main(String[] args) {
        final NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new QuoteOfTheMomentServerHandler());

            bootstrap.bind(PORT).sync().channel().closeFuture().await();
        }catch (Exception e){

        }finally {
            group.shutdownGracefully();
        }
    }
}

package com.boai.springbootrbq.Netty.HttpSnoop;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class SnoopServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        final ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpResponseDecoder());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new SnoopClientHandler());
    }
}

package com.boai.springbootrbq.Netty.HttpSnoop;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Set;

public class SnoopClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("status : " + response.status());
            System.out.println("protocalVersion :" + response.protocolVersion());

            if (!response.headers().isEmpty()) {
                final Set<String> names = response.headers().names();
                names.forEach(n -> {
                    final List<String> all = response.headers().getAll(n);
                    all.forEach(a -> System.out.println("header : " + n + " = " + a));
                });
            }

            if (HttpUtil.isTransferEncodingChunked(response)) {
                System.out.println("CHUNKED CONTENT : {");
            } else {
                System.out.println("CONTENT : {");
            }

            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                System.out.println(content.content().toString(CharsetUtil.UTF_8));

                if (content instanceof LastHttpContent) {
                    System.out.println("} END OF CONTENT");
                    ctx.close();
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

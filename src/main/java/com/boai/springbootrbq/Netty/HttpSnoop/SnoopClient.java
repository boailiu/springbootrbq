package com.boai.springbootrbq.Netty.HttpSnoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.net.URISyntaxException;

public class SnoopClient {

    static final String url = "http://127.0.0.1:8989/";

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        final URI uri = new URI(url);
        String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
        String host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
        int port = uri.getPort();
        if (port == -1) {
            if ("http".equalsIgnoreCase(scheme)) {
                port = 80;
            } else if ("https".equalsIgnoreCase(scheme)) {
                port = 443;
            }
        }

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            System.err.println("only HTTP(S) supported");
            return;
        }

        final NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SnoopClientInitializer());

            // make the connection attempt
            final Channel channel = bootstrap.connect(host, port).sync().channel();

            final DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                                                                              uri.getRawPath(), Unpooled.EMPTY_BUFFER);

            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);

            request.headers().set(HttpHeaderNames.COOKIE, ClientCookieEncoder
                    .encode(new DefaultCookie("my-cookie", "foo"),
                            new DefaultCookie("another-cookie", "bar")));

            channel.writeAndFlush(request);

            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}

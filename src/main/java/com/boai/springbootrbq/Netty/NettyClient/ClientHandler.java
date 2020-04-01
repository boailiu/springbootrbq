package com.boai.springbootrbq.Netty.NettyClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    public ClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        String message = "恢复湿地恢复到死";
        byte[] reqMsgByte = message.getBytes("UTF-8");
        ByteBuf reqByteBuf = Unpooled.buffer(reqMsgByte.length);
        /**
         * writeBytes：将指定的源数组的数据传输到缓冲区
         * 调用 ChannelHandlerContext 的 writeAndFlush 方法将消息发送给服务器
         */
        reqByteBuf.writeBytes(reqMsgByte);
        ctx.writeAndFlush(reqByteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        ByteBuf in = (ByteBuf) o;
        System.out.println("Client receive: " + in.toString(CharsetUtil.UTF_8));
//        ctx.write(o);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package netty.lesson1.helloworld;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *@author chengnl
 *@date 2015年2月8日 下午5:06:08
 *@version 1.0
 *@Description:客户端处理类
 */
public class TimeClientHandler extends ChannelHandlerAdapter{
   private final ByteBuf firstMessage;
   public TimeClientHandler() {
	 byte[] req = "QUERY TIME ORDER"	.getBytes();
	 firstMessage = Unpooled.buffer(req.length);
	 firstMessage.writeBytes(req);
   }
   @Override
   public void channelActive(ChannelHandlerContext ctx){
	   ctx.writeAndFlush(this.firstMessage);
   }
   
   @Override
   public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException{
	   ByteBuf buf = (ByteBuf)msg;
	   byte[] req = new byte[buf.readableBytes()];
	   buf.readBytes(req);
	   String body = new String(req,"UTF-8");
	   System.out.println("Now is :"+body);
   }
   @Override
   public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
	   ctx.close();
	   
   }
}

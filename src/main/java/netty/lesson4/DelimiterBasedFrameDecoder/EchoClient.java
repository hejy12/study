package netty.lesson4.DelimiterBasedFrameDecoder;

import javax.swing.JSpinner.NumberEditor;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 *@author chengnl
 *@date 2015年2月8日 下午4:59:03
 *@version 1.0
 *@Description: 时间客户端
 */
public class EchoClient {
    public  void connect(int port ,String host) throws Exception{
    	EventLoopGroup group = new NioEventLoopGroup();
    	try{
    		Bootstrap b = new Bootstrap();
    		b.group(group).channel(NioSocketChannel.class)
    		 .option(ChannelOption.TCP_NODELAY, true)
    		 .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
					arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
					arg0.pipeline().addLast(new StringDecoder());
					arg0.pipeline().addLast(new EchoClientHandler());
					
				}
			});
    		//发起异步连接操作
    		ChannelFuture f = b.connect(host,port).sync();
    		//等待客户端链路关闭
    		f.channel().closeFuture().sync();
    	}finally{
    		group.shutdownGracefully();
    	}
    }
    
    public static void main(String[] args) throws Exception {
		int port =8080;
		if(args!=null&&args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				
			}
		}
		new EchoClient().connect(port, "127.0.0.1");
	}
}

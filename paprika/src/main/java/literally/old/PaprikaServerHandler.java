package literally.old;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class PaprikaServerHandler extends SimpleChannelUpstreamHandler{

	@Override
	public void messageReceived(ChannelHandlerContext context, MessageEvent event) throws Exception{
		System.out.println((String) event.getMessage());
	}
	
//	@Override
//	public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent e){
//		
//	}
}

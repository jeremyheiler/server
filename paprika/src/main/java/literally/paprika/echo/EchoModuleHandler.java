package literally.paprika.echo;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class EchoModuleHandler extends SimpleChannelHandler{
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e){
		Object message=e.getMessage();
		Channel ch=(Channel)e.getChannel();
		ch.write(message);
		
		ChannelBuffer buf=(ChannelBuffer)message;
			while(buf.readable()){
				System.out.print((char) buf.readByte());
				System.out.flush();
			}
			System.out.println();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e){
		e.getCause().printStackTrace();
		Channel ch=(Channel)e.getChannel();
		ch.close();
	}
	
}

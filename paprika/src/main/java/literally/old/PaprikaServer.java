package literally.old;

import java.net.InetSocketAddress;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class PaprikaServer implements Runnable{

	@Override
	public void run(){
		ServerBootstrap boostrap = new ServerBootstrap(new NioServerSocketChannelFactory());
		boostrap.setPipelineFactory(new PaprikaServerPipelineFactory());
		boostrap.bind(new InetSocketAddress(2323));
	}
}

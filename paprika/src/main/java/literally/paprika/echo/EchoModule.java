package literally.paprika.echo;

import literally.paprika.Module;
import literally.paprika.Port;
import literally.paprika.PortManager;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class EchoModule implements Module{
	static final ChannelGroup channels=new DefaultChannelGroup("Echo Server");
	public static final String NAME="Echo Server";
	private final ChannelFactory factory;
	private Channel channel=null;
	private Port port;
	
	public EchoModule(){
		this.factory=new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()
			);
	}
	
	public void register(PortManager manager){
		this.port = manager.acquire(8000);
	}
	
	@Override
	public String getName(){
		return NAME;
	}

	@Override
	public void start(){
		ServerBootstrap bootstrap=new ServerBootstrap(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory(){
			public ChannelPipeline getPipeline(){
				return Channels.pipeline(new EchoModuleHandler());
			}
		});
		    
		bootstrap.setOption("child.tcpNoDelay",true);
		bootstrap.setOption("child.keepAlive",true);
		channel=bootstrap.bind(new InetSocketAddress(port.get()));
		channels.add(channel);
	}

	@Override
	public void stop(){
		ChannelGroupFuture future=channels.close();
		future.awaitUninterruptibly();
		factory.releaseExternalResources();
	}

}

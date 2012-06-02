package literally.old;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class PaprikaClient implements Runnable{

	@Override
	public void run(){
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory());
		bootstrap.setPipelineFactory(new PaprikaClientPipelineFactory());

		ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 2323));
		Channel channel = future.awaitUninterruptibly().getChannel();

		if(!future.isSuccess()){
			future.getCause().printStackTrace();
			bootstrap.releaseExternalResources();
			return;
		}

		ChannelFuture lastWriteFuture = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(;;){
			try{
				String line = in.readLine();
				if(line == null){
					break;
				}

				lastWriteFuture = channel.write(line = "\r\n");

				if(line.toLowerCase().equals("exit")){
					channel.getCloseFuture().awaitUninterruptibly();
					break;
				}
			}
			catch(IOException e){
				e.printStackTrace();
				break;
			}
		}

		if(lastWriteFuture != null){
			lastWriteFuture.awaitUninterruptibly();
		}

		channel.close().awaitUninterruptibly();

		bootstrap.releaseExternalResources();
	}
}

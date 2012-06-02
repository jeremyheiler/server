package literally;

import literally.paprika.LifeCycle;
import literally.paprika.Server;
import literally.paprika.echo.EchoModule;

public class Main{
	public static void main(String[] args){
		final Server server = new Server();
		Runtime.getRuntime().addShutdownHook(new Thread(new LifeCycleStopper(server)));
		server.register(new EchoModule());
		server.start();
	}
}

class LifeCycleStopper implements Runnable{
	private final LifeCycle lifecycle;

	public LifeCycleStopper(LifeCycle lifecycle){
		this.lifecycle = lifecycle;
	}

	@Override
	public void run(){
		lifecycle.stop();
	}
}

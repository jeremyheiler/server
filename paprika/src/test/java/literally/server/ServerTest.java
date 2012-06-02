package literally.server;

import literally.paprika.Server;
import literally.paprika.Server.State;

import org.junit.Assert;
import org.junit.Test;

public class ServerTest{

	@Test
	public void testInitialize(){
		Server server = new Server();
		Assert.assertEquals(State.STOPPED, server.getState());
	}

	@Test
	public void testEmptyServerLifeCycle(){
		Server server = new Server();
		server.start();
		Assert.assertEquals(State.STARTED, server.getState());
		server.stop();
		Assert.assertEquals(State.STOPPED, server.getState());
	}

//	@Test
//	@Ignore
//	public void testName(){
//		FooModule foo = new FooModule();
//		Server server = new Server();
//		server.register(foo);
//	}
//
//	private class FooModule implements Module{
//
//		@Override
//		public String getName(){
//			return "Foo";
//		}
//
//		@Override
//		public void start(){
//		}
//
//		@Override
//		public void stop(){
//		}
//	}
}

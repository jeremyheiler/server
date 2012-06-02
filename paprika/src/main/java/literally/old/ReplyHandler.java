package literally.old;

import java.net.Socket;

public class ReplyHandler implements Runnable{
	private final Socket socket;
	
	public ReplyHandler(Socket socket){
		if(socket == null){
			throw new NullPointerException("socket is null");
		}
		this.socket = socket;
	}

	@Override
	public void run(){
	}
}

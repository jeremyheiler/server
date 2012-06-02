package literally.old;

import java.io.IOException;

import java.io.InputStreamReader;
import java.net.Socket;

public class CommandHandler implements Runnable{
	private final Socket socket;

	public CommandHandler(Socket socket){
		if(socket == null){
			throw new NullPointerException("socket is null");
		}
		this.socket = socket;
	}

	@Override
	public void run(){
		try{
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			char[] buf = new char[1024];
			int i = reader.read(buf);
			String command = new String(buf,0, i);
			System.out.println(command);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}

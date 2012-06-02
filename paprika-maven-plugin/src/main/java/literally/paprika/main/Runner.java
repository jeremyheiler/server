package literally.paprika.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Runner{
	private List<File> classpath = new ArrayList<File>();
	private int port;

	public Runner(){
		this(3000);
	}

	public Runner(int port){
		this.port = port;
	}

	public Runner setPort(int port){
		this.port = port;
		return this;
	}
	
	public Runner addClasspath(File dir){
		classpath.add(dir);
		return this;
	}

	public String execute() throws IOException{
		ProcessBuilder pb = new ProcessBuilder("java", "literally.paprika.main.Bootstrap", "-Dpaprika.port=" + port);
		try{
			Process p = pb.start();
			String status = read(p.getInputStream());
			if(!status.isEmpty()){
				return status;
			}
			String error = read(p.getErrorStream());
			if(!status.isEmpty()){
				return "999 " + error;
			}
		}
		catch(IllegalThreadStateException e){
		}
		return "000";
	}

	private String read(InputStream in) throws IOException{
		StringBuilder buf = new StringBuilder();
		int c = -1;
		while((c = in.read()) != 0){
			buf.append((char) c);
		}
		return buf.toString();
	}
}

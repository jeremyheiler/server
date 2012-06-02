package literally.server.demo;

import java.io.File;
import java.io.InputStream;

public class Main{
	public static void main(String[] args) throws Exception{
		try{
			ProcessBuilder pb = new ProcessBuilder("java", "literally.server.demo.Boot");
			pb.directory(new File("/home/jeremy/projects/literally/server-demo/target/classes"));
			
			Process p = pb.start();

			read("STDOUT", p.getInputStream());
			read("STDERR", p.getErrorStream());

			//Thread.sleep(5000);
			System.out.println("Child process has returned with exit value " + p.exitValue());
		}
		catch(IllegalThreadStateException e){
			System.out.println("Exited before child process finished.");
		}
	}
	
	public static void read(String label, InputStream in) throws Exception{
		System.out.println(label + ":");
		StringBuilder buf = new StringBuilder();
		int c = -1;
		while((c = in.read()) != 0){
			buf.append((char)c);
		}
		System.out.println(buf);
	}
}

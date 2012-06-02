package literally.server.demo;

public class Boot{
	public static void main(String[] args) throws Exception{
		System.out.print("200 OK");
		System.out.write(0);
		System.out.flush();
		System.err.write(0);
		System.err.flush();
		//Thread.sleep(5000);
	}
}

package literally.paprika;

public class Port{
	private final int port;

	public Port(int port){
		if(port < 0 || port > 65535){
			throw new IllegalPortException(port);
		}
		this.port = port;
	}
	
	public int get(){
		return port;
	}
	
	@Override
	public boolean equals(Object that){
		if(this == that){
			return true;
		}
		if(that instanceof Port){
			return ((Port) that).port == this.port;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 37 * 23 + port;
	}
}

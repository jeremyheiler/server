package literally.paprika;

@SuppressWarnings("serial")
public class IllegalPortException extends RuntimeException{

	public IllegalPortException(int port){
		super(Integer.toString(port));
	}
}

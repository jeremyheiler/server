package literally.paprika;

import java.util.HashSet;
import java.util.Set;

public class PortManager{
	private final Set<Port> aquiredPorts = new HashSet<>();

	public Port acquire(int port){
		Port p = new Port(port);
		if(aquiredPorts.add(p)){
			return p;
		}
		else{
			throw new RuntimeException("Port " + port + " already acquired.");
		}
	}

	public boolean isAvailable(int port){
		return !aquiredPorts.contains(new Port(port));
	}
}

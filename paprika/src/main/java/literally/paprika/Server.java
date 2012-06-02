package literally.paprika;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Server is a thread-safe container for managing the life cycle of server
 * modules.
 */
public final class Server implements LifeCycle{
	private static final Logger log = LoggerFactory.getLogger(Server.class);
	private final List<Module> modules = new ArrayList<>();

	// need to consider module start/stop order

	/***/
	public Server(){
	}

	protected volatile State state = State.STOPPED;

	/***/
	public enum State{
		STOPPED, STARTING, STARTED, STOPPING
	}

	/***/
	public final State getState(){
		return state;
	}

	/***/
	public final boolean is(State state){
		return this.state == state;
	}

	/***/
	public void register(Module module){
		modules.add(module);
	}

	/***/
	public void unregister(Module module){
		modules.remove(module);
	}

	/***/
	@Override
	public void start(){
		State state = getState();
		if(state == State.STARTED || state == State.STARTING){
			log.info("Server is already started or is being started by another thread.");
			return;
		}
		this.state = State.STARTING;
		log.info("Server is starting.");
		startModules();
		this.state = State.STARTED;
		log.info("Server has started.");
	}

	/***/
	@Override
	public void stop(){
		State state = getState();
		if(state == State.STOPPED || state == State.STOPPING){
			log.info("Server is already stopped or is being stopped by another thread.");
			return;
		}
		this.state = State.STOPPING;
		log.info("Server is stopping.");
		stopModules();
		this.state = State.STOPPED;
		log.info("Server has stopped.");
	}

	private void startModules(){
		for(Module module : modules){
			if(state == State.STOPPING){
				break;
			}
			try{
				log.info("Starting module " + module.getName());
				module.start();
				log.info("\tStarted.");
			}
			catch(Exception e){
				log.error("Error starting module " + module.getName() + ": " + e.getMessage(), e);
				// don't start the rest of the modules?
			}
		}
	}

	private void stopModules(){
		for(Module module : modules){
			try{
				log.info("Stopping module: " + module.getName());
				module.stop();
				log.info("\tStopped.");
			}
			catch(Exception e){
				log.info("Error stopping module : " + module.getName() + ": " + e.getMessage(), e);
				// maybe put modules in a failed shutdown queue?
			}
		}
	}
}

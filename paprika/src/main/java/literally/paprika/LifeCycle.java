package literally.paprika;

/**
 * This interface allows something to start and stop.
 * 
 * The name should be change, as LifeCycle really doesn't fit.
 */
public interface LifeCycle{

	public void start();

	public void stop();

}

package literally.paprika;

public interface Module extends LifeCycle{

	public String getName();

	public void register(PortManager manager);

	@Override
	public void start();

	@Override
	public void stop();

}

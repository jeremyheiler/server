package literally.server;

import literally.paprika.PortManager;

import org.junit.Assert;
import org.junit.Test;

public class PortManagerTest{

	@Test
	public void testAcquire(){
		PortManager pm = new PortManager();
		Assert.assertTrue(pm.isAvailable(23));
		pm.acquire(23);
		Assert.assertFalse(pm.isAvailable(23));
	}
}

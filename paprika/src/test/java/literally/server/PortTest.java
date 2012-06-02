package literally.server;

import literally.paprika.IllegalPortException;
import literally.paprika.Port;

import org.junit.Assert;
import org.junit.Test;

public class PortTest{

	@Test
	public void testGet(){
		Assert.assertEquals(23, new Port(23).get());
	}

	@Test
	public void testLowerBound(){
		try{
			new Port(0);
		}
		catch(IllegalPortException e){
			Assert.fail();
		}
	}

	@Test
	public void testUpperBound(){
		try{
			new Port(65535);
		}
		catch(IllegalPortException e){
			Assert.fail();
		}
	}

	@Test(expected = IllegalPortException.class)
	public void testNegativePort(){
		new Port(-1);
	}

	@Test(expected = IllegalPortException.class)
	public void testTooLargePort(){
		new Port(65536);
	}

	@Test
	public void testEqualsIdentity(){
		Port p = new Port(23);
		Assert.assertTrue(p.equals(p));
	}

	@Test
	public void testEqualsValue(){
		Port p = new Port(23);
		Port q = new Port(23);
		Assert.assertTrue(p.equals(q));
		Assert.assertTrue(q.equals(p));
	}

	@Test
	public void testNotEqualsValue(){
		Port p = new Port(23);
		Port q = new Port(24);
		Assert.assertFalse(p.equals(q));
		Assert.assertFalse(q.equals(p));
	}

	@Test
	public void testNotEqualsNonPort(){
		Port p = new Port(23);
		Assert.assertFalse(p.equals(new Object()));
	}

	@Test
	public void testEqualHashCode(){
		Port p = new Port(23);
		Port q = new Port(23);
		Assert.assertEquals(p.hashCode(), q.hashCode());
	}
}

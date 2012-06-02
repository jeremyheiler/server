package literally.paprika.main;

import org.junit.Assert;
import org.junit.Test;

public class JavaCommandTest{

	@Test
	public void testDefault(){
		Assert.assertEquals("java", new JavaCommandBuilder().toString());
	}

	@Test
	public void testMain(){
		Assert.assertEquals("java Main", new JavaCommandBuilder().setMain("Main").toString());
	}

	@Test
	public void testMainWithArg(){
		JavaCommandBuilder java = new JavaCommandBuilder().setMain("Main").addArgument("foo");
		Assert.assertEquals("java Main foo", java.toString());
	}

	@Test
	public void testMainWithTwoArgs(){
		JavaCommandBuilder java = new JavaCommandBuilder().setMain("Main").addArgument("foo").addArgument("bar");
		Assert.assertEquals("java Main foo bar", java.toString());
	}
	
	@Test
	public void testSingleDArg(){
		JavaCommandBuilder java = new JavaCommandBuilder().addD("foo", "bar").setMain("Baz");
		Assert.assertEquals("java -Dfoo=bar Baz", java.toString());
	}
	
	@Test
	public void testOneDirClasspath(){
		JavaCommandBuilder java = new JavaCommandBuilder().addClasspath("/foo/bar").setMain("Baz");
		Assert.assertEquals("java -cp /foo/bar Baz", java.toString());
	}
	
	@Test
	public void testTwoDirClasspath(){
		JavaCommandBuilder java = new JavaCommandBuilder().addClasspath("/foo/bar").addClasspath("/boo/goo").setMain("Baz");
		Assert.assertEquals("java -cp /foo/bar:/boo/goo Baz", java.toString());
	}
}

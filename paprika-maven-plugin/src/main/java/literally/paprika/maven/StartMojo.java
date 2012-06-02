package literally.paprika.maven;

import java.io.File;
import literally.paprika.main.JavaCommandBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Goal which starts a Paprika server.
 * 
 * @goal start
 * @phase process-sources
 */
public class StartMojo extends AbstractMojo{

	/**
	 * The enclosing project.
	 * 
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

	/**
	 * Port to run the Paprika server on.
	 * 
	 * @parameter default-value="3000"
	 * @required
	 */
	private int port;

	/**
	 * @parameter default-value="${basedir}"
	 * @required
	 * @readonly
	 */
	private File basedir;

	public void execute() throws MojoExecutionException{
		JavaCommandBuilder command = new JavaCommandBuilder();
		command.setMain("literally.paprika.main.Boostrap");
		
		//try{
			//List<?> runtime = project.getRuntimeArtifacts();
			String outDir = project.getBuild().getOutputDirectory();
			getLog().info(outDir);
			//new Runner(port).addClasspath(new File(outDir)).execute();
		//}
		//catch(IOException e){
		//	throw new MojoExecutionException("Failed to run process.", e);
		//}
	}
}

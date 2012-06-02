package literally.paprika.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JavaCommandBuilder{
	private final Map<String, String> dArgs = new HashMap<String, String>();
	private final List<String> classpath = new ArrayList<String>();
	private final List<String> arguments = new ArrayList<String>();
	private String main;

	protected List<String> build(){
		List<String> command = new ArrayList<String>();
		command.add("java");
		for(Map.Entry<String, String> entry : dArgs.entrySet()){
			command.add("-D" + entry.getKey() + "=" + entry.getValue());
		}
		if(!classpath.isEmpty()){
			command.add("-cp");
			StringBuilder cp = new StringBuilder();
			boolean first = true;
			for(String element : classpath){
				if(first){
					first = false;
				}
				else{
					cp.append(":");
				}
				cp.append(element);
			}
			command.add(cp.toString());
		}
		if(main != null){
			command.add(main);
			for(String arg : arguments){
				command.add(arg);
			}
		}
		return command;
	}

	public JavaCommandBuilder setMain(String main){
		this.main = main;
		return this;
	}

	public JavaCommandBuilder addArgument(String arg){
		arguments.add(arg);
		return this;
	}

	public JavaCommandBuilder addD(String key, Object value){
		dArgs.put(key, value.toString());
		return this;
	}

	public JavaCommandBuilder addClasspath(String path){
		classpath.add(path);
		return this;
	}

	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder();
		Iterator<String> command = build().iterator();
		if(command.hasNext()){
			buf.append(command.next());
			while(command.hasNext()){
				buf.append(" ").append(command.next());
			}
		}
		return buf.toString();
	}
}

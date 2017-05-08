package activiti_maven_project.com.git.common.exception;

@SuppressWarnings("serial")
public class BpmException extends Exception{

	public BpmException() {
		super();
	}
	
	public BpmException(String message) {
		super(message);
	}
	
	public BpmException(String message,Throwable cause) {
		super(message,cause);
	}
	
}

package jworldgen.exceptionHandler;

public class JWorldGenException extends Exception {

	protected String exceptionMessage;
	
	public final String getMessage()
	{
		return this.exceptionMessage;
	}
	
	public JWorldGenException()
	{
		exceptionMessage = "Unknown Exception!";
	}
	
	private static final long serialVersionUID = 3720690278181366738L;

}

package descryptor.jworldgen.exceptions;

public class NoWorldException extends JWorldGenException {
	
	private static final long serialVersionUID = 6803552351595444247L;
	
	public NoWorldException()
	{
		exceptionMessage = "No world area defined!";
	}
}

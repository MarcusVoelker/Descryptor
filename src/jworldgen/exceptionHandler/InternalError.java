package jworldgen.exceptionHandler;

public class InternalError extends JWorldGenException {

	private static final long serialVersionUID = -7914341479957751230L;

	public InternalError(String name)
	{
		exceptionMessage = "Internal Error: "+name;
	}
}

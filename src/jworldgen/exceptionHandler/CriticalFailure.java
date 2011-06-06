package jworldgen.exceptionHandler;

public class CriticalFailure extends Exception {
	
	protected String exceptionMessage;
	
	public final String getMessage()
	{
		return this.exceptionMessage;
	}
	
	private static final long serialVersionUID = 1165938295399529885L;
	
	public CriticalFailure(String name)
	{
		exceptionMessage = "Critical Failure in JWorldGen: "+name;
	}
}

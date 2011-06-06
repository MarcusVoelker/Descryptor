package jworldgen.exceptionHandler;

public class UnknownIdentifier extends JWorldGenException {
	
	private static final long serialVersionUID = 236437642236627258L;

	public UnknownIdentifier(String name)
	{
		exceptionMessage = "Unknown identifer: "+name;
	}
}

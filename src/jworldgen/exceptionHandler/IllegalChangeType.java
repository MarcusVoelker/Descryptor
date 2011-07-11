package jworldgen.exceptionHandler;

public class IllegalChangeType extends JWorldGenException {
	
	private static final long serialVersionUID = 236437642236627258L;

	public IllegalChangeType(String name)
	{
		exceptionMessage = "Illegal Change Type: "+name;
	}
}

package jworldgen.exceptionHandler;

public class IllegalModifierType extends JWorldGenException {
	
	private static final long serialVersionUID = 236437642236627258L;

	public IllegalModifierType(String name)
	{
		exceptionMessage = "Illegal Modifier Type: "+name;
	}
}

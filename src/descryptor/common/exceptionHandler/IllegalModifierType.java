package descryptor.common.exceptionHandler;

import descryptor.jworldgen.exceptions.JWorldGenException;

public class IllegalModifierType extends JWorldGenException {
	
	private static final long serialVersionUID = 236437642236627258L;

	public IllegalModifierType(String name)
	{
		exceptionMessage = "Illegal Modifier Type: "+name;
	}
}

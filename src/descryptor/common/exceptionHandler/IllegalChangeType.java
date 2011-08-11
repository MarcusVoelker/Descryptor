package descryptor.common.exceptionHandler;

import descryptor.jworldgen.exceptions.JWorldGenException;

public class IllegalChangeType extends JWorldGenException {
	
	private static final long serialVersionUID = 236437642236627258L;

	public IllegalChangeType(String name)
	{
		exceptionMessage = "Illegal Change Type: "+name;
	}
}

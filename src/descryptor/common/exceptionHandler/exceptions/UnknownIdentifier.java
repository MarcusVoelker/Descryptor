package descryptor.common.exceptionHandler.exceptions;

import descryptor.jworldgen.exceptions.JWorldGenException;

public class UnknownIdentifier extends JWorldGenException {
	
	private static final long serialVersionUID = 236437642236627258L;

	public UnknownIdentifier(String name)
	{
		exceptionMessage = "Unknown identifer: "+name;
	}
}

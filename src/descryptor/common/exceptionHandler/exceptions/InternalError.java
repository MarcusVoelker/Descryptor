package descryptor.common.exceptionHandler.exceptions;

import descryptor.jworldgen.exceptions.JWorldGenException;

public class InternalError extends JWorldGenException {

	private static final long serialVersionUID = -7914341479957751230L;

	public InternalError(String name)
	{
		exceptionMessage = "Internal Error: "+name;
	}
}

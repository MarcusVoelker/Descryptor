package descryptor.common.exceptionHandler.exceptions;

import descryptor.jworldgen.exceptions.JWorldGenException;

public class RecursionException extends JWorldGenException {

	private static final long serialVersionUID = -3645009011805386025L;
	
	public RecursionException()
	{
		exceptionMessage = "Infinite recursion detected!";
	}
}

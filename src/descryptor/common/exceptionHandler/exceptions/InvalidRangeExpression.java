package descryptor.common.exceptionHandler.exceptions;

import descryptor.jworldgen.exceptions.JWorldGenException;

public class InvalidRangeExpression extends JWorldGenException{

	private static final long serialVersionUID = -282329711150723796L;
	public InvalidRangeExpression()
	{
		exceptionMessage = "Invalid Range Expression!";
	}
}

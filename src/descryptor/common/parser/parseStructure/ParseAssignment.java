package descryptor.common.parser.parseStructure;

import descryptor.common.ale.Assignment;
import descryptor.common.parser.ParseObject;

public class ParseAssignment extends ParseObject{
	private String identifier;
	private ParseALE expression;
	
	public ParseAssignment(String identifier, ParseALE expression)
	{
		this.identifier = identifier;
		this.expression = expression;
	}
	
	public Assignment toAssignment()
	{
		return new Assignment(identifier,expression.toALE());
	}
}
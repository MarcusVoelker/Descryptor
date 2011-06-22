package jworldgen.parser.parseStructure;

import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;

public class ParseAssignment {
	private String identifier;
	private ParseALE expression;
	
	public ParseAssignment(String identifier, ParseALE expression)
	{
		this.identifier = identifier;
		this.expression = expression;
	}
	
	public void evaluate(RNG rng, VariableResolver resolver)
	{
		resolver.setVariable(identifier, expression.evaluate(rng, resolver));
	}
	
	public String toString()
	{
		return identifier + " = " + expression.toString();
	}
}

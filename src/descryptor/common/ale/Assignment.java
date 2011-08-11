package descryptor.common.ale;

import descryptor.common.RNG;

public class Assignment {
	private String identifier;
	private ALE expression;
	
	public Assignment(String identifier, ALE expression)
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

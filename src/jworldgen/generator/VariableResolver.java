package jworldgen.generator;

import java.util.Hashtable;

public class VariableResolver {
	private Hashtable<String,Number> varTable;
	
	public VariableResolver()
	{
		varTable = new Hashtable<String,Number>();
	}
	
	public void setVariable(String identifier, Number value)
	{
		varTable.put(identifier, value);
	}
	
	public Number getVariable(String identifier)
	{
		return varTable.get(identifier);
	}
	
	public boolean isDefined (String identifier)
	{
		return varTable.containsKey(identifier);
	}
}

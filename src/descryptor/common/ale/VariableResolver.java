package descryptor.common.ale;

import java.util.Hashtable;


public class VariableResolver {
	private Hashtable<String,Number> varTable;
	private Hashtable<String,Evaluatable> methods;
	
	public VariableResolver()
	{
		varTable = new Hashtable<String,Number>();
		methods = new Hashtable<String,Evaluatable>();
	}
	
	private VariableResolver(Hashtable<String,Number> varTable, Hashtable<String,Evaluatable> methods)
	{
		this.varTable = varTable;
		this.methods = methods;
	}
	
	public void setVariable(String identifier, Number value)
	{
		varTable.put(identifier, value);
	}
	
	public void addFunction(String identifier, Evaluatable method)
	{
		methods.put(identifier, method);
	}
	public Number getVariable(String identifier)
	{
		if (isDefined(identifier))
			return varTable.get(identifier);
		return 0;
	}
	
	public boolean isDefined(String identifier)
	{
		return varTable.containsKey(identifier);
	}
	
	public Number evaluateFunction(String identifier, Number[] values)
	{
		Evaluatable method = methods.get(identifier);
		return method.getValue(values);
	}
	
	@SuppressWarnings("unchecked")
	public VariableResolver clone()
	{
		return new VariableResolver((Hashtable<String, Number>) varTable.clone(),methods);
	}
}

package jworldgen.generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;

public class VariableResolver {
	private Hashtable<String,Number> varTable;
	private Hashtable<String,Method> methods;
	private Hashtable<String,Object> methodOwners;
	
	public VariableResolver()
	{
		varTable = new Hashtable<String,Number>();
		methods = new Hashtable<String,Method>();
		methodOwners = new Hashtable<String,Object>();
	}
	
	private VariableResolver(Hashtable<String,Number> varTable, Hashtable<String,Method> methods, Hashtable<String,Object> methodOwners)
	{
		this.varTable = varTable;
		this.methods = methods;
		this.methodOwners = methodOwners;
	}
	
	public void setVariable(String identifier, Number value)
	{
		varTable.put(identifier, value);
	}
	
	public void addFunction(String identifier, Method method, Object owner)
	{
		methods.put(identifier, method);
		methodOwners.put(identifier, owner);
	}
	public Number getVariable(String identifier)
	{
		return varTable.get(identifier);
	}
	
	public boolean isDefined (String identifier)
	{
		return varTable.containsKey(identifier);
	}
	
	public Number evaluateFunction(String identifier, Number[] values)
	{
		Method method = methods.get(identifier);
		try {
			try {
				return (Number) method.invoke(methodOwners.get(identifier),(Object[]) values);
			} catch (IllegalArgumentException e) {
				ExceptionLogger.logException(new InternalError("Reflection Error!"),LoggerLevel.ERROR);
			} catch (IllegalAccessException e) {
				ExceptionLogger.logException(new InternalError("Reflection Error!"),LoggerLevel.ERROR);
			} catch (InvocationTargetException e) {
				ExceptionLogger.logException(new InternalError("Reflection Error: "+e.getCause().getMessage()),LoggerLevel.ERROR);
			}
		} catch (CriticalFailure e1) {
			//Should Not Be Reachable
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public VariableResolver clone()
	{
		return new VariableResolver((Hashtable<String, Number>) varTable.clone(),methods,methodOwners);
	}
}

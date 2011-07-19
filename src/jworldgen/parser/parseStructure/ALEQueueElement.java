package jworldgen.parser.parseStructure;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;

public class ALEQueueElement {
	public String identifier;
	public ALEElementType type;
	
	public ALEQueueElement(String identifier, ALEElementType type)
	{
		this.identifier = identifier;
		this.type = type;
	}
	
	private Float toFloat(Number v, RNG rng)
	{
		switch (type)
		{
		case SINE:
			return (float) Math.sin(v.floatValue());
		case COSINE:
			return (float) Math.cos(v.floatValue());
		case TANGENT:
			return (float) Math.tan(v.floatValue());
		case ARCSINE:
			return (float) Math.asin(v.floatValue());
		case ARCCOSINE:
			return (float) Math.acos(v.floatValue());
		case ARCTANGENT:
			return (float) Math.atan(v.floatValue());
		case SQRT:
			return (float) Math.sqrt(v.floatValue());
		case ABSOLUTE:
			return Math.abs(v.floatValue());
		}
		return null;
	}
	
	private Integer toInt(Number v, RNG rng)
	{
		switch (type)
		{
		case ABSOLUTE:
			return Math.abs(v.intValue());
		case NOT:
			return v.intValue() == 0 ? 1 : 0;
		case BITWISE_NOT:
			return ~v.intValue();
		}
		return null;
	}
	
	private Float toFloat(Number v1, Number v2, RNG rng)
	{
		switch (type)
		{
		case PLUS:
			return v1.floatValue()+v2.floatValue();
		case MINUS:
			return v1.floatValue()-v2.floatValue();
		case MULTIPLY:
			return v1.floatValue()*v2.floatValue();
		case DIVIDE:
			return v1.floatValue()/v2.floatValue();
		case RANDOM:
			return rng.nextFloat(v1.floatValue(), v2.floatValue());
		}
		return null;
	}
	
	private Integer toInt(Number v1, Number v2, RNG rng)
	{
		switch (type)
		{
		case PLUS:
			return v1.intValue()+v2.intValue();
		case MINUS:
			return v1.intValue()-v2.intValue();
		case MULTIPLY:
			return v1.intValue()*v2.intValue();
		case DIVIDE:
			return v1.intValue()/v2.intValue();
		case MODULO:
			return v1.intValue()/v2.intValue();
		case RANDOM:
			return rng.nextInt(v1.intValue(), v2.intValue());
		case EQUALS:
			return v1.floatValue() == v2.floatValue()?1:0;
		case NEQUALS:
			return v1.floatValue() != v2.floatValue()?1:0;
		case GREATER:
			return v1.floatValue() > v2.floatValue()?1:0;
		case GOE:
			return v1.floatValue() >= v2.floatValue()?1:0;
		case LESS:
			return v1.floatValue() < v2.floatValue()?1:0;
		case LOE:
			return v1.floatValue() <= v2.floatValue()?1:0;
		case AND:
			return (v1.intValue() != 0 && v2.intValue() != 0) ? 1 : 0;
		case OR:
			return (v1.intValue() != 0 || v2.intValue() != 0) ? 1 : 0;
		case BITWISE_AND:
			return v1.intValue() & v2.intValue();
		case BITWISE_OR:
			return v1.intValue() | v2.intValue();
		}
		return null;
	}
	
	public Number toValue(RNG rng, VariableResolver resolver)
	{
		switch (type)
		{
			case INTEGER:
				return Integer.parseInt(identifier);
			case FLOAT:
				return Float.parseFloat(identifier);
			case VARIABLE:
				return resolver.getVariable(identifier);
		}
		return null;
	}
	
	public Number toValue(Number v, RNG rng)
	{
		switch(type.returnType)
		{
		case ALWAYS_FLOAT:
			return toFloat(v, rng);
		case ALWAYS_INT:
			return toInt(v, rng);
		case INPUT:
			if (v instanceof Integer)
				return toInt(v, rng);
			return toFloat(v, rng);
		default:
			try {
				ExceptionLogger.logException(new InternalError("Invalid return type in ALEElement"), LoggerLevel.ERROR);
			} catch (CriticalFailure e) {
				//Should not be reachable
			}
			return 0;
		}
				
	}
	
	public Number toValue(Number v1, Number v2, RNG rng)
	{
		switch(type.returnType)
		{
		case ALWAYS_FLOAT:
			return toFloat(v1, v2, rng);
		case ALWAYS_INT:
			return toInt(v1, v2, rng);
		case INPUT:
			if ((v1 instanceof Integer) && (v2 instanceof Integer))
			{
					return toInt(v1, v2, rng);
			} else {
					return toFloat(v1, v2, rng);
			}
		default:
			try {
				ExceptionLogger.logException(new InternalError("Invalid return type in ALEElement"), LoggerLevel.ERROR);
			} catch (CriticalFailure e) {
				//Should not be reachable
			}
			return 0;
		}
	}
}

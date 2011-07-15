package jworldgen.parser.parseStructure;

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
	
	private Float toFloat(Float v, RNG rng)
	{
		switch (type)
		{
		case SINE:
			return (float) Math.sin(v);
		case COSINE:
			return (float) Math.cos(v);
		case TANGENT:
			return (float) Math.tan(v);
		case ARCSINE:
			return (float) Math.asin(v);
		case ARCCOSINE:
			return (float) Math.acos(v);
		case ARCTANGENT:
			return (float) Math.atan(v);
		case SQRT:
			return (float) Math.sqrt(v);
		case ABSOLUTE:
			return (float) Math.abs(v);
		}
		return null;
	}
	
	private Float toFloat(Float v1,Float v2, RNG rng)
	{
		switch (type)
		{
		case PLUS:
			return v1+v2;
		case MINUS:
			return v1-v2;
		case MULTIPLY:
			return v1*v2;
		case DIVIDE:
			return v1/v2;
		case RANDOM:
			return rng.nextFloat(v1, v2);
		}
		return null;
	}
	
	private Integer toInt(Integer v1, Integer v2, RNG rng)
	{
		switch (type)
		{
		case PLUS:
			return v1+v2;
		case MINUS:
			return v1-v2;
		case MULTIPLY:
			return v1*v2;
		case DIVIDE:
			return v1/v2;
		case RANDOM:
			return rng.nextInt(v1, v2);
		}
		return null;
	}
	
	public Number toValue(Number v, RNG rng)
	{
		return toFloat(v.floatValue(), rng);
	}
	
	public Number toValue(Number v1, Number v2, RNG rng)
	{
		if ((v1 instanceof Integer) && (v2 instanceof Integer))
		{
				return toInt(v1.intValue(),v2.intValue(), rng);
		} else {
				return toFloat(v1.floatValue(),v2.floatValue(), rng);
		}
	}
}

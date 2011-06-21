package jworldgen.parser.parseStructure;

import jworldgen.generator.RNG;

public class ALEQueueElement {
	public String identifier;
	public ALEElementType type;
	
	public ALEQueueElement(String identifier, ALEElementType type)
	{
		this.identifier = identifier;
		this.type = type;
	}
	
	public Number toValue(ALEElementType resultType, RNG rng)
	{
		switch (resultType)
		{
			case INTEGER:
				return Integer.parseInt(identifier);
			case FLOAT:
				return Float.parseFloat(identifier);
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
	public Number toValue(Number v1, Number v2, ALEElementType resultType, RNG rng)
	{
		switch (resultType)
		{
			case INTEGER:
				return toInt((Integer) v1,(Integer) v2, rng);
			case FLOAT:
				return toFloat((Float) v1,(Float) v2, rng);
		}
		return null;
	}
}

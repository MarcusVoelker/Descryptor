package descryptor.common.parser.parseStructure;

import java.util.ArrayList;

import descryptor.common.ale.Assignment;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.IllegalModifierType;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.generator.modifiers.MetaballModifier;
import descryptor.common.generator.modifiers.Modifier;
import descryptor.common.generator.modifiers.PerlinModifier;
import descryptor.common.generator.modifiers.VoronoiModifier;
import descryptor.common.generator.modifiers.WeightedPerlinModifier;
import descryptor.common.generator.modifiers.WorleyModifier;
import descryptor.common.parser.ParseObject;


public class ParseModifier extends ParseObject{
	public ArrayList<ParseAssignment> assignments;
	
	private String identifier;
	private String type;
	
	public ParseModifier()
	{
		assignments = new ArrayList<ParseAssignment>();
	}
	
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public Modifier toModifier()
	{
		ArrayList<Assignment> realAssignments = new ArrayList<Assignment>();
		for (ParseAssignment pa : assignments)
		{
			realAssignments.add(pa.toAssignment());
		}
		if (type.equals("Perlin"))
			return new PerlinModifier(identifier,realAssignments);
		if (type.equals("WeightedPerlin"))
			return new WeightedPerlinModifier(identifier,realAssignments);
		if (type.equals("Metaball"))
			return new MetaballModifier(identifier,realAssignments);
		if (type.equals("Voronoi"))
			return new VoronoiModifier(identifier,realAssignments);
		if (type.equals("Worley"))
			return new WorleyModifier(identifier,realAssignments);
		try {
			ExceptionLogger.logException(new IllegalModifierType(type), LoggerLevel.ERROR);
		} catch (CriticalFailure e) {
			//Should not be reachable
		}
		return null;
	}
}

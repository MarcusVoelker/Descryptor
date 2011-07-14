package jworldgen.parser.parseStructure;

import java.util.ArrayList;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.IllegalModifierType;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.worldStructure.modifiers.MetaballModifier;
import jworldgen.generator.worldStructure.modifiers.Modifier;
import jworldgen.generator.worldStructure.modifiers.PerlinModifier;
import jworldgen.generator.worldStructure.modifiers.VoronoiModifier;
import jworldgen.generator.worldStructure.modifiers.WeightedPerlinModifier;
import jworldgen.generator.worldStructure.modifiers.WorleyModifier;

public class ParseModifier {
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
			
		if (type.equals("Perlin"))
			return new PerlinModifier(identifier,assignments);
		if (type.equals("WeightedPerlin"))
			return new WeightedPerlinModifier(identifier,assignments);
		if (type.equals("Metaball"))
			return new MetaballModifier(identifier,assignments);
		if (type.equals("Voronoi"))
			return new VoronoiModifier(identifier,assignments);
		if (type.equals("Worley"))
			return new WorleyModifier(identifier,assignments);
		try {
			ExceptionLogger.logException(new IllegalModifierType(type), LoggerLevel.ERROR);
		} catch (CriticalFailure e) {
			//Should not be reachable
		}
		return null;
	}
}

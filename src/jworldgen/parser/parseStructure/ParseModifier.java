package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.IllegalChangeType;
import jworldgen.exceptionHandler.IllegalModifierType;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.worldStructure.modifiers.ChangeType;
import jworldgen.generator.worldStructure.modifiers.MetaballModifier;
import jworldgen.generator.worldStructure.modifiers.Modifier;
import jworldgen.generator.worldStructure.modifiers.PerlinModifier;
import jworldgen.generator.worldStructure.modifiers.WeightedPerlinModifier;
import jworldgen.generator.worldStructure.modifiers.VoronoiModifier;

public class ParseModifier {
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,String> types;
	private Hashtable<Integer,Integer> typeIDs;
	
	public ArrayList<ParseAssignment> assignments;
	
	private String identifier;
	private String type;
	private String changeType;
	
	public ParseModifier()
	{
		probabilities = new Hashtable<Integer,Integer>();
		types = new Hashtable<Integer,String>();
		typeIDs = new Hashtable<Integer,Integer>();
		assignments = new ArrayList<ParseAssignment>();
	}
	public void addProb(int value, int id)
	{
		probabilities.put(id, value);
	}
	
	public void addType(String type, int id)
	{
		types.put(id, type);
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
	
	public void setChangeType(String changeType)
	{
		this.changeType = changeType;
	}
	
	public void insertBlockIDs(BlockMap blockmap)
	{
		for (Enumeration<Integer> e = types.keys(); e.hasMoreElements();)
		{
			Integer id = e.nextElement();
			String tileType = types.get(id);
			typeIDs.put(id, blockmap.registerBlock(tileType));
		}
	}
	
	public Modifier toModifier()
	{
		ChangeType chType;
		if (changeType.equals("Modify"))
			chType = ChangeType.MODIFY;
		else if (changeType.equals("Stack"))
			chType = ChangeType.STACK;
		else {
			try {
				ExceptionLogger.logException(new IllegalChangeType(type), LoggerLevel.ERROR);
			} catch (CriticalFailure e) {
				//Should not be reachable
			} finally {
				chType = ChangeType.MODIFY;
			}
		}
			
		if (type.equals("Perlin"))
			return new PerlinModifier(probabilities,typeIDs,identifier,assignments,chType);
		if (type.equals("WeightedPerlin"))
			return new WeightedPerlinModifier(probabilities,typeIDs,identifier,assignments,chType);
		if (type.equals("Metaball"))
			return new MetaballModifier(typeIDs.get(0),identifier,assignments,chType);
		if (type.equals("Voronoi"))
			return new VoronoiModifier(probabilities,typeIDs,identifier,assignments,chType);
		try {
			ExceptionLogger.logException(new IllegalModifierType(type), LoggerLevel.ERROR);
		} catch (CriticalFailure e) {
			//Should not be reachable
		}
		return null;
	}
}

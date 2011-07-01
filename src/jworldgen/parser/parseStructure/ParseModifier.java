package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.IllegalModifierType;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.worldStructure.modifiers.Modifier;
import jworldgen.generator.worldStructure.modifiers.PerlinModifier;

public class ParseModifier {
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,String> types;
	private Hashtable<Integer,Integer> typeIDs;
	
	public ArrayList<ParseAssignment> assignments;
	
	private String identifier;
	private String type;
	
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
		if (type.equals("Perlin"))
			return new PerlinModifier(probabilities,typeIDs,identifier,assignments);
		try {
			ExceptionLogger.logException(new IllegalModifierType(type), LoggerLevel.ERROR);
		} catch (CriticalFailure e) {
			//Should not be reachable
		}
		return null;
	}
}

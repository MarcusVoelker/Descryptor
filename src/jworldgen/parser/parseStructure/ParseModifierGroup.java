package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.IllegalChangeType;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.worldStructure.ModifierGroup;
import jworldgen.generator.worldStructure.modifiers.ChangeType;

public class ParseModifierGroup {
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,String> types;
	private ArrayList<String> modifiers;
	private Hashtable<Integer,Integer> typeIDs;
	
	public ArrayList<ParseAssignment> assignments;
	
	private String identifier;
	private String changeType;
	
	public ParseModifierGroup()
	{
		probabilities = new Hashtable<Integer,Integer>();
		types = new Hashtable<Integer,String>();
		typeIDs = new Hashtable<Integer,Integer>();
		assignments = new ArrayList<ParseAssignment>();
		modifiers = new ArrayList<String>();
	}
	public void addProb(int value, int id)
	{
		probabilities.put(id, value);
	}
	
	public void addType(String type, int id)
	{
		types.put(id, type);
	}
	
	public void addModifier(String name)
	{
		modifiers.add(name);
	}
	
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	public String getIdentifier()
	{
		return identifier;
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
	
	public ModifierGroup toModifierGroup()
	{
		ChangeType chType;
		if (changeType.equals("Modify"))
			chType = ChangeType.MODIFY;
		else if (changeType.equals("Stack"))
			chType = ChangeType.STACK;
		else
		{
			try {
				ExceptionLogger.logException(new IllegalChangeType(changeType), LoggerLevel.ERROR);
			} catch (CriticalFailure e) {
				// Should never be reached
			} finally {
				chType = ChangeType.MODIFY;
			}
		}
		return new ModifierGroup(identifier, assignments, probabilities, typeIDs, modifiers, chType);
	}
}

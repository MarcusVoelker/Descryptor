package jworldgen.parser.parseStructure;

import java.util.ArrayList;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.IllegalChangeType;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.worldStructure.ModifierGroup;
import jworldgen.generator.worldStructure.modifiers.ChangeType;

public class ParseModifierGroup {
	private ArrayList<ParseALE> drawConstraints;
	private ArrayList<String> types;
	private ArrayList<String> modifiers;
	private ArrayList<Integer> typeIDs;
	
	public ArrayList<ParseAssignment> assignments;
	
	private String identifier;
	private String changeType;
	
	public ParseModifierGroup()
	{
		drawConstraints = new ArrayList<ParseALE>();
		types = new ArrayList<String>();
		typeIDs = new ArrayList<Integer>();
		assignments = new ArrayList<ParseAssignment>();
		modifiers = new ArrayList<String>();
	}
	public void addType(ParseALE ale, String type)
	{
		drawConstraints.add(ale);
		types.add(type);
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
		for (String type : types)
		{
			typeIDs.add(blockmap.registerBlock(type));
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
		return new ModifierGroup(identifier, assignments, drawConstraints, typeIDs, modifiers, chType);
	}
}

package descryptor.jworldgen.parser.parseStructure;

import java.util.ArrayList;

import descryptor.common.ale.ALE;
import descryptor.common.ale.Assignment;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.IllegalChangeType;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.generator.modifiers.ChangeType;
import descryptor.common.parser.ParseObject;
import descryptor.common.parser.parseStructure.ParseAssignment;
import descryptor.jworldgen.generator.ModifierGroup;


public class ParseModifierGroup extends ParseObject{
	protected ArrayList<ALE> drawConstraints;
	protected ArrayList<String> types;
	protected ArrayList<String> modifiers;
	protected ArrayList<Integer> typeIDs;
	
	public ArrayList<ParseAssignment> assignments;
	
	protected String identifier;
	protected String changeType;
	
	public ParseModifierGroup()
	{
		drawConstraints = new ArrayList<ALE>();
		types = new ArrayList<String>();
		typeIDs = new ArrayList<Integer>();
		assignments = new ArrayList<ParseAssignment>();
		modifiers = new ArrayList<String>();
	}
	public void addType(ALE ale, String type)
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
		ArrayList<Assignment> realAssignments = new ArrayList<Assignment>();
		for (ParseAssignment pa : assignments)
		{
			realAssignments.add(pa.toAssignment());
		}
		return new ModifierGroup(identifier, realAssignments, drawConstraints, typeIDs, modifiers, chType);
	}
}

package descryptor.jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Hashtable;

import descryptor.common.generator.modifiers.Modifier;
import descryptor.common.parser.parseStructure.ParseModifier;
import descryptor.jworldgen.generator.ModifierGroup;
import descryptor.jworldgen.generator.worldStructure.TreeNodeArea;


public class ParseList {
	private ArrayList<ParseArea> areas;
	private ArrayList<ParseModifier> modifiers;
	private ArrayList<ParseModifierGroup> modifierGroups;
	
	public ParseList()
	{
		areas = new ArrayList<ParseArea>();
		modifiers = new ArrayList<ParseModifier>();
		modifierGroups = new ArrayList<ParseModifierGroup>();
	}
	
	
	public void addArea(ParseArea area)
	{
		areas.add(area);
	}
	
	public void addModifier(ParseModifier modifier)
	{
		modifiers.add(modifier);
	}
	
	public void addModifierGroup(ParseModifierGroup modifierGroup)
	{
		modifierGroups.add(modifierGroup);
	}
	
	public void insertBlockIDs(BlockMap blockmap)
	{
		for(ParseArea area : areas)
		{
			area.insertBlockID(blockmap);
		}
		for(ParseModifierGroup modifierGroup : modifierGroups)
		{
			modifierGroup.insertBlockIDs(blockmap);
		}
	}
	
	public Hashtable<String,TreeNodeArea> createAreaNodes()
	{
		Hashtable<String,TreeNodeArea> areaNodes = new Hashtable<String,TreeNodeArea>();
		for (ParseArea area : areas)
		{
			areaNodes.put(area.getIdentifier(), area.toAreaNode());
		}
		return areaNodes;
	}
	
	public Hashtable<String,Modifier> createModifiers()
	{
		Hashtable<String,Modifier> modifierNodes = new Hashtable<String,Modifier>();
		for (ParseModifier modifier : modifiers)
		{
			modifierNodes.put(modifier.getIdentifier(), modifier.toModifier());
		}
		return modifierNodes;
	}
	
	public Hashtable<String,ModifierGroup> createModifierGroups()
	{
		Hashtable<String,ModifierGroup> modifierGroupNodes = new Hashtable<String,ModifierGroup>();
		for (ParseModifierGroup modifierGroup : modifierGroups)
		{
			modifierGroupNodes.put(modifierGroup.getIdentifier(), modifierGroup.toModifierGroup());
		}
		return modifierGroupNodes;
	}
}

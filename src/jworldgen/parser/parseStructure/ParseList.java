package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Hashtable;

import jworldgen.generator.worldStructure.ModifierGroup;
import jworldgen.generator.worldStructure.TreeNodeArea;
import jworldgen.generator.worldStructure.modifiers.Modifier;

public class ParseList {
	private ArrayList<ParseArea> areas;
	private ArrayList<ParseModifier> modifiers;
	private ArrayList<ParseModifierGroup> modifierGroups;
	private ArrayList<ParseKind> kinds;
	public ParseList()
	{
		areas = new ArrayList<ParseArea>();
		modifiers = new ArrayList<ParseModifier>();
		modifierGroups = new ArrayList<ParseModifierGroup>();
		kinds = new ArrayList<ParseKind>();
	}
	
	
	public void addArea(ParseArea area)
	{
		areas.add(area);
		kinds.add(ParseKind.AREA);
	}
	
	public void addModifier(ParseModifier modifier)
	{
		modifiers.add(modifier);
		kinds.add(ParseKind.MODIFIER);
	}
	
	public void addModifierGroup(ParseModifierGroup modifierGroup)
	{
		modifierGroups.add(modifierGroup);
		kinds.add(ParseKind.MODIFIER_GROUP);
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

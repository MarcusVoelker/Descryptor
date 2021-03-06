package descryptor.jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import descryptor.common.RNG;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.exceptionHandler.exceptions.UnknownIdentifier;
import descryptor.common.generator.modifiers.Modifier;
import descryptor.jworldgen.exceptions.NoWorldException;
import descryptor.jworldgen.generator.ModifierGroup;
import descryptor.jworldgen.parser.parseStructure.BlockMap;
import descryptor.jworldgen.parser.parseStructure.ParseList;


public class Ruleset 
{
	private BlockMap blockMap;
	private TreeNodeArea world;
	
	public Ruleset(ParseList list) throws CriticalFailure
	{
		blockMap = new BlockMap();
		createFromParseList(list);
		ExceptionLogger.log("Ruleset successfully created", LoggerLevel.COARSE);
	}
	
	private void createFromParseList(ParseList list) throws CriticalFailure
	{
		list.insertBlockIDs(blockMap);
		Hashtable<String,TreeNodeArea> areas = list.createAreaNodes();
		Hashtable<String,Modifier> modifiers = list.createModifiers();	
		Hashtable<String,ModifierGroup> modifierGroups = list.createModifierGroups();	
		for (Enumeration<String> e = areas.keys(); e.hasMoreElements();)
		{
			String areaName = e.nextElement();
			ArrayList<String> modNames = areas.get(areaName).getModifierGroupNames();
			for (String name : modNames)
			{
				ModifierGroup mod = modifierGroups.get(name);
				if (mod != null)
				{
					ExceptionLogger.log("Adding ModifierGroup \""+name+"\"", LoggerLevel.FINEST);
					mod.addModifiers(modifiers);
					areas.get(areaName).addModifierGroup(mod.clone());
				}
				else
				{
					ExceptionLogger.logException(new UnknownIdentifier(name), LoggerLevel.ERROR);
				}
			}
		}
		for (Enumeration<String> e = areas.keys(); e.hasMoreElements();)
		{
			String areaName = e.nextElement();
			ExceptionLogger.log("Parsing Area \""+areaName+"\"", LoggerLevel.FINE);
			ArrayList<String> subNames = areas.get(areaName).getSubAreaNames();
			for (String name : subNames)
			{
				TreeNodeArea area = areas.get(name);
				if (area != null)
				{
					ExceptionLogger.log("Adding SubArea \""+name+"\"", LoggerLevel.FINEST);
					areas.get(areaName).addSubArea(area.clone());
				}
				else
				{
					ExceptionLogger.logException(new UnknownIdentifier(name), LoggerLevel.ERROR);
				}
			}
		}
		world = areas.get("World");
		if (world == null)
		{
			ExceptionLogger.logException(new NoWorldException(), LoggerLevel.CRITICAL);
		}
		else
		{
			world.setAsRootNode();
		}
	}
	
	public TreeNodeArea getWorld()
	{
		return world;
	}
	
	public Hashtable<Integer,String> getBlockMap()
	{
		return blockMap.getInverseMap();
	}
	
	public void expandToWorldTree(RNG rng)
	{
		world.expandToWorldTree(rng, 1, 1, 1, 0, 0, 0, 0, 0);
		ExceptionLogger.log("World successfully expanded", LoggerLevel.COARSE);
	}
	public String toString()
	{
		return world.toString();
	}
	
}

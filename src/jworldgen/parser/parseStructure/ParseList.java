package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Hashtable;

import jworldgen.generator.worldStructure.Modifier;
import jworldgen.generator.worldStructure.TreeNodeArea;
import jworldgen.generator.worldStructure.TreeNodeRoom;

public class ParseList {
	private ArrayList<ParseRoom> rooms;
	private ArrayList<ParseArea> areas;
	private ArrayList<ParseModifier> modifiers;
	private ArrayList<ParseKind> kinds;
	public ParseList()
	{
		rooms = new ArrayList<ParseRoom>();
		areas = new ArrayList<ParseArea>();
		modifiers = new ArrayList<ParseModifier>();
		kinds = new ArrayList<ParseKind>();
	}
	
	public void addRoom (ParseRoom room)
	{
		rooms.add(room);
		kinds.add(ParseKind.ROOM);
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
	
	public void insertBlockIDs(BlockMap blockmap)
	{
		for(ParseRoom room : rooms)
		{
			room.insertBlockIDs(blockmap);
		}
		for(ParseArea area : areas)
		{
			area.insertBlockID(blockmap);
		}
		for(ParseModifier modifier : modifiers)
		{
			modifier.insertBlockIDs(blockmap);
		}
	}
	
	public String toString()
	{
		return (rooms.size()+"|"+areas.size());
	}
	
	public Hashtable<String,TreeNodeRoom> createRoomNodes()
	{
		Hashtable<String,TreeNodeRoom> roomNodes = new Hashtable<String,TreeNodeRoom>();
		for (ParseRoom room : rooms)
		{
			roomNodes.put(room.getIdentifier(), room.toRoomNode());
		}
		return roomNodes;
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
}

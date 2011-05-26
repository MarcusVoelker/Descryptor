package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Hashtable;

import jworldgen.generator.worldStructure.TreeNodeArea;
import jworldgen.generator.worldStructure.TreeNodeRoom;

public class ParseList {
	private ArrayList<ParseRoom> rooms;
	private ArrayList<ParseArea> areas;
	
	public ParseList()
	{
		rooms = new ArrayList<ParseRoom>();
		areas = new ArrayList<ParseArea>();
	}
	
	public void addRoom (ParseRoom room)
	{
		rooms.add(room);
	}
	
	public void addArea(ParseArea area)
	{
		areas.add(area);
	}
	
	public void insertBlockIDs(BlockMap blockmap)
	{
		for(ParseRoom room : rooms)
		{
			room.insertBlockIDs(blockmap);
		}
		for(ParseArea area : areas)
		{
			area.insertBlockIDs(blockmap);
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
}

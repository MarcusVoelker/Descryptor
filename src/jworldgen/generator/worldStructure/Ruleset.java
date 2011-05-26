package jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.parser.parseStructure.BlockMap;
import jworldgen.parser.parseStructure.ParseList;

public class Ruleset 
{
	private BlockMap blockMap;
	private TreeNodeArea world;
	
	public Ruleset(ParseList list)
	{
		blockMap = new BlockMap();
		createFromParseList(list);
	}
	
	private void createFromParseList(ParseList list)
	{
		list.insertBlockIDs(blockMap);
		Hashtable<String,TreeNodeRoom> rooms = list.createRoomNodes();
		Hashtable<String,TreeNodeArea> areas = list.createAreaNodes();
		for (Enumeration<String> e = areas.keys(); e.hasMoreElements();)
		{
			String areaName = e.nextElement();
			ArrayList<String> subNames = areas.get(areaName).getSubAreaNames();
			for (String name : subNames)
			{
				TreeNodeArea area = areas.get(name);
				if (area != null)
				{
					areas.get(areaName).addSubArea(area.clone());
				}
				else
				{
					areas.get(areaName).addRoom(rooms.get(name).clone());
				}
			}
		}
		world = areas.get("World");
	}
	
	public TreeNodeArea getWorld()
	{
		return world;
	}
	
	public BlockMap getBlockMap()
	{
		return blockMap;
	}
	
	
}

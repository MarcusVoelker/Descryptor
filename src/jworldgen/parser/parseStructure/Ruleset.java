package jworldgen.parser.parseStructure;

import java.util.Hashtable;

public class Ruleset 
{
	private BlockMap blockMap;
	private Hashtable<String,TreeNodeRoom> rooms;
	private Hashtable<String,TreeNodeArea> areas;
	
	public Ruleset(ParseList list)
	{
		blockMap = new BlockMap();
		createFromParseList(list);
	}
	
	private void createFromParseList(ParseList list)
	{
		list.insertBlockIDs(blockMap);
		rooms = list.createRoomNodes();
		areas = list.createAreaNodes();
	}
	
	
}

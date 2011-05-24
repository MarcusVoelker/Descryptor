package jworldgen.parser.parseStructure;

import java.util.Hashtable;

public class Ruleset 
{
	private Hashtable<String,Integer> blockMap;
	public Ruleset(ParseList list)
	{
		blockMap = new Hashtable<String,Integer>();
		createFromParseList(list);
	}
	
	public static int addToBlockmap(Hashtable<String,Integer> blockmap, Integer maxID, String key)
	{
		if (blockmap.containsKey(key))
		{
			return blockmap.get(key);
		}
		else
		{
			blockmap.put(key, ++maxID);
			return maxID;
		}
	}
	
	private void createFromParseList(ParseList list)
	{
		
	}
	
	
}

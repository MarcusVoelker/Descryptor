package jworldgen.parser.parseStructure;

import java.util.Hashtable;

public class BlockMap {
	private Hashtable<String,Integer> blockMap;
	private Integer maxID;
	
	public BlockMap()
	{
		blockMap = new Hashtable<String,Integer>();
		maxID = 0;
		blockMap.put("None", 0);
	}
	
	public int registerBlock(String key)
	{
		if (blockMap.containsKey(key))
		{
			return blockMap.get(key);
		}
		else
		{
			blockMap.put(key, ++maxID);
			return maxID;
		}
	}
	
}

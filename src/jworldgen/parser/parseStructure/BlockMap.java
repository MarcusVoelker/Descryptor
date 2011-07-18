package jworldgen.parser.parseStructure;

import java.util.Enumeration;
import java.util.Hashtable;

public class BlockMap {
	private Hashtable<String,Integer> blockMap;
	private Integer maxID;
	
	public BlockMap()
	{
		blockMap = new Hashtable<String,Integer>();
		maxID = 0;
		blockMap.put("None", 0);
		blockMap.put("Empty", -1);
		blockMap.put("Erase", -2);
		blockMap.put("Lock", -3);
		blockMap.put("Unlock", -4);
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
	
	public Hashtable<Integer,String> getInverseMap()
	{
		Hashtable<Integer,String> inverseMap = new Hashtable<Integer,String>();
		for (Enumeration<String> e = blockMap.keys(); e.hasMoreElements();)
		{
			String key = e.nextElement();
			inverseMap.put(blockMap.get(key), key);
		}
		return inverseMap;
	}
}

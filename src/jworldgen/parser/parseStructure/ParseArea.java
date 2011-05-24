package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class ParseArea {
	private ArrayList<ParseSubArea> subAreas;
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,String> tileTypes;
	
	private Hashtable<Integer,Integer> tileIDs;
	
	public ParseArea()
	{
		this.subAreas = new ArrayList<ParseSubArea>();
		this.probabilities = new Hashtable<Integer,Integer>();
		this.tileTypes = new Hashtable<Integer,String>();
	}
	
	public void addSubArea(ParseSubArea area)
	{
		subAreas.add(area);
	}
	
	public void addProbability(Integer prob, int id)
	{
		probabilities.put(id, prob);
	}
	
	public void addType(String tileType, int id)
	{
		tileTypes.put(id, tileType);
	}
	
	public void insertBlockIDs(Hashtable<String,Integer> blockmap, Integer maxID)
	{
		tileIDs = new Hashtable<Integer,Integer>();
		for (Enumeration<Integer> e = tileTypes.keys(); e.hasMoreElements();)
		{
			Integer hashKey = e.nextElement();
			String blockType = tileTypes.get( hashKey );
			Integer id = Ruleset.addToBlockmap(blockmap,maxID,blockType);
			tileIDs.put(hashKey, id);
		}
	}
}

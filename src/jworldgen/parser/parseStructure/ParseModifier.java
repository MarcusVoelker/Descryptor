package jworldgen.parser.parseStructure;

import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.generator.worldStructure.Modifier;

public class ParseModifier {
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,String> types;
	private Hashtable<Integer,Integer> typeIDs;
	
	private String identifier;
	
	public ParseModifier()
	{
		probabilities = new Hashtable<Integer,Integer>();
		types = new Hashtable<Integer,String>();
		typeIDs = new Hashtable<Integer,Integer>();
	}
	public void addProb(int value, int id)
	{
		probabilities.put(id, value);
	}
	
	public void addType(String type, int id)
	{
		types.put(id, type);
	}
	
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public void insertBlockIDs(BlockMap blockmap)
	{
		for (Enumeration<Integer> e = types.keys(); e.hasMoreElements();)
		{
			Integer id = e.nextElement();
			String tileType = types.get(id);
			typeIDs.put(id, blockmap.registerBlock(tileType));
		}
	}
	
	public Modifier toModifier()
	{
		return new Modifier(probabilities,typeIDs,identifier);
	}
}

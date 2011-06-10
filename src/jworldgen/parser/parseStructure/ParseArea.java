package jworldgen.parser.parseStructure;

import java.util.ArrayList;

import jworldgen.generator.worldStructure.TreeNodeArea;

public class ParseArea {
	private ArrayList<ParseSubArea> subAreas;
	private String tileType;
	private String identifier;
	private ArrayList<String> modifiers;
	
	private Integer tileID;
	
	public ParseArea()
	{
		this.subAreas = new ArrayList<ParseSubArea>();
		this.modifiers = new ArrayList<String>();
	}
	
	public void addSubArea(ParseSubArea area)
	{
		subAreas.add(area);
	}
	
	public void setType(String tileType)
	{
		this.tileType = tileType;
	}
	
	public void setIdentifier (String identifier)
	{
		this.identifier = identifier;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public void insertBlockID(BlockMap blockmap)
	{
		tileID = blockmap.registerBlock(tileType);
	}
	
	public void addModifier(String modName)
	{
		modifiers.add(modName);
	}
	
	public TreeNodeArea toAreaNode()
	{
		return new TreeNodeArea(subAreas,tileID,identifier,modifiers);
	}
}

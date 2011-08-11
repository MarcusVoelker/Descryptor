package descryptor.jworldgen.parser.parseStructure;

import java.util.ArrayList;

import descryptor.common.parser.ParseObject;
import descryptor.jworldgen.generator.worldStructure.TreeNodeArea;


public class ParseArea extends ParseObject{
	private ArrayList<ParseSubArea> subAreas;
	private String tileType;
	private String identifier;
	private ArrayList<String> modifierGroups;
	
	private Integer tileID;
	
	public ParseArea()
	{
		this.subAreas = new ArrayList<ParseSubArea>();
		this.modifierGroups = new ArrayList<String>();
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
	
	public void addModifierGroup(String modName)
	{
		modifierGroups.add(modName);
	}
	
	public TreeNodeArea toAreaNode()
	{
		return new TreeNodeArea(subAreas,tileID,identifier,modifierGroups);
	}
}

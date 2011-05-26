package jworldgen.parser.parseStructure;

import jworldgen.generator.worldStructure.TreeNodeRoom;


public class ParseRoom {
	private String floor;
	private String ceiling;
	private String walls;
	private String background;
	private String identifier;
	private int floorID;
	private int ceilingID;
	private int wallID;
	private int backgroundID;
	
	public void setFloor (String floor)
	{
		this.floor = floor;
	}
	
	public void setCeiling (String ceiling)
	{
		this.ceiling = ceiling;
	}
	
	public void setWalls (String walls)
	{
		this.walls = walls;
	}
	
	public void setBackground (String background)
	{
		this.background = background;
	}
	
	public void setIdentifier (String identifier)
	{
		this.identifier = identifier;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	public void insertBlockIDs(BlockMap blockmap)
	{
		floorID = blockmap.registerBlock(floor);
		ceilingID = blockmap.registerBlock(ceiling);
		wallID = blockmap.registerBlock(walls);
		backgroundID = blockmap.registerBlock(background);
	}
	
	public TreeNodeRoom toRoomNode()
	{
		return new TreeNodeRoom(floorID, ceilingID, wallID, backgroundID, identifier);
	}
}

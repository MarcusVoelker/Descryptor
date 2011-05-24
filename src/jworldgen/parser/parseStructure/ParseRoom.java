package jworldgen.parser.parseStructure;

import java.util.Hashtable;

public class ParseRoom {
	private String floor;
	private String ceiling;
	private String walls;
	private String background;
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
	
	public void insertBlockIDs(Hashtable<String,Integer> blockmap, Integer maxID)
	{
		floorID = Ruleset.addToBlockmap(blockmap,maxID,floor);
		ceilingID = Ruleset.addToBlockmap(blockmap,maxID,ceiling);
		wallID = Ruleset.addToBlockmap(blockmap,maxID,walls);
		backgroundID = Ruleset.addToBlockmap(blockmap,maxID,background);
	}
}

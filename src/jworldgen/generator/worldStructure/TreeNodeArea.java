package jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Hashtable;

import jworldgen.parser.parseStructure.ParseSubArea;

public class TreeNodeArea {
	private ArrayList<ParseSubArea> parseSubAreas;
	private Hashtable<Integer,Integer> probabilities;	
	private Hashtable<Integer,Integer> tileIDs;
	
	private ArrayList<TreeNodeArea> subAreas;
	private ArrayList<TreeNodeRoom> rooms;
	
	private String identifier;
	
	private int count;
	private int countVariance;
	private float xPos;
	private float xPosVar;
	private float yPos;
	private float yPosVar;
	private float width;
	private float widthVar;
	private float height;
	private float heightVar;
	
	public TreeNodeArea(ArrayList<ParseSubArea> parseSubAreas, Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> tileIDs, String identifier)
	{
		this.parseSubAreas = parseSubAreas;
		this.probabilities = probabilities;
		this.tileIDs = tileIDs;
		this.identifier = identifier;
		subAreas = new ArrayList<TreeNodeArea>();
		rooms = new ArrayList<TreeNodeRoom>();
	}
	
	private TreeNodeArea(ArrayList<ParseSubArea> parseSubAreas, Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> tileIDs, ArrayList<TreeNodeArea> subAreas,ArrayList<TreeNodeRoom> rooms,String identifier)
	{
		this(parseSubAreas,probabilities,tileIDs,identifier);
		this.subAreas = subAreas;
		this.rooms = rooms;
	}
	
	public void setCount(int value, int variance)
	{
		this.count = value;
		this.countVariance = variance;
	}
	
	public void setXPos(float value, float variance)
	{
		this.xPos = value;
		this.xPosVar = variance;
	}
	
	public void setYPos(float value, float variance)
	{
		this.yPos = value;
		this.yPosVar = variance;
	}
	
	public void setWidth(float value, float variance)
	{
		this.width = value;
		this.widthVar = variance;
	}
	
	public void setHeight(float value, float variance)
	{
		this.height = value;
		this.heightVar = variance;
	}
	
	public ArrayList<String> getSubAreaNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		for (ParseSubArea area: parseSubAreas)
		{
			result.add(area.areaType);
		}
		return result;
	}
	
	public void addSubArea(TreeNodeArea area)
	{
		subAreas.add(area);
		String areaName = area.getIdentifier();
		for (ParseSubArea parseSubArea : parseSubAreas)
		{
			if (parseSubArea.areaType.equals(areaName))
			{
				area.setCount(parseSubArea.count,parseSubArea.countVar);
				area.setXPos(parseSubArea.xPos,parseSubArea.xPosVar);
				area.setYPos(parseSubArea.yPos,parseSubArea.yPosVar);
				area.setWidth(parseSubArea.width,parseSubArea.widthVar);
				area.setHeight(parseSubArea.height,parseSubArea.heightVar);
			}
		}
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	public void addRoom(TreeNodeRoom room)
	{
		rooms.add(room);
		String roomName = room.getIdentifier();
		for (ParseSubArea parseSubArea : parseSubAreas)
		{
			if (parseSubArea.areaType.equals(roomName))
			{
				room.setCount(parseSubArea.count,parseSubArea.countVar);
				room.setXPos(parseSubArea.xPos,parseSubArea.xPosVar);
				room.setYPos(parseSubArea.yPos,parseSubArea.yPosVar);
				room.setWidth(parseSubArea.width,parseSubArea.widthVar);
				room.setHeight(parseSubArea.height,parseSubArea.heightVar);
			}
		}
	}
	
	public TreeNodeArea clone()
	{
		return new TreeNodeArea(parseSubAreas, probabilities, tileIDs, subAreas, rooms, identifier);
	}
	
	public WorldArea toWorldArea()
	{
		return new WorldArea();
	}
}

package jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.generator.RNG;
import jworldgen.generator.World;
import jworldgen.parser.parseStructure.ParseSubArea;

public class TreeNodeArea {
	private ArrayList<ParseSubArea> parseSubAreas;
	private Hashtable<Integer,Integer> probabilities;	
	private Hashtable<Integer,Integer> tileIDs;
	
	private ArrayList<TreeNodeArea> subAreas;
	
	protected String identifier;
	
	protected int count;
	protected int countVariance;
	protected float xPos;
	protected float xPosVar;
	protected float yPos;
	protected float yPosVar;
	protected float width;
	protected float widthVar;
	protected float height;
	protected float heightVar;
	
	protected boolean isStamp = false;
	
	private int probSum;
	
	public TreeNodeArea()
	{
		subAreas = new ArrayList<TreeNodeArea>();
	}
	
	public TreeNodeArea(ArrayList<ParseSubArea> parseSubAreas, Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> tileIDs, String identifier)
	{
		this();
		this.parseSubAreas = parseSubAreas;
		this.probabilities = probabilities;
		this.tileIDs = tileIDs;
		this.identifier = identifier;
		probSum = 0;
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			Integer key = e.nextElement();
			probSum += probabilities.get(key);
		}
	}
	
	private TreeNodeArea(ArrayList<ParseSubArea> parseSubAreas, Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> tileIDs, ArrayList<TreeNodeArea> subAreas,String identifier)
	{
		this(parseSubAreas,probabilities,tileIDs,identifier);
		this.subAreas = subAreas;
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
	
	public void makeStamp()
	{
		this.isStamp = true;
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
				if (parseSubArea.isStamp)
					area.makeStamp();
			}
		}
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	public void addRoom(TreeNodeRoom room)
	{
		subAreas.add(room);
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
				if (parseSubArea.isStamp)
					room.makeStamp();
				parseSubAreas.remove(parseSubArea);
				break;
			}
		}
	}
	
	public TreeNodeArea clone()
	{
		TreeNodeArea newArea = new TreeNodeArea(parseSubAreas, probabilities, tileIDs, subAreas, identifier);
		newArea.setCount(count, countVariance);
		newArea.setHeight(height, heightVar);
		newArea.setWidth(width, widthVar);
		newArea.setXPos(xPos, xPosVar);
		newArea.setYPos(yPos, yPosVar);
		if (isStamp)
			newArea.makeStamp();
		return newArea;
	}
	
	protected  int calculateCount(RNG rng)
	{
		return rng.nextInt(count, countVariance);
	}
	
	protected float calculateFloat(RNG rng, float curVal, float variance, int index, int subCount)
	{
		if (curVal == -1) {
			curVal = 1.0f/subCount;
		} else if (curVal == -2) {
			curVal = index*1.0f/subCount;
		} 
		return  rng.nextFloat(curVal, variance);
		
	}
	
	public void expandToWorldTree(RNG rng, float parentHeight, float parentWidth, float parentXPos, float parentYPos, int index, int subCount)
	{
		
		xPos = calculateFloat(rng,xPos,xPosVar,index,subCount)*parentWidth+parentXPos;
		yPos = calculateFloat(rng,yPos,yPosVar,index,subCount)*parentHeight+parentYPos;
		if (!isStamp)
		{
			width = calculateFloat(rng,width,widthVar,index,subCount)*parentWidth;
			height = calculateFloat(rng,height,heightVar,index,subCount)*parentHeight;
		}
		ArrayList<TreeNodeArea> realSubAreas = new ArrayList<TreeNodeArea>();
		
		for (TreeNodeArea tna: subAreas)
		{
			int newSubCount = tna.calculateCount(rng);
			for (int i = 0; i < newSubCount; i++)
			{
				TreeNodeArea newArea = tna.clone();
				newArea.expandToWorldTree(rng, height, width, xPos, yPos, i, newSubCount);
				realSubAreas.add(newArea);
			}
		}
		subAreas = realSubAreas;
	}
	
	protected int getNextTileId(RNG rng)
	{
		int probValue = rng.nextAreaInt(0,probSum);
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			int curElement = e.nextElement();
			probValue -= probabilities.get(curElement);
			if (probValue < 0)
				return tileIDs.get(curElement);
		}
		return 0;		
	}
	
	public void fillWorld(RNG rng, World world)
	{
		if (isStamp)
		{
			for (int x = (int) Math.floor(xPos*world.getWidth()); x < (int) Math.floor(xPos*world.getWidth()+width); x++)
			{
				for (int y = (int) Math.floor(yPos*world.getHeight()); y < (int) Math.floor(yPos*world.getHeight()+height); y++)
				{
					world.setValue(x, y, getNextTileId(rng));
				}
			}
			return;
		}
		for (int x = (int) Math.floor(xPos*world.getWidth()); x < (int) Math.floor((xPos+width)*world.getWidth()); x++)
		{
			for (int y = (int) Math.floor(yPos*world.getHeight()); y < (int) Math.floor((yPos+height)*world.getHeight()); y++)
			{
				world.setValue(x, y, getNextTileId(rng));
			}
		}
		
		for (TreeNodeArea tna: subAreas)
		{
			tna.fillWorld(rng, world);
		}
	}
	
	public String toString()
	{
		String subs = "";
		for (TreeNodeArea tna: subAreas)
		{
			subs += tna.toString()+ " ";
		}
		return identifier+": ("+xPos+", "+yPos+") SubAreas:"+"("+subs+")";
	}
}

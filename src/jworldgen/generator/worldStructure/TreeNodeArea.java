package jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.exceptionHandler.ExceptionHandler;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.exceptionHandler.RecursionException;
import jworldgen.generator.RNG;
import jworldgen.generator.World;
import jworldgen.parser.parseStructure.ParseSubArea;

public class TreeNodeArea {
	private ArrayList<ParseSubArea> parseSubAreas;
	private Hashtable<Integer,Integer> probabilities;	
	private Hashtable<Integer,Integer> tileIDs;
	
	private ArrayList<TreeNodeArea> subAreas;
	
	protected String identifier;
	
	protected int countLow;
	protected int countHigh;
	protected float xPosLow;
	protected float xPosHigh;
	protected float yPosLow;
	protected float yPosHigh;
	protected float widthLow;
	protected float widthHigh;
	protected float heightLow;
	protected float heightHigh;
	
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
	
	public void setCount(int low, int high)
	{
		this.countLow = low;
		this.countHigh = high;
	}
	
	public void setXPos(float low, float high)
	{
		this.xPosLow = low;
		this.xPosHigh = high;
	}
	
	public void setYPos(float low, float high)
	{
		this.yPosLow = low;
		this.yPosHigh = high;
	}
	
	public void setWidth(float low, float high)
	{
		this.widthLow = low;
		this.widthHigh = high;
	}
	
	public void setHeight(float low, float high)
	{
		this.heightLow = low;
		this.heightHigh = high;
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
		if (areaName.equals(identifier))
		{
			ExceptionHandler.logException(new RecursionException(), LoggerLevel.ERROR);
			return;
		}
		for (ParseSubArea parseSubArea : parseSubAreas)
		{
			if (parseSubArea.areaType.equals(areaName))
			{
				area.setCount(parseSubArea.countLow,parseSubArea.countHigh);
				area.setXPos(parseSubArea.xPosLow,parseSubArea.xPosHigh);
				area.setYPos(parseSubArea.yPosLow,parseSubArea.yPosHigh);
				area.setWidth(parseSubArea.widthLow,parseSubArea.widthHigh);
				area.setHeight(parseSubArea.heightLow,parseSubArea.heightHigh);
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
				room.setCount(parseSubArea.countLow,parseSubArea.countHigh);
				room.setXPos(parseSubArea.xPosLow,parseSubArea.xPosHigh);
				room.setYPos(parseSubArea.yPosLow,parseSubArea.yPosHigh);
				room.setWidth(parseSubArea.widthLow,parseSubArea.widthHigh);
				room.setHeight(parseSubArea.heightLow,parseSubArea.heightHigh);
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
		newArea.setCount(countLow, countHigh);
		newArea.setHeight(heightLow, heightHigh);
		newArea.setWidth(widthLow, widthHigh);
		newArea.setXPos(xPosLow, xPosHigh);
		newArea.setYPos(yPosLow, yPosHigh);
		if (isStamp)
			newArea.makeStamp();
		return newArea;
	}
	
	protected  int calculateCount(RNG rng)
	{
		return rng.nextInt(countLow, countHigh);
	}
	
	protected float resolveSymbolicValue(float symbol, int index, int subCount)
	{
		if (symbol == -1) {
			return 1.0f/subCount;
		} else if (symbol == -2) {
			return index*1.0f/subCount;
		} 
		return symbol;
	}
	protected float calculateFloat(RNG rng, float low, float high, int index, int subCount)
	{
		low = resolveSymbolicValue(low,index,subCount);
		high = resolveSymbolicValue(high,index,subCount);
		return  rng.nextFloat(low, high);
		
	}
	
	public void expandToWorldTree(RNG rng, float parentHeight, float parentWidth, float parentXPos, float parentYPos, int index, int subCount)
	{
		
		xPosLow = calculateFloat(rng,xPosLow,xPosHigh,index,subCount)*parentWidth+parentXPos;
		yPosLow = calculateFloat(rng,yPosLow,yPosHigh,index,subCount)*parentHeight+parentYPos;
		if (!isStamp)
		{
			widthLow = calculateFloat(rng,widthLow,widthHigh,index,subCount)*parentWidth;
			heightLow = calculateFloat(rng,heightLow,heightHigh,index,subCount)*parentHeight;
		}
		ArrayList<TreeNodeArea> realSubAreas = new ArrayList<TreeNodeArea>();
		
		for (TreeNodeArea tna: subAreas)
		{
			int newSubCount = tna.calculateCount(rng);
			for (int i = 0; i < newSubCount; i++)
			{
				TreeNodeArea newArea = tna.clone();
				newArea.expandToWorldTree(rng, heightLow, widthLow, xPosLow, yPosLow, i, newSubCount);
				realSubAreas.add(newArea);
			}
		}
		subAreas = realSubAreas;
	}
	
	protected int getNextTileId(RNG rng)
	{
		int probValue = rng.nextInt(0,probSum);
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
			for (int x = (int) Math.floor(xPosLow*world.getWidth()); x < (int) Math.floor(xPosLow*world.getWidth()+widthLow); x++)
			{
				for (int y = (int) Math.floor(yPosLow*world.getHeight()); y < (int) Math.floor(yPosLow*world.getHeight()+heightLow); y++)
				{
					world.setValue(x, y, getNextTileId(rng));
				}
			}
			return;
		}
		for (int x = (int) Math.floor(xPosLow*world.getWidth()); x < (int) Math.floor((xPosLow+widthLow)*world.getWidth()); x++)
		{
			for (int y = (int) Math.floor(yPosLow*world.getHeight()); y < (int) Math.floor((yPosLow+heightLow)*world.getHeight()); y++)
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
		return identifier+": ("+xPosLow+", "+yPosLow+") SubAreas:"+"("+subs+")";
	}
}

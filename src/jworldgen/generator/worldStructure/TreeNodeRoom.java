package jworldgen.generator.worldStructure;

import java.util.ArrayList;

import jworldgen.generator.RNG;

public class TreeNodeRoom {
	private int floorID;
	private int ceilingID;
	private int wallID;
	private int backgroundID;
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
	
	public TreeNodeRoom(int floorID, int ceilingID, int wallID, int backgroundID, String identifier)
	{
		this.floorID = floorID;
		this.ceilingID = ceilingID;
		this.wallID = wallID;
		this.backgroundID = backgroundID;
		this.identifier = identifier;
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
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public int calculateCount(RNG rng)
	{
		return rng.nextInt(count, countVariance);
	}
	
	public TreeNodeRoom clone()
	{
		return new TreeNodeRoom(floorID,ceilingID,wallID,backgroundID,identifier);
	}
	
	private float calculateFloat(RNG rng, float curVal, float variance, int index, int subCount)
	{
		if (curVal == -1) {
			curVal = index*1.0f/subCount;
		} else if (curVal == -2) {
			curVal = 1.0f/subCount;
		} 
		return  rng.nextFloat(curVal, variance);
		
	}
	
	public void expandToWorldTree(RNG rng, float parentHeight, float parentWidth, float parentXPos, float parentYPos, int index, int subCount)
	{
		width = calculateFloat(rng,width,widthVar,index,subCount)*parentWidth;
		height = calculateFloat(rng,height,heightVar,index,subCount)*parentHeight;
		xPos = calculateFloat(rng,xPos,xPosVar,index,subCount)*parentXPos;
		yPos = calculateFloat(rng,yPos,yPosVar,index,subCount)*parentYPos;
		
		xPos+=parentXPos;
		yPos+=parentYPos;
	}
}

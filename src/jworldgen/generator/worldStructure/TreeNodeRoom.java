package jworldgen.generator.worldStructure;

import jworldgen.generator.RNG;
import jworldgen.generator.World;

public class TreeNodeRoom extends TreeNodeArea{
	private int floorID;
	private int ceilingID;
	private int wallID;
	private int backgroundID;
	
	public TreeNodeRoom(int floorID, int ceilingID, int wallID, int backgroundID, String identifier)
	{
		this.floorID = floorID;
		this.ceilingID = ceilingID;
		this.wallID = wallID;
		this.backgroundID = backgroundID;
		this.identifier = identifier;
	}
	
	public TreeNodeRoom clone()
	{
		TreeNodeRoom tnr = new TreeNodeRoom(floorID,ceilingID,wallID,backgroundID,identifier);
		tnr.setCount(count, countVariance);
		tnr.setHeight(height, heightVar);
		tnr.setWidth(width, widthVar);
		tnr.setXPos(xPos, xPosVar);
		tnr.setYPos(yPos, yPosVar);
		if (isStamp)
			tnr.makeStamp();
		return tnr;
	}
	
	public void fillWorld(RNG rng, World world)
	{
		int left;
		int right;
		int top;
		int bottom;
		left = (int) Math.floor(xPos*world.getWidth());
		top = (int) Math.floor(yPos*world.getHeight());
		
		if (isStamp)
		{
			right = Math.round(left+width*2);
			bottom = Math.round(top+height*2);
		}
		else
		{
			right = (int) Math.floor((xPos+width)*world.getWidth());
			bottom = (int) Math.floor((yPos+height)*world.getHeight());
		}
		
		for (int x = left+1; x < right-1; x++)
		{
			for (int y = top+1; y < bottom-1; y++)
			{
				world.setValue(x, y, backgroundID);
			}
		}
		for (int y = top; y < bottom; y++)
		{
			world.setValue(left, y, wallID);
			world.setValue(right-1, y, wallID);
		}
		for (int x = left; x < right; x++)
		{
			world.setValue(x, top, ceilingID);
			world.setValue(x, bottom-1, floorID);
		}
	}
	
	public void expandToWorldTree(RNG rng, float parentHeight, float parentWidth, float parentXPos, float parentYPos, int index, int subCount)
	{
		width = calculateFloat(rng,width,widthVar,index,subCount)*parentWidth;
		height = calculateFloat(rng,height,heightVar,index,subCount)*parentHeight;
		xPos = calculateFloat(rng,xPos,xPosVar,index,subCount)*parentWidth+parentXPos;
		yPos = calculateFloat(rng,yPos,yPosVar,index,subCount)*parentHeight+parentYPos;
	}
}

package jworldgen.generator.worldStructure;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
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
		try {
			tnr.setCount(countLow, countHigh);
			tnr.setHeight(heightLow, heightHigh);
			tnr.setWidth(widthLow, widthHigh);
			tnr.setXPos(xPosLow, xPosHigh);
			tnr.setYPos(yPosLow, yPosHigh);
		} catch (CriticalFailure e) {
			//This should never be reached.
			e.printStackTrace();
		}
		if (isStamp)
			tnr.makeStamp();
		return tnr;
	}
	
	public void fillWorld(RNG rng, World world, int gridSize)
	{
		int left;
		int right;
		int top;
		int bottom;
		left = (int) Math.floor(xPosLow*world.getWidth());
		top = (int) Math.floor(yPosLow*world.getHeight());
		
		if (isStamp)
		{
			right = Math.round(left+widthLow*2);
			bottom = Math.round(top+heightLow*2);
		}
		else
		{
			right = (int) Math.floor((xPosLow+widthLow)*world.getWidth());
			bottom = (int) Math.floor((yPosLow+heightLow)*world.getHeight());
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
		ExceptionLogger.log("Expanding Room \""+identifier+"\"", LoggerLevel.FINEST);
		widthLow = calculateFloat(rng,widthLow,widthHigh,index,subCount)*parentWidth;
		heightLow = calculateFloat(rng,heightLow,heightHigh,index,subCount)*parentHeight;
		xPosLow = calculateFloat(rng,xPosLow,xPosHigh,index,subCount)*parentWidth+parentXPos;
		yPosLow = calculateFloat(rng,yPosLow,yPosHigh,index,subCount)*parentHeight+parentYPos;
	}
}

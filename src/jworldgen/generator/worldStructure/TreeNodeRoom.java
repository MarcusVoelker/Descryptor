package jworldgen.generator.worldStructure;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;
import jworldgen.generator.World;
import jworldgen.parser.parseStructure.ParseAssignment;

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
		tnr.setAssignments(assignments);
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
		ExceptionLogger.log("Expanding Room \""+identifier+"\"", LoggerLevel.FINEST);
		VariableResolver resolver = new VariableResolver();
		for (ParseAssignment assignment : assignments)
		{
			assignment.evaluate(rng, resolver);
		}
		xPos = ((Float) resolver.getVariable("xPos"))*parentWidth+parentXPos;
		yPos = ((Float) resolver.getVariable("yPos"))*parentHeight+parentYPos;
		width = ((Float) resolver.getVariable("width"))*parentWidth+parentXPos;
		height = ((Float) resolver.getVariable("height"))*parentHeight+parentYPos;
	}
}

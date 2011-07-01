package jworldgen.generator.worldStructure;

import java.util.ArrayList;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.exceptionHandler.RecursionException;
import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;
import jworldgen.generator.World;
import jworldgen.generator.worldStructure.modifiers.Modifier;
import jworldgen.parser.parseStructure.ParseAssignment;
import jworldgen.parser.parseStructure.ParseSubArea;

public class TreeNodeArea {
	private ArrayList<ParseSubArea> parseSubAreas;
	private Integer tileID;
	private ArrayList<String> parseModifiers;
	
	private ArrayList<TreeNodeArea> subAreas;
	private ArrayList<Modifier> modifiers;
	
	protected float xPos;
	protected float yPos;
	protected float zPos;
	protected float width;
	protected float height;
	protected float depth;
	
	protected String identifier;
	
	protected ArrayList<ParseAssignment> assignments;
	
	protected boolean isStamp = false;
	
	public TreeNodeArea()
	{
		subAreas = new ArrayList<TreeNodeArea>();
		modifiers = new ArrayList<Modifier>();
	}
	
	public TreeNodeArea(ArrayList<ParseSubArea> parseSubAreas, Integer tileID, String identifier, ArrayList<String> parseModifiers)
	{
		this();
		this.parseSubAreas = parseSubAreas;
		this.tileID = tileID;
		this.identifier = identifier;
		this.parseModifiers = parseModifiers;
	}
	
	private TreeNodeArea(ArrayList<ParseSubArea> parseSubAreas, Integer tileID, ArrayList<TreeNodeArea> subAreas,String identifier, ArrayList<Modifier> modifiers)
	{
		this(parseSubAreas,tileID,identifier,null);
		this.subAreas = subAreas;
		this.modifiers = modifiers;
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
	
	public ArrayList<String> getModifierNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		for (String mod: parseModifiers)
		{
			result.add(mod);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void setAssignments(ArrayList<ParseAssignment> assignments)
	{
		if (assignments == null)
			this.assignments = null;
		else
			this.assignments = (ArrayList<ParseAssignment>) assignments.clone();
	}
	public void addSubArea(TreeNodeArea area) throws CriticalFailure
	{
		subAreas.add(area);
		String areaName = area.getIdentifier();
		if (areaName.equals(identifier))
		{
			ExceptionLogger.logException(new RecursionException(), LoggerLevel.ERROR);
			return;
		}
		for (ParseSubArea parseSubArea : parseSubAreas)
		{
			if (parseSubArea.areaType.equals(areaName))
			{
				area.setAssignments(parseSubArea.assignments);
				if (parseSubArea.isStamp)
					area.makeStamp();
			}
		}
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public void addModifier(Modifier mod) throws CriticalFailure
	{
		modifiers.add(mod);
	}
	
	public TreeNodeArea clone()
	{
		TreeNodeArea newArea = new TreeNodeArea(parseSubAreas, tileID, subAreas, identifier, modifiers);
		newArea.setAssignments(assignments);
		if (isStamp)
			newArea.makeStamp();
		return newArea;
	}
	
	protected  int calculateCount(RNG rng)
	{
		VariableResolver resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		return (Integer) resolver.getVariable("count");
	}
	
	public void setAsRootNode()
	{
		xPos = 0;
		yPos = 0;
		zPos = 0;
		width = 1;
		height = 1;
		depth = 1;
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
	
	public void expandToWorldTree(RNG rng, float parentHeight, float parentWidth, float parentDepth, float parentXPos, float parentYPos, float parentZPos, int index, int subCount)
	{
		ExceptionLogger.log("Expanding Area \""+identifier+"\"", LoggerLevel.FINEST);
		VariableResolver resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
			xPos = ((Float) resolver.getVariable("xPos"))*parentWidth+parentXPos;
			yPos = ((Float) resolver.getVariable("yPos"))*parentHeight+parentYPos;
			if (resolver.isDefined("zPos"))
				zPos = ((Float) resolver.getVariable("zPos"))*parentDepth+parentZPos;
			else
				zPos = 0.0f;
			if (!isStamp)
			{
				width = ((Float) resolver.getVariable("width"))*parentWidth;
				height = ((Float) resolver.getVariable("height"))*parentHeight;
				if (resolver.isDefined("depth"))
					depth = ((Float) resolver.getVariable("depth"))*parentDepth;
				else
					depth = 1.0f;
			}
		}
		ArrayList<TreeNodeArea> realSubAreas = new ArrayList<TreeNodeArea>();
		
		for (TreeNodeArea tna: subAreas)
		{
			int newSubCount = tna.calculateCount(rng);
			for (int i = 0; i < newSubCount; i++)
			{
				TreeNodeArea newArea = tna.clone();
				newArea.expandToWorldTree(rng, height, width, depth, xPos, yPos, zPos, i, newSubCount);
				realSubAreas.add(newArea);
			}
		}
		subAreas = realSubAreas;
	}
	
	private void determineValue(RNG rng, World world, int x, int y, int z)
	{
		if (tileID != 0)
			world.setValue(x, y, z, tileID);
		for (Modifier mod : modifiers)
		{
			int value = mod.getValue(x, y, z);
			if (value != 0)
			{
				world.setValue(x, y, z, value);
			}
		}
	}
	public void fillWorld(RNG rng, World world)
	{
		for (Modifier mod : modifiers)
		{
			mod.setRNG(rng, Math.max(world.getWidth(),world.getHeight()));
		}
		if (isStamp)
		{
			for (int x = (int) Math.floor(xPos*world.getWidth()); x < (int) Math.floor(xPos*world.getWidth()+width); x++)
			{
				for (int y = (int) Math.floor(yPos*world.getHeight()); y < (int) Math.floor(yPos*world.getHeight()+height); y++)
				{			
					for (int z = (int) Math.floor(zPos*world.getDepth()); z < (int) Math.floor(zPos*world.getDepth()+depth); z++)
					{			
						determineValue(rng,world,x,y,z);
					}
				}
			}
			return;
		}
		for (int x = (int) Math.floor(xPos*world.getWidth()); x < (int) Math.floor((xPos+width)*world.getWidth()); x++)
		{
			for (int y = (int) Math.floor(yPos*world.getHeight()); y < (int) Math.floor((yPos+height)*world.getHeight()); y++)
			{
				for (int z = (int) Math.floor(zPos*world.getDepth()); z < (int) Math.floor((zPos+depth)*world.getDepth()); z++)
				{
					determineValue(rng,world,x,y,z);
				}
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

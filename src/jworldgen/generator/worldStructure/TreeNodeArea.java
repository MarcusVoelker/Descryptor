package jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Stack;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.exceptionHandler.RecursionException;
import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;
import jworldgen.generator.World;
import jworldgen.generator.worldStructure.modifiers.MetaballModifier;
import jworldgen.generator.worldStructure.modifiers.Modifier;
import jworldgen.generator.worldStructure.modifiers.PerlinModifier;
import jworldgen.generator.worldStructure.modifiers.VoronoiModifier;
import jworldgen.generator.worldStructure.modifiers.WeightedPerlinModifier;
import jworldgen.generator.worldStructure.modifiers.WorleyModifier;
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
	
	protected int minX;
	protected int minY;
	protected int minZ;
	protected int maxX;
	protected int maxY;
	protected int maxZ;
	
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
		resolver.setVariable("$i", 0);
		resolver.setVariable("$n", 0);
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
		resolver.setVariable("$i", index);
		resolver.setVariable("$n", subCount);
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
			xPos = (resolver.getVariable("xPos").floatValue())*parentWidth+parentXPos;
			yPos = (resolver.getVariable("yPos").floatValue())*parentHeight+parentYPos;
			if (resolver.isDefined("zPos"))
				zPos = (resolver.getVariable("zPos").floatValue())*parentDepth+parentZPos;
			else
				zPos = 0.0f;
			if (!isStamp)
			{
				width = (resolver.getVariable("width").floatValue())*parentWidth;
				height = (resolver.getVariable("height").floatValue())*parentHeight;
				if (resolver.isDefined("depth"))
					depth = (resolver.getVariable("depth").floatValue())*parentDepth;
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
	
	private void determineValue(RNG rng, Stack<Integer> stack, int x, int y, int z)
	{
		stack.push(tileID);
		for (Modifier mod : modifiers)
		{
			int value = mod.getValue(x, y, z);
			switch(mod.getChangeType())
			{
			case MODIFY:
				stack.pop();
				break;
			case STACK:
				break;
			}
			stack.push(value);
		}
	}
	public void fillWorld(long seed, World world)
	{
		prepareForFilling(new RNG(seed), world);
		for (int x = minX; x < maxX; x++)
		{
			for (int y = minY; y < maxY; y++)
			{
				for (int z = minZ; z < maxZ; z++)
				{
					Stack<Integer> stack = new Stack<Integer>();
					setValue(new RNG(seed,x,y,z),stack,x,y,z);
					boolean breakLoop = false;
					while(!stack.empty() && stack.peek() < 1 && !breakLoop)
					{
						switch(stack.pop())
						{
						case 0:
							break;
						case -1:
							stack.push(-1);
							breakLoop = true;
							break;
						case -2:
							while(!stack.empty() && stack.peek() == 0)
								stack.pop();
							if (!stack.empty())
								stack.pop();
						case -3:
							break;
						case -4:
							if (stack.peek() == -3)
								stack.pop();
							break;
						}
					}
					if (stack.empty() || stack.peek() < 1)
					{
						world.setValue(x, y, z, 0);
					}
					else
					{
						world.setValue(x, y, z, stack.peek());
					}
				}
			}
		}
	}
	
	public void prepareForFilling(RNG rng, World world)
	{
		minX = (int) Math.floor(xPos*world.getWidth());
		minY = (int) Math.floor(yPos*world.getHeight());
		minZ = (int) Math.floor(zPos*world.getDepth());
		maxX = (int) Math.floor((xPos+width)*world.getWidth());
		maxY = (int) Math.floor((yPos+height)*world.getHeight());
		maxZ = (int) Math.floor((zPos+depth)*world.getDepth());
		for (Modifier mod : modifiers)
		{
			switch(mod.getType())
			{
			case PERLIN:
				((PerlinModifier) mod).setRNG(rng, Math.max(world.getWidth(),world.getHeight()));
				mod.setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case WEIGHTED_PERLIN:
				((WeightedPerlinModifier) mod).setRNG(rng, Math.max(world.getWidth(),world.getHeight()));
				mod.setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case METABALL:
				mod.setRNG(rng);
				((MetaballModifier) mod).setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case VORONOI:
				mod.setRNG(rng);
				((VoronoiModifier) mod).setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case WORLEY:
				mod.setRNG(rng);
				((WorleyModifier) mod).setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			}
		}
		for (TreeNodeArea tna: subAreas)
		{
			tna.prepareForFilling(rng, world);
		}
	}
	
	public void setValue(RNG rng, Stack<Integer> stack, int x, int y, int z)
	{
		if (x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ)
			determineValue(rng,stack,x,y,z);
		for (TreeNodeArea tna: subAreas)
		{
			tna.setValue(rng, stack, x, y, z);
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
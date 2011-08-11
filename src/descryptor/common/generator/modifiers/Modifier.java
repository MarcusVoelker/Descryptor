package descryptor.common.generator.modifiers;

import java.util.ArrayList;

import descryptor.common.RNG;
import descryptor.common.ale.Evaluatable;
import descryptor.common.ale.Assignment;


public abstract class Modifier implements Evaluatable{
	
	protected String identifier;
	protected RNG rng;
	protected ModifierType type;
	protected int minX,minY,minZ,maxX,maxY,maxZ;
	
	protected ArrayList<Assignment> assignments;
	
	public Modifier(String identifier, ArrayList<Assignment> assignments)
	{
		this.identifier = identifier;
		this.assignments = assignments;
	}
	
	public void setRNG(RNG rng)
	{
		this.rng = rng;
	}
	
	public void setLocation(int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
	{
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}
	
	public abstract Modifier clone();
	
	public abstract float getValue(int x, int y, int z);
	
	public Number getValue(Number[] params)
	{
		return getValue(params[0].intValue(),params[1].intValue(),params[2].intValue());
	}
	
	public ModifierType getType()
	{
		return type;
	}
}

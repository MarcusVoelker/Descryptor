package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;

import jworldgen.generator.PerlinGenerator;
import jworldgen.generator.RNG;
import jworldgen.parser.parseStructure.ParseAssignment;

public abstract class Modifier {
	
	protected String identifier;
	protected PerlinGenerator perlin;
	protected RNG rng;
	
	protected ArrayList<ParseAssignment> assignments;
	
	public Modifier(String identifier, ArrayList<ParseAssignment> assignments)
	{
		this.identifier = identifier;
		this.assignments = assignments;
	}
	
	public void setRNG(RNG rng, int size)
	{
		this.rng = rng;
		perlin = new PerlinGenerator(rng, size);
	}
	
	public abstract Modifier clone();
	
	public abstract int getValue(int x, int y, int z);
}

package jworldgen.generator.worldStructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.generator.PerlinGenerator;
import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class Modifier {
	
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,Integer> typeIDs;
	String identifier;
	PerlinGenerator perlin;
	RNG rng;
	
	private ArrayList<ParseAssignment> assignments;
	
	int probSum;
	
	public Modifier(Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> typeIDs, String identifier, ArrayList<ParseAssignment> assignments)
	{
		this.probabilities = probabilities;
		this.typeIDs = typeIDs;
		this.identifier = identifier;
		this.assignments = assignments;
		probSum = 0;
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			probSum += probabilities.get(e.nextElement());
		}
	}
	
	public void setRNG(RNG rng, int size)
	{
		this.rng = rng;
		perlin = new PerlinGenerator(rng, size);
	}
	public Modifier clone()
	{
		return new Modifier(probabilities, typeIDs, identifier, assignments);
	}
	public int getValue(int x, int y, int z)
	{
		VariableResolver resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		double noiseValue = perlin.noise(x, y, z, (Integer) resolver.getVariable("scale"));
		int curProb = probSum;
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			int key = e.nextElement();
			curProb -= probabilities.get(key);
			if (curProb/(float)probSum <= noiseValue)
			{
				return typeIDs.get(key);
			}
		}
		return 0;
	}
}

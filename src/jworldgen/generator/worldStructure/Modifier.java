package jworldgen.generator.worldStructure;

import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.generator.PerlinGenerator;
import jworldgen.generator.RNG;

public class Modifier {
	
	private Hashtable<Integer,Integer> probabilities;
	private Hashtable<Integer,Integer> typeIDs;
	String identifier;
	PerlinGenerator perlin;
	
	int probSum;
	
	public Modifier(Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> typeIDs, String identifier)
	{
		this.probabilities = probabilities;
		this.typeIDs = typeIDs;
		this.identifier = identifier;
		probSum = 0;
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			probSum += probabilities.get(e.nextElement());
		}
	}
	
	public void setRNG(RNG rng, int size)
	{
		perlin = new PerlinGenerator(rng, size);
	}
	public Modifier clone()
	{
		return new Modifier(probabilities, typeIDs, identifier);
	}
	public int getValue(int x, int y, int z)
	{
		double noiseValue = perlin.noise(x, y, z, 16);
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

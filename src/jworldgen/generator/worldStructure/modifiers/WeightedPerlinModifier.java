package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;
import java.util.Hashtable;

import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class WeightedPerlinModifier extends PerlinModifier{
	
	float heightWeight;
	int scale;
	public WeightedPerlinModifier(Hashtable<Integer,Integer> probabilities, Hashtable<Integer, Integer> typeIDs, String identifier, ArrayList<ParseAssignment> assignments, ChangeType chType) 
	{
		super(probabilities, typeIDs, identifier, assignments, chType);
		this.type = ModifierType.WEIGHTED_PERLIN;
		VariableResolver resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		this.scale = resolver.getVariable("scale").intValue();
		this.heightWeight = resolver.getVariable("heightWeight").floatValue();
	}

	@Override
	public int getValue(int x, int y, int z) {
		
		double noiseValue = perlin.noise(x, y, z, scale);
		int typeCount = typeIDs.size();
		for (int i = 0; i < probSum-1; i++)
		{
			float percentage = (i/(float) probSum)+2*((maxY - (float) y)/(maxY-minY))/probSum;
			if ((noiseValue+heightWeight*((float) y - minY)/(maxY-minY))/(heightWeight+1) < percentage)
			{
				int ctr = 1;
				for (int key = 1; key <= typeCount; key++)
				{
					i -= probabilities.get(key);
					if (i < 0)
					{
						return typeIDs.get(ctr);
					}
					ctr++;
				}
			}
		}
		return typeIDs.get(typeCount);
	}
	
	@Override
	public Modifier clone() {
		return new WeightedPerlinModifier(probabilities, typeIDs, identifier, assignments, chType);
	}
}
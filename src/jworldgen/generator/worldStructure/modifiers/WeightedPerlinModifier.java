package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;

import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class WeightedPerlinModifier extends PerlinModifier{
	
	float heightWeight;
	int scale;
	public WeightedPerlinModifier(String identifier, ArrayList<ParseAssignment> assignments) 
	{
		super(identifier, assignments);
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
	public float getValue(int x, int y, int z) 
	{
		double noiseValue = perlin.noise(x, y, z, scale);
		float result = (float) (noiseValue+heightWeight*((float) y - minY)/(maxY-minY))/(heightWeight+1);
		return result;
	}
	
	@Override
	public Modifier clone() {
		return new WeightedPerlinModifier(identifier, assignments);
	}
}
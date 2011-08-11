package descryptor.common.generator.modifiers;

import java.util.ArrayList;

import descryptor.common.ale.Assignment;
import descryptor.common.ale.VariableResolver;


public class WeightedPerlinModifier extends PerlinModifier{
	
	float heightWeight;
	int scale;
	public WeightedPerlinModifier(String identifier, ArrayList<Assignment> assignments) 
	{
		super(identifier, assignments);
		this.type = ModifierType.WEIGHTED_PERLIN;
		VariableResolver resolver = new VariableResolver();
		if (assignments != null)
		{
			for (Assignment assignment : assignments)
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
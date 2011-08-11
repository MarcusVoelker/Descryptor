package descryptor.common.generator.modifiers;

import java.util.ArrayList;

import descryptor.common.PerlinGenerator;
import descryptor.common.RNG;
import descryptor.common.ale.Assignment;
import descryptor.common.ale.VariableResolver;


public class PerlinModifier extends Modifier {
	
	protected PerlinGenerator perlin;
	
	public PerlinModifier(String identifier, ArrayList<Assignment> assignments)
	{
		super(identifier, assignments);
		this.type = ModifierType.PERLIN;
		this.identifier = identifier;
		this.assignments = assignments;
	}
	
	public void setRNG(RNG rng, int size)
	{
		super.setRNG(rng);
		perlin = new PerlinGenerator(rng, size);
	}
	
	public float getValue(int x, int y, int z)
	{
		VariableResolver resolver = new VariableResolver();
		if (assignments != null)
		{
			for (Assignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		double noiseValue = perlin.noise(x, y, z, (Integer) resolver.getVariable("scale"));
		return (float) noiseValue;
	}

	public Modifier clone() {
		return new PerlinModifier(identifier, assignments);
	}
}

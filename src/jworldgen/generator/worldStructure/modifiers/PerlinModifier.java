package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.generator.PerlinGenerator;
import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class PerlinModifier extends Modifier {
	
	protected PerlinGenerator perlin;
	
	public PerlinModifier(String identifier, ArrayList<ParseAssignment> assignments)
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
			for (ParseAssignment assignment : assignments)
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

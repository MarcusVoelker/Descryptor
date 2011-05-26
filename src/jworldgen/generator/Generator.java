package jworldgen.generator;

import jworldgen.generator.worldStructure.Ruleset;
import jworldgen.generator.worldStructure.TreeNodeArea;

public class Generator {
	
	Ruleset rules;
	RNG randomNumberGenerator;
	public Generator (Ruleset rules)
	{
		this.rules = rules;
	}
	
	public void generateFromSeed(long seed)
	{
		this.randomNumberGenerator = new RNG(seed);
		TreeNodeArea world = rules.getWorld();
	}
}

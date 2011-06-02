package jworldgen.generator;

import jworldgen.generator.worldStructure.Ruleset;
import jworldgen.generator.worldStructure.TreeNodeArea;

public class Generator {
	
	private Ruleset rules;
	private RNG randomNumberGenerator;
	public Generator (Ruleset rules)
	{
		this.rules = rules;
	}
	
	public World generateFromSeed(long seed, int width, int height)
	{
		this.randomNumberGenerator = new RNG(seed);
		TreeNodeArea world = rules.getWorld();
		World blockWorld = new World(width,height);
		world.fillWorld(randomNumberGenerator,blockWorld);
		return blockWorld;
	}
}

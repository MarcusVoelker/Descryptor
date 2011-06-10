package jworldgen.generator;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.filehandler.TextFileReader;
import jworldgen.generator.worldStructure.Ruleset;
import jworldgen.generator.worldStructure.TreeNodeArea;
import jworldgen.parser.RuleParser;
import jworldgen.parser.parseStructure.ParseList;

public class Generator {
	
	private Ruleset rules;
	private RNG randomNumberGenerator;
	public Generator (Ruleset rules)
	{
		this.rules = rules;
	}
	
	public World generateRandomly(int width, int height)
	{
		this.randomNumberGenerator = new RNG();
		TreeNodeArea world = rules.getWorld();
		World blockWorld = new World(width,height);
		world.fillWorld(randomNumberGenerator,blockWorld);
		ExceptionLogger.log("World successfully filled", LoggerLevel.COARSE);
		return blockWorld;
	}
	
	public World generateFromSeed(long seed, int width, int height)
	{
		this.randomNumberGenerator = new RNG(seed);
		TreeNodeArea world = rules.getWorld();
		World blockWorld = new World(width,height);
		world.fillWorld(randomNumberGenerator,blockWorld);
		ExceptionLogger.log("World successfully filled", LoggerLevel.COARSE);
		return blockWorld;
	}
	
	public static World generateFromFile(String fileName, long seed, int width, int height) throws CriticalFailure
	{
		String input = TextFileReader.readTextFile(fileName);
		ParseList list = RuleParser.parse(input);
		Ruleset rules = new Ruleset(list);
		rules.expandToWorldTree(new RNG(seed));
		Generator gen = new Generator(rules);
		World world = gen.generateFromSeed(seed, width, height);
		return world;
	}
	
	public static World generateFromFile(String fileName, int width, int height) throws CriticalFailure
	{
		String input = TextFileReader.readTextFile(fileName);
		ParseList list = RuleParser.parse(input);
		Ruleset rules = new Ruleset(list);
		rules.expandToWorldTree(new RNG());
		Generator gen = new Generator(rules);
		World world = gen.generateRandomly(width, height);
		return world;
	}
}

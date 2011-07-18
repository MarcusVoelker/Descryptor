package jworldgen.generator;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.exceptionHandler.ExceptionLogger;
import jworldgen.exceptionHandler.LoggerLevel;
import jworldgen.filehandler.TextFileReader;
import jworldgen.generator.worldStructure.Ruleset;
import jworldgen.generator.worldStructure.TreeNodeArea;
import jworldgen.generator.worldStructure.VoxelStack;
import jworldgen.parser.RuleParser;
import jworldgen.parser.parseStructure.ParseList;

public class Generator {
	
	private Ruleset rules;
	private RNG randomNumberGenerator;
	private TreeNodeArea world;
	private long seed;
	public Generator (Ruleset rules, long seed)
	{
		this.rules = rules;
		this.seed = seed;
	}
	
	public World generateRandomly(int width, int height, int depth)
	{
		this.randomNumberGenerator = new RNG();
		TreeNodeArea world = rules.getWorld();
		World blockWorld = new World(width,height,depth);
		world.fillWorld(randomNumberGenerator.nextInt(0, Integer.MAX_VALUE),blockWorld);
		ExceptionLogger.log("World successfully filled", LoggerLevel.COARSE);
		return blockWorld;
	}
	
	public World generateFromSeed(long seed, int width, int height, int depth)
	{
		this.randomNumberGenerator = new RNG(seed);
		TreeNodeArea world = rules.getWorld();
		World blockWorld = new World(width,height,depth);
		world.fillWorld(seed,blockWorld);
		ExceptionLogger.log("World successfully filled", LoggerLevel.COARSE);
		return blockWorld;
	}
	
	public void calculateBlock(World world, int x, int y, int z)
	{
		VoxelStack stack = new VoxelStack();
		this.world.setValue(new RNG(seed,x,y,z), stack, x, y, z);
		world.setValue(x, y, z, stack.evaluate());
	}
	
	public World createWorld(int width, int height, int depth)
	{
		world = rules.getWorld();
		World blockWorld = new World(width,height,depth);
		world.prepareForFilling(new RNG(seed), blockWorld);
		return blockWorld;
	}
	
	public static Generator getGeneratorFromFile(String fileName, long seed) throws CriticalFailure
	{
		String input = TextFileReader.readTextFile(fileName);
		ParseList list = RuleParser.parse(input);
		Ruleset rules = new Ruleset(list);
		rules.expandToWorldTree(new RNG(seed));
		return new Generator(rules,seed);
	}
	public static World generateFromFile(String fileName, long seed, int width, int height, int depth) throws CriticalFailure
	{
		String input = TextFileReader.readTextFile(fileName);
		ParseList list = RuleParser.parse(input);
		Ruleset rules = new Ruleset(list);
		rules.expandToWorldTree(new RNG(seed));
		Generator gen = new Generator(rules,seed);
		World world = gen.generateFromSeed(seed, width, height, depth);
		return world;
	}
	
	public static World generateFromFile(String fileName, int width, int height, int depth) throws CriticalFailure
	{
		String input = TextFileReader.readTextFile(fileName);
		ParseList list = RuleParser.parse(input);
		Ruleset rules = new Ruleset(list);
		rules.expandToWorldTree(new RNG());
		Generator gen = new Generator(rules, new RNG().nextInt(0, Integer.MAX_VALUE));
		World world = gen.generateRandomly(width, height, depth);
		return world;
	}
}

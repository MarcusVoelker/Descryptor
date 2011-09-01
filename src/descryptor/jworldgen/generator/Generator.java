package descryptor.jworldgen.generator;

import java.util.Hashtable;

import descryptor.common.RNG;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.generator.AbstractGenerator;
import descryptor.common.generator.AbstractProduct;
import descryptor.jworldgen.generator.worldStructure.Ruleset;
import descryptor.jworldgen.generator.worldStructure.TreeNodeArea;
import descryptor.jworldgen.generator.worldStructure.VoxelStack;
import descryptor.jworldgen.parser.RuleParser;
import descryptor.jworldgen.parser.parseStructure.ParseList;


public class Generator extends AbstractGenerator{
	
	private Ruleset rules;
	private TreeNodeArea world;
	
	public Generator ()
	{
		
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
	
	public int calculateBlock(int x, int y, int z) {
		VoxelStack stack = new VoxelStack();
		this.world.setValue(new RNG(seed,x,y,z), stack, x, y, z);
		return stack.evaluate();
	}
	
	public String calculateBlockName(int x, int y, int z) {
		int id = calculateBlock(x,y,z);
		Hashtable<Integer,String> blockmap = rules.getBlockMap();
		return blockmap.get(id);
	}
	
	public Hashtable<Integer,String> getBlockMap()
	{
		return rules.getBlockMap();
	}

	@Override
	public void initialize(long seed, Object[] params) throws CriticalFailure {
		initialize(seed,(String) params[0]);
	}
	
	private void initialize(long seed, String input) throws CriticalFailure {
		ParseList list = RuleParser.parse(input);
		rules = new Ruleset(list);
		rules.expandToWorldTree(new RNG(seed));
		this.seed = seed;
	}

	@Override
	public void reInitialize(long seed) {
		rules.expandToWorldTree(new RNG(seed));
		this.seed = seed;		
	}

	@Override
	public AbstractProduct generateFully(Object[] params) {
		return generateFully((Integer) params[0],(Integer) params[1],(Integer) params[2]);
	}
	
	private AbstractProduct generateFully(int width, int height, int depth) {
		return generateFromSeed(seed, width, height, depth);
	}
}

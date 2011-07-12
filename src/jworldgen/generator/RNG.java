package jworldgen.generator;

import java.util.Random;

public class RNG {
	private Random randomCore;
	
	public RNG()
	{
		randomCore = new Random();
	}
	public RNG(long seed)
	{
		randomCore = new Random(seed);
	}
	public RNG(long seed, int x, int y, int z)
	{
		randomCore = new Random(seed);
		long seed1 = nextInt(0,x);
		long seed2 = nextInt(0,y);
		long seed3 = nextInt(0,z);
		randomCore = new Random(seed1*nextInt(0,5)+seed2*nextInt(0,5)+seed3*nextInt(0,5));
	}
	
	public int nextInt(int low, int high)
	{
		if (high == low)
			return low;
		return randomCore.nextInt(high-low)+low;
	}
	
	public float nextFloat (float low, float high)
	{
		return randomCore.nextFloat()*(high-low)+low;
	}
}

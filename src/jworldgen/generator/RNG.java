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

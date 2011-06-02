package jworldgen.generator;

import java.util.Random;

public class RNG {
	private Random randomCore;
	
	public RNG(long seed)
	{
		randomCore = new Random(seed);
	}
	
	public int nextInt(int center, int spread)
	{
		if(spread == 0)
			return center;
		return randomCore.nextInt(spread*2)+center;
	}
	
	public int nextAreaInt(int low, int high)
	{
		if (high == low)
			return low;
		return randomCore.nextInt(high-low)+low;
	}
	
	public float nextFloat (float center, float spread)
	{
		return randomCore.nextFloat()*spread*2+center;
	}
}

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
		return randomCore.nextInt(spread*2)+center;
	}
	
	public float nextFloat (float center, float spread)
	{
		return randomCore.nextFloat()*spread*2+center;
	}
}

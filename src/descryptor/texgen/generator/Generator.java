package descryptor.texgen.generator;

import descryptor.common.RNG;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.generator.AbstractGenerator;
import descryptor.common.generator.AbstractProduct;

public class Generator extends AbstractGenerator {
	
	public Generator()
	{
		
	}
	@Override
	public void initialize(long seed, Object[] params) throws CriticalFailure {
		initialize(seed,(String) params[0]);
	}
	
	private void initialize(long seed, String input) throws CriticalFailure {
		//ParseList list = RuleParser.parse(input);
		this.seed = seed;
	}

	@Override
	public void reInitialize(long seed) {
		this.seed = seed;		
	}

	@Override
	public AbstractProduct generateFully(Object[] params) {
		return generateFully((Integer) params[0],(Integer) params[1]);
	}
	
	private AbstractProduct generateFully(int width, int height) {
		return generateFromSeed(seed, width, height);
	}
	
	public Texture generateFromSeed(long seed, int width, int height)
	{
		this.randomNumberGenerator = new RNG(seed);
		ExceptionLogger.log("Texture successfully filled", LoggerLevel.COARSE);
		return new Texture(16, 16);
	}
}

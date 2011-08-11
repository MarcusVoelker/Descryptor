package descryptor.common.generator;

import descryptor.common.RNG;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;

public abstract class AbstractGenerator {
	protected long seed;
	protected RNG randomNumberGenerator;
	public abstract void initialize(long seed, Object[] params) throws CriticalFailure;
	public abstract void reInitialize(long seed);
	public abstract AbstractProduct generateFully(Object[] params);
}

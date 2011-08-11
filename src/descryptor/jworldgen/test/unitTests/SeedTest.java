package descryptor.jworldgen.test.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import descryptor.common.util.Util;

public class SeedTest {

	@Test
	public void testSeedConverter() {
		for (int i = 0; i < 10000; i++)
		{
			long seed = (long) Math.floor((Math.random()*Long.MAX_VALUE));
			String seedString = Util.seedToSeedString(seed);
			long sameSeed = Util.seedStringToSeed(seedString);
			assertTrue(seed == sameSeed);
		}
	}
}

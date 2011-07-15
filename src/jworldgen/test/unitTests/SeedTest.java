package jworldgen.test.unitTests;

import static org.junit.Assert.*;
import jworldgen.util.Util;

import org.junit.Test;

public class SeedTest {

	@Test
	public void testSeedConverter() {
		for (int i = 0; i < 10000; i++)
		{
			long seed = (long) Math.floor((Math.random()*Long.MAX_VALUE));
			long sameSeed = Util.seedStringToSeed(Util.seedToSeedString(seed));
			assertTrue(seed == sameSeed);
		}
	}
}

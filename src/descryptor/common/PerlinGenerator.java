package descryptor.common;


public class PerlinGenerator {

	int size;
	final int[] p;
	public PerlinGenerator(RNG rng, int size) {
		this.size = size;
		p = new int[size*2];
		for (int i = 0; i < 2*size; i++)
			p[i] = i;
		for (int i = 0; i < size*size; i++) {
			int j = rng.nextInt(0, size);
			int k = rng.nextInt(0, size);
			int nSwap = p[j];
			p[j] = p[k];
			p[k] = nSwap;
		}
	}
	public double noise(int x, int y, int z, int gridSize) {
	      double X = (x/gridSize),              						
	          Y = (y/gridSize),   
	          Z = (z/gridSize);
	      X += (x%gridSize)/(double) gridSize;
	      Y += (y%gridSize)/(double) gridSize;
	      Z += (z%gridSize)/(double) gridSize;
	      double result = (noise(X,Y,Z)+1)/2;
	      return result;
	}
	
	private double noise(double x, double y, double z) {
	      int X = (int)Math.floor(x),                  // FIND UNIT CUBE THAT
	          Y = (int)Math.floor(y),                  // CONTAINS POINT.
	          Z = (int)Math.floor(z);
	      x -= Math.floor(x);                                // FIND RELATIVE X,Y,Z
	      y -= Math.floor(y);                                // OF POINT IN CUBE.
	      z -= Math.floor(z);
	      double u = fade(x),                                // COMPUTE FADE CURVES
	             v = fade(y),                                // FOR EACH OF X,Y,Z.
	             w = fade(z);
	      int A = p[X  ]+Y, AA = p[A]+Z, AB = p[A+1]+Z,      // HASH COORDINATES OF
	          B = p[X+1]+Y, BA = p[B]+Z, BB = p[B+1]+Z;      // THE 8 CUBE CORNERS,

	      return lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ),  // AND ADD
	                                     grad(p[BA  ], x-1, y  , z   )), // BLENDED
	                             lerp(u, grad(p[AB  ], x  , y-1, z   ),  // RESULTS
	                                     grad(p[BB  ], x-1, y-1, z   ))),// FROM  8
	                     lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  // CORNERS
	                                     grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
	                             lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
	                                     grad(p[BB+1], x-1, y-1, z-1 ))));
	   }
	   static double fade(double t) { return t * t * t * (t * (t * 6 - 15) + 10); }
	   static double lerp(double t, double a, double b) { return a + t * (b - a); }
	   static double grad(int hash, double x, double y, double z) {
	      int h = hash & 15;                      // CONVERT LO 4 BITS OF HASH CODE
	      double u = h<8 ? x : y,                 // INTO 12 GRADIENT DIRECTIONS.
	             v = h<4 ? y : h==12||h==14 ? x : z;
	      return (((h&1) == 0 ? u : -u) + ((h&2) == 0 ? v : -v));
	   }
}

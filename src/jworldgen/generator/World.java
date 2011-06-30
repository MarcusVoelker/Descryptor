package jworldgen.generator;

public class World {
	private int[][][] rawData;
	
	public World(int width, int height, int depth)
	{
		rawData = new int[width][height][depth];
	}
	
	public int getWidth()
	{
		return rawData.length;
	}
	
	public int getHeight()
	{
		return rawData[0].length;
	}
	
	public int getDepth()
	{
		return rawData[0][0].length;
	}
	
	public void setValue(int x, int y, int z, int value)
	{
		if (x >= getWidth() || y >= getHeight() || z >= getDepth() || x < 0 || y < 0 || z < 0)
		{
			return;
		}
		rawData[x][y][z] = value;
	}
	
	public int getValue(int x, int y, int z)
	{
		return rawData[x][y][z];
	}
	
}

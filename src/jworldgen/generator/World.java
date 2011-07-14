package jworldgen.generator;

public class World {
	private int [][][] rawData;
	
	public World(int width, int height, int depth)
	{
		rawData = new int[width][height][depth];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				for (int k = 0; k < depth; k++)
				{
					rawData[i][j][k] = 0;
				}
			}
		}
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
		/*
		Stack<Integer> stack = rawData[x][y][z];
		if (stack.peek() != -3 || value == -4)
			stack.push(value);*/
	}
	
	public int getValue(int x, int y, int z)
	{
		if (x >= getWidth() || y >= getHeight() || z >= getDepth() || x < 0 || y < 0 || z < 0)
		{
			return 0;
		}
		return rawData[x][y][z];
	}
	
}

package jworldgen.generator;

import java.util.Stack;

public class World {
	private Stack<Integer>[][][] rawData;
	
	@SuppressWarnings("unchecked")
	public World(int width, int height, int depth)
	{
		rawData = (Stack<Integer>[][][]) new Stack[width][height][depth];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				for (int k = 0; k < depth; k++)
				{
					rawData[i][j][k] = new Stack<Integer>();
					rawData[i][j][k].push(0);
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
		if (rawData[x][y][z].peek() != -3 || value == -4)
			rawData[x][y][z].push(value);
	}
	
	public void replaceValue(int x, int y, int z, int value)
	{
		if (x >= getWidth() || y >= getHeight() || z >= getDepth() || x < 0 || y < 0 || z < 0 || value < 1)
		{
			return;
		}
		rawData[x][y][z].pop();
		rawData[x][y][z].push(value);
	}
	
	public int getValue(int x, int y, int z)
	{
		while(!rawData[x][y][z].empty() && rawData[x][y][z].peek() < 1)
		{
			switch(rawData[x][y][z].pop())
			{
			case 0:
				break;
			case -1:
				rawData[x][y][z].push(-1);
				return 0;
			case -2:
				while(!rawData[x][y][z].empty() && rawData[x][y][z].peek() == 0)
					rawData[x][y][z].pop();
				if (!rawData[x][y][z].empty())
					rawData[x][y][z].pop();
			case -3:
				break;
			case -4:
				if (rawData[x][y][z].peek() == -3)
					rawData[x][y][z].pop();
				break;
			}
		}
		if (rawData[x][y][z].empty())
			return 0;
		return rawData[x][y][z].peek();
	}
	
}

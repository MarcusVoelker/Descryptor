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
	
	public void initValue(int x, int y, int z)
	{
		rawData[x][y][z] = new Stack<Integer>();
		rawData[x][y][z].push(0);
	}
	public void setValue(int x, int y, int z, int value)
	{
		if (x >= getWidth() || y >= getHeight() || z >= getDepth() || x < 0 || y < 0 || z < 0)
		{
			return;
		}
		Stack<Integer> stack = rawData[x][y][z];
		if (stack.peek() != -3 || value == -4)
			stack.push(value);
	}
	
	public void replaceValue(int x, int y, int z, int value)
	{
		if (x >= getWidth() || y >= getHeight() || z >= getDepth() || x < 0 || y < 0 || z < 0 || value < 1)
		{
			return;
		}
		Stack<Integer> stack = rawData[x][y][z];
		stack.pop();
		stack.push(value);
	}
	
	public int getValue(int x, int y, int z)
	{
		Stack<Integer> stack = rawData[x][y][z];
		while(!stack.empty() && stack.peek() < 1)
		{
			switch(stack.pop())
			{
			case 0:
				break;
			case -1:
				stack.push(-1);
				return 0;
			case -2:
				while(!stack.empty() && stack.peek() == 0)
					stack.pop();
				if (!stack.empty())
					stack.pop();
			case -3:
				break;
			case -4:
				if (stack.peek() == -3)
					stack.pop();
				break;
			}
		}
		if (stack.empty())
			return 0;
		return stack.peek();
	}
	
}

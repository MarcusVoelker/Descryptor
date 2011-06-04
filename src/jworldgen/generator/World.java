package jworldgen.generator;

public class World {
	private int[][] rawData;
	
	public World(int width, int height)
	{
		rawData = new int[width][height];
	}
	
	public int getWidth()
	{
		return rawData.length;
	}
	
	public int getHeight()
	{
		return rawData[0].length;
	}
	
	public void setValue(int x, int y, int value)
	{
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0)
		{
			return;
		}
		rawData[x][y] = value;
	}
	
	public int getValue(int x, int y)
	{
		return rawData[x][y];
	}
	
	public String toString()
	{
		String result = "";
		for (int i = 0; i < getHeight();i++)
		{
			for (int j = 0; j < getWidth(); j++)
			{
				result += rawData[j][i];
			}
			result += "\n";
		}
		return result;
	}
}

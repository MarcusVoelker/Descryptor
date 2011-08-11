package descryptor.texgen.generator;

import descryptor.common.generator.AbstractProduct;

public class Texture extends AbstractProduct {
	private Pixel [][] rawData;
	
	public Texture(int width, int height)
	{
		rawData = new Pixel[width][height];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				rawData[i][j] = new Pixel();
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
	
	public void setValue(int x, int y, Pixel value)
	{
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0)
		{
			return;
		}
		rawData[x][y] = value;
	}
	
	public Pixel getValue(int x, int y)
	{
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0)
		{
			return null;
		}
		return rawData[x][y];
	}
}

package descryptor.texgen.generator;

import descryptor.common.generator.AbstractAtom;

public class Pixel extends AbstractAtom {
	public byte red;
	public byte green;
	public byte blue;
	public byte alpha;
	
	public Pixel()
	{
		red = 0;
		green = 0;
		blue = 0;
		alpha = 0;
	}
	
	public Pixel(byte red, byte green, byte blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = 1;
	}
	
	public Pixel(byte red, byte green, byte blue, byte alpha)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
}

package descryptor.texgen.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.filehandler.TextFileReader;
import descryptor.common.util.Util;
import descryptor.texgen.generator.Generator;
import descryptor.texgen.generator.Pixel;
import descryptor.texgen.generator.Texture;

public class TestStartup {
	
	static Texture texture;
	
	
	private static float heightFactor;
	private static float widthFactor;
	private static void load()
	{
		try {
			long seed = (long) Math.floor((Math.random()*Long.MAX_VALUE));
			Display.setTitle("Seed: "+Util.seedToSeedString(seed));
			String input = TextFileReader.readTextFile("data/TextureRules.txt");
			Generator gen = new Generator();
			gen.initialize(seed,new Object[]{input});
			texture = (Texture) gen.generateFully(new Object[]{50,50});
		} catch (CriticalFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void setup2D()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		widthFactor = Display.getDisplayMode().getWidth()/texture.getWidth();
		heightFactor = Display.getDisplayMode().getHeight()/texture.getHeight();
	}
	
	private static void determineColor(Pixel pixel)
	{
		GL11.glColor4f(pixel.red/256.0f, pixel.green/256.0f, pixel.blue/256.0f, pixel.alpha/256.0f);
	}
	
	private static void render2D()
	{
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			for (int i =0; i < texture.getWidth(); i++)
			{
				for (int j = 0; j < texture.getHeight(); j++)
				{
					determineColor(texture.getValue(i, j));
					// draw quad
					GL11.glBegin(GL11.GL_QUADS);
					    GL11.glVertex2f(widthFactor*i,heightFactor*j);
					    GL11.glVertex2f(widthFactor*(i+1),heightFactor*j);
					    GL11.glVertex2f(widthFactor*(i+1),heightFactor*(j+1));
					    GL11.glVertex2f(widthFactor*i,heightFactor*(j+1));
					GL11.glEnd();
				}
			}
			Display.update();
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
		{
			load();
		}
	}
	
	static boolean use3D = true;
	public static void main(String[] args)
	{
		try {
			Display.setDisplayMode(new DisplayMode(1000,1000));
			Display.create();
			load();
			setup2D();
			while(!Display.isCloseRequested())
			{
				render2D();
			}
			Display.destroy();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

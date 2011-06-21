package jworldgen.test;

import jworldgen.generator.PerlinGenerator;
import jworldgen.generator.RNG;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class TestPerlin {
	public static void main(String[] args)
	{
		try {
			PerlinGenerator perlin = new PerlinGenerator(new RNG(),100);
			Display.setDisplayMode(new DisplayMode(1000,1000));
			Display.create();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			int widthFactor = 1;
			int[] classes = new int[1000];
			for (int i =0; i < 100; i++)
			{
				for (int j = 0; j < 100; j++)
				{
					double noise = perlin.noise(i, j, 0, 10);
					classes[(int) Math.floor(noise*1000)]++;
				}
			}
			/*for (int i = 1 ; i < classes.length; i++)
			{
				classes[i] += classes[i-1]; 
			}*/
			while(!Display.isCloseRequested())
			{
				// Clear the screen and depth buffer
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
				
				for (int i = 0; i < classes.length; i++)
				{
					GL11.glColor3f(1-i/1000.0f,i/1000.0f,0);
					
					// draw quad
					GL11.glBegin(GL11.GL_QUADS);
					    GL11.glVertex2f(widthFactor*i,Display.getDisplayMode().getHeight());
					    GL11.glVertex2f(widthFactor*(i+1),Display.getDisplayMode().getHeight());
					    GL11.glVertex2f(widthFactor*(i+1),Display.getDisplayMode().getHeight()-classes[i]*20);
					    GL11.glVertex2f(widthFactor*i,Display.getDisplayMode().getHeight()-classes[i]*20);
					GL11.glEnd();
				}
				Display.update();
			}
			Display.destroy();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

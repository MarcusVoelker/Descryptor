package jworldgen.test;

import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.generator.Generator;
import jworldgen.generator.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class TestStartup {
	
	private static void setColor(int hue, float sat, float val, int max)
	{
		int h = 6*hue/max;
		float f = 6*(float) hue/max - h;
		float p = val*(1-sat);
		float q = val*(1-sat*f);
		float t = val*(1-sat*(1-f));
		if (h == 0 || h == 6)
			GL11.glColor3f(val,t,p);
		if (h == 1)
			GL11.glColor3f(q,val,p);
		if (h == 2)
			GL11.glColor3f(p,val,t);
		if (h == 3)
			GL11.glColor3f(p,q,val);
		if (h == 4)
			GL11.glColor3f(t,p,val);
		if (h == 5)
			GL11.glColor3f(val,p,q);
	}
	
	public static void main(String[] args)
	{
		try {
			Display.setDisplayMode(new DisplayMode(1024,1024));
			Display.create();
			World world = Generator.generateFromFile("data/TestRules.txt", 1024, 1024, 1);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			float widthFactor = Display.getDisplayMode().getWidth()/world.getWidth();
			float heightFactor = Display.getDisplayMode().getHeight()/world.getHeight();
			while(!Display.isCloseRequested())
			{
				// Clear the screen and depth buffer
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
				for (int k = 0; k < world.getDepth(); k++)
				{
					for (int i =0; i < world.getWidth(); i++)
					{
						for (int j = 0; j < world.getHeight(); j++)
						{
							int color = world.getValue(i, j, k);
							setColor(color,0.5f,color/10.0f,10);
							
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
				}
			}
			Display.destroy();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CriticalFailure e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

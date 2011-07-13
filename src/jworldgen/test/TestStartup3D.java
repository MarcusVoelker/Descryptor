package jworldgen.test;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;
import jworldgen.exceptionHandler.CriticalFailure;
import jworldgen.generator.Generator;
import jworldgen.generator.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class TestStartup3D {
	
	
	private static int cuttingDepth = 0;
	private static boolean pressed = false;
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
	
	private static boolean hasInput(int direction) 
	{
	    switch(direction) 
	    {
	    	case Keyboard.KEY_LEFT:
	        return
	          Keyboard.isKeyDown(Keyboard.KEY_LEFT);

	      case Keyboard.KEY_RIGHT:
	        return
	          Keyboard.isKeyDown(Keyboard.KEY_RIGHT);

	      case Keyboard.KEY_SPACE:
	        return
	          Keyboard.isKeyDown(Keyboard.KEY_SPACE);
	    }
		return false;
	}
	public static void main(String[] args)
	{
		try {
			Display.setDisplayMode(new DisplayMode(800,800));
			Display.create();
			World world = Generator.generateFromFile("data/WorleyRules.txt", 50, 50, 50);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GLU.gluPerspective(45.0f, 1.0f, 0.5f, 400.0f);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			glEnable(GL_DEPTH_TEST);
			float angle = 0.01f;
			while(!Display.isCloseRequested())
			{
				// Clear the screen and depth buffer
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glTranslatef(0, -15, -3*world.getDepth());
				GL11.glRotatef(angle, 0, 1, 0);
				GL11.glTranslatef(-0.5f*world.getWidth(), 0.5f*world.getHeight(),0);
				for (int k = cuttingDepth; k < world.getDepth() - cuttingDepth; k++)
				{
					GL11.glTranslatef(0.0f, 0.0f, -k);
					for (int i = cuttingDepth; i < world.getWidth() - cuttingDepth; i++)
					{
						GL11.glTranslatef(i, 0.0f, 0.0f);
						for (int j = cuttingDepth; j < world.getHeight() - cuttingDepth; j++)
						{
							int color = world.getValue(i, j, k);
							if (color != 0)
							{
								GL11.glTranslatef(0.0f, -j, 0.0f);
								setColor(color,0.5f,color/10.0f,10);
								GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
								// draw quad
								glEnable( GL11.GL_POLYGON_OFFSET_FILL );
								GL11.glPolygonOffset( 1, 1 );
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,0,0);
								    GL11.glVertex3f(0,1,0);
								    GL11.glVertex3f(1,1,0);
								    GL11.glVertex3f(1,0,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,1,1);
								    GL11.glVertex3f(1,1,1);
								    GL11.glVertex3f(1,1,0);
								    GL11.glVertex3f(0,1,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,1,0);
								    GL11.glVertex3f(0,1,1);
								    GL11.glVertex3f(0,0,1);
								    GL11.glVertex3f(0,0,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,0,1);
								    GL11.glVertex3f(0,1,1);
								    GL11.glVertex3f(1,1,1);
								    GL11.glVertex3f(1,0,1);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,0,1);
								    GL11.glVertex3f(1,0,1);
								    GL11.glVertex3f(1,0,0);
								    GL11.glVertex3f(0,0,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(1,1,0);
								    GL11.glVertex3f(1,1,1);
								    GL11.glVertex3f(1,0,1);
								    GL11.glVertex3f(1,0,0);
								GL11.glEnd();
								GL11.glDisable( GL11.GL_POLYGON_OFFSET_FILL );
								GL11.glColor3f(0.05f,0.05f,0.05f);
								GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
								// draw quad
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,0,0);
								    GL11.glVertex3f(0,1,0);
								    GL11.glVertex3f(1,1,0);
								    GL11.glVertex3f(1,0,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,1,1);
								    GL11.glVertex3f(1,1,1);
								    GL11.glVertex3f(1,1,0);
								    GL11.glVertex3f(0,1,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,1,0);
								    GL11.glVertex3f(0,1,1);
								    GL11.glVertex3f(0,0,1);
								    GL11.glVertex3f(0,0,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,0,1);
								    GL11.glVertex3f(0,1,1);
								    GL11.glVertex3f(1,1,1);
								    GL11.glVertex3f(1,0,1);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(0,0,1);
								    GL11.glVertex3f(1,0,1);
								    GL11.glVertex3f(1,0,0);
								    GL11.glVertex3f(0,0,0);
								GL11.glEnd();
								GL11.glBegin(GL11.GL_QUADS);
								    GL11.glVertex3f(1,1,0);
								    GL11.glVertex3f(1,1,1);
								    GL11.glVertex3f(1,0,1);
								    GL11.glVertex3f(1,0,0);
								GL11.glEnd();
								GL11.glTranslatef(0.0f, j, 0.0f);
							}
						}
						GL11.glTranslatef(-i, 0.0f, 0.0f);
					}
					GL11.glTranslatef(0.0f, 0.0f, k);
				}
				Display.update();
				GL11.glTranslatef(0.5f*world.getWidth(), -0.5f*world.getHeight(),0);
				GL11.glRotatef(angle, 0, -1, 0);
				GL11.glTranslatef(0, 15, 3*world.getDepth());
				angle += 1f;
				boolean leftPressed   = hasInput(Keyboard.KEY_LEFT);
				boolean rightPressed   = hasInput(Keyboard.KEY_RIGHT);
				
				if (leftPressed && !pressed) {
					cuttingDepth++;
					pressed = true;
				} else if (rightPressed && !pressed) {
					cuttingDepth--;
					if (cuttingDepth < 0)
						cuttingDepth = 0;
					pressed = true;
				}
				
				if (!leftPressed && !rightPressed)
				{
					pressed = false;
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

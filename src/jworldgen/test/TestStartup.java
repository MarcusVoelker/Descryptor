package jworldgen.test;

import jworldgen.filehandler.TextFileReader;
import jworldgen.generator.Generator;
import jworldgen.generator.RNG;
import jworldgen.generator.World;
import jworldgen.generator.worldStructure.Ruleset;
import jworldgen.parser.ParseException;
import jworldgen.parser.RuleParser;
import jworldgen.parser.parseStructure.ParseList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class TestStartup {
	public static void main(String[] args)
	{
		String input = TextFileReader.readTextFile("data/TestRules.txt");
		try {
			ParseList list = RuleParser.parse(input);
			Ruleset rules = new Ruleset(list);
			rules.expandToWorldTree(new RNG(0));
			Generator gen = new Generator(rules);
			World world = gen.generateFromSeed(0, 100, 100);
			System.out.println(rules.toString());
			Display.setDisplayMode(new DisplayMode(500,500));
			Display.create();
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
						
				for (int i =0; i < world.getWidth(); i++)
				{
					for (int j = 0; j < world.getHeight(); j++)
					{
						int color = world.getValue(i, j);
						GL11.glColor3f(color/6.0f,color/6.0f,color/6.0f);
						
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
			Display.destroy();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

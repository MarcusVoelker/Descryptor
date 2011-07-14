package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;

import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class WorleyModifier extends Modifier {
	
	protected VariableResolver resolver;
	protected ArrayList<Integer> xPos;
	protected ArrayList<Integer> yPos;
	protected ArrayList<Integer> zPos;
	
	protected int probSum;
	
	public WorleyModifier(String identifier, ArrayList<ParseAssignment> assignments)
	{
		super(identifier, assignments);
		this.type = ModifierType.WORLEY;
		this.identifier = identifier;
		this.assignments = assignments;
		resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
	}
	@Override
	public Modifier clone() {
		return new WorleyModifier(identifier, assignments);
	}

	@Override
	public void setLocation(int minX, int minY, int minZ, int maxX, int maxY,
			int maxZ) {
		super.setLocation(minX, minY, minZ, maxX, maxY, maxZ);
		xPos = new ArrayList<Integer>();
		yPos = new ArrayList<Integer>();
		zPos = new ArrayList<Integer>();
		for (int i = 0; i < (Integer) resolver.getVariable("count"); i++)
		{
			xPos.add(rng.nextInt(minX, maxX));
			yPos.add(rng.nextInt(minY, maxY));
			zPos.add(rng.nextInt(minZ, maxZ));
		}
	}
	@Override
	public float getValue(int x, int y, int z) {
		float nextDist = Float.MAX_VALUE;
		for (int i = 0; i < xPos.size(); i++)
		{
			float dist = (x-xPos.get(i))*(x-xPos.get(i))+(y-yPos.get(i))*(y-yPos.get(i))+(z-zPos.get(i))*(z-zPos.get(i));
			//float dist = Math.abs(x-xPos.get(i))+Math.abs(y-yPos.get(i))+Math.abs(z-zPos.get(i));
			if (dist < nextDist)
			{
				nextDist = dist;
			}
		}
		return nextDist;
	}
}
package descryptor.common.generator.modifiers;

import java.util.ArrayList;

import descryptor.common.ale.Assignment;
import descryptor.common.ale.VariableResolver;


public class VoronoiModifier extends Modifier {
	
	protected VariableResolver resolver;
	protected ArrayList<Integer> xPos;
	protected ArrayList<Integer> yPos;
	protected ArrayList<Integer> zPos;
	
	public VoronoiModifier(String identifier, ArrayList<Assignment> assignments)
	{
		super(identifier, assignments);
		this.type = ModifierType.VORONOI;
		this.identifier = identifier;
		this.assignments = assignments;
		resolver = new VariableResolver();
		if (assignments != null)
		{
			for (Assignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
	}
	@Override
	public Modifier clone() {
		return new VoronoiModifier(identifier, assignments);
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
		int value = 0;
		float nextDist = Float.MAX_VALUE;
		for (int i = 0; i < xPos.size(); i++)
		{
			float dist = (x-xPos.get(i))*(x-xPos.get(i))+(y-yPos.get(i))*(y-yPos.get(i))+(z-zPos.get(i))*(z-zPos.get(i));
			//float dist = Math.abs(x-xPos.get(i))+Math.abs(y-yPos.get(i))+Math.abs(z-zPos.get(i));
			if (dist < nextDist)
			{
				nextDist = dist;
				value = i;
			}
		}
		return value;
	}
}
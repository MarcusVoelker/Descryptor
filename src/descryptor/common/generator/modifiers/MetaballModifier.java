package descryptor.common.generator.modifiers;

import java.util.ArrayList;

import descryptor.common.ale.Assignment;
import descryptor.common.ale.VariableResolver;


public class MetaballModifier extends Modifier {
	
	protected float threshold;
	protected VariableResolver resolver;
	protected ArrayList<Integer> xPos;
	protected ArrayList<Integer> yPos;
	protected ArrayList<Integer> zPos;
	
	public MetaballModifier(String identifier, ArrayList<Assignment> assignments)
	{
		super(identifier, assignments);
		this.type = ModifierType.METABALL;
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
		threshold = resolver.getVariable("threshold").floatValue();
	}
	@Override
	public Modifier clone() {
		return new MetaballModifier(identifier, assignments);
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
		float value = 0;
		for (int i = 0; i < xPos.size(); i++)
		{
			float tempVal = 0;
			tempVal += (x-xPos.get(i))*(x-xPos.get(i));
			tempVal += (y-yPos.get(i))*(y-yPos.get(i));
			tempVal += (z-zPos.get(i))*(z-zPos.get(i));
				value += 1/tempVal;
		}
		
		if (value > threshold)
			return 1;
		return 0;
	}

}
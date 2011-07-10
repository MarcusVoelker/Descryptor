package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;

import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class MetaballModifier extends Modifier {
	
	protected int typeID;
	protected float threshold;
	protected VariableResolver resolver;
	protected ArrayList<Integer> xPos;
	protected ArrayList<Integer> yPos;
	protected ArrayList<Integer> zPos;
	
	public MetaballModifier(int typeID, String identifier, ArrayList<ParseAssignment> assignments)
	{
		super(identifier, assignments);
		this.type = ModifierType.METABALL;
		this.identifier = identifier;
		this.assignments = assignments;
		this.typeID = typeID;
		resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		threshold = (Float) resolver.getVariable("threshold");
	}
	@Override
	public Modifier clone() {
		return new MetaballModifier(typeID, identifier, assignments);
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
	public int getValue(int x, int y, int z) {
		float value = 0;
		for (int i = 0; i < xPos.size(); i++)
		{
			float tempVal = 0;
			tempVal += Math.abs(x-xPos.get(i));
			tempVal += Math.abs(y-yPos.get(i));
			tempVal += Math.abs(z-zPos.get(i));
				value += 1/tempVal;
		}
		
		if (value > threshold)
			return typeID;
		return 0;
	}

}
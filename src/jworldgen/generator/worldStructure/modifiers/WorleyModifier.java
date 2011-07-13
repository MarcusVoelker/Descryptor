package jworldgen.generator.worldStructure.modifiers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import jworldgen.generator.VariableResolver;
import jworldgen.parser.parseStructure.ParseAssignment;

public class WorleyModifier extends Modifier {
	
	protected Hashtable<Integer,Integer> probabilities;
	protected Hashtable<Integer,Integer> typeIDs;
	protected VariableResolver resolver;
	protected ArrayList<Integer> xPos;
	protected ArrayList<Integer> yPos;
	protected ArrayList<Integer> zPos;
	
	protected int probSum;
	protected int radius;
	
	public WorleyModifier(Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> typeIDs, String identifier, ArrayList<ParseAssignment> assignments, ChangeType chType)
	{
		super(identifier, assignments, chType);
		this.type = ModifierType.WORLEY;
		this.identifier = identifier;
		this.assignments = assignments;
		this.probabilities = probabilities;
		this.typeIDs = typeIDs;
		resolver = new VariableResolver();
		if (assignments != null)
		{
			for (ParseAssignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		radius = resolver.getVariable("radius").intValue();
		probSum = 0;
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			probSum += probabilities.get(e.nextElement());
		}
	}
	@Override
	public Modifier clone() {
		return new WorleyModifier(probabilities, typeIDs, identifier, assignments, chType);
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
		int curProb = probSum;
		for (Enumeration<Integer> e = probabilities.keys(); e.hasMoreElements();)
		{
			int key = e.nextElement();
			curProb -= probabilities.get(key);
			if (curProb/(float)probSum <= nextDist/(radius*radius))
			{
				return typeIDs.get(key);
			}
		}
		return 0;
	}
}
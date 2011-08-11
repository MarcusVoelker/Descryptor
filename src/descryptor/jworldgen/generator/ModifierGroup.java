package descryptor.jworldgen.generator;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import descryptor.common.RNG;
import descryptor.common.ale.ALE;
import descryptor.common.ale.Assignment;
import descryptor.common.ale.VariableResolver;
import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.common.exceptionHandler.exceptions.UnknownIdentifier;
import descryptor.common.generator.modifiers.ChangeType;
import descryptor.common.generator.modifiers.MetaballModifier;
import descryptor.common.generator.modifiers.Modifier;
import descryptor.common.generator.modifiers.PerlinModifier;
import descryptor.common.generator.modifiers.VoronoiModifier;
import descryptor.common.generator.modifiers.WeightedPerlinModifier;
import descryptor.common.generator.modifiers.WorleyModifier;


public class ModifierGroup{
	protected String identifier;
	protected RNG rng;
	protected int minX,minY,minZ,maxX,maxY,maxZ;
	protected int maxScale;
	protected Hashtable<String,Modifier> modifiers;
	private ArrayList<ALE> drawConstraints;
	private ArrayList<String> modifierNames;
	private ArrayList<Integer> typeIDs;
	
	protected VariableResolver resolver;
	protected ArrayList<Assignment> assignments;
	protected ChangeType changeType;

	
	public ModifierGroup(String identifier, ArrayList<Assignment> assignments, ArrayList<ALE> drawConstraints, ArrayList<Integer> typeIDs, ArrayList<String> modifierNames, ChangeType changeType)
	{
		this.identifier = identifier;
		this.assignments = assignments;
		this.drawConstraints = drawConstraints;
		this.modifierNames = modifierNames;
		this.typeIDs = typeIDs;
		this.changeType = changeType;
	}
	
	public ModifierGroup(String identifier, ArrayList<Assignment> assignments, ArrayList<ALE> drawConstraints, ArrayList<Integer> typeIDs, Hashtable<String,Modifier> modifiers, ChangeType changeType)
	{
		this.identifier = identifier;
		this.assignments = assignments;
		this.drawConstraints = drawConstraints;
		this.modifiers = modifiers;
		this.typeIDs = typeIDs;
		this.changeType = changeType;
	}
	
	public void setRNG(RNG rng)
	{
		this.rng = rng;
	}
	
	public void setLocation(int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
	{
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		this.maxScale = Math.max(Math.max(maxX - minX, maxY - minY), maxZ - minZ);
	}
	
	public ModifierGroup clone()
	{
		return new ModifierGroup(identifier,assignments,drawConstraints,typeIDs,modifiers,changeType);
	}
	
	public ChangeType getChangeType()
	{
		return changeType;
	}
	
	public void addModifiers(Hashtable<String,Modifier> modifiers)
	{
		this.modifiers = new Hashtable<String,Modifier>();
		for (String modName : modifierNames)
		{
			if (modifiers.containsKey(modName))
			{
				this.modifiers.put(modName, modifiers.get(modName));
			}
			else
			{
				try {
					ExceptionLogger.logException(new UnknownIdentifier(modName), LoggerLevel.ERROR);
				} catch (CriticalFailure e) {
					//Should not be reachable
				}
			}
		}
	}
	public void prepareForFilling (RNG rng)
	{
		for (Enumeration<String> e = modifiers.keys(); e.hasMoreElements();)
		{
			Modifier mod = modifiers.get(e.nextElement());
			switch(mod.getType())
			{
			case PERLIN:
				((PerlinModifier) mod).setRNG(rng, maxScale);
				mod.setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case WEIGHTED_PERLIN:
				((WeightedPerlinModifier) mod).setRNG(rng, maxScale);
				mod.setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case METABALL:
				mod.setRNG(rng);
				((MetaballModifier) mod).setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case VORONOI:
				mod.setRNG(rng);
				((VoronoiModifier) mod).setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			case WORLEY:
				mod.setRNG(rng);
				((WorleyModifier) mod).setLocation(minX, minY, minZ, maxX, maxY, maxZ);
				break;
			}
		}
		resolver = new VariableResolver();
		for (Enumeration<String> e = modifiers.keys(); e.hasMoreElements();)
		{
			String key = e.nextElement();
			resolver.addFunction(key, modifiers.get(key));
		}
		
		resolver.setVariable("minX", minX);
		resolver.setVariable("minY", minY);
		resolver.setVariable("minZ", minZ);
		resolver.setVariable("maxX", maxX);
		resolver.setVariable("maxY", maxY);
		resolver.setVariable("maxZ", maxZ);
	}
	public Integer getValue(int x, int y, int z)
	{
		VariableResolver resolver = this.resolver.clone();
		for (Enumeration<String> e = modifiers.keys(); e.hasMoreElements();)
		{
			String key = e.nextElement();
			resolver.setVariable(key, modifiers.get(key).getValue(x, y, z));
		}
		resolver.setVariable("xPos", x);
		resolver.setVariable("yPos", y);
		resolver.setVariable("zPos", z);
		
		if (assignments != null)
		{
			for (Assignment assignment : assignments)
			{
				assignment.evaluate(rng, resolver);
			}
		}
		for (int i = 0; i < drawConstraints.size(); i++)
		{
			if (drawConstraints.get(i) == null || drawConstraints.get(i).evaluate(rng, resolver).intValue() != 0)
			{
				return typeIDs.get(i);
			}
		}
		return null;
	}
}

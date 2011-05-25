package jworldgen.parser.parseStructure;

import java.util.ArrayList;
import java.util.Hashtable;

public class TreeNodeArea {
	private ArrayList<ParseSubArea> subAreas;
	private Hashtable<Integer,Integer> probabilities;	
	private Hashtable<Integer,Integer> tileIDs;
	
	private int count;
	private int countVariance;
	private float xPos;
	private float xPosVar;
	private float yPos;
	private float yPosVar;
	private float width;
	private float widthVar;
	private float height;
	private float heightVar;
	
	public TreeNodeArea(ArrayList<ParseSubArea> subAreas, Hashtable<Integer,Integer> probabilities, Hashtable<Integer,Integer> tileIDs)
	{
		this.subAreas = subAreas;
		this.probabilities = probabilities;
		this.tileIDs = tileIDs;
	}
	
	public void setCount(int value, int variance)
	{
		this.count = value;
		this.countVariance = variance;
	}
	
	public void setXPos(float value, float variance)
	{
		this.xPos = value;
		this.xPosVar = variance;
	}
	
	public void setYPos(float value, float variance)
	{
		this.yPos = value;
		this.yPosVar = variance;
	}
	
	public void setWidth(float value, float variance)
	{
		this.width = value;
		this.widthVar = variance;
	}
	
	public void setHeight(float value, float variance)
	{
		this.height = value;
		this.heightVar = variance;
	}
}

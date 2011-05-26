package jworldgen.generator.worldStructure;

public class TreeNodeRoom {
	private int floorID;
	private int ceilingID;
	private int wallID;
	private int backgroundID;
	private String identifier;
	
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
	
	public TreeNodeRoom(int floorID, int ceilingID, int wallID, int backgroundID, String identifier)
	{
		this.floorID = floorID;
		this.ceilingID = ceilingID;
		this.wallID = wallID;
		this.backgroundID = backgroundID;
		this.identifier = identifier;
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
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public TreeNodeRoom clone()
	{
		return new TreeNodeRoom(floorID,ceilingID,wallID,backgroundID,identifier);
	}
}

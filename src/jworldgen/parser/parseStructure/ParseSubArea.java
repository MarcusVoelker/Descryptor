package jworldgen.parser.parseStructure;

public class ParseSubArea {
	private String areaType;
	private boolean isArea;
	private int count;
	private int countVariance;
	
	// $i = -1; $n = -2
	private float xPos;
	private float xPosVar;
	private float yPos;
	private float yPosVar;
	private float width;
	private float widthVar;
	private float height;
	private float heightVar;
	
	public void setAreaType (String areaType)
	{
		this.areaType = areaType;
		this.isArea = true;
	}
	
	public void setRoomType (String roomType)
	{
		this.areaType = roomType;
		this.isArea = false;
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

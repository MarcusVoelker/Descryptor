package jworldgen.parser.parseStructure;

import java.util.ArrayList;

public class ParseList {
	private ArrayList<ParseRoom> rooms;
	private ArrayList<ParseArea> areas;
	
	public ParseList()
	{
		rooms = new ArrayList<ParseRoom>();
		areas = new ArrayList<ParseArea>();
	}
	
	public void addRoom (ParseRoom room)
	{
		rooms.add(room);
	}
	
	public void addArea(ParseArea area)
	{
		areas.add(area);
	}
	
	public String toString()
	{
		return (rooms.size()+"|"+areas.size());
	}
}

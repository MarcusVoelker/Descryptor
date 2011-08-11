package descryptor.jworldgen.parser.parseStructure;

import java.util.ArrayList;

import descryptor.common.parser.ParseObject;
import descryptor.common.parser.parseStructure.ParseAssignment;

public class ParseSubArea extends ParseObject{
	public String areaType;
	public boolean isArea;
	
	public ArrayList<ParseAssignment> assignments;
	
	public ParseSubArea()
	{
		this.assignments = new ArrayList<ParseAssignment>();
	}
}

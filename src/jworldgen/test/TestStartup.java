package jworldgen.test;

import jworldgen.filehandler.TextFileReader;
import jworldgen.parser.ParseException;
import jworldgen.parser.RuleParser;

public class TestStartup {
	public static void main(String[] args)
	{
		String input = TextFileReader.readTextFile("data/TestRules.txt");
		try {
			RuleParser.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

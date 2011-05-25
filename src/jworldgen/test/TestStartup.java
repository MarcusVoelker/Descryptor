package jworldgen.test;

import jworldgen.filehandler.TextFileReader;
import jworldgen.parser.ParseException;
import jworldgen.parser.RuleParser;
import jworldgen.parser.parseStructure.ParseList;
import jworldgen.parser.parseStructure.Ruleset;

public class TestStartup {
	public static void main(String[] args)
	{
		String input = TextFileReader.readTextFile("data/TestRules.txt");
		try {
			ParseList list = RuleParser.parse(input);
			Ruleset rules = new Ruleset(list);
			rules.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

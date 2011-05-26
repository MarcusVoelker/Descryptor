package jworldgen.test;

import jworldgen.filehandler.TextFileReader;
import jworldgen.generator.worldStructure.Ruleset;
import jworldgen.parser.ParseException;
import jworldgen.parser.RuleParser;
import jworldgen.parser.parseStructure.ParseList;

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

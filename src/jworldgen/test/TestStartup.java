package jworldgen.test;

import jworldgen.filehandler.TextFileReader;
import jworldgen.generator.Generator;
import jworldgen.generator.RNG;
import jworldgen.generator.World;
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
			rules.expandToWorldTree(new RNG(0));
			Generator gen = new Generator(rules);
			World world = gen.generateFromSeed(0, 100, 100);
			System.out.println(world.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

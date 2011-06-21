package jworldgen.parser.parseStructure;

import java.util.PriorityQueue;
import java.util.Stack;

import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;

public class ParseALE {
	private PriorityQueue<ALEQueueElement> postfix;
	
	public ParseALE(PriorityQueue<ALEQueueElement> postfix)
	{
		this.postfix = postfix;
	}
	
	public Number evaluate(RNG rng, VariableResolver resolver)
	{
		Stack<Number> evaluation = new Stack<Number>();
		while(!postfix.isEmpty())
		{
			ALEQueueElement element = postfix.poll();
			switch(element.type.arity)
			{
				case 0:
					evaluation.push(element.toValue(rng,resolver));
					break;
				case 2:
					Number v2 = evaluation.pop();
					Number v1 = evaluation.pop();
					evaluation.push(element.toValue(v1,v2,rng));
					break;
			}
		}
		return evaluation.pop();
	}
	
	
}

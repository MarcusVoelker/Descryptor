package jworldgen.parser.parseStructure;

import java.util.PriorityQueue;
import java.util.Stack;

import jworldgen.generator.RNG;

public class ParseALE {
	private PriorityQueue<ALEQueueElement> postfix;
	private ALEElementType resultType;
	
	public ParseALE(PriorityQueue<ALEQueueElement> postfix, ALEElementType resultType)
	{
		this.postfix = postfix;
		this.resultType = resultType;
	}
	
	public Number evaluate(RNG rng)
	{
		Stack<Number> evaluation = new Stack<Number>();
		while(!postfix.isEmpty())
		{
			ALEQueueElement element = postfix.poll();
			switch(element.type.arity)
			{
				case 0:
					evaluation.push(element.toValue(resultType,rng));
					break;
				case 2:
					Number v2 = evaluation.pop();
					Number v1 = evaluation.pop();
					evaluation.push(element.toValue(v1,v2,resultType,rng));
					break;
			}
		}
		return evaluation.pop();
	}
}

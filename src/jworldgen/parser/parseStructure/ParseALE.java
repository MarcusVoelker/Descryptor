package jworldgen.parser.parseStructure;

import java.util.LinkedList;
import java.util.Stack;

import jworldgen.generator.RNG;
import jworldgen.generator.VariableResolver;

public class ParseALE {
	private LinkedList<ALEQueueElement> postfix;
	
	public ParseALE(LinkedList<ALEQueueElement> postfix)
	{
		this.postfix = postfix;
	}
	
	public Number evaluate(RNG rng, VariableResolver resolver)
	{
		Stack<Number> evaluation = new Stack<Number>();
		@SuppressWarnings("unchecked")
		LinkedList<ALEQueueElement> postCopy = (LinkedList<ALEQueueElement>) postfix.clone();
		while(!postCopy.isEmpty())
		{
			ALEQueueElement element = postCopy.poll();
			switch(element.type.arity)
			{
				case 0:
					evaluation.push(element.toValue(rng,resolver));
					break;
				case 1:
					Number v = evaluation.pop();
					evaluation.push(element.toValue(v,rng));
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
	
	public String toString()
	{
		String result = "";
		for (ALEQueueElement aqe : postfix)
		{
			result += aqe.identifier + " ";
		}
		return result;
	}
	
}

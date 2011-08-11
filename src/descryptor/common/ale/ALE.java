package descryptor.common.ale;

import java.util.LinkedList;
import java.util.Stack;

import descryptor.common.RNG;


public class ALE {
	private LinkedList<ALEQueueElement> postfix;
	
	public ALE(LinkedList<ALEQueueElement> postfix)
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
			if (element.type == ALEElementType.FUNCTION)
			{
				int startPos = element.identifier.indexOf("|")+1;
				String arityString = element.identifier.substring(startPos,element.identifier.length());
				int arity = Integer.parseInt(arityString); 
				Number[] params = new Number[arity];
				for (int i = 0; i < params.length; i++)
				{
					params[i] = evaluation.pop();
				}
				evaluation.push(resolver.evaluateFunction(element.identifier.substring(0,startPos-1), params));
			}
			else
			{
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

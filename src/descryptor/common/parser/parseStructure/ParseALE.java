package descryptor.common.parser.parseStructure;

import java.util.LinkedList;
import java.util.Stack;

import descryptor.common.ale.ALE;
import descryptor.common.ale.ALEElementType;
import descryptor.common.ale.ALEQueueElement;
import descryptor.common.parser.ParseObject;

public class ParseALE extends ParseObject{
	
	private LinkedList<ALEQueueElement> infix;
	public ParseALE(LinkedList<ALEQueueElement> infix)
	{
		this.infix = infix;
	}
	
	//Increments the arity coded into the identifier of the topmost function 
	private static void incTopFunction(Stack<ALEQueueElement> opStack)
	  {
		opStack.pop();
		if (!opStack.empty())
		{
		  	ALEQueueElement ele = (ALEQueueElement) opStack.peek();
			if (ele.type == ALEElementType.FUNCTION)
			{
			  int startPos = ele.identifier.indexOf("|")+1;
			  String arityString = ele.identifier.substring(startPos,ele.identifier.length());
			  int newArity = Integer.parseInt(arityString)+1;
			  ele.identifier = ele.identifier.substring(0,startPos-1)+"|"+newArity;
			}
		}
		opStack.push(new ALEQueueElement("(", ALEElementType.WRAPPER));
	  }
	
	//Implementation of Shunting-Yard
	public ALE toALE()
	{
		LinkedList<ALEQueueElement> postfix = new LinkedList<ALEQueueElement>();
		Stack<ALEQueueElement> stack = new Stack<ALEQueueElement>();
		while(!infix.isEmpty())
		{
			ALEQueueElement top = infix.poll();
			if (top.type == ALEElementType.WRAPPER) {
				//Special characters not present in the postfix representation
				if (top.identifier.equals("(")) {
					stack.push(top);
				} else if (top.identifier.equals(",")) {
					while (!stack.peek().identifier.equals("("))
					{
						postfix.add(stack.pop());
					}
					incTopFunction(stack);
				} else if (top.identifier.equals(")")) {
					while (!stack.peek().identifier.equals("("))
					{
						postfix.add(stack.pop());
					}
					incTopFunction(stack);
					stack.pop();
				}
			} else if (top.type.priority == -1 && top.type.arity == 0) {
				//Literal or Variable (0-ary function)
				postfix.add(top);
			} else if (top.type.priority == -1) {
				//Function
				stack.push(top);
			} else {
				//Operator
				while (!stack.empty() && !stack.peek().identifier.equals("(") && stack.peek().type.priority < top.type.priority)
				{
					postfix.add(stack.pop());
				}
				stack.push(top);
			}
		}
		while (!stack.empty())
		{
			postfix.add(stack.pop());
		}
		return new ALE(postfix);
	}
	
	public String toString()
	{
		String result = "";
		for (ALEQueueElement e : infix)
		{
			result += e.identifier+" ";
		}
		return result;
	}
}

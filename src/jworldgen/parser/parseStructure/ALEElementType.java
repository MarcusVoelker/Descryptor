package jworldgen.parser.parseStructure;

public enum ALEElementType {
	//0-ary nodes
	VARIABLE(0), INTEGER(0), FLOAT(0),
	//unary nodes
	SINE(1), COSINE(1), TANGENT(1), ARCSINE(1), ARCCOSINE(1), ARCTANGENT(1), SQRT(1),
	//binary nodes
	PLUS(2), MINUS(2), MULTIPLY(2), DIVIDE(2), RANDOM(2),
	//Error
	ERROR(-1);
	
	public int arity;
	private ALEElementType(int arity)
	{
		this.arity = arity;
	}
}

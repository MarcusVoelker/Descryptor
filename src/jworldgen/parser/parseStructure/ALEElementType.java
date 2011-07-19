package jworldgen.parser.parseStructure;

public enum ALEElementType {
	
	//0-ary nodes
	VARIABLE	( 0, -1, ALEReturnType.INPUT		), 
	INTEGER		( 0, -1, ALEReturnType.ALWAYS_INT	), 
	FLOAT		( 0, -1, ALEReturnType.ALWAYS_FLOAT	),
	
	//unary nodes
	SINE		( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	COSINE		( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	TANGENT		( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	ARCSINE		( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	ARCCOSINE	( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	ARCTANGENT	( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	SQRT		( 1, -1, ALEReturnType.ALWAYS_FLOAT	), 
	ABSOLUTE	( 1, -1, ALEReturnType.INPUT		),
	NOT			( 1,  6, ALEReturnType.ALWAYS_INT	),
	BITWISE_NOT	( 1,  0, ALEReturnType.ALWAYS_INT	),
	//binary nodes
	PLUS		( 2,  4, ALEReturnType.INPUT		), 
	MINUS		( 2,  4, ALEReturnType.INPUT		), 
	MULTIPLY	( 2,  3, ALEReturnType.INPUT		), 
	DIVIDE		( 2,  3, ALEReturnType.INPUT		),
	MODULO		( 2,  3, ALEReturnType.ALWAYS_INT	),
	RANDOM		( 2,  2, ALEReturnType.INPUT		), 
	EQUALS		( 2,  5, ALEReturnType.ALWAYS_INT	),
	NEQUALS		( 2,  5, ALEReturnType.ALWAYS_INT	), 
	GREATER		( 2,  5, ALEReturnType.ALWAYS_INT	), 
	GOE			( 2,  5, ALEReturnType.ALWAYS_INT	), 
	LESS		( 2 , 5, ALEReturnType.ALWAYS_INT	), 
	LOE			( 2,  5, ALEReturnType.ALWAYS_INT	),
	AND			( 2,  7, ALEReturnType.ALWAYS_INT	),
	OR			( 2,  7, ALEReturnType.ALWAYS_INT	),
	BITWISE_AND	( 2,  1, ALEReturnType.ALWAYS_INT	),
	BITWISE_OR	( 2,  1, ALEReturnType.ALWAYS_INT	),
	//Error
	ERROR		(-1, -1, ALEReturnType.ALWAYS_INT	);
	
	public final int arity;
	public final int priority;
	public final ALEReturnType returnType;
	private ALEElementType(int arity, int priority, ALEReturnType returnType)
	{
		this.arity = arity;
		this.priority = priority;
		this.returnType = returnType;
	}
}

enum ALEReturnType {
	ALWAYS_INT, ALWAYS_FLOAT, INPUT
}

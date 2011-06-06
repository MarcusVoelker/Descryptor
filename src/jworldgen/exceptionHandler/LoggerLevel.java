package jworldgen.exceptionHandler;

public enum LoggerLevel {
	NONE (0, ""), CRITICAL (1, "CRITICAL"), ERROR (2, "ERROR"), WARNING (3, "WARNING"), COARSE (4, "Log"), FINE (5, "Log"), FINEST (6, "Log");
	
	private int value;
	private String logKind;
	LoggerLevel(int value, String logKind)
	{
		this.value = value;
		this.logKind = logKind;
	}
	public int getValue()
	{
		return value;
	}
	public String getLogKind()
	{
		return logKind;
	}
}
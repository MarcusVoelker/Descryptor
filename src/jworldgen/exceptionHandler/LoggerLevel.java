package jworldgen.exceptionHandler;

public enum LoggerLevel {
	NONE (0, ""), CRITICAL (1, "CRITICAL"), ERROR (2, "ERROR"), COARSE (3, "Log"), FINE (4, "Log"), FINEST (5, "Log");
	
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
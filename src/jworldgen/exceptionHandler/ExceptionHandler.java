package jworldgen.exceptionHandler;

public class ExceptionHandler {
	private final static LoggerLevel level = LoggerLevel.FINEST;
	public static void logException(Throwable e, LoggerLevel logLevel)
	{
		try {
			throw e;
		} catch (JWorldGenException e1) {
			if (level.getValue() >= logLevel.getValue())
			{
				System.err.println(logLevel+": "+e1.getMessage());
			}
		} catch (Throwable e1) {
			if (level.getValue() >= logLevel.getValue())
			{
				System.err.println(logLevel+": "+e1.getMessage());
			}
		} finally {
			
		}
	}
	
	public static void log(String logString, LoggerLevel logLevel)
	{
		if (level.getValue() >= logLevel.getValue())
		{
			System.out.println(level.getLogKind()+": "+logString);
		}
	}
}


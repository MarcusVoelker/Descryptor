package jworldgen.exceptionHandler;

public class ExceptionLogger {
	private final static LoggerLevel level = LoggerLevel.COARSE;
	public static void logException(Throwable e, LoggerLevel logLevel) throws CriticalFailure
	{
		try {
			throw e;
		} catch (JWorldGenException e1) {
			if (level.getValue() >= logLevel.getValue())
			{
				System.err.println(logLevel+": "+e1.getMessage());
			}
		} catch (Throwable e1) {
			if (logLevel == LoggerLevel.CRITICAL)
			{
				e.printStackTrace();
			}
			if (level.getValue() >= logLevel.getValue())
			{
				System.err.println(logLevel+": "+e1.getMessage());
			}
		} finally {
			if (logLevel == LoggerLevel.CRITICAL)
			{
				throw new CriticalFailure(e.getMessage());
			}
		}
	}
	
	public static void log(String logString, LoggerLevel logLevel)
	{
		if (level.getValue() >= logLevel.getValue())
		{
			if (logLevel.getValue() <= LoggerLevel.WARNING.getValue())	
			{
				System.err.println(logLevel.getLogKind()+": "+logString);
			}
			else
			{
				System.out.println(logLevel.getLogKind()+": "+logString);
			}
		}
	}
}


package descryptor.jworldgen.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import descryptor.common.exceptionHandler.ExceptionLogger;
import descryptor.common.exceptionHandler.LoggerLevel;
import descryptor.common.exceptionHandler.exceptions.CriticalFailure;
import descryptor.jworldgen.generator.World;

public class WorldSaver {
	public static void saveWorld (World world, String filename) throws CriticalFailure
	{
		File file = new File(filename);
		BufferedWriter writer = null;

		try {
			if (!file.exists())
			{
				file.createNewFile();
			}
			else
			{
				file.delete();
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file));

			writer.write(world.getWidth());
			writer.write(world.getHeight());
			writer.write(world.getDepth());
			for (int i = 0 ; i < world.getWidth(); i++)
			{
				for (int j = 0; j < world.getHeight(); j++)
				{
					for (int k = 0; k < world.getDepth(); k++)
					{
						writer.write(world.getValue(i, j, k));
					}
				}
			}

		} catch (FileNotFoundException e) {
			ExceptionLogger.logException(e, LoggerLevel.CRITICAL);
		} catch (IOException e) {
			ExceptionLogger.logException(e, LoggerLevel.CRITICAL);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				ExceptionLogger.logException(e, LoggerLevel.CRITICAL);
			}
		}
	}
}

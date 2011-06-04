package jworldgen.filehandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import jworldgen.exceptionHandler.ExceptionHandler;
import jworldgen.exceptionHandler.LoggerLevel;

public class TextFileReader {

	public static String readTextFile(String filename) {
		File file = new File(filename);
		StringBuffer contents = new StringBuffer();
	    BufferedReader reader = null;

	    try {
	    	reader = new BufferedReader(new FileReader(file));
	        String text = null;

	        while ((text = reader.readLine()) != null)
	        	contents.append(text).append(System.getProperty("line.separator"));

	        } catch (FileNotFoundException e) {
	            ExceptionHandler.logException(e, LoggerLevel.CRITICAL);
	        } catch (IOException e) {
	        	ExceptionHandler.logException(e, LoggerLevel.CRITICAL);
	        } finally {
	            try {
	                if (reader != null) {
	                    reader.close();
	                }
	            } catch (IOException e) {
	            	ExceptionHandler.logException(e, LoggerLevel.CRITICAL);
	            }
	        }

	        return contents.toString();
	    }
	}



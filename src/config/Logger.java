package config;

import java.io.PrintWriter;

public class Logger {
	private static volatile Logger logger = null;
	private static PrintWriter writer;

	private Logger() {
		try {
		writer = new PrintWriter("the-file-name.txt", "UTF-8");
		}
		catch(Exception ex){
			//throw Exception
		}
	}
	
	public static Logger getLogger() {
		if (logger == null) {
			logger = new Logger();
		}
		
		return logger;
	}
	
	public synchronized void printMessage(String severity, String message) {
		writer.println("severity : " + severity);
		writer.println("message  : " + message);
	}
	
	public synchronized void printException(Exception ex) {
		writer.println("function : " + ex.getStackTrace()[0].getMethodName());
		writer.println("Line: " + ex.getStackTrace()[0].getLineNumber());
		writer.println("Stack : " + ex.getStackTrace().toString());
	}
}

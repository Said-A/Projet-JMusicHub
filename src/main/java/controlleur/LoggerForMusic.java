package main.java.controlleur;

import java.io.IOException;
import java.util.logging.*;
import java.util.logging.SimpleFormatter;


public class LoggerForMusic{

	Logger logger;
	FileHandler fh;

	public LoggerForMusic(){
		logger = Logger.getLogger("flogger");
		try {
			FileHandler fh=new FileHandler("Log.txt");
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void LogInfo(String logg){
		logger.log(Level.INFO,logg);
				
	}
	public void LogWarning(String logg) {
		logger.log(Level.WARNING,logg);
	}

}

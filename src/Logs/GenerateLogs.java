package Logs;

import org.testng.log4testng.Logger;

public class GenerateLogs {

	static Logger logger = Logger.getLogger(GenerateLogs.class);

	public static void INFO(String message) {
		logger.info(message);
	}

	public static void ERROR(String Errormessage) {
		logger.error(Errormessage);
	}

	public static void DEBBUG(String debugmessage) {
		logger.debug(debugmessage);
	}

}

package utilities;

import org.apache.log4j.Logger;

/**
 * <h1>Assessment Test</h1>
 * Logger Utilities for the BDD framework
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class LoggerUtils {
	private static Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public static void error(String message) {
		logger.error(message);
	}

	public static void info(String message) {
		logger.info(message);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}
}

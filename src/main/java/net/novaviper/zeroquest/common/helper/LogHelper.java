package net.novaviper.zeroquest.common.helper;

import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
import net.novaviper.zeroquest.common.lib.Constants;

import org.apache.logging.log4j.Level;

/**
 * @author ProPercivalalb
 */
public class LogHelper {

	public static FMLRelaunchLog instance = FMLRelaunchLog.log;

	private static void log(Level level, String format, Object... data) {
		FMLRelaunchLog.log(Constants.name, level, format, data);
	}

	/**
	 * Tells there is an error in the application, possibly recoverable.
	 */
	public static void error(String format, Object... data) {
		log(Level.ERROR, format, data);
	}

	/**
	 * Tells there is a severe error that will prevent the application from
	 * continuing.
	 */
	public static void fatal(String format, Object... data) {
		log(Level.FATAL, format, data);
	}

	/**
	 * Tells there is an event that might possible lead to an error.
	 */
	public static void warn(String format, Object... data) {
		log(Level.WARN, format, data);
	}

	/**
	 * Tells an event for informational purposes.
	 */
	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	/**
	 * Tells a general debugging event.
	 */
	public static void debug(String format, Object... data) {
		log(Level.DEBUG, format, data);
	}

	/**
	 * Tells a fine-grained debug message, typically capturing the flow through
	 * the application.
	 */
	public static void trace(String format, Object... data) {
		log(Level.TRACE, format, data);
	}

	/**
	 * Tells that all events should be logged.
	 */
	public static void all(String format, Object... data) {
		log(Level.ALL, format, data);
	}
}

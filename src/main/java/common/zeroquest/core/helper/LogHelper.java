package common.zeroquest.core.helper;

import net.minecraftforge.fml.relauncher.FMLRelaunchLog;

import org.apache.logging.log4j.Level;

import common.zeroquest.lib.Constants;

/**
 * @author ProPercivalalb
 */
public class LogHelper {

	public static FMLRelaunchLog instance = FMLRelaunchLog.log;

	private static void log(Level level, String format, Object... data) {
		FMLRelaunchLog.log("Zero Quest", level, format, data);
	}

	public static void error(String format, Object... data) {
		log(Level.ERROR, format, data);
	}

	public static void fatal(String format, Object... data) {
		log(Level.FATAL, format, data);
	}

	public static void warn(String format, Object... data) {
		log(Level.WARN, format, data);
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	public static void debug(String format, Object... data) {
		log(Level.DEBUG, format, data);
	}

	public static void trace(String format, Object... data) {
		log(Level.TRACE, format, data);
	}

	public static void all(String format, Object... data) {
		log(Level.ALL, format, data);
	}
}

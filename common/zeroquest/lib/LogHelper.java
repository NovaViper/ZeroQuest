package common.zeroquest.lib;


import java.util.logging.Level;
import java.util.logging.Logger;

import common.zeroquest.ZeroQuest;

import cpw.mods.fml.common.FMLLog;


/**
 * @author ProPercivalalb
 */
public class LogHelper {


    private static Logger mapLogger = Logger.getLogger(ZeroQuest.modid);


    public static void init() {
    	mapLogger.setParent(FMLLog.getLogger());
    }


    public static void log(Level logLevel, String message) {
    	mapLogger.log(logLevel, message);
    }
    
    public static void logWarning(String message) {
    	mapLogger.log(Level.WARNING, message);
    }
    
    public static void logInfo(String message) {
    	mapLogger.log(Level.INFO, message);
    }


    public static void logSevere(String message) {
    	mapLogger.log(Level.SEVERE, message);
    }
}


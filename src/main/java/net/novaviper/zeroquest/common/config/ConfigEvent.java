package net.novaviper.zeroquest.common.config;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.novaviper.zeroquest.common.lib.Constants;

public class ConfigEvent {
	
	   @SubscribeEvent
	   public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		   if(event.modID.equalsIgnoreCase(Constants.modid))
		   {
			   ConfigHandler.loadConfig();
		   }
	   }

}

package common.zeroquest.events;

import common.zeroquest.ZeroQuest;
import common.zeroquest.core.handlers.ConfigHandler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigEvent {
	
	   @SubscribeEvent
	   public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		   if(event.modID.equalsIgnoreCase(ZeroQuest.modid))
		   {
			   ConfigHandler.loadConfig();
		   }
	   }

}

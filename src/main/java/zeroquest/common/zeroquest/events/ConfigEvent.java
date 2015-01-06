package common.zeroquest.events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import common.zeroquest.core.handlers.ConfigHandler;
import common.zeroquest.lib.Constants;

public class ConfigEvent {
	
	   @SubscribeEvent
	   public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		   if(event.modID.equalsIgnoreCase(Constants.modid))
		   {
			   ConfigHandler.loadConfig();
		   }
	   }

}

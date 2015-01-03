package common.zeroquest.events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import common.zeroquest.ZeroQuest;
import common.zeroquest.core.handlers.ConfigHandler;

public class ConfigEvent {
	
	   @SubscribeEvent
	   public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		   if(event.modID.equalsIgnoreCase(ZeroQuest.modid))
		   {
			   ConfigHandler.loadConfig();
		   }
	   }

}

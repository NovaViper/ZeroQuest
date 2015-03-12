package common.zeroquest.events;

import common.zeroquest.entity.EntityMetalZertum;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingEvents {
	
	@SubscribeEvent
	public void blastResist(LivingHurtEvent event){
		if(event.entity instanceof EntityMetalZertum){	
			if(event.source.isExplosion()){
				if(EntityMetalZertum.blastResist != null){
					event.ammount = (float)(event.ammount / ((EntityLivingBase)event.entity).getEntityAttribute(EntityMetalZertum.blastResist).getBaseValue());
				}
			}
		}
		//System.out.println(event.ammount);
	}
}

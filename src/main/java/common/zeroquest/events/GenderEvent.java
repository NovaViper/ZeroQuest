package common.zeroquest.events;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import common.zeroquest.entity.zertum.EntityZertumEntity;

public class GenderEvent {

	@SubscribeEvent
	public void genderSet(LivingSpawnEvent event) {
		if (event.entity instanceof EntityZertumEntity) {
			EntityZertumEntity dog = (EntityZertumEntity) event.entity;
			if (dog.getRNG().nextInt(2) == 0) {
				dog.setGender("male");
			}
			else if (dog.getRNG().nextInt(2) == 1) {
				dog.setGender("female");
			}
		}
	}
}

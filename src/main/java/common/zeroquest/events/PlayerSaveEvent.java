package common.zeroquest.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerSaveEvent {

	@SubscribeEvent
	public void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {

	}

	/* ===============UNUSED============== */// TODO
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {}

	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {}

	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {}
}
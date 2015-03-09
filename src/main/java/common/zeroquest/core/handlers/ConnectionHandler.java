package common.zeroquest.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import common.zeroquest.ModItems;
import common.zeroquest.lib.Constants;

/**
 * @author ProPercivalalb
 */
public class ConnectionHandler {

	@SubscribeEvent
	public void playerLoggedIn(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		NBTTagCompound entityData = player.getEntityData();

		if (Constants.STARTING_ITEMS && !entityData.getBoolean("gotStartingItems")) {
            
            entityData.setBoolean("gotStartingItems", true);
            //player.inventory.addItemStackToInventory(book);
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.commandSeal));
        }
	}
}

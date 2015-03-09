package common.zeroquest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import common.zeroquest.entity.util.EntityDoggyBeam;

/**
 * @author ProPercivalalb
 **/
public class ItemCommandSeal extends Item {
	
	public ItemCommandSeal() {
		super();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if(!world.isRemote) {
            world.spawnEntityInWorld(new EntityDoggyBeam(world, player));
        }

        return stack;
    }
}

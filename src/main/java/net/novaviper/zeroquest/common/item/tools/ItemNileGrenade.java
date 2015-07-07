package net.novaviper.zeroquest.common.item.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.entity.projectile.EntityGrenade;
import net.novaviper.zeroquest.common.item.ItemZQ;

public class ItemNileGrenade extends ItemZQ {

	public ItemNileGrenade() {
		this.maxStackSize = 64;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		if (!p_77659_3_.capabilities.isCreativeMode) {
			--p_77659_1_.stackSize;
		}

		p_77659_2_.playSoundAtEntity(p_77659_3_, "random.fizz", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!p_77659_2_.isRemote) {
			p_77659_2_.spawnEntityInWorld(new EntityGrenade(p_77659_2_, p_77659_3_));
		}

		return p_77659_1_;
	}

}
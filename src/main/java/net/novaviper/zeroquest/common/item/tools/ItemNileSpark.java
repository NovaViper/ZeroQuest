package net.novaviper.zeroquest.common.item.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.item.ItemZQ;

public class ItemNileSpark extends ItemZQ {

	public ItemNileSpark() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(25);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 * 
	 * @param pos
	 *        The block being right-clicked
	 * @param side
	 *        The side being right-clicked
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		pos = pos.offset(side);

		if (!playerIn.canPlayerEdit(pos, side, stack)) {
			return false;
		}
		else {
			if (worldIn.isAirBlock(pos)) {
				worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				worldIn.setBlockState(pos, ModBlocks.nileFire.getDefaultState());
			}

			stack.damageItem(1, playerIn);
			return true;
		}
	}
}
package common.zeroquest.item;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDarkSpark extends Item{

	public ItemDarkSpark() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(25);
		this.setCreativeTab(ZeroQuest.DarkTab);
	}
	
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(side);

        if (!playerIn.canPlayerEdit(pos, side, stack))
        {
            return false;
        }
        else
        {
            if (worldIn.isAirBlock(pos))
            {
                worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.darkFire.getDefaultState());
            }

            stack.damageItem(1, playerIn);
            return true;
        }
    }
}
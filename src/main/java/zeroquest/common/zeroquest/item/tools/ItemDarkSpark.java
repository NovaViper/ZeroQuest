package common.zeroquest.item.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import common.zeroquest.ZeroQuest;
import common.zeroquest.block.portal.TeleporterDarkax;

public class ItemDarkSpark extends Item{
	
	public ItemDarkSpark() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(25);
		this.setCreativeTab(ZeroQuest.DarkTab);
	}
	
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(!worldIn.isRemote){
    		if(playerIn.dimension != ZeroQuest.DarkaxID){
    			TeleporterDarkax.teleport(playerIn, ZeroQuest.DarkaxID, false);
    		}else{
    			TeleporterDarkax.teleport(playerIn, 0, false);    			
    		}
    	}
        return true;
    }
}
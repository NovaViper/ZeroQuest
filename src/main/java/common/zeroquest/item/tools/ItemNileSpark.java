package common.zeroquest.item.tools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import common.zeroquest.block.portal.TeleporterNillax;

public class ItemNileSpark extends Item{
	
	public ItemNileSpark() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(25);
		this.setCreativeTab(ZeroQuest.ZeroTab);
	}
	
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(!worldIn.isRemote){
    		if(playerIn.dimension != ZeroQuest.NillaxID){
    			TeleporterNillax.teleport(playerIn, ZeroQuest.NillaxID, false);
    		}else{
    			TeleporterNillax.teleport(playerIn, 0, false);    			
    		}
    	}
        return true;
    }
}
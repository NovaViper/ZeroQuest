package common.zeroquest.item;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDarkSpark extends Item{

	public ItemDarkSpark() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(25);
		this.setCreativeTab(ZeroQuest.DarkTab);
	}
	
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10){
		if(side == 0){
			y--;
		}
		if(side == 1){
			y++;
		}
		if(side == 2){
			z--;
		}
		if(side == 3){
			z++;
		}
		if(side == 4){
			x--;
		}
		if(side == 5){
			x++;
		}
		
		
		if(!player.canPlayerEdit(x, y, z, side, itemstack)){
			return false;
		}else{
			if(world.isAirBlock(x, y, z)){
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1F, itemRand.nextFloat()*0.4F + 0.8F);
				world.setBlock(x, y, z, ModBlocks.darkaxFire);
			}
			
			itemstack.damageItem(1, player);
			return true;
		}
	}
	
}
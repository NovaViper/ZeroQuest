package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;

public class ItemEssence extends Item
{

	public ItemEssence() 
	{
		super();
	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ZeroQuest.modid + ":" + this.getUnlocalizedName().substring(5));
	}*/
	
}
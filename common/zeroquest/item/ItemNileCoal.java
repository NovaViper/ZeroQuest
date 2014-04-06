package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemNileCoal extends Item
{

	public ItemNileCoal(int par1) {
		super(par1);
        this.setMaxDamage(0);
        this.setCreativeTab(ZeroQuest.ZeroTab);
	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ZeroQuest.modid + ":" + this.getUnlocalizedName().substring(5));
	}*/

}

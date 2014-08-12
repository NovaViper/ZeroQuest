package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemNileCoal extends Item
{

	public ItemNileCoal() {
		super();
        this.setMaxDamage(0);
	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ZeroQuest.modid + ":" + this.getUnlocalizedName().substring(5));
	}*/

}

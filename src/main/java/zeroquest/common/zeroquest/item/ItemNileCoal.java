package common.zeroquest.item;

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

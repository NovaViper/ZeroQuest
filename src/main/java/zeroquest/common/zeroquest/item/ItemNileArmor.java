package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemNileArmor extends ItemArmor {
	
	private String texturePath = ZeroQuest.modid + ":" + "textures/models/armor/";

	public ItemNileArmor(ArmorMaterial nileEssenceMaterial2, int par3, int par4, String type) {
		super(nileEssenceMaterial2, par3, par4);
		this.maxStackSize = 1;
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setTextureName(type, par4);
		
		
	}
	
	private void setTextureName(String type, int armorPart){
		if(armorType == 0 || armorType == 1 || armorType == 3){
			this.texturePath += type + "_layer_1.png";
		}else{
			this.texturePath += type + "_layer_2.png";
		}
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		this.itemIcon = register.registerIcon(ZeroQuest.modid + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer){
		return texturePath;
		
	}

}

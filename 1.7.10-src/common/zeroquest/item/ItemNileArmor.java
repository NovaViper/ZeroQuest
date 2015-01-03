package common.zeroquest.item;

import org.apache.logging.log4j.Logger;

import common.zeroquest.ModItems;
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
	private static final Logger Log = ZeroQuest.Log;

	public ItemNileArmor(ArmorMaterial armorMaterial, int armorType, String name) {
		super(armorMaterial, 0, armorType);
		this.setUnlocalizedName(name);
		this.maxStackSize = 1;
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setTextureName(ZeroQuest.modid + ":" + getUnlocalizedName().substring(5));		
	}

	 /**
	* This method returns what file MC should use, to render the armor (make it visible from above).
	*/
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == ModItems.nileHelmet || stack.getItem() == ModItems.nileChest || stack.getItem() == ModItems.nileBoots)
		{
			return texturePath + "nile_layer_1.png";
		}
		else if(stack.getItem() == ModItems.nileLegs)
		{
			return texturePath + "nile_layer_2.png";
		}
		else
		{
			Log.fatal("Invalid Item ItemNileArmor");
			return null;
		}
	}
}

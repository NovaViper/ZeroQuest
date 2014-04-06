package common.zeroquest.item;

import java.util.Random;

import common.zeroquest.ZeroQuest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDust extends Item
{

	public ItemDust(int id) 
	{
		super(id);
		this.setCreativeTab(ZeroQuest.ZeroTab);
	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ZeroQuest.modid + ":" + this.getUnlocalizedName().substring(5));
	}*/
	
}
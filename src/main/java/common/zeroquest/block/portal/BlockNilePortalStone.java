package common.zeroquest.block.portal;

import java.util.List;
import java.util.Random;

import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockNilePortalStone extends Block
{

	public BlockNilePortalStone(Material material) 
	{
		super(material);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeMetal);
		//this.setLightLevel(1.0F);
		this.setResistance(100000);	
	}
}

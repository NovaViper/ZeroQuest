package common.zeroquest.block;

import java.util.List;
import java.util.Random;

import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNillaxStone extends Block
{

	public BlockNillaxStone(int id, Material material) 
	{
		super(id, material);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setHardness(1.0F);
		this.setStepSound(soundMetalFootstep);
		this.setLightValue(1.0F);
		this.setResistance(100000);	
	}
}

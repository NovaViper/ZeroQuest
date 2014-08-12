package common.zeroquest.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

import common.zeroquest.ZeroQuest;

public class BlockSlabBedRock extends BlockSlab{

	public BlockSlabBedRock(boolean fullBlock, Material material) {
		super(fullBlock, material);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int i) {
		return super.getUnlocalizedName();
	}

}


package common.zeroquest.block;

import common.zeroquest.ZeroQuest;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockNileSlab extends BlockSlab{

	public BlockNileSlab(boolean fullBlock, Material material) {
		super(fullBlock, material);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int i) {
		return super.getUnlocalizedName();
	}

}

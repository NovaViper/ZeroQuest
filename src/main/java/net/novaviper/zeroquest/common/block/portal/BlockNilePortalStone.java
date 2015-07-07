package net.novaviper.zeroquest.common.block.portal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNilePortalStone extends Block {

	public BlockNilePortalStone() {
		super(Material.rock);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeMetal);
		// this.setLightLevel(1.0F);
		this.setResistance(100000);
	}
}

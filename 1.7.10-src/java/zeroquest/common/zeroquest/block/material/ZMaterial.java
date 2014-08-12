package common.zeroquest.block.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

public class ZMaterial extends Material{

	public static final Material acid = new MaterialLiquid(MapColor.grassColor);
	
	public ZMaterial(MapColor par1MapColor) {
		super(par1MapColor);
	}
}

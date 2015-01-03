package common.zeroquest.block;

import common.zeroquest.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVitoid extends BlockCrops{

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;
	
	public BlockVitoid() {
		super();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		if(metadata < 7){
			if(metadata == 6){
				metadata = 5;
			}
			return iconArray[metadata >> 1];
		}
		
		return iconArray[6];
	}
	
	@Override
	public Item func_149866_i(){
		return ModItems.vitoidSeed;
	}
	
	@Override
	public Item func_149865_P(){
		return ModItems.vitoidFruit;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister){
        this.iconArray = new IIcon[7];

        for (int i = 0; i < this.iconArray.length; i++)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(this.getTextureName() + "_stage_" + (i+1));
        }
    }
}
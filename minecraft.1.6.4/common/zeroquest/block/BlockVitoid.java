package common.zeroquest.block;

import common.zeroquest.ModItems;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVitoid extends BlockCrops{

	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;
	
	public BlockVitoid(int par1) {
		super(par1);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata){
		if(metadata < 7){
			if(metadata == 6){
				metadata = 5;
			}
			return iconArray[metadata >> 1];
		}
		
		return iconArray[6];
	}
	
	public int getSeedItem(){
		return ModItems.vitoidSeed.itemID;
	}
	
	public int getCropItem(){
		return ModItems.vitoidFruit.itemID;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
        this.iconArray = new Icon[7];

        for (int i = 0; i < this.iconArray.length; i++)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(this.getTextureName() + "_stage_" + (i+1));
        }
    }
}
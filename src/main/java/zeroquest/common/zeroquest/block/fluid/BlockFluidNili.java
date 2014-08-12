package common.zeroquest.block.fluid;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import common.zeroquest.particle.ParticleEffects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidNili extends BlockFluidClassic{
	
	@SideOnly(Side.CLIENT)
	private IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	private IIcon flowingIcon;
	public BlockFluidNili(Fluid fluid, Material material) {
		super(fluid, material);
        this.blockHardness = 100.0F;
        this.setLightOpacity(10);
        this.setLightLevel(0F);
		this.useNeighborBrightness = true;
		this.disableStats();
	}
	  
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
   
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
    	if(entity instanceof EntityLivingBase){
    		((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 10)); //100 = 5 Seconds , 20 = 1 Second
    		((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 200, 2)); //100 = 5 Seconds , 20 = 1 Second,
    		((EntityLivingBase)entity).addPotionEffect(new PotionEffect(13, 300, 1)); //100 = 5 Seconds , 20 = 1 Second
    	}	
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldObj, int posX, int posY, int posZ, Random rand)
    {
    	if (this.blockMaterial == Material.water && worldObj.getBlock(posX, posY + 1, posZ).getMaterial() == Material.air && !worldObj.getBlock(posX, posY + 1, posZ).isOpaqueCube())
    	{
    
    		int direction = worldObj.getBlockMetadata(posX, posY, posZ);
  
    		float x1 = (float)posX + rand.nextFloat();
    		float y1 = (float)posY + rand.nextFloat();
    		float z1 = (float)posZ + rand.nextFloat();

    		float f = rand.nextFloat();
    		float f1 = rand.nextFloat();

    		if(direction == 4){
    			ParticleEffects.spawnParticle("bluedust", (double)(x1 - f), (double)(y1), (double)(z1 + f1), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}else if(direction == 5){
    			ParticleEffects.spawnParticle("bluedust", (double)(x1 + f), (double)(y1), (double)(z1 + f1), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}else if(direction == 2){
    			ParticleEffects.spawnParticle("bluedust", (double)(x1 + f1), (double)(y1), (double)(z1 - f), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}else if(direction == 3){
    			ParticleEffects.spawnParticle("bluedust", (double)(x1 + f1), (double)(y1), (double)(z1 + f), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}else if(direction == 1){
    			ParticleEffects.spawnParticle("bluedust", (double)(x1 + f1), (double)(y1), (double)(z1 + f), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}else if(direction == 0){
    			ParticleEffects.spawnParticle("bluedust", (double)(posX + f1), (double)(y1 + 1.0), (double)(posZ + f1), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}else if(direction == -1){
    			ParticleEffects.spawnParticle("bluedust", (double)(posX + f1), (double)(y1 + 1.0), (double)(posZ + f1), 0D /*red*/, 0D /*green*/, 0D /*blue*/);
    		}
    	}
    }
   
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon("zero_quest:niliStill");
            flowingIcon = register.registerIcon("zero_quest:niliFlow");
    }
}
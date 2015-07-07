package net.novaviper.zeroquest.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDarkPortalFX extends EntityFX
{
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;
    private static final String __OBFID = "CL_00000921";

    public EntityDarkPortalFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.motionX = xSpeedIn;
        this.motionY = ySpeedIn;
        this.motionZ = zSpeedIn;
        this.portalPosX = this.posX = xCoordIn;
        this.portalPosY = this.posY = yCoordIn;
        this.portalPosZ = this.posZ = zCoordIn;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		float f = this.rand.nextFloat() * 0.1F + 0.2F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.particleMaxAge = (int) (Math.random() * 10.0D) + 40;
        this.noClip = true;
        this.setParticleTextureIndex((int)(Math.random() * 8.0D));
    }

    /**
     * Renders the particle
     *  
     * @param worldRendererIn The WorldRenderer instance
     */
    public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
    {
        float f6 = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
        f6 = 1.0F - f6;
        f6 *= f6;
        f6 = 1.0F - f6;
        this.particleScale = this.portalParticleScale * f6;
        super.renderParticle(worldRendererIn, entityIn, partialTicks, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
    }

    public int getBrightnessForRender(float partialTicks)
    {
        int i = super.getBrightnessForRender(partialTicks);
        float f1 = (float)this.particleAge / (float)this.particleMaxAge;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f1 * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }

        return j | k << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float partialTicks)
    {
        float f1 = super.getBrightness(partialTicks);
        float f2 = (float)this.particleAge / (float)this.particleMaxAge;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        float f1 = f;
        f = -f + f * f * 2.0F;
        f = 1.0F - f;
        this.posX = this.portalPosX + this.motionX * (double)f;
        this.posY = this.portalPosY + this.motionY * (double)f + (double)(1.0F - f1);
        this.posZ = this.portalPosZ + this.motionZ * (double)f;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            private static final String __OBFID = "CL_00002590";

            public EntityFX getEntityFX(int p_178902_1_, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int ... p_178902_15_)
            {
                return new EntityDarkPortalFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }
}
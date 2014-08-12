package common.zeroquest.particle;

import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityLavaSplashFX extends EntityRainFX
{
    public EntityLavaSplashFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6);
        this.particleGravity = 0.04F;
        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleRed = 1.0F;
        this.particleGreen = 0.0F;
        this.particleBlue = 0.0F;

        if (par10 == 0.0D && (par8 != 0.0D || par12 != 0.0D))
        {
            this.motionX = par8;
            this.motionY = par10 + 0.1D;
            this.motionZ = par12;
        }
    }
}

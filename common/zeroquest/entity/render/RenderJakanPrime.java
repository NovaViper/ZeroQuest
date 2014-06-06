package common.zeroquest.entity.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.entity.model.ModelJakanPrime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderJakanPrime extends RenderLiving
{
    private static final ResourceLocation jakanPrimeTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakanprime.png");
    private static final ResourceLocation jakanPrimeTextureTamed = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakanprime_tamed.png");
    private static final ResourceLocation saddledJakanPrimeTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakan_saddle.png");
    
    public RenderJakanPrime(ModelJakanPrime par1ModelJakanPrime, float par3)
    {
        super(par1ModelJakanPrime, par3);
        this.setRenderPassModel(par1ModelJakanPrime);
    }
    
    protected int func_82447_a(EntityJakanPrime par1EntityJakanPrime, int par2, float par3)
    {
        float f1;
        
        if (par2 == 1 && par1EntityJakanPrime.isTamed())
        {
            this.bindTexture(saddledJakanPrimeTextures);
            f1 = 1.0F;
            int j = par1EntityJakanPrime.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation getTextures(EntityJakanPrime par1EntityJakan)
    {
        return par1EntityJakan.isTamed() ? jakanPrimeTextureTamed : jakanPrimeTexture;
    }
    
    protected float getWingRotation(EntityJakanPrime par1EntityJakanPrime, float par2)
    {
        float f1 = par1EntityJakanPrime.field_70888_h + (par1EntityJakanPrime.field_70886_e - par1EntityJakanPrime.field_70888_h) * par2;
        float f2 = par1EntityJakanPrime.field_70884_g + (par1EntityJakanPrime.destPos - par1EntityJakanPrime.field_70884_g) * par2;
        return (MathHelper.sin(f1) + 0.5F) * f2;
    }
    
    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getWingRotation((EntityJakanPrime)par1EntityLivingBase, par2);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityJakanPrime)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getTextures((EntityJakanPrime)par1Entity);
    }
}
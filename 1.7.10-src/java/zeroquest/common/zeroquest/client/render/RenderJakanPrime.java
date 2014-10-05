package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelJakanPrime;
import common.zeroquest.entity.EntityJakanPrime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderJakanPrime extends RenderLiving
{
    private static final ResourceLocation jakanTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakanprime.png");
    private static final ResourceLocation jakanTextureTamed = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakanprime_tamed.png");
    private static final ResourceLocation saddledJakanTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakan_saddle.png");
    
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
            this.bindTexture(saddledJakanTextures);
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

    protected ResourceLocation getTextures(EntityJakanPrime par1EntityJakanPrime)
    {
        return par1EntityJakanPrime.isTamed() ? jakanTextureTamed : jakanTexture;
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
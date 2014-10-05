package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelJakan;
import common.zeroquest.entity.EntityJakan;
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
public class RenderJakan extends RenderLiving
{
    private static final ResourceLocation jakanTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakan.png");
    private static final ResourceLocation jakanTextureTamed = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakan_tamed.png");
    private static final ResourceLocation saddledJakanTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakan_saddle.png");
    
    public RenderJakan(ModelJakan par1ModelJakan, float par3)
    {
        super(par1ModelJakan, par3);
        this.setRenderPassModel(par1ModelJakan);
    }
    
    protected int func_82447_a(EntityJakan par1EntityJakan, int par2, float par3)
    {
        float f1;
        
        if (par2 == 1 && par1EntityJakan.getSaddled())
        {
            this.bindTexture(saddledJakanTextures);
            f1 = 1.0F;
            int j = par1EntityJakan.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation getTextures(EntityJakan par1EntityJakan)
    {
        return par1EntityJakan.isTamed() ? jakanTextureTamed : jakanTexture;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityJakan)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getTextures((EntityJakan)par1Entity);
    }
}
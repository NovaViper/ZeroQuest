package common.zeroquest.entity.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.model.ModelKurr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderKurr extends RenderLiving
{
    private static final ResourceLocation kurrTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kurr.png");

    public RenderKurr(ModelKurr par1ModelKurr, float par3)
    {
        super(par1ModelKurr, par3);
        this.setRenderPassModel(par1ModelKurr);
    }

    protected ResourceLocation getTextures(EntityKurr par1EntityJakan)
    {
        return kurrTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getTextures((EntityKurr)par1Entity);
    }
}
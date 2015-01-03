package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelZertum;
import common.zeroquest.entity.EntityZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderZertum extends RenderLiving
{
    private static final ResourceLocation zertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum.png");
    private static final ResourceLocation tamedZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_tame.png");
    private static final ResourceLocation anrgyZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_angry.png");
    private static final ResourceLocation ZertumCollarTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_collar.png");
    private static final ResourceLocation ZertumDyingTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_dying.png");

    public RenderZertum(ModelZertum par1ModelZertum, float par3)
    {
        super(par1ModelZertum, par3);
        this.setRenderPassModel(par1ModelZertum);
    }

    protected int func_82447_a(EntityZertum par1EntityZertum, int par2, float par3)
    {
        if (par2 == 0 && par1EntityZertum.getWolfShaking())
        {
            float f1 = par1EntityZertum.getBrightness(par3) * par1EntityZertum.getShadingWhileShaking(par3);
            this.bindTexture(zertumTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (par2 == 1 && par1EntityZertum.isTamed())
        {
            this.bindTexture(ZertumCollarTextures);
            int j = par1EntityZertum.getCollarColor();
            GL11.glColor3f(EntitySheep.fleeceColorTable[j][0], EntitySheep.fleeceColorTable[j][1], EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else if (par2 == 0 && par1EntityZertum.getHealth() <=10 &&  par1EntityZertum.isTamed())
        {
            this.bindTexture(ZertumDyingTextures);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110914_a(EntityZertum par1Entityzertum)
    {
        return par1Entityzertum.isTamed() ? tamedZertumTextures : (par1Entityzertum.isAngry() ? anrgyZertumTextures : zertumTextures);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityZertum)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((EntityZertum)par1Entity);
    }
}

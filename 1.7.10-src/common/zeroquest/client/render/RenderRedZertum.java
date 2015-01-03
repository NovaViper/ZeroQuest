package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelRedZertum;
import common.zeroquest.entity.EntityRedZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderRedZertum extends RenderLiving
{
    private static final ResourceLocation redZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/rzertum.png");
    private static final ResourceLocation tamedRedZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/rzertum_tame.png");
    private static final ResourceLocation anrgyRedZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/rzertum_angry.png");
    private static final ResourceLocation ZertumCollarTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_collar.png");
    private static final ResourceLocation ZertumDyingTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_dying.png");

    public RenderRedZertum(ModelRedZertum par1ModelZertum, float par3)
    {
        super(par1ModelZertum, par3);
        this.setRenderPassModel(par1ModelZertum);
    }

    protected int func_82447_a(EntityRedZertum par1EntityZertum, int par2, float par3)
    {
        if (par2 == 0 && par1EntityZertum.getWolfShaking())
        {
            float f1 = par1EntityZertum.getBrightness(par3) * par1EntityZertum.getShadingWhileShaking(par3);
            this.bindTexture(redZertumTextures);
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

    protected ResourceLocation func_110914_a(EntityRedZertum par1Entityzertum)
    {
        return par1Entityzertum.isTamed() ? tamedRedZertumTextures : (par1Entityzertum.isAngry() ? anrgyRedZertumTextures : redZertumTextures);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityRedZertum)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((EntityRedZertum)par1Entity);
    }
}

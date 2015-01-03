package common.zeroquest.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelDestroZertum;
import common.zeroquest.entity.EntityDestroZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDestroZertum extends RenderLiving
{
    private static final ResourceLocation destroZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/deszertum.png");
    private static final ResourceLocation tamedDestroZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/deszertum_tame.png");
    private static final ResourceLocation angryDestroZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/deszertum_angry.png");
    private static final ResourceLocation ZertumCollarTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_collar.png");
    private static final ResourceLocation ZertumDyingTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_dying.png");

    public RenderDestroZertum(ModelDestroZertum par1ModelZertum, float par3)
    {
        super(par1ModelZertum, par3);
        this.setRenderPassModel(par1ModelZertum);
    }
    
    protected int func_82447_a(EntityDestroZertum par1EntityZertum, int par2, float par3)
    {
        if (par2 == 0 && par1EntityZertum.getWolfShaking())
        {
            float f1 = par1EntityZertum.getBrightness(par3) * par1EntityZertum.getShadingWhileShaking(par3);
            this.bindTexture(destroZertumTextures);
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

    protected ResourceLocation func_110914_a(EntityDestroZertum par1Entityzertum)
    {
        return par1Entityzertum.isTamed() ? tamedDestroZertumTextures : (par1Entityzertum.isAngry() ? angryDestroZertumTextures : destroZertumTextures);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityDestroZertum)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((EntityDestroZertum)par1Entity);
    }
}
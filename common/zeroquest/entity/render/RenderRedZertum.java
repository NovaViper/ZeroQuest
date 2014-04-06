package common.zeroquest.entity.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.model.ModelRedZertum;
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
    private static final ResourceLocation zwolfTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/rzertum.png");
    private static final ResourceLocation tamedZWolfTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/rzertum_tame.png");
    private static final ResourceLocation anrgyZWolfTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/rzertum_angry.png");
    private static final ResourceLocation zwolfCollarTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_collar.png");
    private static final ResourceLocation zwolfDyingTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_dying.png");

    public RenderRedZertum(ModelRedZertum par1ModelRedZertum, float par3)
    {
        super(par1ModelRedZertum, par3);
        this.setRenderPassModel(par1ModelRedZertum);
    }

    protected float getTailRotation(EntityRedZertum par1EntityZertum, float par2)
    {
        return par1EntityZertum.getTailRotation();
    }

    protected int func_82447_a(EntityRedZertum par1EntityZertum, int par2, float par3)
    {
        float f1;

        if (par2 == 0 && par1EntityZertum.getWolfShaking())
        {
            f1 = par1EntityZertum.getBrightness(par3) * par1EntityZertum.getShadingWhileShaking(par3);
            this.bindTexture(zwolfTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (par2 == 1 && par1EntityZertum.isTamed())
        {
            this.bindTexture(zwolfCollarTextures);
            f1 = 1.0F;
            int j = par1EntityZertum.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else if (par2 == 2 && par1EntityZertum.getHealth() <=10 &&  par1EntityZertum.isTamed())
        {
            this.bindTexture(zwolfDyingTextures);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110914_a(EntityRedZertum par1Entityzertum)
    {
        return par1Entityzertum.isTamed() ? tamedZWolfTextures : (par1Entityzertum.isAngry() ? anrgyZWolfTextures : zwolfTextures);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityRedZertum)par1EntityLivingBase, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getTailRotation((EntityRedZertum)par1EntityLivingBase, par2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((EntityRedZertum)par1Entity);
    }
}

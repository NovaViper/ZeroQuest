package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.render.layers.LayerZertumCollarDying;
import common.zeroquest.client.render.model.ModelZertum;
import common.zeroquest.entity.EntityZertum;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZertum extends RenderLiving
{
    private static final ResourceLocation zertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum.png");
    private static final ResourceLocation tamedZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_tame.png");
    private static final ResourceLocation angryZertumTextures = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/zertum/zertum_angry.png");
    
    public RenderZertum(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_){
    
        super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
        this.addLayer(new LayerZertumCollarDying(this));
    }

    public void func_177135_a(EntityZertum p_177135_1_, double p_177135_2_, double p_177135_4_, double p_177135_6_, float p_177135_8_, float p_177135_9_)
    {
        if (p_177135_1_.isWolfWet())
        {
            float f2 = p_177135_1_.getBrightness(p_177135_9_) * p_177135_1_.getShadingWhileWet(p_177135_9_);
            GlStateManager.color(f2, f2, f2);
        }

        super.doRender((EntityLiving)p_177135_1_, p_177135_2_, p_177135_4_, p_177135_6_, p_177135_8_, p_177135_9_);
    }

    protected ResourceLocation getEntityTexture(EntityZertum p_110775_1_)
    {
        return p_110775_1_.isTamed() ? tamedZertumTextures : (p_110775_1_.isAngry() ? angryZertumTextures : zertumTextures);
    }

    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityZertum)p_110775_1_);
    }
}
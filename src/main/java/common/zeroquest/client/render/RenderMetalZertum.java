package common.zeroquest.client.render;

import common.zeroquest.client.render.layers.LayerMetalZertumCollarDying;
import common.zeroquest.client.render.layers.LayerZertumCollarDying;
import common.zeroquest.entity.EntityMetalZertum;
import common.zeroquest.lib.Constants;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMetalZertum extends RenderLiving
{
    private static final ResourceLocation zertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/mzertum.png");
    private static final ResourceLocation tamedZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/mzertum_tame.png");
    private static final ResourceLocation angryZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/mzertum_angry.png");
    
    public RenderMetalZertum(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_){
    
        super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
        this.addLayer(new LayerMetalZertumCollarDying(this));
    }

    public void func_177135_a(EntityMetalZertum p_177135_1_, double p_177135_2_, double p_177135_4_, double p_177135_6_, float p_177135_8_, float p_177135_9_)
    {
        if (p_177135_1_.isWolfWet())
        {
            float f2 = p_177135_1_.getBrightness(p_177135_9_) * p_177135_1_.getShadingWhileWet(p_177135_9_);
            GlStateManager.color(f2, f2, f2);
        }

        super.doRender((EntityLiving)p_177135_1_, p_177135_2_, p_177135_4_, p_177135_6_, p_177135_8_, p_177135_9_);
    }

    protected ResourceLocation getEntityTexture(EntityMetalZertum p_110775_1_)
    {
        return p_110775_1_.isTamed() ? tamedZertumTextures : (p_110775_1_.isAngry() ? angryZertumTextures : zertumTextures);
    }

    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityMetalZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityMetalZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityMetalZertum)p_110775_1_);
    }
}
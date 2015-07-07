package net.novaviper.zeroquest.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.client.render.entity.layers.LayerKortor;
import net.novaviper.zeroquest.common.entity.creature.EntityKortor;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.util.ResourceReference;

@SideOnly(Side.CLIENT)
public class RenderKortor extends RenderLiving
{
    
    public RenderKortor(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_){
        
        super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
        this.addLayer(new LayerKortor(this));
    }

    protected ResourceLocation getEntityTexture(EntityKortor entity)
    {
        return entity.isTamed() ? ResourceReference.getKortorSkins("_tame") : (entity.isAngry() ? ResourceReference.getKortorSkins("_angry") : ResourceReference.getKortorSkins(""));
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityKortor)p_110775_1_);
    }
}

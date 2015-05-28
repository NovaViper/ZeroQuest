package common.zeroquest.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.client.render.layers.LayerKortor;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.ResourceReference;

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

package common.zeroquest.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.client.render.layers.LayerJakanSaddle;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class RenderJakan extends RenderLiving
{	
    private static final ResourceLocation jakanTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/jakan/jakan.png");
    private static final ResourceLocation jakanTextureTamed = new ResourceLocation(Constants.modid + ":" + "textures/entity/jakan/jakan_tamed.png");
    
    public RenderJakan(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_){
        
        super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
        this.addLayer(new LayerJakanSaddle(this));
    }

    protected ResourceLocation getEntityTexture(EntityJakan p_110775_1_)
    {
        return p_110775_1_.isTamed() ? jakanTextureTamed : jakanTexture;
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityJakan)p_110775_1_);
    }
}

package common.zeroquest.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.render.layers.LayerKortorSaddle;
import common.zeroquest.entity.EntityKortor;

@SideOnly(Side.CLIENT)
public class RenderKortor extends RenderLiving
{	private static final ResourceLocation kortorTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor.png");
	private static final ResourceLocation kortorTamedTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor_tamed.png");
	private static final ResourceLocation kortorAngryTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor_angry.png");
    
    public RenderKortor(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_){
        
        super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
        this.addLayer(new LayerKortorSaddle(this));
    }

    protected ResourceLocation getEntityTexture(EntityKortor p_110775_1_)
    {
        return p_110775_1_.isTamed() ? kortorTamedTexture : (p_110775_1_.isAngry() ? kortorAngryTexture : kortorTexture);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityKortor)p_110775_1_);
    }
}

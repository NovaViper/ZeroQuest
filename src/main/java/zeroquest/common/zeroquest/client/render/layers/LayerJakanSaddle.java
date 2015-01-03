package common.zeroquest.client.render.layers;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.ZeroQuest;
import common.zeroquest.client.render.RenderJakan;
import common.zeroquest.client.render.model.ModelJakan;
import common.zeroquest.entity.EntityJakan;

@SideOnly(Side.CLIENT)
public class LayerJakanSaddle implements LayerRenderer
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/jakan/jakan_saddle.png");
    private final RenderJakan field_177146_b;
    private static final String __OBFID = "CL_00002414";

    public LayerJakanSaddle(RenderJakan p_i46113_1_)
    {
        this.field_177146_b = p_i46113_1_;
    }

    public void doRenderLayer(EntityJakan p_177155_1_, float p_177155_2_, float p_177155_3_, float p_177155_4_, float p_177155_5_, float p_177155_6_, float p_177155_7_, float p_177155_8_)
    {
        if (p_177155_1_.getSaddled())
        {
            this.field_177146_b.bindTexture(TEXTURE);
            this.field_177146_b.getMainModel().render(p_177155_1_, p_177155_2_, p_177155_3_, p_177155_5_, p_177155_6_, p_177155_7_, p_177155_8_);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }

    public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
    {
        this.doRenderLayer((EntityJakan)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }
}
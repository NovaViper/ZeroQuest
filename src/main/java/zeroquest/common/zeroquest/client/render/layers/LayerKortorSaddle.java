package common.zeroquest.client.render.layers;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.render.RenderKortor;
import common.zeroquest.client.render.model.ModelKortor;
import common.zeroquest.entity.EntityKortor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerKortorSaddle implements LayerRenderer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor_saddle.png");
    private final RenderKortor field_177146_b;
    private static final String __OBFID = "CL_00002414";

    public LayerKortorSaddle(RenderKortor p_i46113_1_)
    {
        this.field_177146_b = p_i46113_1_;
    }

    public void doRenderLayer(EntityKortor p_177155_1_, float p_177155_2_, float p_177155_3_, float p_177155_4_, float p_177155_5_, float p_177155_6_, float p_177155_7_, float p_177155_8_)
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
        this.doRenderLayer((EntityKortor)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }
}
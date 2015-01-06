package common.zeroquest.client.render.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.client.render.RenderForisZertum;
import common.zeroquest.entity.EntityForisZertum;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class LayerForisZertumCollarDying implements LayerRenderer
{
    private static final ResourceLocation field_177147_a = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/zertum_collar.png");
    private static final ResourceLocation ZertumDyingTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/zertum_dying.png");
    private final RenderForisZertum field_177146_b;
    private static final String __OBFID = "CL_00002405";

    public LayerForisZertumCollarDying(RenderForisZertum p_177145_1_)
    {
        this.field_177146_b = p_177145_1_;
    }

    public void func_177145_a(EntityForisZertum p_177145_1_, float p_177145_2_, float p_177145_3_, float p_177145_4_, float p_177145_5_, float p_177145_6_, float p_177145_7_, float p_177145_8_)
    {
        if (p_177145_1_.isTamed() && !p_177145_1_.isInvisible())
        {
            this.field_177146_b.bindTexture(field_177147_a);
            EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(p_177145_1_.getCollarColor().getMetadata());
            float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
            GlStateManager.color(afloat[0], afloat[1], afloat[2]);
            this.field_177146_b.getMainModel().render(p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_, p_177145_7_, p_177145_8_);
        }
        
        if(p_177145_1_.isTamed() && p_177145_1_.getHealth() <=10 && !p_177145_1_.isInvisible()){
            this.field_177146_b.bindTexture(ZertumDyingTextures);
            GlStateManager.color(1f, 1f, 1f);
            this.field_177146_b.getMainModel().render(p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_, p_177145_7_, p_177145_8_);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }

    public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
    {
        this.func_177145_a((EntityForisZertum)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }
}

package common.zeroquest.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.client.renderer.entity.RenderMetalZertum;
import common.zeroquest.entity.zertum.EntityMetalZertum;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class LayersMetalZertum implements LayerRenderer
{
    private static final ResourceLocation ZertumCollarTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/zertum_collar.png");
    private static final ResourceLocation ZertumDyingTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/zertum_dying.png");
    private static final ResourceLocation ZertumSaddleTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/zertum_saddle.png");
    private static final ResourceLocation EvolvedZertumCollarTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/evo/zertum_collar.png");
    private static final ResourceLocation EvolvedZertumDyingTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/evo/zertum_dying.png");
    private static final ResourceLocation EvolvedZertumSaddleTexture = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/evo/zertum_saddle.png");
    private final RenderMetalZertum field_177146_b;
    private static final String __OBFID = "CL_00002405";

    public LayersMetalZertum(RenderMetalZertum p_177145_1_)
    {
        this.field_177146_b = p_177145_1_;
    }

    public void func_177145_a(EntityMetalZertum entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	if(!entity.hasEvolved()){
    		if (entity.isTamed() && !entity.isInvisible())
    		{
    			this.field_177146_b.bindTexture(ZertumCollarTexture);
    			EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
    			float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
    			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
    			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
    		}
        
    		if(entity.isTamed() && entity.getHealth() <=10 && !entity.isInvisible()){
    			this.field_177146_b.bindTexture(ZertumDyingTexture);
    			GlStateManager.color(1f, 1f, 1f);
    			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
    		}
        
    		if(entity.isTamed() && entity.isSaddled() && !entity.isInvisible()){
    			this.field_177146_b.bindTexture(ZertumSaddleTexture);
    			GlStateManager.color(1f, 1f, 1f);
    			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
    		}
    	}
    	else if(entity.hasEvolved()){
    		if (entity.isTamed() && !entity.isInvisible())
    		{
    			this.field_177146_b.bindTexture(EvolvedZertumCollarTexture);
    			EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
    			float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
    			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
    			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
    		}
        
    		if(entity.isTamed() && entity.getHealth() <=10 && !entity.isInvisible()){
    			this.field_177146_b.bindTexture(EvolvedZertumDyingTexture);
    			GlStateManager.color(1f, 1f, 1f);
    			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
    		}
    		
    		if(entity.isTamed() && entity.isSaddled() && !entity.isInvisible()){
    			this.field_177146_b.bindTexture(EvolvedZertumSaddleTexture);
    			GlStateManager.color(1f, 1f, 1f);
    			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
    		}
    	}
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }

    public void doRenderLayer(EntityLivingBase entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.func_177145_a((EntityMetalZertum)entity, par1, par2, par3, par4, par5, par6, par7);
    }
}

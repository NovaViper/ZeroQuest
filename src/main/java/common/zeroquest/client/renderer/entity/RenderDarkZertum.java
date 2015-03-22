package common.zeroquest.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.client.renderer.entity.layers.LayersDarkZertum;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class RenderDarkZertum extends RenderLiving
{
    private static final ResourceLocation darkZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/dzertum.png");
    private static final ResourceLocation tamedDarkZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/dzertum_tame.png");
    private static final ResourceLocation angryDarkZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/dzertum_angry.png");

    public RenderDarkZertum(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_)
    {
        super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
        this.addLayer(new LayersDarkZertum(this));
    }

    public void func_177135_a(EntityDarkZertum p_177135_1_, double p_177135_2_, double p_177135_4_, double p_177135_6_, float p_177135_8_, float p_177135_9_)
    {
        if (p_177135_1_.isWolfWet())
        {
            float f2 = p_177135_1_.getBrightness(p_177135_9_) * p_177135_1_.getShadingWhileWet(p_177135_9_);
            GlStateManager.color(f2, f2, f2);
        }

        super.doRender((EntityLiving)p_177135_1_, p_177135_2_, p_177135_4_, p_177135_6_, p_177135_8_, p_177135_9_);
    }

    protected ResourceLocation getEntityTexture(EntityDarkZertum p_110775_1_)
    {
        return p_110775_1_.isTamed() ? tamedDarkZertumTextures : (p_110775_1_.isAngry() ? angryDarkZertumTextures : darkZertumTextures);
    }

    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityDarkZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityDarkZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityDarkZertum)p_110775_1_);
    }

    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_177135_a((EntityDarkZertum)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    @Override
	public void passSpecialRender(EntityLivingBase entityLivingBase, double p_77033_2_, double p_77033_4_, double p_77033_6_) {
    	EntityDarkZertum dog = (EntityDarkZertum)entityLivingBase;
        
        if(!dog.getDogName().isEmpty())
        	super.passSpecialRender(entityLivingBase, p_77033_2_, p_77033_4_, p_77033_6_);
    }
    
    @Override
    protected void func_177069_a(Entity entity, double x, double y, double z, String displayName, float scale, double distanceFromPlayer) {
    	super.func_177069_a(entity, x, y, z, displayName, scale, distanceFromPlayer);
        
    	EntityDarkZertum dog = (EntityDarkZertum)entity;
    	
    	if (distanceFromPlayer < 100.0D) {
        	
            y += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.016666668F * 0.7F);
        	
            String tip = dog.mode.getMode().getTip();
            
            String label = String.format("%s[%d]", tip, dog.getDogHunger());
            
            if (dog.isPlayerSleeping())
                this.renderLivingLabel(dog, label,  x, y - 0.5D, z, 64, 0.7F);
            else
                this.renderLivingLabel(dog, label, x, y, z, 64, 0.7F);
        }
    	
    	if (distanceFromPlayer < 100.0D) {
    		y += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.016666668F * 0.5F);
              
           if(this.renderManager.livingPlayer.isSneaking()) {
        	   EntityLivingBase owner = dog.getOwnerEntity();
        	   if(owner != null)
        		   this.renderLivingLabel(dog, owner.getDisplayName().getUnformattedText(), x, y, z, 5, 0.5F);
        	   else
        		   this.renderLivingLabel(dog, dog.getOwnerId(), x, y, z, 5, 0.5F);
           }
    	}
    }
    
    protected void renderLivingLabel(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_, float scale) {
    	 double d3 = p_147906_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);

         if (d3 <= (double)(p_147906_9_ * p_147906_9_)) {
             FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
             float f1 = 0.016666668F * scale;
             GlStateManager.pushMatrix();
             GlStateManager.translate((float)p_147906_3_ + 0.0F, (float)p_147906_5_ + p_147906_1_.height + 0.5F, (float)p_147906_7_);
             GL11.glNormal3f(0.0F, 1.0F, 0.0F);
             GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
             GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
             GlStateManager.scale(-f1, -f1, f1);
             GlStateManager.disableLighting();
             GlStateManager.depthMask(false);
             GlStateManager.disableDepth();
             GlStateManager.enableBlend();
             GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
             Tessellator tessellator = Tessellator.getInstance();
             WorldRenderer worldrenderer = tessellator.getWorldRenderer();
             byte b0 = 0;

             if (p_147906_2_.equals("deadmau5"))
                 b0 = -10;

             GlStateManager.disableTexture2D();
             worldrenderer.startDrawingQuads();
             int j = fontrenderer.getStringWidth(p_147906_2_) / 2;
             worldrenderer.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
             worldrenderer.addVertex((double)(-j - 1), (double)(-1 + b0), 0.0D);
             worldrenderer.addVertex((double)(-j - 1), (double)(8 + b0), 0.0D);
             worldrenderer.addVertex((double)(j + 1), (double)(8 + b0), 0.0D);
             worldrenderer.addVertex((double)(j + 1), (double)(-1 + b0), 0.0D);
             tessellator.draw();
             GlStateManager.enableTexture2D();
             fontrenderer.drawString(p_147906_2_, -fontrenderer.getStringWidth(p_147906_2_) / 2, b0, 553648127);
             GlStateManager.enableDepth();
             GlStateManager.depthMask(true);
             fontrenderer.drawString(p_147906_2_, -fontrenderer.getStringWidth(p_147906_2_) / 2, b0, -1);
             GlStateManager.enableLighting();
             GlStateManager.disableBlend();
             GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
             GlStateManager.popMatrix();
         }
    }
}
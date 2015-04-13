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
import common.zeroquest.client.renderer.entity.layers.LayersDestroZertum;
import common.zeroquest.entity.zertum.EntityDestroZertum;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class RenderDestroZertum extends RenderLiving
{
    private static final ResourceLocation destroZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/deszertum.png");
    private static final ResourceLocation tamedDestroZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/deszertum_tame.png");
    private static final ResourceLocation angryDestroZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/deszertum_angry.png");
    private static final ResourceLocation evolvedDestroZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/evo/deszertum.png");
    private static final ResourceLocation evolvedTamedDestroZertumTextures = new ResourceLocation(Constants.modid + ":" + "textures/entity/zertum/evo/deszertum_tame.png");
    
    public RenderDestroZertum(RenderManager render, ModelBase base, float par1){
    
        super(render, base, par1);
        this.addLayer(new LayersDestroZertum(this));
    }

    public void func_177135_a(EntityDestroZertum entity, double par1, double par2, double par3, float par4, float par5)
    {
        if (entity.isWolfWet())
        {
            float f2 = entity.getBrightness(par5) * entity.getShadingWhileWet(par5);
            GlStateManager.color(f2, f2, f2);
        }

        super.doRender((EntityLiving)entity, par1, par2, par3, par4, par5);
    }

    protected ResourceLocation getEntityTexture(EntityDestroZertum entity)
    {
    	if(!entity.hasEvolved()){
    		if(entity.isTamed()){
    			return tamedDestroZertumTextures;
    		}else if(entity.isAngry()){
    			return angryDestroZertumTextures;
    		}else if(!entity.isTamed() && !entity.isAngry()){
    			return destroZertumTextures;
    		}
    	}else if(entity.hasEvolved()){
    		if(entity.isTamed()){
    			return evolvedTamedDestroZertumTextures;
    		}else if(!entity.isTamed() && !entity.isAngry()){
    			return evolvedDestroZertumTextures;
    		}
    	}
		return null;
    }

    public void doRender(EntityLiving entity, double par1, double par2, double par3, float par4, float par5)
    {
        this.func_177135_a((EntityDestroZertum)entity, par1, par2, par3, par4, par5);
    }

    public void doRender(EntityLivingBase entity, double par1, double par2, double par3, float par4, float par5)
    {
        this.func_177135_a((EntityDestroZertum)entity, par1, par2, par3, par4, par5);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityDestroZertum)p_110775_1_);
    }
    
    @Override
	public void passSpecialRender(EntityLivingBase entityLivingBase, double par1, double par2, double par3) {
    	EntityDestroZertum dog = (EntityDestroZertum)entityLivingBase;
        
        if(!dog.getDogName().isEmpty())
        	super.passSpecialRender(entityLivingBase, par1, par2, par3);
    }
    
    @Override
    protected void func_177069_a(Entity entity, double x, double y, double z, String displayName, float scale, double distanceFromPlayer) {
    	super.func_177069_a(entity, x, y, z, displayName, scale, distanceFromPlayer);
        
    	EntityDestroZertum dog = (EntityDestroZertum)entity;
    	
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
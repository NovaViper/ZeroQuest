package common.zeroquest.entity.model;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityZertum;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelJakan extends ModelBase
{
	  //fields
	    ModelRenderer Horn1;
	    ModelRenderer Horn2;
	    ModelRenderer Head;
	    ModelRenderer Snout2;
	    ModelRenderer Snout1;
	    ModelRenderer Neck2;
	    ModelRenderer Neck1;
	    ModelRenderer Torso;
	    ModelRenderer FLLeg1;
	    ModelRenderer FLLeg2;
	    ModelRenderer FRLeg1;
	    ModelRenderer FRLeg2;
	    ModelRenderer BLLeg1;
	    ModelRenderer BLLeg2;
	    ModelRenderer BRLeg1;
	    ModelRenderer BRLeg2;
	    ModelRenderer Tail1;
	    ModelRenderer Tail2;
	    ModelRenderer Tail3;
	    ModelRenderer Tail4;
	    ModelRenderer Tail5;
	  
	  public ModelJakan()
	  {
	    textureWidth = 128;
	    textureHeight = 64;
	    
	      Head = new ModelRenderer(this, 74, 31);
	      Head.addBox(-3F, 0F, -12F, 5, 5, 5);
	      Head.setRotationPoint(1F, 7F, -7F);
	      Head.setTextureSize(128, 64);
	      Head.mirror = true;
	      setRotation(Head, 0F, 0F, 0F);
	      Snout2 = new ModelRenderer(this, 87, 42);
	      Snout2.addBox(-1.5F, 4F, -14F, 2, 1, 2);
	      Snout2.setRotationPoint(1F, 7F, -7F);
	      Snout2.setTextureSize(128, 64);
	      Snout2.mirror = true;
	      setRotation(Snout2, 0F, 0F, 0F);
	      Snout1 = new ModelRenderer(this, 74, 42);
	      Snout1.addBox(-2F, 2F, -15F, 3, 2, 3);
	      Snout1.setRotationPoint(1F, 7F, -7F);
	      Snout1.setTextureSize(128, 64);
	      Snout1.mirror = true;
	      setRotation(Snout1, 0F, 0F, 0F);
	      Neck2 = new ModelRenderer(this, 51, 19);
	      Neck2.addBox(-2.5F, 1.833333F, -8.233334F, 4, 4, 7);
	      Neck2.setRotationPoint(1F, 13F, -7F);
	      Neck2.setTextureSize(128, 64);
	      Neck2.mirror = true;
	      setRotation(Neck2, -0.8551081F, 0F, 0F);
	      Neck1 = new ModelRenderer(this, 33, 13);
	      Neck1.addBox(-3F, 0F, -4F, 5, 5, 7);
	      Neck1.setRotationPoint(1F, 13F, -7F);
	      Neck1.setTextureSize(128, 64);
	      Neck1.mirror = true;
	      setRotation(Neck1, -0.4089647F, 0F, 0F);
	      Torso = new ModelRenderer(this, 0, 0);
	      Torso.addBox(-3.5F, 0F, 0F, 7, 6, 12);
	      Torso.setRotationPoint(0.5F, 13F, -7F);
	      Torso.setTextureSize(128, 64);
	      Torso.mirror = true;
	      setRotation(Torso, 0F, 0F, 0F);
	      FLLeg1 = new ModelRenderer(this, 27, 0);
	      FLLeg1.addBox(0F, 0F, 0F, 2, 4, 2);
	      FLLeg1.setRotationPoint(4F, 16.3F, -5F);
	      FLLeg1.setTextureSize(128, 64);
	      FLLeg1.mirror = true;
	      setRotation(FLLeg1, 0F, 0F, 0F);
	      FLLeg2 = new ModelRenderer(this, 11, 19);
	      FLLeg2.addBox(-1F, 2.6F, -2F, 2, 5, 2);
	      FLLeg2.setRotationPoint(5F, 16.3F, -5F);
	      FLLeg2.setTextureSize(128, 64);
	      FLLeg2.mirror = true;
	      setRotation(FLLeg2, 0F, 0F, 0F);
	      FRLeg1 = new ModelRenderer(this, 27, 0);
	      FRLeg1.addBox(-2F, 0F, 0F, 2, 4, 2);
	      FRLeg1.setRotationPoint(-3F, 16.3F, -5F);
	      FRLeg1.setTextureSize(128, 64);
	      FRLeg1.mirror = true;
	      setRotation(FRLeg1, 0F, 0F, 0F);
	      FRLeg2 = new ModelRenderer(this, 11, 19);
	      FRLeg2.addBox(-2F, 2.6F, -2F, 2, 5, 2);
	      FRLeg2.setRotationPoint(-3F, 16.3F, -5F);
	      FRLeg2.setTextureSize(128, 64);
	      FRLeg2.mirror = true;
	      setRotation(FRLeg2, 0F, 0F, 0F);
	      BLLeg1 = new ModelRenderer(this, 0, 0);
	      BLLeg1.addBox(0F, 0F, 0F, 2, 5, 3);
	      BLLeg1.setRotationPoint(4F, 16F, 1F);
	      BLLeg1.setTextureSize(128, 64);
	      BLLeg1.mirror = true;
	      setRotation(BLLeg1, 0F, 0F, 0F);
	      BLLeg2 = new ModelRenderer(this, 0, 19);
	      BLLeg2.addBox(0F, 3F, 3F, 2, 5, 3);
	      BLLeg2.setRotationPoint(4F, 16F, 1F);
	      BLLeg2.setTextureSize(128, 64);
	      BLLeg2.mirror = true;
	      setRotation(BLLeg2, 0F, 0F, 0F);
	      BRLeg1 = new ModelRenderer(this, 0, 0);
	      BRLeg1.addBox(0F, 0F, 0F, 2, 5, 3);
	      BRLeg1.setRotationPoint(-5F, 16F, 1F);
	      BRLeg1.setTextureSize(128, 64);
	      BRLeg1.mirror = true;
	      setRotation(BRLeg1, 0F, 0F, 0F);
	      BRLeg2 = new ModelRenderer(this, 0, 19);
	      BRLeg2.addBox(0F, 3F, 3F, 2, 5, 3);
	      BRLeg2.setRotationPoint(-5F, 16F, 1F);
	      BRLeg2.setTextureSize(128, 64);
	      BRLeg2.mirror = true;
	      setRotation(BRLeg2, 0F, 0F, 0F);	
	      Tail1 = new ModelRenderer(this, 35, 2);
	      Tail1.addBox(-2.5F, 0F, 0F, 5, 5, 5);
	      Tail1.setRotationPoint(0.5F, 13.5F, 5F);
	      Tail1.setTextureSize(128, 64);
	      Tail1.mirror = true;
	      setRotation(Tail1, -0.3346145F, 0F, 0F);
	      Tail2 = new ModelRenderer(this, 56, 2);
	      Tail2.addBox(-2F, -0.5F, 4F, 4, 4, 6);
	      Tail2.setRotationPoint(0.5F, 13.5F, 5F);
	      Tail2.setTextureSize(128, 64);
	      Tail2.mirror = true;
	      setRotation(Tail2, -0.5205064F, 0F, 0F);
	      Tail3 = new ModelRenderer(this, 71, 0);
	      Tail3.addBox(-1.5F, 1F, 8F, 3, 3, 5);
	      Tail3.setRotationPoint(0.5F, 13.5F, 5F);
	      Tail3.setTextureSize(128, 64);
	      Tail3.mirror = true;
	      setRotation(Tail3, -0.4089706F, 0F, 0F);
	      Tail4 = new ModelRenderer(this, 83, 0);
	      Tail4.addBox(-1F, 3.5F, 12F, 2, 2, 3);
	      Tail4.setRotationPoint(0.5F, 13.5F, 5F);
	      Tail4.setTextureSize(128, 64);
	      Tail4.mirror = true;
	      setRotation(Tail4, -0.2602562F, 0F, 0F);
	      Tail5 = new ModelRenderer(this, 83, 0);
	      Tail5.addBox(-1F, 6F, 13.8F, 2, 2, 3);
	      Tail5.setRotationPoint(0.5F, 13.5F, 5F);
	      Tail5.setTextureSize(128, 64);
	      Tail5.mirror = true;
	      setRotation(Tail5, -0.0860354F, 0F, 0F);
	      Horn1 = new ModelRenderer(this, 0, 31);
	      Horn1.addBox(-3F, -1F, -8F, 1, 1, 6);
	      Horn1.setRotationPoint(1F, 7F, -7F);
	      Horn1.setTextureSize(128, 64);
	      Horn1.mirror = true;
	      setRotation(Horn1, 0F, 0F, 0F);
	      Horn2 = new ModelRenderer(this, 0, 31);
	      Horn2.addBox(1F, -1F, -8F, 1, 1, 6);
	      Horn2.setRotationPoint(1F, 7F, -7F);
	      Horn2.setTextureSize(128, 64);
	      Horn2.mirror = true;
	      setRotation(Horn2, 0F, 0F, 0F);
	  }
	    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	    {
	        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
	        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

	        if (this.isChild)
	        {
	            float f6 = 2.0F;
	    	    Horn1.render(par7);
	    	    Horn2.render(par7);
	    	    Head.render(par7);
	    	    Snout2.render(par7);
	    	    Snout1.render(par7);
	    	    Neck2.renderWithRotation(par7);
	    	    Neck1.renderWithRotation(par7);
	    	    Torso.render(par7);
	    	    FLLeg1.render(par7);
	    	    FLLeg2.render(par7);
	    	    FRLeg1.render(par7);
	    	    FRLeg2.render(par7);
	    	    BLLeg1.render(par7);
	    	    BLLeg2.render(par7);
	    	    BRLeg1.render(par7);
	    	    BRLeg2.render(par7);
	    	    Tail1.renderWithRotation(par7);
	    	    Tail2.renderWithRotation(par7);
	    	    Tail3.renderWithRotation(par7);
	    	    Tail4.renderWithRotation(par7);
	    	    Tail5.renderWithRotation(par7);
	        }
	        else
	        {
	            GL11.glPushMatrix();
	            GL11.glScalef(2.6F, 2.6F, 2.6F);
	            GL11.glTranslatef(0.0F, -0.9F, 0.0F);
	    	    Horn1.render(par7);
	    	    Horn2.render(par7);
	    	    Head.render(par7);
	    	    Snout2.render(par7);
	    	    Snout1.render(par7);
	    	    Neck2.renderWithRotation(par7);
	    	    Neck1.renderWithRotation(par7);
	    	    Torso.render(par7);
	    	    FLLeg1.renderWithRotation(par7);
	    	    FLLeg2.renderWithRotation(par7);
	    	    FRLeg1.renderWithRotation(par7);
	    	    FRLeg2.renderWithRotation(par7);
	    	    BLLeg1.renderWithRotation(par7);
	    	    BLLeg2.renderWithRotation(par7);
	    	    BRLeg1.renderWithRotation(par7);
	    	    BRLeg2.renderWithRotation(par7);
	    	    Tail1.renderWithRotation(par7);
	    	    Tail2.renderWithRotation(par7);
	    	    Tail3.renderWithRotation(par7);
	    	    Tail4.renderWithRotation(par7);
	    	    Tail5.renderWithRotation(par7);
	            GL11.glPopMatrix();
	        }
	    }

	    /**
	     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
	     * and third as in the setRotationAngles method.
	     */
	    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	    {	
	    	
            	this.Torso.setRotationPoint(0.5F, 13F, -7F);
            	this.Torso.rotateAngleX = par3 / (180F / (float)Math.PI);
            	
	            this.BRLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
	            this.FRLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
	            this.BRLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
	            this.FRLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
	            this.BLLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
	            this.FLLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
	            this.BLLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
	            this.FLLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
	            
	            this.Tail1.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.455F * par3;
	            this.Tail2.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.454F * par3;
	            this.Tail3.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.453F * par3;
	            this.Tail4.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.452F * par3;
	            this.Tail5.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.451F * par3;

	    }

	    /**
	     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
	     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
	     * "far" arms and legs can swing at most.
	     */
	    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	    {
	        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	        this.Head.rotateAngleX = par5 / (280F / (float)Math.PI);
	        this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
	        this.Snout1.rotateAngleX = par5 / (280F / (float)Math.PI);
	        this.Snout1.rotateAngleY = par4 / (180F / (float)Math.PI);
	        this.Snout2.rotateAngleX = par5 / (280F / (float)Math.PI);
	        this.Snout2.rotateAngleY = par4 / (180F / (float)Math.PI);
	        this.Horn1.rotateAngleX = par5 / (280F / (float)Math.PI);
	        this.Horn1.rotateAngleY = par4 / (180F / (float)Math.PI);
	        this.Horn2.rotateAngleX = par5 / (280F / (float)Math.PI);
	        this.Horn2.rotateAngleY = par4 / (180F / (float)Math.PI);
	        
		    this.Neck2.setRotationPoint(1F, 13F, -7F);
		    this.Neck1.setRotationPoint(1F, 13F, -7F);
	        this.Neck1.rotateAngleY = par4 / (180F / (float)Math.PI);
	        this.Neck2.rotateAngleY = par4 / (180F / (float)Math.PI);
	    }
	    
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	}


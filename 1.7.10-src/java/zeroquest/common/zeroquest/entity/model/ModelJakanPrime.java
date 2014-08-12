package common.zeroquest.entity.model;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityJakanPrime;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelJakanPrime extends ModelBase
{
  //fields
    public ModelRenderer LWing;
    public ModelRenderer RWing;
    public ModelRenderer LWingTip;
    public ModelRenderer RWingTip;
    public ModelRenderer Tail1;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer THorn1;
    public ModelRenderer THorn2;
    public ModelRenderer Head;
    public ModelRenderer Neck1;
    public ModelRenderer Neck2;
    public ModelRenderer Torso;
    public ModelRenderer FLLeg;
    public ModelRenderer FRLeg;
    public ModelRenderer BLLeg;
    public ModelRenderer BRLeg;
  
  public ModelJakanPrime()
  {
    textureWidth = 128;
    textureHeight = 64;
    
    setTextureOffset("RWing.bone", 0, 40);
    setTextureOffset("RWingTip.bone", 30, 44);
    setTextureOffset("LWing.bone", 0, 40);
    setTextureOffset("LWingTip.bone", 30, 44);
    
    setTextureOffset("RWing.skin", 66, 52);
    setTextureOffset("RWingTip.skin", 88, 39);
    setTextureOffset("LWing.skin", 30, 52);
    setTextureOffset("LWingTip.skin", -9, 52);
    
    setTextureOffset("Head.skull", 74, 31);
    setTextureOffset("Head.horn1", 0, 31);
    setTextureOffset("Head.horn2", 0, 31);
    setTextureOffset("Head.horn3", 0, 31);
    setTextureOffset("Head.snout1", 74, 42);
    setTextureOffset("Head.snout2", 87, 42);
    
    setTextureOffset("FLLeg.leg1", 27, 0);
    setTextureOffset("FLLeg.leg2", 11, 19);
    setTextureOffset("FRLeg.leg1", 27, 0);
    setTextureOffset("FRLeg.leg2", 11, 19);
    setTextureOffset("BLLeg.leg1", 0, 0);
    setTextureOffset("BLLeg.leg2", 0, 19);
    setTextureOffset("BRLeg.leg1", 0, 0);
    setTextureOffset("BRLeg.leg2", 0, 19);

    RWing = new ModelRenderer(this, "RWing");
    RWing.addBox("bone", -12F, 0F, 0F, 12, 2, 2);
    RWing.addBox("skin", -12F, 1F, 1F, 12, 0, 12);
    RWing.setRotationPoint(-2F, 11.3F, -6F);
    RWingTip = new ModelRenderer(this, "RWingTip");
    RWingTip.addBox("bone", -12F, 0.5F, 0.5F, 14, 1, 1);
    RWingTip.addBox("skin", -12F, 1F, 1F, 14, 0, 12);
    RWingTip.setRotationPoint(-14F, 0F, 0F);
    LWing = new ModelRenderer(this, "LWing");
    LWing.addBox("bone", 0F, 0F, 0F, 12, 2, 2);
    LWing.addBox("skin", 0F, 1F, 1F, 12, 0, 12);
    LWing.setRotationPoint(3F, 11.3F, -6F); 
    LWingTip = new ModelRenderer(this, "LWingTip");
    LWingTip.addBox("bone", 0F, 0.5F, 0.5F, 14, 1, 1);
    LWingTip.addBox("skin", 0F, 1F, 1F, 14, 0, 12);
    LWingTip.setRotationPoint(12F, 0F, 0F);
    
    Tail1 = new ModelRenderer(this, 42, 0);
    Tail1.addBox(-2.5F, 0F, 0F, 5, 5, 6);
    Tail1.setRotationPoint(0.5F, 12.5F, 5F);
    Tail1.setTextureSize(128, 64);
    Tail1.mirror = true;
    setRotation(Tail1, 0F, 0F, 0F);
    Tail2 = new ModelRenderer(this, 67, 2);
    Tail2.addBox(-2F, 0.5F, 5F, 4, 4, 6);
    Tail2.setRotationPoint(0.5F, 12.5F, 5F);
    Tail2.setTextureSize(128, 64);
    Tail2.mirror = true;
    setRotation(Tail2, 0F, 0F, 0F);
    Tail3 = new ModelRenderer(this, 90, 0);
    Tail3.addBox(-1.5F, 1F, 9F, 3, 3, 6);
    Tail3.setRotationPoint(0.5F, 12.5F, 5F);
    Tail3.setTextureSize(128, 64);
    Tail3.mirror = true;
    setRotation(Tail3, 0F, 0F, 0F);
    Tail4 = new ModelRenderer(this, 111, 0);
    Tail4.addBox(-1F, 1.5F, 13F, 2, 2, 6);
    Tail4.setRotationPoint(0.5F, 12.5F, 5F);
    Tail4.setTextureSize(128, 64);
    Tail4.mirror = true;
    setRotation(Tail4, 0F, 0F, 0F);
    THorn1 = new ModelRenderer(this, 0, 31);
    THorn1.addBox(1F, 2F, 17.8F, 1, 1, 6);
    THorn1.setRotationPoint(0.5F, 12.5F, 5F);
    THorn1.setTextureSize(128, 64);
    THorn1.mirror = true;
    setRotation(THorn1, 0F, 0F, 0F);
    THorn2 = new ModelRenderer(this, 0, 31);
    THorn2.addBox(-2F, 2F, 17.8F, 1, 1, 6);
    THorn2.setRotationPoint(0.5F, 12.5F, 5F);
    THorn2.setTextureSize(128, 64);
    THorn2.mirror = true;
    setRotation(THorn2, 0F, 0F, 0F);
      Head = new ModelRenderer(this, "Head");
      Head.addBox("skull", -3F, 0F, -12F, 5, 5, 5);
      Head.addBox("horn1", -3F, -1F, -8F, 1, 1, 6);
      Head.addBox("horn2", 1F, -1F, -8F, 1, 1, 6);
      Head.addBox("horn3", -1F, -1F, -10F, 1, 1, 6);
      Head.addBox("snout1", -2F, 2F, -15F, 3, 2, 3);
      Head.addBox("snout2", -1.5F, 4F, -14F, 2, 1, 2);
      Head.setRotationPoint(1F, 6F, -7F);
      Neck1 = new ModelRenderer(this, 33, 13);
      Neck1.addBox(-3F, 0F, -4F, 5, 5, 7);
      Neck1.setRotationPoint(1F, 12F, -7F);
      setRotation(Neck1, -0.4089647F, 0F, 0F);
      Neck2 = new ModelRenderer(this, 51, 19);
      Neck2.addBox(-2.5F, 1.833333F, -8.233334F, 4, 4, 7);
      Neck2.setRotationPoint(1F, 12F, -7F);
      setRotation(Neck2, -0.8551081F, 0F, 0F);
      Torso = new ModelRenderer(this, 0, 0);
      Torso.addBox(-3.5F, 0F, 0F, 7, 6, 12);
      Torso.setRotationPoint(0.5F, 12F, -7F);
      setRotation(Torso, 0F, 0F, 0F);
      BLLeg = new ModelRenderer(this, "BLLeg");
      BLLeg.addBox("leg1", 0F, 0F, 0F, 2, 5, 3);
      BLLeg.addBox("leg2", 0F, 3F, 3F, 2, 5, 3);
      BLLeg.setRotationPoint(4F, 16F, 1F);
      BRLeg = new ModelRenderer(this, "BRLeg");
      BRLeg.addBox("leg1", 0F, 0F, 0F, 2, 5, 3);
      BRLeg.addBox("leg2", 0F, 3F, 3F, 2, 5, 3);
      BRLeg.setRotationPoint(-5F, 16F, 1F);
      FLLeg = new ModelRenderer(this, "FLLeg");
      FLLeg.addBox("leg1", 0F, 0F, 0F, 2, 4, 2);
      FLLeg.addBox("leg2", 0F, 2.6F, 2F, 2, 5, 2);
      FLLeg.setRotationPoint(4F, 16.3F, -5F);
      FRLeg = new ModelRenderer(this, "FRLeg");
      FRLeg.addBox("leg1", -2F, 0F, 0F, 2, 4, 2);
      FRLeg.addBox("leg2", -2F, 2.6F, 2F, 2, 5, 2);
      FRLeg.setRotationPoint(-3F, 16.3F, -5F);
      RWing.addChild(this.RWingTip);
      LWing.addChild(this.LWingTip); 
  }
  
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      
      if (this.isChild)
      {
        RWing.render(par7);
        LWing.render(par7); 
  	    Head.render(par7);
  	    Neck2.renderWithRotation(par7);
  	    Neck1.renderWithRotation(par7);
  	    Torso.render(par7);
	    FLLeg.render(par7);
	    FRLeg.render(par7);
	    BLLeg.render(par7);
	    BRLeg.render(par7);
  	    Tail1.render(par7);
  	    Tail2.render(par7);
  	    Tail3.render(par7);
  	    Tail4.render(par7);
  	    THorn1.render(par7);
  	    THorn2.render(par7);
      }
      else
      {
          GL11.glPushMatrix();
          GL11.glScalef(2.6F, 2.6F, 2.6F);
          GL11.glTranslatef(0.0F, -0.9F, 0.0F);
          RWing.render(par7);
          LWing.render(par7); 
  	    Head.render(par7);
  	    Neck2.renderWithRotation(par7);
  	    Neck1.renderWithRotation(par7);
  	    Torso.render(par7);
	    FLLeg.render(par7);
	    FRLeg.render(par7);
	    BLLeg.render(par7);
	    BRLeg.render(par7);
  	    Tail1.render(par7);
  	    Tail2.render(par7);
  	    Tail3.render(par7);
  	    Tail4.render(par7);
  	    THorn1.render(par7);
  	    THorn2.render(par7);
          GL11.glPopMatrix();
      }
  }

  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {	
      EntityJakanPrime entityjakanprime = (EntityJakanPrime)par1EntityLivingBase;
      
      if (entityjakanprime.isSitting())
      {
       this.RWing.setRotationPoint(-2F, 17.3F, -6F);
       this.LWing.setRotationPoint(3F, 17.3F, -6F);   
	   this.Tail1.setRotationPoint(0.5F, 18.3F, 5F);
	   this.Tail2.setRotationPoint(0.5F, 18.3F, 5F);
	   this.Tail3.setRotationPoint(0.5F, 18.3F, 5F);
	   this.Tail4.setRotationPoint(0.5F, 18.3F, 5F);
       this.THorn1.setRotationPoint(0.5F, 18.3F, 5F);
       this.THorn2.setRotationPoint(0.5F, 18.3F, 5F);
       this.Torso.setRotationPoint(0.5F, 17.8F, -7F);
       this.Head.setRotationPoint(1F, 12F, -7F);
       this.Neck1.setRotationPoint(1F, 17.8F, -7F);
       this.Neck2.setRotationPoint(1F, 17.8F, -7F);
       this.BLLeg.setRotationPoint(4F, 17.8F, 6F);
       this.BLLeg.rotateAngleX = -1.598687F;
       this.BRLeg.setRotationPoint(-5F, 17.8F, 6F);
       this.BRLeg.rotateAngleX = -1.598687F;
       this.FLLeg.setRotationPoint(4F, 19.3F, -5F);
       this.FLLeg.rotateAngleX = -1.59868F;
       this.FRLeg.setRotationPoint(-3F, 19.3F, -5F);
       this.FRLeg.rotateAngleX = -1.598687F;
      }
      else
      {
	        this.RWing.setRotationPoint(-2F, 11.3F, -6F);
	        this.LWing.setRotationPoint(3F, 11.3F, -6F);    	  
		  	this.Tail1.setRotationPoint(0.5F, 12.5F, 5F);
			this.Tail2.setRotationPoint(0.5F, 12.5F, 5F);
			this.Tail3.setRotationPoint(0.5F, 12.5F, 5F);
			this.Tail4.setRotationPoint(0.5F, 12.5F, 5F);
            this.THorn1.setRotationPoint(0.5F, 12.7F, 5F);
            this.THorn2.setRotationPoint(0.5F, 12.7F, 5F);
	      	this.Torso.setRotationPoint(0.5F, 12F, -7F);
	      	this.Neck1.setRotationPoint(1F, 12F, -7F);
	      	this.Neck2.setRotationPoint(1F, 12F, -7F);
	      	this.Head.setRotationPoint(1F, 6F, -7F);
	      	this.BLLeg.setRotationPoint(4F, 16F, 1F);
	      	this.BRLeg.setRotationPoint(-5F, 16F, 1F);
	      	this.FLLeg.setRotationPoint(4F, 16.3F, -5F);
	      	this.FRLeg.setRotationPoint(-3F, 16.3F, -5F);
      	this.Torso.rotateAngleX = par3 / (180F / (float)Math.PI);
      	
      	
          this.BRLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.FRLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.BLLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          this.FLLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          
          this.Tail1.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.455F * par3;
          this.Tail2.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.454F * par3;
          this.Tail3.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.453F * par3;
          this.Tail4.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.452F * par3;
          this.THorn1.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.453F * par3;
          this.THorn2.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.452F * par3;

      }
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
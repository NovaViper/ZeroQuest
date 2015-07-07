package net.novaviper.zeroquest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.novaviper.zeroquest.common.entity.creature.EntityKortor;

import org.lwjgl.opengl.GL11;


public class ModelKortor extends ModelBase
{
  //fields
    ModelRenderer HornBase;
    ModelRenderer Horn1;
    ModelRenderer Horn2;
    ModelRenderer Horn3;
    ModelRenderer Head;
    ModelRenderer Jaw;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer Neck2;
    ModelRenderer Neck1;
    ModelRenderer Torso;
  
  public ModelKortor()
  {
    textureWidth = 128;
    textureHeight = 64;
    
    setTextureOffset("RLeg.leg", 0, 28);
    setTextureOffset("RLeg.foot", 51, 32);
    setTextureOffset("LLeg.leg", 0, 28);
    setTextureOffset("LLeg.foot", 51, 32);
    
    HornBase = new ModelRenderer(this, 89, 16);
    HornBase.addBox(-1F, -0.2666667F, -0.5F, 2, 2, 2);
    HornBase.setRotationPoint(0F, 1F, -20F);
    HornBase.mirror = true;
    setRotation(HornBase, 0F, 0F, 0F);
    Horn1 = new ModelRenderer(this, 89, 21);
    Horn1.addBox(-1.5F, -2F, 0F, 3, 5, 3);
    Horn1.setRotationPoint(0F, -3F, 1F);
    Horn1.setTextureSize(128, 64);
    Horn1.mirror = true;
    setRotation(Horn1, -0.5205006F, 0F, 0F);
    Horn2 = new ModelRenderer(this, 89, 30);
    Horn2.addBox(-1F, -2F, 0F, 2, 5, 2);
    Horn2.setRotationPoint(0F, -6F, 4F);
    Horn2.setTextureSize(128, 64);
    Horn2.mirror = true;
    setRotation(Horn2, -0.8922867F, 0F, 0F);
    Horn3 = new ModelRenderer(this, 89, 38);
    Horn3.addBox(-0.5F, -3F, 0F, 1, 5, 1);
    Horn3.setRotationPoint(0F, -7.3F, 7.5F);
    Horn3.setTextureSize(128, 64);
    Horn3.mirror = true;
    setRotation(Horn3, -1.33843F, 0F, 0F);
      Head = new ModelRenderer(this, 38, 0);
      Head.addBox(-3.5F, 0F, -20F, 7, 5, 9);
      Head.setRotationPoint(0F, -6F, -11F);
      Jaw = new ModelRenderer(this, 46, 53);
      Jaw.addBox(-3.5F, 0F, -10F, 7, 2, 9);
      Jaw.setRotationPoint(0F, 5F, -11F);
      setRotation(Jaw, 0F, 0F, 0F);
      RightLeg = new ModelRenderer(this, "RLeg");
      RightLeg.addBox("leg", -5F, 0F, -2.5F, 5, 19, 5);
      RightLeg.addBox("foot", -5.5F, 17F, -5F, 6, 5, 8);
      RightLeg.setRotationPoint(-4F, 2F, -1F);
      RightLeg.setTextureSize(128, 64);
      RightLeg.mirror = true;
      setRotation(RightLeg, 0F, 0F, 0F);
      LeftLeg = new ModelRenderer(this, "LLeg");
      LeftLeg.addBox("leg", 0F, 0F, -2.5F, 5, 19, 5);
      LeftLeg.addBox("foot", -0.5F, 17F, -5F, 6, 5, 8);
      LeftLeg.setRotationPoint(4F, 2F, -1F);
      LeftLeg.setTextureSize(128, 64);
      LeftLeg.mirror = true;
      setRotation(LeftLeg, 0F, 0F, 0F);
      Tail1 = new ModelRenderer(this, 22, 26);
      Tail1.addBox(-4F, -3F, 0F, 6, 6, 7);
      Tail1.setRotationPoint(1F, 4F, 5F);
      Tail1.setTextureSize(128, 64);
      Tail1.mirror = true;
      setRotation(Tail1, 0F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 22, 40);
      Tail2.addBox(-3.5F, -2.5F, 7F, 5, 5, 7);
      Tail2.setRotationPoint(1F, 4F, 5F);
      Tail2.setTextureSize(128, 64);
      Tail2.mirror = true;
      setRotation(Tail2, 0F, 0F, 0F);
      Tail3 = new ModelRenderer(this, 22, 53);
      Tail3.addBox(-3.5F, -2.5F, 14F, 4, 4, 7);
      Tail3.setRotationPoint(1.5F, 4.5F, 5F);
      Tail3.setTextureSize(128, 64);
      Tail3.mirror = true;
      setRotation(Tail3, 0F, 0F, 0F);
      Tail4 = new ModelRenderer(this, 0, 54);
      Tail4.addBox(-3F, -2F, 21F, 3, 3, 7);
      Tail4.setRotationPoint(1.5F, 4.5F, 5F);
      Tail4.setTextureSize(128, 64);
      Tail4.mirror = true;
      setRotation(Tail4, 0F, 0F, 0F);
      Tail5 = new ModelRenderer(this, 71, 51);
      Tail5.addBox(-2.5F, -1.5F, 28F, 2, 2, 7);
      Tail5.setRotationPoint(1.5F, 4.5F, 5F);
      Tail5.setTextureSize(128, 64);
      Tail5.mirror = true;
      setRotation(Tail5, 0F, 0F, 0F);
      RightArm = new ModelRenderer(this, 0, 0);
      RightArm.addBox(-2F, 0F, 0F, 2, 6, 1);
      RightArm.setRotationPoint(-4F, 6F, -8F);
      RightArm.setTextureSize(128, 64);
      RightArm.mirror = true;
      setRotation(RightArm, 0F, 0F, 0F);
      LeftArm = new ModelRenderer(this, 0, 0);
      LeftArm.addBox(0F, 0F, 0F, 2, 6, 1);
      LeftArm.setRotationPoint(4F, 6F, -8F);
      LeftArm.setTextureSize(128, 64);
      LeftArm.mirror = true;
      setRotation(LeftArm, 0F, 0F, 0F);
      Neck2 = new ModelRenderer(this, 57, 16);
      Neck2.addBox(-2.5F, 2.4F, -12.2F, 5, 6, 9);
      Neck2.setRotationPoint(0F, 1F, -11F);
      Neck2.setTextureSize(128, 64);
      Neck2.mirror = true;
      setRotation(Neck2, -0.669215F, 0F, 0F);
      Neck1 = new ModelRenderer(this, 88, 0);
      Neck1.addBox(-3F, -0.5F, -6.5F, 6, 7, 8);
      Neck1.setRotationPoint(0F, 1F, -11F);
      Neck1.setTextureSize(128, 64);
      Neck1.mirror = true;
      setRotation(Neck1, -0.2230717F, 0F, 0F);
      Torso = new ModelRenderer(this, 0, 0);
      Torso.addBox(-4F, 0F, 0F, 8, 8, 16);
      Torso.setRotationPoint(0F, 0F, -11F);
      Torso.setTextureSize(128, 64);
      Torso.mirror = true;
      setRotation(Torso, 0F, 0F, 0F);
      Head.addChild(Jaw);
      Head.addChild(HornBase);
      HornBase.addChild(Horn1);
      HornBase.addChild(Horn2);
      HornBase.addChild(Horn3); 
  }
  
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      
      if(this.isChild){
          float f6 = 2.0F;
          GL11.glPushMatrix();
          GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
          GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
          Head.render(par7);
          Neck1.renderWithRotation(par7);
          Neck2.renderWithRotation(par7);
          Torso.render(par7);
          RightArm.render(par7);
          LeftArm.render(par7);
          RightLeg.render(par7);
          LeftLeg.render(par7);
          Tail1.render(par7);
          Tail2.render(par7);
          Tail3.render(par7);
          GL11.glPopMatrix();
    	  
      }else{
          GL11.glPushMatrix();
          GL11.glScalef(1F, 1F, 1F);
          GL11.glTranslatef(0.0F, 0F, 0.0F);
          Head.render(par7);
          Neck1.renderWithRotation(par7);
          Neck2.renderWithRotation(par7);
          Torso.render(par7);
          RightArm.render(par7);
          LeftArm.render(par7);
          RightLeg.render(par7);
          LeftLeg.render(par7);
          Tail1.render(par7);
          Tail2.render(par7);
          Tail3.render(par7);
          Tail4.render(par7);
          Tail5.render(par7);
          GL11.glPopMatrix();
      }
  }
  
  private float func_110683_a(float par1, float par2, float par3)
  {
      float f3;

      for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
      {
          ;
      }

      while (f3 >= 180.0F)
      {
          f3 -= 360.0F;
      }

      return par1 + par3 * f3;
  }
  
  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
      
      EntityKortor entitykortor = (EntityKortor)par1EntityLivingBase;
      float f11 = entitykortor.func_110201_q(par4);
      
      if (entitykortor.isSitting())
      {
      	
          Tail5.setRotationPoint(1.5F, 17.5F, 5F);
          Tail4.setRotationPoint(1.5F, 17.5F, 5F);
          Tail3.setRotationPoint(1.5F, 17.5F, 5F);
          Tail2.setRotationPoint(1F, 17F, 5F);
          Tail1.setRotationPoint(1F, 17F, 5F);
          LeftLeg.setRotationPoint(4F, 17F, -1F);
          RightLeg.setRotationPoint(-4F, 17F, -1F);
          Head.setRotationPoint(0F, 7F, -11F);
          Jaw.setRotationPoint(0F, 5F, -11F);
          Horn3.setRotationPoint(0F, -7.3F, 7.5F);
          Horn2.setRotationPoint(0F, -6F, 4F);
          Horn1.setRotationPoint(0F, -3F, 1F);
          HornBase.setRotationPoint(0F, 1F, -20F);
          Torso.setRotationPoint(0F, 13F, -11F);
          Neck1.setRotationPoint(0F, 14F, -11F);
          Neck2.setRotationPoint(0F, 14F, -11F);
          RightArm.setRotationPoint(-4F, 15F, -8F);
          LeftArm.setRotationPoint(4F, 15F, -8F);
          setRotation(LeftLeg, -1.226897F, 0F, 0F);
          setRotation(RightLeg, -1.226894F, 0F, 0F);
          setRotation(LeftArm, -1.226897F, 0F, 0F);
          setRotation(RightArm, -1.226894F, 0F, 0F);

      }
      else
      {
    	    HornBase.setRotationPoint(0F, 1F, -20F);
    	    Horn1.setRotationPoint(0F, -3F, 1F);
    	    Horn2.setRotationPoint(0F, -6F, 4F);
    	    Horn3.setRotationPoint(0F, -7.3F, 7.5F);
    	    Head.setRotationPoint(0F, -6F, -11F);
    	    Jaw.setRotationPoint(0F, 5F, -11F);
    	    RightLeg.setRotationPoint(-4F, 2F, -1F);
    	    LeftLeg.setRotationPoint(4F, 2F, -1F);
    	    Tail1.setRotationPoint(1F, 4F, 5F);
    	    Tail2.setRotationPoint(1F, 4F, 5F);
    	    Tail3.setRotationPoint(1.5F, 4.5F, 5F);
    	    Tail4.setRotationPoint(1.5F, 4.5F, 5F);
    	    Tail5.setRotationPoint(1.5F, 4.5F, 5F);
    	    RightArm.setRotationPoint(-4F, 6F, -8F);
    	    LeftArm.setRotationPoint(4F, 6F, -8F);
    	    Neck2.setRotationPoint(0F, 1F, -11F);
    	    Neck1.setRotationPoint(0F, 1F, -11F);
    	    Torso.setRotationPoint(0F, 0F, -11F);
      
    	  this.Torso.rotateAngleY = par3 / (180F / (float)Math.PI);
          this.RightLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.LeftLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          this.RightArm.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.LeftArm.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          
          this.Tail1.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.455F * par3;
          this.Tail2.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.454F * par3;
          this.Tail3.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.453F * par3;
          this.Tail4.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.454F * par3;
          this.Tail5.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.453F * par3;
      }
      
      this.Jaw.rotateAngleX = 0.0F + 0.15707964F * f11;

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
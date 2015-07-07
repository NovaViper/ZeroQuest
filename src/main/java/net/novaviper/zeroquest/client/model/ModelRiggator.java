package net.novaviper.zeroquest.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.novaviper.zeroquest.common.entity.mob.EntityRiggator;

public class ModelRiggator extends ModelBase
{
  //fields
    ModelRenderer LeftHindLeg2;
    ModelRenderer LeftHindLeg1;
    ModelRenderer RightHindLeg2;
    ModelRenderer RightHindLeg1;
    ModelRenderer Mane1;
    ModelRenderer Mane2;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer Torso;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Neck1;
    ModelRenderer Neck2;
    ModelRenderer Head;
    ModelRenderer Tongue;
    ModelRenderer LeftFang;
    ModelRenderer RightFang;
    ModelRenderer BottomJaw;
    ModelRenderer TopJaw;
    private float rightLegStartingRotation = -0.2974289F;
    private float leftLegStartingRotation = -0.2974289F;
    private float neck1StartingRotation = -0.6015813F;
    private float neck2StartingRotation = -0.3105F;
    private float mane2StartingRotation = -0.34653F;
    
    
  public ModelRiggator()
  {
    textureWidth = 128;
    textureHeight = 64;

    LeftHindLeg1 = new ModelRenderer(this, 11, 18);
    LeftHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
    LeftHindLeg1.setRotationPoint(1.5F, 13F, 5F);
    setRotation(LeftHindLeg1, -0.2974289F, 0F, 0F);
    LeftHindLeg2 = new ModelRenderer(this, 9, 44);
    LeftHindLeg2.addBox(-1F, 0F, -1F, 2, 8, 2);
    LeftHindLeg2.setRotationPoint(0F, 3F, 0.5F);
    setRotation(LeftHindLeg2, 0.2974289F, 0F, 0F);
    RightHindLeg1 = new ModelRenderer(this, 11, 31);
    RightHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
    RightHindLeg1.setRotationPoint(-2.5F, 13F, 5F);
    setRotation(RightHindLeg1, -0.2974289F, 0F, 0F);
    RightHindLeg2 = new ModelRenderer(this, 0, 44);
    RightHindLeg2.addBox(-1F, 0F, -1F, 2, 8, 2);
    RightHindLeg2.setRotationPoint(0F, 3F, 0.5F);
    setRotation(RightHindLeg2, 0.2974289F, 0F, 0F);
    Mane1 = new ModelRenderer(this, 43, 0);
    Mane1.addBox(-3F, -3F, -3F, 6, 6, 7);
    Mane1.setRotationPoint(-0.5F, 14F, -3F);
    setRotation(Mane1, 1.496439F, 0F, 0F);
    Neck1 = new ModelRenderer(this, 0, 0);
    Neck1.addBox(-2.5F, -12F, -4F, 4, 7, 4);
    Neck1.setRotationPoint(0.5F, 0F, 0F);
    setRotation(Neck1, -0.6015813F, 0F, 0F);
    Neck2 = new ModelRenderer(this, 23, 15);
    Neck2.addBox(-2F, -16F, 3F, 4, 7, 4);
    Neck2.setRotationPoint(0F, -3F, -3F);
    setRotation(Neck2, -0.3105F, 0F, 0F);
    Mane2 = new ModelRenderer(this, 18, 0);
    Mane2.addBox(-2.5F, -7F, -3.6F, 5, 6, 7);
    Mane2.setRotationPoint(0F, 0F, 0F);
    setRotation(Mane2, -0.34653F, 0F, 0F);
      RightLeg = new ModelRenderer(this, 0, 30);
      RightLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
      RightLeg.setRotationPoint(-3F, 14F, -4F);
      LeftLeg = new ModelRenderer(this, 0, 16);
      LeftLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
      LeftLeg.setRotationPoint(2F, 14F, -4F);
      Tail1 = new ModelRenderer(this, 91, 0);
      Tail1.addBox(-1.5F, 0F, -1F, 4, 8, 4);
      Tail1.setRotationPoint(-1F, 14F, 5F);
      setRotation(Tail1, 1.315962F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 110, 0);
      Tail2.addBox(-1.5F, 0F, -1F, 3, 8, 3);
      Tail2.setRotationPoint(0.5F, 7F, 0.5F);
      setRotation(Tail2, -0.141605F, 0F, 0F);
      Tail3 = new ModelRenderer(this, 119, 13);
      Tail3.addBox(-1.5F, 0F, -1F, 2, 8, 2);
      Tail3.setRotationPoint(0.5F, 5.4F, 0.5F);
      setRotation(Tail3, -0.092891F, 0F, 0F);
      Torso = new ModelRenderer(this, 69, 0);
      Torso.addBox(-2.5F, -3F, -3F, 5, 9, 5);
      Torso.setRotationPoint(-0.5F, 12.5F, 1F);
      setRotation(Torso, 1.496439F, 0F, 0F);
      Head = new ModelRenderer(this, 46, 15);
      Head.addBox(-2.5F, 1.5F, -20F, 5, 5, 5);
      Head.setRotationPoint(-0.5F, 2.5F, -3F);
      BottomJaw = new ModelRenderer(this, 46, 34);
      BottomJaw.addBox(-2F, -1F, -5F, 4, 2, 5);
      BottomJaw.setRotationPoint(0F, 5F, -19.5F);
      TopJaw = new ModelRenderer(this, 46, 26);
      TopJaw.addBox(-2F, -1F, -5F, 4, 2, 5);
      TopJaw.setRotationPoint(0F, 3F, -19.5F);
      LeftFang = new ModelRenderer(this, 67, 17);
      LeftFang.addBox(-0.5F, 0.5F, -0.5F, 1, 3, 1);
      LeftFang.setRotationPoint(1.5F, 0.5F, -4.5F);
      RightFang = new ModelRenderer(this, 67, 17);
      RightFang.addBox(-0.5F, 0.5F, -0.5F, 1, 3, 1);
      RightFang.setRotationPoint(-1.5F, 0.5F, -4.5F);
      Tongue = new ModelRenderer(this, 43, 42);
      Tongue.addBox(-2F, 0.5F, -13F, 4, 0, 13);
      Tongue.setRotationPoint(0F, 4F, -19F);
      Head.addChild(TopJaw);
      Head.addChild(BottomJaw);
      Head.addChild(Tongue);
      TopJaw.addChild(LeftFang);      
      TopJaw.addChild(RightFang);
      LeftHindLeg1.addChild(LeftHindLeg2);
      RightHindLeg1.addChild(RightHindLeg2);
      Mane1.addChild(Mane2);
      Mane1.addChild(Neck1);
      Mane1.addChild(Neck2);
      Tail1.addChild(Tail2);
      Tail2.addChild(Tail3);
  }
  
  @Override
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      
      EntityRiggator entityriggator = (EntityRiggator)par1Entity;
      boolean openMouth = entityriggator.isMouthOpen();
      
      if (this.isChild)
      {
          float f6 = 2.0F;
          GL11.glPushMatrix();
          GL11.glTranslatef(0.0F, 5.0F * par7, 6.0F * par7);
          Head.render(par7);
          Tongue.showModel = openMouth;
          LeftFang.showModel = openMouth;
          RightFang.showModel = openMouth;
          GL11.glPopMatrix();
          GL11.glPushMatrix();
          GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
          GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
          LeftHindLeg1.render(par7);
          RightHindLeg1.render(par7);
          Mane1.render(par7);
          RightLeg.render(par7);
          LeftLeg.render(par7);
          Tail1.render(par7);
          Torso.render(par7);
          Head.render(par7);
          GL11.glPopMatrix();
      }
      else
      {
          GL11.glPushMatrix();
          GL11.glScalef(1.5F, 1.5F, 1.5F);
          GL11.glTranslatef(0.0F, -0.5F, 0.0F);
          Head.render(par7);
          Tongue.showModel = openMouth;
          LeftFang.showModel = openMouth;
          RightFang.showModel = openMouth;  
          LeftHindLeg1.render(par7);
          RightHindLeg1.render(par7);
          Mane1.render(par7);
          RightLeg.render(par7);
          LeftLeg.render(par7);
          Tail1.render(par7);
          Torso.render(par7);
          GL11.glPopMatrix();
      }
  }
  
  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  @Override
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
      EntityRiggator entityriggator = (EntityRiggator)par1EntityLivingBase;
      float f11 = entityriggator.func_110201_q(par4);
      
      this.TopJaw.rotateAngleX = 0.0F + -0.5948578F * f11;
      this.BottomJaw.rotateAngleX = 0.0F + 0.3717861F * f11;
	  
          this.RightHindLeg1.rotateAngleX = rightLegStartingRotation + MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          this.LeftHindLeg1.rotateAngleX = leftLegStartingRotation + MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.RightLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.LeftLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    this.Head.rotateAngleX = par5 / (280F / (float)Math.PI);
    this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
    this.Neck1.rotateAngleX = neck1StartingRotation + par5 / (280F / (float)Math.PI);
    this.Neck2.rotateAngleX = neck2StartingRotation + par5 / (280F / (float)Math.PI);
    this.Mane2.rotateAngleX = mane2StartingRotation + par5 / (280F / (float)Math.PI);
    this.Mane1.rotateAngleY = par4 / (180F / (float)Math.PI);
  }

}

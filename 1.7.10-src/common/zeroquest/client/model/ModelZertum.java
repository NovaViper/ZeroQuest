package common.zeroquest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelZertum extends ModelBase
{
  //fields
    ModelRenderer Nose;
    ModelRenderer Ear2;
    ModelRenderer Ear1;
    ModelRenderer Head;
    ModelRenderer LeftHindLeg2;
    ModelRenderer LeftHindLeg1;
    ModelRenderer RightHindLeg2;
    ModelRenderer RightHindLeg1;
    ModelRenderer Mane1;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer Tail1;
    ModelRenderer Mane2;
    ModelRenderer Torso;
    ModelRenderer Tail2;
    ModelRenderer Neck;
    private float rightLegStartingRotation = -0.2974289F;
    private float lightLegStartingRotation = -0.2974289F;
  
  public ModelZertum()
  {
    textureWidth = 128;
    textureHeight = 64;
    
    Nose = new ModelRenderer(this, 25, 27);
    Nose.addBox(-1.5F, 0F, -3F, 3, 3, 4);
    Nose.setRotationPoint(0F, 2.5F, -15F);
    Nose.setTextureSize(64, 32);
    Nose.mirror = true;
    setRotation(Nose, 0F, 0F, 0F);
    Ear2 = new ModelRenderer(this, 40, 28);
    Ear2.addBox(-1F, -3F, 0F, 2, 3, 1);
    Ear2.setRotationPoint(2F, -0.5F, -10.5F);
    Ear2.setTextureSize(64, 32);
    Ear2.mirror = true;
    setRotation(Ear2, 0F, 0F, 0F);
    Ear1 = new ModelRenderer(this, 40, 28);
    Ear1.addBox(-1F, -3F, 0F, 2, 3, 1);
    Ear1.setRotationPoint(-2F, -0.5F, -10.5F);
    Ear1.setTextureSize(64, 32);
    Ear1.mirror = true;
    setRotation(Ear1, 0F, 0F, 0F);
    Head = new ModelRenderer(this, 25, 15);
    Head.addBox(-3F, -0.5F, -14F, 6, 6, 5);
    Head.setRotationPoint(-0.5F, 6F, -3F);
    Head.setTextureSize(64, 32);
    Head.mirror = true;
    setRotation(Head, 0F, 0F, 0F);
      LeftHindLeg1 = new ModelRenderer(this, 11, 18);
      LeftHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
      LeftHindLeg1.setRotationPoint(1.5F, 13F, 5F);
      LeftHindLeg1.setTextureSize(128, 64);
      LeftHindLeg1.mirror = true;
      setRotation(LeftHindLeg1, -0.2974289F, 0F, 0F);
      LeftHindLeg2 = new ModelRenderer(this, 9, 44);
      LeftHindLeg2.addBox(-1F, 0F, -1F, 2, 8, 2);
      LeftHindLeg2.setRotationPoint(0F, 3F, 0.5F);
      LeftHindLeg2.setTextureSize(128, 64);
      LeftHindLeg2.mirror = true;
      setRotation(LeftHindLeg2, 0.2974289F, 0F, 0F);
      RightHindLeg1 = new ModelRenderer(this, 11, 31);
      RightHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
      RightHindLeg1.setRotationPoint(-2.5F, 13F, 5F);
      RightHindLeg1.setTextureSize(128, 64);
      RightHindLeg1.mirror = true;
      setRotation(RightHindLeg1, -0.2974289F, 0F, 0F);
      RightHindLeg2 = new ModelRenderer(this, 0, 44);
      RightHindLeg2.addBox(-1F, 0F, -1F, 2, 8, 2);
      RightHindLeg2.setRotationPoint(0F, 3F, 0.5F);
      RightHindLeg2.setTextureSize(128, 64);
      RightHindLeg2.mirror = true;
      setRotation(RightHindLeg2, 0.2974289F, 0F, 0F);
      Mane1 = new ModelRenderer(this, 43, 0);
      Mane1.addBox(-3F, -3F, -3F, 6, 6, 7);
      Mane1.setRotationPoint(-0.5F, 14F, -3F);
      Mane1.setTextureSize(128, 64);
      Mane1.mirror = true;
      setRotation(Mane1, 1.496439F, 0F, 0F);
      Neck = new ModelRenderer(this, 0, 0);
      Neck.addBox(-2.5F, -12F, -4F, 4, 7, 4);
      Neck.setRotationPoint(0.5F, 0F, 0F);
      Neck.setTextureSize(64, 32);
      Neck.mirror = true;
      setRotation(Neck, -0.6015813F, 0F, 0F);
      Mane2 = new ModelRenderer(this, 18, 0);
      Mane2.addBox(-2.5F, -7F, -3.6F, 5, 6, 7);
      Mane2.setRotationPoint(0F, 0F, 0F);
      Mane2.setTextureSize(64, 32);
      Mane2.mirror = true;
      setRotation(Mane2, -0.34653F, 0F, 0F);
      RightLeg = new ModelRenderer(this, 0, 30);
      RightLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
      RightLeg.setRotationPoint(-3F, 14F, -4F);
      RightLeg.setTextureSize(128, 64);
      RightLeg.mirror = true;
      setRotation(RightLeg, 0F, 0F, 0F);
      LeftLeg = new ModelRenderer(this, 0, 16);
      LeftLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
      LeftLeg.setRotationPoint(2F, 14F, -4F);
      LeftLeg.setTextureSize(128, 64);
      LeftLeg.mirror = true;
      setRotation(LeftLeg, 0F, 0F, 0F);
      Tail1 = new ModelRenderer(this, 91, 0);
      Tail1.addBox(-1.5F, 0F, -1F, 4, 8, 4);
      Tail1.setRotationPoint(-1F, 14F, 5F);
      Tail1.setTextureSize(128, 64);
      Tail1.mirror = true;
      setRotation(Tail1, 1.315962F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 110, 0);
      Tail2.addBox(-1.5F, 0F, -1F, 3, 8, 3);
      Tail2.setRotationPoint(0.5F, 6F, 0F);
      Tail2.setTextureSize(64, 32);
      Tail2.mirror = true;
      setRotation(Tail2, 0.141605F, 0F, 0F);
      Torso = new ModelRenderer(this, 69, 0);
      Torso.addBox(-2.5F, -3F, -3F, 5, 9, 5);
      Torso.setRotationPoint(-0.5F, 12.5F, 1F);
      Torso.setTextureSize(128, 64);
      Torso.mirror = true;
      setRotation(Torso, 1.496439F, 0F, 0F);
      Head.addChild(Nose);
      Head.addChild(Ear1);
      Head.addChild(Ear2);
      LeftHindLeg1.addChild(LeftHindLeg2);
      RightHindLeg1.addChild(RightHindLeg2);
      Mane1.addChild(Mane2);
      Mane1.addChild(Neck);
      Tail1.addChild(Tail2);
  }
  
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      if (this.isChild)
      {
          float f6 = 2.0F;
          GL11.glPushMatrix();
          GL11.glTranslatef(0.0F, 5.0F * par7, 6.0F * par7);
          Head.render(par7);
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
          GL11.glPopMatrix();
      }
      else
      {
          GL11.glPushMatrix();
          GL11.glScalef(1.5F, 1.5F, 1.5F);
          GL11.glTranslatef(0.0F, -0.5F, 0.0F);
          Head.render(par7);
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
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
      EntityZertum entityzertum = (EntityZertum)par1EntityLivingBase;
      
      if (entityzertum.isAngry())
      {
          this.Ear1.rotateAngleX = -0.5948578F;
          this.Ear2.rotateAngleX = -0.5948578F;
      }else if (entityzertum.getHealth() <=10){
          this.Ear1.rotateAngleX = -0.9948578F;
          this.Ear2.rotateAngleX = -0.9948578F;
      }else{
          this.Ear1.rotateAngleX = 0.0F;
          this.Ear2.rotateAngleX = 0.0F;
      }
      
      if (entityzertum.isSitting()) //TODO
      {
          LeftHindLeg1.setRotationPoint(1.5F, 18F, 5F);
          LeftHindLeg1.rotateAngleX = -2.082003F;
          LeftHindLeg2.setRotationPoint(0F, 3.7F, 0F);//          
          LeftHindLeg2.rotateAngleX = 2.082003F;
          RightHindLeg1.setRotationPoint(-2.5F, 18F, 5F);
          RightHindLeg1.rotateAngleX = -2.082003F;
          RightHindLeg2.setRotationPoint(0F, 3.7F, 0F);//         
          RightHindLeg2.rotateAngleX = 2.082003F;
          Torso.setRotationPoint(-0.5F, 15F, 1F);
          Torso.rotateAngleX = 0.9759358F;
          Tail1.setRotationPoint(-1F, 19.5F, 5F);
          Tail1.rotateAngleX = 1.055712F;
    	  
      }
      else
      {  
          LeftHindLeg1.setRotationPoint(1.5F, 13F, 5F);
          LeftHindLeg1.rotateAngleX = -0.2974289F;
          LeftHindLeg2.setRotationPoint(0F, 3F, 0.5F);
          LeftHindLeg2.rotateAngleX = 0.2974289F;
          RightHindLeg1.setRotationPoint(-2.5F, 13F, 5F);
          RightHindLeg1.rotateAngleX = -0.2974289F;
          RightHindLeg2.setRotationPoint(0F, 3F, 0.5F);
          RightHindLeg2.rotateAngleX = 0.2974289F;
          Torso.setRotationPoint(-0.5F, 12.5F, 1F);
          Torso.rotateAngleX = 1.496439F;
          Tail1.setRotationPoint(-1F, 14F, 5F);
          Tail1.rotateAngleX = 1.315962F;
          
          this.RightHindLeg1.rotateAngleX = rightLegStartingRotation + MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          this.LeftHindLeg1.rotateAngleX = lightLegStartingRotation + MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.RightLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.LeftLeg.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
      }
	  
      this.Head.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
      this.Mane1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.08F);
      this.Torso.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
      this.Tail1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.32F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    this.Head.rotateAngleX = par5 / (280F / (float)Math.PI);
    this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
    this.Mane1.rotateAngleY = par4 / (180F / (float)Math.PI);
  }
}
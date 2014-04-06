package common.zeroquest.entity.model;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityRedZertum;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelDarkZertum extends ModelBase
{
  //fields
    ModelRenderer Spine;
    ModelRenderer WolfHead;
    ModelRenderer Body;
    ModelRenderer Mane;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Tail;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
  
  public ModelDarkZertum()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      WolfHead = new ModelRenderer(this, 0, 0);
      WolfHead.addBox(-3F, -3F, -2F, 6, 6, 4);
      WolfHead.setRotationPoint(-1F, 13.5F, -7F);
      WolfHead.setTextureSize(64, 32);
      WolfHead.mirror = true;
      setRotation(WolfHead, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 18, 14);
      Body.addBox(-4F, -2F, -3F, 6, 9, 6);
      Body.setRotationPoint(0F, 14F, 2F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 1.570796F, 0F, 0F);
      Mane = new ModelRenderer(this, 21, 0);
      Mane.addBox(-4F, -3F, -3F, 8, 6, 7);
      Mane.setRotationPoint(-1F, 14F, -3F);
      Mane.setTextureSize(64, 32);
      Mane.mirror = true;
      setRotation(Mane, 1.570796F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 0, 19);
      Leg1.addBox(-1F, 0F, -1F, 2, 8, 2);
      Leg1.setRotationPoint(-2.5F, 16F, 7F);
      Leg1.setTextureSize(64, 32);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 51, 21);
      Leg2.addBox(-1F, 0F, -1F, 2, 8, 2);
      Leg2.setRotationPoint(0.5F, 16F, 7F);
      Leg2.setTextureSize(64, 32);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg3 = new ModelRenderer(this, 0, 19);
      Leg3.addBox(-1F, 0F, -1F, 2, 8, 2);
      Leg3.setRotationPoint(-2.5F, 16F, -4F);
      Leg3.setTextureSize(64, 32);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 0F, 0F);
      Leg4 = new ModelRenderer(this, 51, 21);
      Leg4.addBox(-1F, 0F, -1F, 2, 8, 2);
      Leg4.setRotationPoint(0.5F, 16F, -4F);
      Leg4.setTextureSize(64, 32);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 0F, 0F);
      Tail = new ModelRenderer(this, 9, 19);
      Tail.addBox(-1F, 0F, -1F, 2, 8, 2);
      Tail.setRotationPoint(-1F, 12F, 9F);
      Tail.setTextureSize(64, 32);
      Tail.mirror = true;
      Ear1 = new ModelRenderer(this, 17, 14);
      Ear1.addBox(-3F, -5F, 0F, 2, 2, 1);
      Ear1.setRotationPoint(-1F, 13.5F, -7F);
      Ear1.setTextureSize(64, 32);
      Ear1.mirror = true;
      setRotation(Ear1, 0F, 0F, 0F);
      Ear2 = new ModelRenderer(this, 17, 14);
      Ear2.addBox(1F, -5F, 0F, 2, 2, 1);
      Ear2.setRotationPoint(-1F, 13.5F, -7F);
      Ear2.setTextureSize(64, 32);
      Ear2.mirror = true;
      setRotation(Ear2, 0F, 0F, 0F);
      Nose = new ModelRenderer(this, 0, 10);
      Nose.addBox(-2F, 0F, -5F, 3, 3, 5);
      Nose.setRotationPoint(-0.5F, 13.5F, -8F);
      Nose.setTextureSize(64, 32);
      Nose.mirror = true;
      setRotation(Nose, 0F, 0F, 0F);
      Spine = new ModelRenderer(this, 44, 15);
      Spine.addBox(-1.5F, 0F, -5F, 2, 3, 4);
      Spine.setRotationPoint(-0.5F, 13F, -7F);
      Spine.setTextureSize(64, 32);
      Spine.mirror = true;
      setRotation(Spine, 0F, 0F, 0F);
  }
  
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

      if (this.isChild)
      {
          float f6 = 2.0F;
          GL11.glPushMatrix();
          GL11.glTranslatef(0.0F, 5.0F * par7, 2.0F * par7);
          this.WolfHead.renderWithRotation(par7);
          this.Ear1.render(par7);
          this.Ear2.render(par7);
          this.Spine.render(par7);
          this.Nose.render(par7);
          GL11.glPopMatrix();
          GL11.glPushMatrix();
          GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
          GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
          this.Body.render(par7);
          this.Leg1.render(par7);
          this.Leg2.render(par7);
          this.Leg3.render(par7);
          this.Leg4.render(par7);
          this.Tail.renderWithRotation(par7);
          this.Mane.render(par7);
          GL11.glPopMatrix();
      }
      else
      {
          this.WolfHead.renderWithRotation(par7);
          this.Nose.render(par7);
          this.Body.render(par7);
          this.Leg1.render(par7);
          this.Leg2.render(par7);
          this.Leg3.render(par7);
          this.Leg4.render(par7);
          this.Tail.renderWithRotation(par7);
          this.Mane.render(par7);
          this.Ear1.render(par7);
          this.Ear2.render(par7);
          this.Spine.render(par7);
      }
  }

  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
      EntityDarkZertum entityzertum = (EntityDarkZertum)par1EntityLivingBase;

      if (entityzertum.isAngry())
      {
          this.Tail.rotateAngleY = 0.0F;
      }
      else
      {
          this.Tail.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
      }

      if (entityzertum.isSitting())
      {
          this.Mane.setRotationPoint(-1.0F, 16.0F, -3.0F);
          this.Mane.rotateAngleX = ((float)Math.PI * 2F / 5F);
          this.Mane.rotateAngleY = 0.0F;
          this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
          this.Body.rotateAngleX = ((float)Math.PI / 4F);
          this.Tail.setRotationPoint(-1.0F, 21.0F, 6.0F);
          this.Leg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
          this.Leg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
          this.Leg2.setRotationPoint(0.5F, 22.0F, 2.0F);
          this.Leg2.rotateAngleX = ((float)Math.PI * 3F / 2F);
          this.Leg3.rotateAngleX = 5.811947F;
          this.Leg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
          this.Leg4.rotateAngleX = 5.811947F;
          this.Leg4.setRotationPoint(0.51F, 17.0F, -4.0F);
      }
      else
      {
          this.Body.setRotationPoint(0.0F, 14.0F, 2.0F);
          this.Body.rotateAngleX = ((float)Math.PI / 2F);
          this.Mane.setRotationPoint(-1.0F, 14.0F, -3.0F);
          this.Mane.rotateAngleX = this.Body.rotateAngleX;
          this.Tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
          this.Leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
          this.Leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
          this.Leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
          this.Leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
          this.Leg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
          this.Leg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.Leg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
          this.Leg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
      }

      this.WolfHead.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
      this.Ear1.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
      this.Ear2.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
      this.Spine.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
      this.Nose.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
      this.Mane.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.08F);
      this.Body.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
      this.Tail.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.2F);
  }

  /**
   * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
   * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
   * "far" arms and legs can swing at most.
   */
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      this.WolfHead.rotateAngleX = par5 / (180F / (float)Math.PI);
      this.WolfHead.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.Nose.rotateAngleX = par5 / (180F / (float)Math.PI);
      this.Nose.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.Ear1.rotateAngleX = par5 / (180F / (float)Math.PI);
      this.Ear1.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.Ear2.rotateAngleX = par5 / (180F / (float)Math.PI);
      this.Ear2.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.Spine.rotateAngleX = par5 / (210F / (float)Math.PI);
      this.Spine.rotateAngleY = par4 / (210F / (float)Math.PI);
      this.Tail.rotateAngleX = par3;
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}

package common.zeroquest.client.model;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityRowarn;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelRowarn extends ModelBase {
	ModelRenderer TopJaw;
	ModelRenderer BottomJaw;
	ModelRenderer Neck1;
	ModelRenderer Neck2;
	ModelRenderer Torso1;
	ModelRenderer Torso2;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Tail3;
	ModelRenderer Tail4;
	ModelRenderer Fin1;
	ModelRenderer Fin2;
	ModelRenderer LeftHindLeg1;
	ModelRenderer LeftHindLeg2;
	ModelRenderer LeftHindLeg3;
	ModelRenderer LeftHindClaw1;
	ModelRenderer LeftHindClaw2;
	ModelRenderer RightHindLeg1;
	ModelRenderer RightHindLeg2;
	ModelRenderer RightHindLeg3;
	ModelRenderer RightHindClaw1;
	ModelRenderer RightHindClaw2;
	ModelRenderer LeftFrontLeg;
	ModelRenderer LeftFrontClaw1;
	ModelRenderer LeftFrontClaw2;
	ModelRenderer RightFrontLeg;
	ModelRenderer RightFrontClaw1;
	ModelRenderer RightFrontClaw2;
	private final float rightFemur = -0.2974289F;
	private final float leftFemur = -0.2974289F;

	public ModelRowarn() {
		textureWidth = 256;
		textureHeight = 128;

		TopJaw = new ModelRenderer(this, 0, 54);
		TopJaw.addBox(-2F, -2F, -8.5F, 5, 3, 8);
		TopJaw.setRotationPoint(-0.5F, -1.5F, -5.2F);
		setRotation(TopJaw, 0.8948578F, 0F, 0F);
		BottomJaw = new ModelRenderer(this, 0, 68);
		BottomJaw.addBox(-2F, -1F, -9.5F, 5, 2, 9);
		BottomJaw.setRotationPoint(0F, 2F, 0F);
		setRotation(BottomJaw, 0F, 0F, 0F);
		Neck1 = new ModelRenderer(this, 21, 0);
		Neck1.addBox(-2F, -2F, -7F, 4, 4, 6);
		Neck1.setRotationPoint(-0.5F, 13F, -7F);
		setRotation(Neck1, -0.3346075F, 0F, 0F);
		Neck2 = new ModelRenderer(this, 0, 0);
		Neck2.addBox(-2F, -2F, -7F, 4, 4, 6);
		Neck2.setRotationPoint(0F, 1F, -5F);
		setRotation(Neck2, -0.5948578F, 0F, 0F);
		Torso1 = new ModelRenderer(this, 69, 0);
		Torso1.addBox(-2.5F, -2.5F, 0F, 5, 5, 9);
		Torso1.setRotationPoint(-0.5F, 12.5F, -2F);
		setRotation(Torso1, -0.1115358F, 0F, 0F);
		Torso2 = new ModelRenderer(this, 42, 0);
		Torso2.addBox(-2.5F, -2.5F, 0F, 5, 5, 8);
		Torso2.setRotationPoint(0F, 0.8F, -7F);
		setRotation(Torso2, 0.1115358F, 0F, 0F);
		Tail1 = new ModelRenderer(this, 99, 0);
		Tail1.addBox(-2F, -2F, 0F, 4, 4, 10);
		Tail1.setRotationPoint(-0.5F, 13.5F, 6F);
		setRotation(Tail1, -0.0415358F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 129, 0);
		Tail2.addBox(-1.5F, -1.5F, 0F, 3, 3, 10);
		Tail2.setRotationPoint(0F, -0.2F, 8F);
		setRotation(Tail2, -0.0787144F, 0F, 0F);
		Tail3 = new ModelRenderer(this, 157, 0);
		Tail3.addBox(-1F, -1F, 0F, 2, 2, 10);
		Tail3.setRotationPoint(0F, -0.3F, 6F);
		setRotation(Tail3, -0.0787144F, 0F, 0F);
		Tail4 = new ModelRenderer(this, 182, 0);
		Tail4.addBox(-0.5F, -0.5F, 0F, 1, 1, 9);
		Tail4.setRotationPoint(0F, 0F, 7.4F);
		setRotation(Tail4, -0.02617695F, 0F, 0F);
		Fin1 = new ModelRenderer(this, 182, 12);
		Fin1.addBox(-4.5F, 0F, 0F, 5, 0, 7);
		Fin1.setRotationPoint(-0.5F, 0F, 7F);
		setRotation(Fin1, 0F, 0F, 0F);
		Fin2 = new ModelRenderer(this, 182, 21);
		Fin2.addBox(-0.5F, 0F, 0F, 5, 0, 7);
		Fin2.setRotationPoint(0.5F, 0F, 7F);
		setRotation(Fin2, 0F, 0F, 0F);
		LeftHindLeg1 = new ModelRenderer(this, 11, 17);
		LeftHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
		LeftHindLeg1.setRotationPoint(1.5F, 13F, 5F);
		LeftHindLeg1.setTextureSize(256, 128);
		LeftHindLeg1.mirror = true;
		setRotation(LeftHindLeg1, -0.2974289F, 0F, 0F);
		LeftHindLeg2 = new ModelRenderer(this, 11, 27);
		LeftHindLeg2.addBox(-1F, 0F, -1F, 2, 4, 2);
		LeftHindLeg2.setRotationPoint(0F, 4.3F, -0.3F);
		LeftHindLeg2.setTextureSize(256, 128);
		LeftHindLeg2.mirror = true;
		setRotation(LeftHindLeg2, 0.8205006F, 0F, 0F);
		LeftHindLeg3 = new ModelRenderer(this, 11, 35);
		LeftHindLeg3.addBox(-1F, 0F, -1F, 2, 4, 2);
		LeftHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
		LeftHindLeg3.setTextureSize(256, 128);
		LeftHindLeg3.mirror = true;
		setRotation(LeftHindLeg3, -0.5205006F, 0F, 0F);
		LeftHindClaw1 = new ModelRenderer(this, 11, 43);
		LeftHindClaw1.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		LeftHindClaw1.setRotationPoint(-0.5F, 3F, 0F);
		LeftHindClaw1.setTextureSize(256, 128);
		LeftHindClaw1.mirror = true;
		setRotation(LeftHindClaw1, 0F, 0.1858939F, 0F);
		LeftHindClaw2 = new ModelRenderer(this, 11, 43);
		LeftHindClaw2.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		LeftHindClaw2.setRotationPoint(0.5F, 3F, 0F);
		LeftHindClaw2.setTextureSize(256, 128);
		LeftHindClaw2.mirror = true;
		setRotation(LeftHindClaw2, 0F, -0.1858931F, 0F);
		RightHindLeg1 = new ModelRenderer(this, 23, 17);
		RightHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
		RightHindLeg1.setRotationPoint(-2.5F, 13F, 5F);
		RightHindLeg1.setTextureSize(256, 128);
		RightHindLeg1.mirror = true;
		setRotation(RightHindLeg1, -0.2974289F, 0F, 0F);
		RightHindLeg2 = new ModelRenderer(this, 23, 27);
		RightHindLeg2.addBox(-1F, 0F, -1F, 2, 4, 2);
		RightHindLeg2.setRotationPoint(0F, 4.3F, -0.3F);
		RightHindLeg2.setTextureSize(256, 128);
		RightHindLeg2.mirror = true;
		setRotation(RightHindLeg2, 0.8205006F, 0F, 0F);
		RightHindLeg3 = new ModelRenderer(this, 23, 35);
		RightHindLeg3.addBox(-1F, 0F, -1F, 2, 4, 2);
		RightHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
		RightHindLeg3.setTextureSize(256, 128);
		RightHindLeg3.mirror = true;
		setRotation(RightHindLeg3, -0.5205006F, 0F, 0F);
		RightHindClaw1 = new ModelRenderer(this, 23, 43);
		RightHindClaw1.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		RightHindClaw1.setRotationPoint(-0.5F, 3F, 0F);
		RightHindClaw1.setTextureSize(256, 128);
		RightHindClaw1.mirror = true;
		setRotation(RightHindClaw1, 0F, 0.1858939F, 0F);
		RightHindClaw2 = new ModelRenderer(this, 23, 43);
		RightHindClaw2.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		RightHindClaw2.setRotationPoint(0.5F, 3F, 0F);
		RightHindClaw2.setTextureSize(256, 128);
		RightHindClaw2.mirror = true;
		setRotation(RightHindClaw2, 0F, -0.1858931F, 0F);
		LeftFrontLeg = new ModelRenderer(this, 0, 17);
		LeftFrontLeg.addBox(-1F, 0F, -1F, 2, 11, 2);
		LeftFrontLeg.setRotationPoint(2F, 13F, -6F);
		LeftFrontLeg.setTextureSize(256, 128);
		LeftFrontLeg.mirror = true;
		setRotation(LeftFrontLeg, 0F, 0F, 0F);
		LeftFrontClaw1 = new ModelRenderer(this, 11, 43);
		LeftFrontClaw1.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		LeftFrontClaw1.setRotationPoint(-0.5F, 10F, 0F);
		LeftFrontClaw1.setTextureSize(256, 128);
		LeftFrontClaw1.mirror = true;
		setRotation(LeftFrontClaw1, 0F, 0.1858939F, 0F);
		LeftFrontClaw2 = new ModelRenderer(this, 11, 43);
		LeftFrontClaw2.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		LeftFrontClaw2.setRotationPoint(0.5F, 10F, 0F);
		LeftFrontClaw2.setTextureSize(256, 128);
		LeftFrontClaw2.mirror = true;
		setRotation(LeftFrontClaw2, 0F, -0.185895F, 0F);
		RightFrontLeg = new ModelRenderer(this, 35, 17);
		RightFrontLeg.addBox(-1F, 0F, -1F, 2, 11, 2);
		RightFrontLeg.setRotationPoint(-3F, 13F, -6F);
		RightFrontLeg.setTextureSize(256, 128);
		RightFrontLeg.mirror = true;
		setRotation(RightFrontLeg, 0F, 0F, 0F);
		RightFrontClaw1 = new ModelRenderer(this, 23, 43);
		RightFrontClaw1.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		RightFrontClaw1.setRotationPoint(-0.5F, 10F, 0F);
		RightFrontClaw1.setTextureSize(256, 128);
		RightFrontClaw1.mirror = true;
		setRotation(RightFrontClaw1, 0F, 0.1858939F, 0F);
		RightFrontClaw2 = new ModelRenderer(this, 23, 43);
		RightFrontClaw2.addBox(-0.5F, 0F, -3F, 1, 1, 3);
		RightFrontClaw2.setRotationPoint(0.5F, 10F, 0F);
		RightFrontClaw2.setTextureSize(256, 128);
		RightFrontClaw2.mirror = true;
		setRotation(RightFrontClaw2, 0F, -0.185895F, 0F);

		TopJaw.addChild(BottomJaw);
		Neck2.addChild(TopJaw);
		Neck1.addChild(Neck2);
		Torso1.addChild(Torso2);

		LeftHindLeg1.addChild(LeftHindLeg2);
		RightHindLeg1.addChild(RightHindLeg2);
		LeftHindLeg2.addChild(LeftHindLeg3);
		RightHindLeg2.addChild(RightHindLeg3);
		LeftHindLeg3.addChild(LeftHindClaw1);
		LeftHindLeg3.addChild(LeftHindClaw2);
		RightHindLeg3.addChild(RightHindClaw1);
		RightHindLeg3.addChild(RightHindClaw2);

		LeftFrontLeg.addChild(LeftFrontClaw1);
		LeftFrontLeg.addChild(LeftFrontClaw2);
		RightFrontLeg.addChild(RightFrontClaw1);
		RightFrontLeg.addChild(RightFrontClaw2);

		Tail1.addChild(Tail2);
		Tail2.addChild(Tail3);
		Tail3.addChild(Tail4);
		Tail4.addChild(Fin1);
		Tail4.addChild(Fin2);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		GL11.glTranslatef(0.0F, -0.75F, 0.0F);
		Neck1.render(par7);
		Torso1.render(par7);
		Tail1.render(par7);
		LeftHindLeg1.render(par7);
		RightHindLeg1.render(par7);
		LeftFrontLeg.render(par7);
		RightFrontLeg.render(par7);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	/**
	 * Used for easily adding entity-dependent animations. The second and third
	 * float params here are the same second and third as in the
	 * setRotationAngles method.
	 */
	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
		EntityRowarn entityzertum = (EntityRowarn) par1EntityLivingBase;

		this.RightHindLeg1.rotateAngleX = rightFemur + MathHelper.cos(par2 * 0.4662F) * 1.4F * par3;
		this.LeftHindLeg1.rotateAngleX = leftFemur + MathHelper.cos(par2 * 0.4662F + (float) Math.PI) * 1.4F * par3;
		this.RightFrontLeg.rotateAngleX = MathHelper.cos(par2 * 0.4662F + (float) Math.PI) * 1.4F * par3;
		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(par2 * 0.4662F) * 1.4F * par3;
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are
	 * used for animating the movement of arms and legs, where par1 represents
	 * the time(so that arms and legs swing back and forth) and par2 represents
	 * how "far" arms and legs can swing at most.
	 */
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}
}

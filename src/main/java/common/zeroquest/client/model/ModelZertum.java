package common.zeroquest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class ModelZertum extends ModelBase {
	// fields
	ModelRenderer Nose;
	ModelRenderer Ear2;
	ModelRenderer Ear1;
	ModelRenderer Head;
	ModelRenderer LeftHindLeg1;
	ModelRenderer LeftHindLeg2;
	ModelRenderer LeftHindLeg3;
	ModelRenderer RightHindLeg1;
	ModelRenderer RightHindLeg2;
	ModelRenderer RightHindLeg3;
	ModelRenderer Mane1;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Tail3;
	ModelRenderer Mane2;
	ModelRenderer Torso;
	ModelRenderer Neck;
	ModelRenderer Pad1;
	ModelRenderer Pad2;
	ModelRenderer Pad3;
	ModelRenderer Pad4;
	ModelRenderer PadPart1;
	ModelRenderer PadPart2;
	ModelRenderer Rope1;
	ModelRenderer Metal1;
	ModelRenderer Rope2;
	ModelRenderer Metal2;
	ModelRenderer Seat1;
	ModelRenderer Seat2;

	private final float rightFemur = -0.2974289F;
	private final float leftFemur = -0.2974289F;
	private final float rightTibia = 0.8205006F;
	private final float leftTibia = 0.8205006F;
	private final float rightMetatarus = -0.5205006F;
	private final float leftMetatarus = -0.5205006F;
	private final float mane2StartingRotation = -0.34653F;
	private final float vertebrae = -0.6015813F;

	// create an animation cycle
	// for movement based animations you need to measure distance moved
	// and perform number of cycles per block distance moved.
	protected double distanceMovedTotal = 0.0D;
	// don't make this too large or animations will be skipped

	protected static final double CYCLES_PER_BLOCK = 1.3D;
	protected int cycleIndex = 0;
	protected float[][] undulationCycle = new float[][] {
			{ -32, -27, -22, -18, -15, -12, -9, -6, -3, 0, 3, 6, 9, 12, 15, 18, 22, 27, 32, 27, 22,
				18, 15, 12, 9, 6, 3, 0, -3, -6, -9, -12, -15, -18, -22, -27, -32 },
				{ 0, 3, 6, 9, 12, 15, 18, 22, 27, 32, 27, 22, 18, 15, 12, 9, 6, 3, 0, -3, -6, -9, -12,
					-15, -18, -22, -27, -32 },
					{ -32, -27, -22, -18, -15, -12, -9, -6, -3, 0, 3, 6, 9, 12, 15, 18, 22, 27, 32, 27, 22,
						18, 15, 12, 9, 6, 3, 0, -3, -6, -9, -12, -15, -18, -22, -27, -32 },
						{ 0, 3, 6, 9, 12, 15, 18, 22, 27, 32, 27, 22, 18, 15, 12, 9, 6, 3, 0, -3, -6, -9, -12,
							-15, -18, -22, -27, -32 }, };

	public ModelZertum() {

		textureWidth = 128;
		textureHeight = 64;

		Nose = new ModelRenderer(this, 25, 27);
		Nose.addBox(-1.5F, 0F, -3F, 3, 3, 4);
		Nose.setRotationPoint(0F, 2.5F, -15F);
		setRotation(Nose, 0F, 0F, 0F);
		Ear2 = new ModelRenderer(this, 40, 28);
		Ear2.addBox(-1F, -3F, 0F, 2, 3, 1);
		Ear2.setRotationPoint(2F, -0.5F, -10.5F);
		setRotation(Ear2, 0F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 40, 28);
		Ear1.addBox(-1F, -3F, 0F, 2, 3, 1);
		Ear1.setRotationPoint(-2F, -0.5F, -10.5F);
		setRotation(Ear1, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 25, 15);
		Head.addBox(-3F, -0.5F, -14F, 6, 6, 5);
		Head.setRotationPoint(-0.5F, 6F, -3F);
		setRotation(Head, 0F, 0F, 0F);
		LeftHindLeg1 = new ModelRenderer(this, 11, 18);
		LeftHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
		LeftHindLeg1.setRotationPoint(1.5F, 13F, 5F);
		setRotation(LeftHindLeg1, -0.2974289F, 0F, 0F);
		LeftHindLeg2 = new ModelRenderer(this, 3, 44);
		LeftHindLeg2.addBox(-1F, 0F, -1F, 2, 4, 2);
		LeftHindLeg2.setRotationPoint(0F, 4.3F, -0.3F);
		setRotation(LeftHindLeg2, leftTibia, 0F, 0F);
		LeftHindLeg3 = new ModelRenderer(this, 0, 51);
		LeftHindLeg3.addBox(-1F, 0F, -1F, 2, 4, 2);
		LeftHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
		setRotation(LeftHindLeg3, leftMetatarus, 0F, 0F);
		RightHindLeg1 = new ModelRenderer(this, 11, 31);
		RightHindLeg1.addBox(-1F, 0F, -1F, 2, 5, 3);
		RightHindLeg1.setRotationPoint(-2.5F, 13F, 5F);
		setRotation(RightHindLeg1, -0.2974289F, 0F, 0F);
		RightHindLeg2 = new ModelRenderer(this, 3, 44);
		RightHindLeg2.addBox(-1F, 0F, -1F, 2, 4, 2);
		RightHindLeg2.setRotationPoint(0F, 4.3F, -0.3F);
		setRotation(RightHindLeg2, rightTibia, 0F, 0F);
		RightHindLeg3 = new ModelRenderer(this, 9, 51);
		RightHindLeg3.addBox(-1F, 0F, -1F, 2, 4, 2);
		RightHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
		setRotation(RightHindLeg3, rightMetatarus, 0F, 0F);
		Mane1 = new ModelRenderer(this, 43, 0);
		Mane1.addBox(-3F, -3F, -3F, 6, 6, 7);
		Mane1.setRotationPoint(-0.5F, 14F, -3F);
		setRotation(Mane1, 1.496439F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 0);
		Neck.addBox(-2.5F, -12F, -4F, 4, 7, 4);
		Neck.setRotationPoint(0.5F, 0F, 0F);
		setRotation(Neck, -0.6015813F, 0F, 0F);
		Mane2 = new ModelRenderer(this, 18, 0);
		Mane2.addBox(-2.5F, -7F, -3.6F, 5, 6, 7);
		Mane2.setRotationPoint(0F, 0F, 0F);
		setRotation(Mane2, -0.34653F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 0, 30);
		RightLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
		RightLeg.setRotationPoint(-3F, 14F, -4F);
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 0, 16);
		LeftLeg.addBox(-1F, 0F, -1F, 2, 10, 2);
		LeftLeg.setRotationPoint(2F, 14F, -4F);
		setRotation(LeftLeg, 0F, 0F, 0F);
		Tail1 = new ModelRenderer(this, 91, 0);
		Tail1.addBox(-1.5F, 0F, -1F, 4, 8, 4);
		Tail1.setRotationPoint(-1F, 14.3F, 6F);
		setRotation(Tail1, 1.315962F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 110, 0);
		Tail2.addBox(-1.5F, 0F, -1F, 3, 8, 3);
		Tail2.setRotationPoint(0.5F, 6F, 0.5F);
		setRotation(Tail2, -0.041605F, 0F, 0F);
		Tail3 = new ModelRenderer(this, 110, 13);
		Tail3.addBox(-1F, 0F, -1F, 2, 8, 2);
		Tail3.setRotationPoint(0F, 6.5F, 0.5F);
		setRotation(Tail3, 0.001605F, 0F, 0F);
		Torso = new ModelRenderer(this, 69, 0);
		Torso.addBox(-2.5F, -3F, -3F, 5, 9, 5);
		Torso.setRotationPoint(-0.5F, 12.5F, 1F);
		setRotation(Torso, 1.496439F, 0F, 0F);

		// Saddle\\
		Pad1 = new ModelRenderer(this, 110, 32);
		Pad1.addBox(-2F, 0F, 0F, 4, 1, 1);
		Pad1.setRotationPoint(-0.5F, 9.3F, -2.5F);
		setRotation(Pad1, -0.0650564F, 0F, 0F);
		Pad2 = new ModelRenderer(this, 110, 35);
		Pad2.addBox(-3F, 0F, 0F, 6, 1, 2);
		Pad2.setRotationPoint(-0.5F, 9.4F, -1.5F);
		setRotation(Pad2, -0.0743572F, 0F, 0F);
		Pad3 = new ModelRenderer(this, 110, 39);
		Pad3.addBox(-2F, 0F, 0F, 5, 1, 4);
		Pad3.setRotationPoint(-1F, 9.5F, 0.2F);
		setRotation(Pad3, -0.0650564F, 0F, 0F);
		Pad4 = new ModelRenderer(this, 110, 48);
		Pad4.addBox(-1.5F, 0F, 0F, 3, 1, 1);
		Pad4.setRotationPoint(-0.5F, 9.8F, 4.1F);
		setRotation(Pad4, -0.0650564F, 0F, 0F);
		PadPart1 = new ModelRenderer(this, 110, 29);
		PadPart1.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		PadPart1.setRotationPoint(1F, 9.23F, -3.5F);
		setRotation(PadPart1, -0.0650564F, 0F, 0F);
		PadPart2 = new ModelRenderer(this, 110, 29);
		PadPart2.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		PadPart2.setRotationPoint(-2F, 9.2F, -3.5F);
		setRotation(PadPart2, -0.0650564F, 0F, 0F);
		Rope1 = new ModelRenderer(this, 105, 32);
		Rope1.addBox(0F, 0F, -0.5F, 1, 4, 1);
		Rope1.setRotationPoint(1.8F, 9.6F, 0F);
		setRotation(Rope1, 0F, 0F, 0F);
		Metal1 = new ModelRenderer(this, 102, 39);
		Metal1.addBox(0F, 0F, -1F, 1, 1, 2);
		Metal1.setRotationPoint(1.8F, 13.6F, 0F);
		setRotation(Metal1, 0F, 0F, 0F);
		Rope2 = new ModelRenderer(this, 105, 32);
		Rope2.addBox(-1F, 0F, -0.5F, 1, 4, 1);
		Rope2.setRotationPoint(-2.8F, 9.6F, 0F);
		setRotation(Rope2, 0F, 0F, 0F);
		Metal2 = new ModelRenderer(this, 102, 39);
		Metal2.addBox(0F, 0F, -1F, 1, 1, 2);
		Metal2.setRotationPoint(-3.8F, 13.6F, 0F);
		setRotation(Metal2, 0F, 0F, 0F);
		Seat1 = new ModelRenderer(this, 100, 45);
		Seat1.addBox(-1F, 0F, 0F, 2, 1, 1);
		Seat1.setRotationPoint(-0.5F, 8.7F, -1.5F);
		setRotation(Seat1, -0.0650484F, 0F, 0F);
		Seat2 = new ModelRenderer(this, 100, 49);
		Seat2.addBox(-2F, 0F, 0F, 4, 1, 1);
		Seat2.setRotationPoint(-0.5F, 9F, 3.2F);
		setRotation(Seat2, -0.0650484F, 0F, 0F);
		Head.addChild(Nose);
		Head.addChild(Ear1);
		Head.addChild(Ear2);
		LeftHindLeg1.addChild(LeftHindLeg2);
		RightHindLeg1.addChild(RightHindLeg2);
		LeftHindLeg2.addChild(LeftHindLeg3);
		RightHindLeg2.addChild(RightHindLeg3);
		Mane1.addChild(Mane2);
		Mane1.addChild(Neck);
		Tail1.addChild(Tail2);
		Tail2.addChild(Tail3);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		EntityZertumEntity entityzertum = (EntityZertumEntity) par1Entity;
		boolean flag = entityzertum.isSaddled();
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		if (this.isChild) {
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
		else {
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

			if (flag) {
				GL11.glPushMatrix();
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glTranslatef(0.0F, -0.5F, 0.0F);
				Pad1.render(par7);
				Pad2.render(par7);
				Pad3.render(par7);
				Pad4.render(par7);
				PadPart1.render(par7);
				PadPart2.render(par7);
				Rope1.render(par7);
				Metal1.render(par7);
				Rope2.render(par7);
				Metal2.render(par7);
				Seat1.render(par7);
				Seat2.render(par7);
				GL11.glPopMatrix();
			}
		}
	}

	/**
	 * Used for easily adding entity-dependent animations. The second and third
	 * float params here are the same second and third as in the
	 * setRotationAngles method.
	 */
	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
		EntityZertumEntity entityzertum = (EntityZertumEntity) par1EntityLivingBase;

		if (entityzertum.isAngry()) {
			this.Ear1.rotateAngleX = -0.5948578F;
			this.Ear2.rotateAngleX = -0.5948578F;
		}
		else if (entityzertum.getHealth() <= Constants.lowHP) {
			this.Ear1.rotateAngleX = -0.9948578F;
			this.Ear2.rotateAngleX = -0.9948578F;
		}
		else {
			this.Ear1.rotateAngleX = 0.0F;
			this.Ear2.rotateAngleX = 0.0F;
		}
		if (!entityzertum.hasEvolved()) {
			if (entityzertum.isSitting())
			{
				LeftHindLeg1.setRotationPoint(1.5F, 18F, 5F);
				LeftHindLeg1.rotateAngleX = -2.082003F;
				LeftHindLeg2.setRotationPoint(0F, 4F, 1.2F);
				LeftHindLeg2.rotateAngleX = 2.6205006F;
				LeftHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
				LeftHindLeg3.rotateAngleX = -0.5205006F;
				RightHindLeg1.setRotationPoint(-2.5F, 18F, 5F);
				RightHindLeg1.rotateAngleX = -2.082003F;
				RightHindLeg2.setRotationPoint(0F, 4F, 1.2F);
				RightHindLeg2.rotateAngleX = 2.6205006F;
				RightHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
				RightHindLeg3.rotateAngleX = -0.5205006F;
				Torso.setRotationPoint(-0.5F, 14F, 1F);
				Torso.rotateAngleX = 0.9759358F;
				Tail1.setRotationPoint(-1F, 18.5F, 5F);
				Tail1.rotateAngleX = 1.167248F;
				Tail2.rotateAngleX = 0.315962F;
				Tail3.rotateAngleX = 0.23333F;

				// Saddle\\
				Pad1.setRotationPoint(-0.5F, 9.7F, -1.5F);
				setRotation(Pad1, -0.288128F, 0F, 0F);
				Pad2.setRotationPoint(-0.5F, 10F, -0.5F);
				setRotation(Pad2, -0.3346075F, 0F, 0F);
				Pad3.setRotationPoint(-1F, 10.7F, 1.4F);
				setRotation(Pad3, -0.5855569F, 0F, 0F);
				Pad4.setRotationPoint(-0.5F, 12.9F, 4.7F);
				setRotation(Pad4, -0.5948648F, 0F, 0F);
				PadPart1.setRotationPoint(1F, 9.6F, -2.4F);
				setRotation(PadPart1, -0.3624853F, 0F, 0F);
				PadPart2.setRotationPoint(-2F, 9.6F, -2.4F);
				setRotation(PadPart2, -0.3624874F, 0F, 0F);
				Rope1.setRotationPoint(1.8F, 10.6F, 1F);
				setRotation(Rope1, -0.3346075F, 0F, 0F);
				Metal1.setRotationPoint(1.8F, 14.2F, -0.3F);
				setRotation(Metal1, -0.3346145F, 0F, 0F);
				Rope2.setRotationPoint(-2.8F, 10.6F, 1F);
				setRotation(Rope2, -0.3346145F, 0F, 0F);
				Metal2.setRotationPoint(-3.8F, 13.6F, 0F);
				setRotation(Metal2, -0.3346145F, 0F, 0F);
				Seat1.setRotationPoint(-0.5F, 11.6F, 4.3F);
				setRotation(Seat1, -0.5483867F, 0F, 0F);
				Seat2.setRotationPoint(-0.5F, 8.9F, -1.2F);
				setRotation(Seat2, -0.2881364F, 0F, 0F);

			}
			else {
				LeftHindLeg1.setRotationPoint(1.5F, 13F, 5F);
				LeftHindLeg1.rotateAngleX = -0.2974289F;
				LeftHindLeg2.setRotationPoint(0F, 4.3F, -0.3F);
				LeftHindLeg2.rotateAngleX = leftTibia;
				LeftHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
				LeftHindLeg3.rotateAngleX = leftMetatarus;
				RightHindLeg1.setRotationPoint(-2.5F, 13F, 5F);
				RightHindLeg1.rotateAngleX = -0.2974289F;
				RightHindLeg2.setRotationPoint(0F, 4.3F, -0.3F);
				RightHindLeg2.rotateAngleX = rightTibia;
				RightHindLeg3.setRotationPoint(0F, 3.55F, 0.2F);
				RightHindLeg3.rotateAngleX = rightMetatarus;
				Torso.setRotationPoint(-0.5F, 12.5F, 1F);
				Torso.rotateAngleX = 1.496439F;
				Tail1.setRotationPoint(-1F, 14.3F, 6F);
				Tail1.rotateAngleX = 1.315962F;
				Tail2.setRotationPoint(0.5F, 6F, 0.5F);
				Tail2.rotateAngleX = -0.041605F;
				Tail3.setRotationPoint(0F, 6.5F, 0.5F);
				Tail3.rotateAngleX = 0.001605F;
				// Saddle\\
				Pad1.setRotationPoint(-0.5F, 9.3F, -2.5F);
				setRotation(Pad1, -0.0650564F, 0F, 0F);
				Pad2.setRotationPoint(-0.5F, 9.4F, -1.5F);
				setRotation(Pad2, -0.0743572F, 0F, 0F);
				Pad3.setRotationPoint(-1F, 9.5F, 0.2F);
				setRotation(Pad3, -0.0650564F, 0F, 0F);
				Pad4.setRotationPoint(-0.5F, 9.8F, 4.1F);
				setRotation(Pad4, -0.0650564F, 0F, 0F);
				PadPart1.setRotationPoint(1F, 9.23F, -3.5F);
				setRotation(PadPart1, -0.0650564F, 0F, 0F);
				PadPart2.setRotationPoint(-2F, 9.2F, -3.5F);
				setRotation(PadPart2, -0.0650564F, 0F, 0F);
				Rope1.setRotationPoint(1.8F, 9.6F, 0F);
				setRotation(Rope1, 0F, 0F, 0F);
				Metal1.setRotationPoint(1.8F, 13.6F, 0F);
				setRotation(Metal1, 0F, 0F, 0F);
				Rope2.setRotationPoint(-2.8F, 9.6F, 0F);
				setRotation(Rope2, 0F, 0F, 0F);
				Metal2.setRotationPoint(-3.8F, 13.6F, 0F);
				setRotation(Metal2, 0F, 0F, 0F);
				Seat1.setRotationPoint(-0.5F, 8.7F, -1.5F);
				setRotation(Seat1, -0.0650484F, 0F, 0F);
				Seat2.setRotationPoint(-0.5F, 9F, 3.2F);
				setRotation(Seat2, -0.0650484F, 0F, 0F);

				this.RightHindLeg1.rotateAngleX = rightFemur + MathHelper.cos(par2 * 0.4662F) * 1.4F * par3;
				this.LeftHindLeg1.rotateAngleX = leftFemur + MathHelper.cos(par2 * 0.4662F + (float) Math.PI) * 1.4F * par3;
				this.RightLeg.rotateAngleX = MathHelper.cos(par2 * 0.4662F + (float) Math.PI) * 1.4F * par3;
				this.LeftLeg.rotateAngleX = MathHelper.cos(par2 * 0.4662F) * 1.4F * par3;
			}
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.Head.rotateAngleX = par5 / (280F / (float) Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.Neck.rotateAngleX = vertebrae + par5 / (280F / (float) Math.PI);
		this.Mane2.rotateAngleX = mane2StartingRotation + par5 / (280F / (float) Math.PI);
		this.Mane1.rotateAngleY = par4 / (180F / (float) Math.PI);
	}

	protected void updateDistanceMovedTotal(Entity parEntity) {
		distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, parEntity.prevPosZ);
	}

	protected double getDistanceMovedTotal(Entity parEntity) {
		return (distanceMovedTotal);
	}

	protected float degToRad(float degrees) {
		return degrees * (float) Math.PI / 180;
	}
}
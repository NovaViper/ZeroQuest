package common.zeroquest.client.renderer.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.zertum.EntityZertumEntity;

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

	// Evolved Model\\
	ModelRenderer Throat;
	ModelRenderer UpSection;
	ModelRenderer MidSection;
	ModelRenderer Pelvis;
	ModelRenderer TailPart1;
	ModelRenderer TailPart2;
	ModelRenderer TailPart3;
	ModelRenderer LeftHumerus;
	ModelRenderer LeftRadius;
	ModelRenderer LeftFClawRPart1;
	ModelRenderer LeftFClawRPart2;
	ModelRenderer LeftFClawMPart1;
	ModelRenderer LeftFClawMPart2;
	ModelRenderer LeftFClawLPart1;
	ModelRenderer LeftFClawLPart2;
	ModelRenderer RightHumerus;
	ModelRenderer RightRadius;
	ModelRenderer RightFClawRPart1;
	ModelRenderer RightFClawRPart2;
	ModelRenderer RightFClawMPart1;
	ModelRenderer RightFClawMPart2;
	ModelRenderer RightFClawLPart1;
	ModelRenderer RightFClawLPart2;
	ModelRenderer LeftFemur;
	ModelRenderer LeftTibia;
	ModelRenderer LeftMetatarsus;
	ModelRenderer LeftClawRPart1;
	ModelRenderer LeftClawRPart2;
	ModelRenderer LeftClawLPart1;
	ModelRenderer LeftClawLPart2;
	ModelRenderer LeftClawMPart1;
	ModelRenderer LeftClawMPart2;
	ModelRenderer RightFemur;
	ModelRenderer RightTibia;
	ModelRenderer RightMetatarsus;
	ModelRenderer RightClawRPart1;
	ModelRenderer RightClawRPart2;
	ModelRenderer RightClawMPart1;
	ModelRenderer RightClawMPart2;
	ModelRenderer RightClawLPart1;
	ModelRenderer RightClawLPart2;
	ModelRenderer RightEarPart1;
	ModelRenderer LeftBottomJaw;
	ModelRenderer RightEarPart2;
	ModelRenderer BottomJawPart1;
	ModelRenderer RightEarPart3;
	ModelRenderer BottomJawPart2;
	ModelRenderer LeftEarPart1;
	ModelRenderer BottomJawCenter;
	ModelRenderer LeftEarPart2;
	ModelRenderer LeftTopTeeth;
	ModelRenderer LeftEarPart3;
	ModelRenderer LeftTopJaw;
	ModelRenderer LeftBottomTeeth;
	ModelRenderer RightTopTeeth;
	ModelRenderer RightBottomTeeth;
	ModelRenderer TopJawPart1;
	ModelRenderer TopJawPart2;
	ModelRenderer TopJawCenter;
	ModelRenderer RightTopJaw;
	ModelRenderer RightBottomJaw;
	ModelRenderer EPad1;
	ModelRenderer EPad2;
	ModelRenderer EPad3;
	ModelRenderer EPad4;
	ModelRenderer EPadPart1;
	ModelRenderer EPadPart2;
	ModelRenderer ERope1;
	ModelRenderer EMetal1;
	ModelRenderer ERope2;
	ModelRenderer EMetal2;
	ModelRenderer ESeat1;
	ModelRenderer ESeat2;

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

		textureWidth = 256;
		textureHeight = 128;

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

		// Ears\\
		RightEarPart1 = new ModelRenderer(this, 18, 60);
		RightEarPart1.addBox(-1F, -1F, -1F, 1, 1, 4);
		RightEarPart1.setRotationPoint(-1.5F, 1.5F, -5F);
		RightEarPart1.setTextureSize(256, 128);
		setRotation(RightEarPart1, 0.0346075F, -0.3346075F, 0F);
		RightEarPart2 = new ModelRenderer(this, 15, 66);
		RightEarPart2.addBox(-1F, -1F, 0F, 2, 2, 6);
		RightEarPart2.setRotationPoint(-0.5F, -0.5F, 0F);
		RightEarPart2.setTextureSize(256, 128);
		setRotation(RightEarPart2, 0.3346075F, -0.0346075F, 0F);
		RightEarPart3 = new ModelRenderer(this, 16, 75);
		RightEarPart3.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
		RightEarPart3.setRotationPoint(-0.5F, 0F, 4F);
		RightEarPart3.setTextureSize(256, 128);
		setRotation(RightEarPart3, 0.0346075F, 0.4115358F, 0.0743572F);
		LeftEarPart1 = new ModelRenderer(this, 18, 60);
		LeftEarPart1.addBox(-1F, -1F, -1F, 1, 1, 4);
		LeftEarPart1.setRotationPoint(3.5F, 1.5F, -5F);
		LeftEarPart1.setTextureSize(256, 128);
		setRotation(LeftEarPart1, 0.3346075F, 0.3346145F, 0F);
		LeftEarPart2 = new ModelRenderer(this, 15, 66);
		LeftEarPart2.addBox(-1F, -1F, 0F, 2, 2, 6);
		LeftEarPart2.setRotationPoint(-0.5F, -0.5F, 0F);
		LeftEarPart2.setTextureSize(256, 128);
		setRotation(LeftEarPart2, 0.0346075F, 0.0346145F, 0F);
		LeftEarPart3 = new ModelRenderer(this, 16, 75);
		LeftEarPart3.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
		LeftEarPart3.setRotationPoint(0.5F, 0F, 4F);
		LeftEarPart3.setTextureSize(256, 128);
		setRotation(LeftEarPart3, 0.0346145F, -0.411544F, -0.074351F);

		Throat = new ModelRenderer(this, 0, 0);
		Throat.addBox(-2F, -3F, -9F, 6, 5, 6);
		Throat.setRotationPoint(-1F, 7.7F, -11F);
		Throat.setTextureOffset(256, 128);
		setRotation(Throat, -0.2288904F, 0F, 0F);
		UpSection = new ModelRenderer(this, 25, 0);
		UpSection.addBox(-2F, -2.5F, -9F, 7, 7, 8);
		UpSection.setRotationPoint(-1.5F, 7F, -6F);
		UpSection.setTextureSize(256, 128);
		setRotation(UpSection, -0.1917118F, 0F, 0F);
		MidSection = new ModelRenderer(this, 56, 0);
		MidSection.addBox(-2F, -2.5F, 0F, 6, 6, 9);
		MidSection.setRotationPoint(-1F, 7.5F, -8F);
		MidSection.setTextureSize(256, 128);
		setRotation(MidSection, -0.0429974F, 0F, 0F);
		Pelvis = new ModelRenderer(this, 87, 0);
		Pelvis.addBox(-2F, -2.5F, 0F, 6, 6, 7);
		Pelvis.setRotationPoint(-1F, 7.8F, 0F);
		Pelvis.setTextureSize(256, 128);
		setRotation(Pelvis, 0F, 0F, 0F);
		TailPart1 = new ModelRenderer(this, 114, 0);
		TailPart1.addBox(-1F, -1F, 0F, 2, 2, 11);
		TailPart1.setRotationPoint(0F, 7F, 6F);
		TailPart1.setTextureOffset(256, 128);
		TailPart2 = new ModelRenderer(this, 141, 0);
		TailPart2.addBox(-1F, -1F, 0F, 2, 2, 11);
		TailPart2.setRotationPoint(0F, 0F, 10F);
		TailPart2.setTextureSize(256, 128);
		setRotation(TailPart2, 0, 0F, 0F);
		TailPart3 = new ModelRenderer(this, 168, 0);
		TailPart3.addBox(-1F, -1F, 0F, 2, 2, 11);
		TailPart3.setRotationPoint(0F, 0F, 10.8F);
		TailPart3.setTextureSize(256, 128);

		LeftHumerus = new ModelRenderer(this, 39, 16);
		LeftHumerus.addBox(0F, 0F, 0F, 3, 10, 4);
		LeftHumerus.setRotationPoint(1.5F, 4.7F, -12F);
		LeftHumerus.setTextureSize(256, 128);
		setRotation(LeftHumerus, 0.3869765F, 0F, 0F);
		LeftRadius = new ModelRenderer(this, 71, 16);
		LeftRadius.addBox(0F, 0F, 0F, 3, 11, 4);
		LeftRadius.setRotationPoint(0F, 8.0F, 0.45F);
		LeftRadius.setTextureSize(256, 128);
		setRotation(LeftRadius, -0.3869765F, 0F, 0F);
		LeftFClawRPart1 = new ModelRenderer(this, 0, 58);
		LeftFClawRPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		LeftFClawRPart1.setRotationPoint(0F, 9.7F, 0F);
		LeftFClawRPart1.setTextureSize(256, 128);
		setRotation(LeftFClawRPart1, 0F, 0.5159687F, 0F);
		LeftFClawRPart2 = new ModelRenderer(this, 5, 58);
		LeftFClawRPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		LeftFClawRPart2.setRotationPoint(0F, 0F, 0F);
		LeftFClawRPart2.setTextureSize(256, 128);
		setRotation(LeftFClawRPart2, 1.16093F, 0.0259717F, 0F);
		LeftFClawMPart1 = new ModelRenderer(this, 0, 58);
		LeftFClawMPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		LeftFClawMPart1.setRotationPoint(1.5F, 9.7F, 0F);
		LeftFClawMPart1.setTextureSize(256, 128);
		setRotation(LeftFClawMPart1, 0F, 0F, 0F);
		LeftFClawMPart2 = new ModelRenderer(this, 5, 58);
		LeftFClawMPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		LeftFClawMPart2.setRotationPoint(0F, 0F, 0F);
		LeftFClawMPart2.setTextureSize(256, 128);
		setRotation(LeftFClawMPart2, 1.16093F, 0F, 0F);
		LeftFClawLPart1 = new ModelRenderer(this, 0, 58);
		LeftFClawLPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		LeftFClawLPart1.setRotationPoint(3F, 9.7F, 0F);
		LeftFClawLPart1.setTextureSize(256, 128);
		setRotation(LeftFClawLPart1, 0F, -0.5159717F, 0F);
		LeftFClawLPart2 = new ModelRenderer(this, 5, 58);
		LeftFClawLPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		LeftFClawLPart2.setRotationPoint(0F, 0F, 0F);
		LeftFClawLPart2.setTextureSize(256, 128);
		setRotation(LeftFClawLPart2, 1.16093F, -0.0059717F, 0F);

		RightHumerus = new ModelRenderer(this, 55, 16);
		RightHumerus.addBox(-3F, 0F, 0F, 3, 10, 4);
		RightHumerus.setRotationPoint(-1.5F, 4.7F, -12F);
		RightHumerus.setTextureSize(256, 128);
		setRotation(RightHumerus, 0.3869765F, 0F, 0F);
		RightRadius = new ModelRenderer(this, 71, 16);
		RightRadius.addBox(-3F, 0F, 0F, 3, 11, 4);
		RightRadius.setRotationPoint(0F, 8.0F, 0.45F);
		RightRadius.setTextureSize(256, 128);
		setRotation(RightRadius, -0.3869765F, 0F, 0F);
		RightFClawRPart1 = new ModelRenderer(this, 0, 58);
		RightFClawRPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		RightFClawRPart1.setRotationPoint(-3F, 9.7F, 0F);
		RightFClawRPart1.setTextureSize(256, 128);
		setRotation(RightFClawRPart1, 0F, 0.5159687F, 0F);
		RightFClawRPart2 = new ModelRenderer(this, 5, 58);
		RightFClawRPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		RightFClawRPart2.setRotationPoint(0F, 0F, 0F);
		RightFClawRPart2.setTextureSize(256, 128);
		setRotation(RightFClawRPart2, 1.16093F, 0.0259717F, 0F);
		RightFClawMPart1 = new ModelRenderer(this, 0, 58);
		RightFClawMPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		RightFClawMPart1.setRotationPoint(-1.5F, 9.7F, 0F);
		RightFClawMPart1.setTextureSize(256, 128);
		setRotation(RightFClawMPart1, 0F, 0F, 0F);
		RightFClawMPart2 = new ModelRenderer(this, 5, 58);
		RightFClawMPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		RightFClawMPart2.setRotationPoint(0F, 0F, 0F);
		RightFClawMPart2.setTextureSize(256, 128);
		setRotation(RightFClawMPart2, 1.16093F, 0F, 0F);
		RightFClawLPart1 = new ModelRenderer(this, 0, 58);
		RightFClawLPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		RightFClawLPart1.setRotationPoint(0F, 9.7F, 0F);
		RightFClawLPart1.setTextureSize(256, 128);
		setRotation(RightFClawLPart1, 0F, -0.5159717F, 0F);
		RightFClawLPart2 = new ModelRenderer(this, 5, 58);
		RightFClawLPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		RightFClawLPart2.setRotationPoint(0F, 0F, 0F);
		RightFClawLPart2.setTextureSize(256, 128);
		setRotation(RightFClawLPart2, 1.16093F, -0.0059717F, 0F);

		LeftFemur = new ModelRenderer(this, 19, 16);
		LeftFemur.addBox(0F, 0F, 0F, 3, 8, 6);
		LeftFemur.setRotationPoint(1.5F, 5.7F, 0F);
		LeftFemur.setTextureSize(256, 128);
		setRotation(LeftFemur, 0F, 0F, 0F);
		LeftTibia = new ModelRenderer(this, 0, 31);
		LeftTibia.addBox(0F, 0F, 0F, 3, 3, 9);
		LeftTibia.setRotationPoint(0F, 7.7F, 0F);
		LeftTibia.setTextureSize(256, 128);
		setRotation(LeftTibia, 0F, 0F, 0F);
		LeftMetatarsus = new ModelRenderer(this, 0, 44);
		LeftMetatarsus.addBox(0F, 0F, 0F, 3, 10, 3);
		LeftMetatarsus.setRotationPoint(0F, -0.02F, 8F);
		LeftMetatarsus.setTextureSize(256, 128);
		setRotation(LeftMetatarsus, -0.1289922F, 0F, 0F);
		LeftClawRPart1 = new ModelRenderer(this, 0, 58);
		LeftClawRPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		LeftClawRPart1.setRotationPoint(0F, 9F, 0F);
		LeftClawRPart1.setTextureSize(256, 128);
		setRotation(LeftClawRPart1, 0F, 0.5159687F, 0F);
		LeftClawRPart2 = new ModelRenderer(this, 5, 58);
		LeftClawRPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		LeftClawRPart2.setRotationPoint(0F, 0F, 0F);
		LeftClawRPart2.setTextureSize(256, 128);
		setRotation(LeftClawRPart2, 1.16093F, -0.0259717F, 0F);
		LeftClawMPart1 = new ModelRenderer(this, 0, 58);
		LeftClawMPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		LeftClawMPart1.setRotationPoint(1.5F, 9F, 0F);
		LeftClawMPart1.setTextureSize(256, 128);
		setRotation(LeftClawMPart1, 0F, 0F, 0F);
		LeftClawMPart2 = new ModelRenderer(this, 5, 58);
		LeftClawMPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		LeftClawMPart2.setRotationPoint(0F, 0F, 0F);
		LeftClawMPart2.setTextureSize(256, 128);
		setRotation(LeftClawMPart2, 1.16093F, 0F, 0F);
		LeftClawLPart1 = new ModelRenderer(this, 0, 58);
		LeftClawLPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		LeftClawLPart1.setRotationPoint(3F, 9F, 0F);
		LeftClawLPart1.setTextureSize(256, 128);
		setRotation(LeftClawLPart1, 0F, -0.5159717F, 0F);
		LeftClawLPart2 = new ModelRenderer(this, 5, 58);
		LeftClawLPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		LeftClawLPart2.setRotationPoint(0F, 0F, 0F);
		LeftClawLPart2.setTextureSize(256, 128);
		setRotation(LeftClawLPart2, 1.16093F, 0.0059717F, 0F);

		RightFemur = new ModelRenderer(this, 0, 16);
		RightFemur.addBox(-2F, 0F, 0F, 3, 8, 6);
		RightFemur.setRotationPoint(-2.5F, 5.7F, 0F);
		RightFemur.setTextureSize(256, 128);
		setRotation(RightFemur, 0F, 0F, 0F);
		RightTibia = new ModelRenderer(this, 0, 31);
		RightTibia.addBox(-2F, 0F, 0F, 3, 3, 9);
		RightTibia.setRotationPoint(0F, 7.7F, 0F);
		RightTibia.setTextureSize(256, 128);
		setRotation(RightTibia, 0F, 0F, 0F);
		RightMetatarsus = new ModelRenderer(this, 0, 44);
		RightMetatarsus.addBox(-2F, 0F, 0F, 3, 10, 3);
		RightMetatarsus.setRotationPoint(0F, -0.02F, 8F);
		RightMetatarsus.setTextureSize(256, 128);
		setRotation(RightMetatarsus, -0.1289922F, 0F, 0F);
		RightClawRPart1 = new ModelRenderer(this, 0, 58);
		RightClawRPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		RightClawRPart1.setRotationPoint(-2F, 9F, 0F);
		RightClawRPart1.setTextureSize(256, 128);
		setRotation(RightClawRPart1, 0F, 0.5159687F, 0F);
		RightClawRPart2 = new ModelRenderer(this, 5, 58);
		RightClawRPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		RightClawRPart2.setRotationPoint(0F, 0F, 0F);
		RightClawRPart2.setTextureSize(256, 128);
		setRotation(RightClawRPart2, 1.16093F, -0.0259717F, 0F);
		RightClawMPart1 = new ModelRenderer(this, 0, 58);
		RightClawMPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		RightClawMPart1.setRotationPoint(-0.5F, 9F, 0F);
		RightClawMPart1.setTextureSize(256, 128);
		setRotation(RightClawMPart1, 0F, 0F, 0F);
		RightClawMPart2 = new ModelRenderer(this, 5, 58);
		RightClawMPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		RightClawMPart2.setRotationPoint(0F, 0F, 0F);
		RightClawMPart2.setTextureSize(256, 128);
		setRotation(RightClawMPart2, 1.16093F, 0F, 0F);
		RightClawLPart1 = new ModelRenderer(this, 0, 58);
		RightClawLPart1.addBox(-0.5F, 0F, -1F, 1, 1, 1);
		RightClawLPart1.setRotationPoint(1F, 9F, 0F);
		RightClawLPart1.setTextureSize(256, 128);
		setRotation(RightClawLPart1, 0F, -0.5159717F, 0F);
		RightClawLPart2 = new ModelRenderer(this, 5, 58);
		RightClawLPart2.addBox(-0.5F, -1F, -3.4F, 1, 1, 3);
		RightClawLPart2.setRotationPoint(0F, 0F, 0F);
		RightClawLPart2.setTextureSize(256, 128);
		setRotation(RightClawLPart2, 1.16093F, 0.0059717F, 0F);

		// Top Jaw\\
		TopJawCenter = new ModelRenderer(this, 56, 38);
		TopJawCenter.addBox(-0.5F, 0F, -7.4F, 2, 1, 8);
		TopJawCenter.setRotationPoint(0.5F, -2.5F, -9F);
		TopJawCenter.setTextureSize(256, 128);
		setRotation(TopJawCenter, 0.2288904F, 0F, 0F);
		LeftTopJaw = new ModelRenderer(this, 39, 50);
		LeftTopJaw.addBox(0F, 0F, -9F, 1, 2, 9);
		LeftTopJaw.setRotationPoint(3F, 0F, 0F);
		LeftTopJaw.setTextureSize(256, 128);
		setRotation(LeftTopJaw, 0F, 0.3346075F, 0F);
		LeftTopTeeth = new ModelRenderer(this, 61, 50);
		LeftTopTeeth.addBox(0.5F, 0F, -9.3F, 0, 1, 5);
		LeftTopTeeth.setRotationPoint(0F, 2F, 0F);
		LeftTopTeeth.setTextureSize(256, 128);
		setRotation(LeftTopTeeth, 0F, -0.0346145F, 0F);
		RightTopJaw = new ModelRenderer(this, 39, 63);
		RightTopJaw.addBox(-1F, 0F, -9F, 1, 2, 9);
		RightTopJaw.setRotationPoint(-2F, 0F, 0F);
		RightTopJaw.setTextureSize(256, 128);
		setRotation(RightTopJaw, 0F, -0.3346075F, 0F);
		RightTopTeeth = new ModelRenderer(this, 61, 50);
		RightTopTeeth.addBox(-0.5F, 0F, -9.3F, 0, 1, 5);
		RightTopTeeth.setRotationPoint(0F, 2F, 0F);
		RightTopTeeth.setTextureSize(256, 128);
		setRotation(RightTopTeeth, 0F, 0.0346075F, 0F);
		TopJawPart1 = new ModelRenderer(this, 38, 40);
		TopJawPart1.addBox(-1F, 0F, -5.7F, 2, 1, 6);
		TopJawPart1.setRotationPoint(-1F, 0F, 0F);
		TopJawPart1.setTextureSize(256, 128);
		setRotation(TopJawPart1, 0F, -0.3346075F, 0F);
		TopJawPart2 = new ModelRenderer(this, 38, 40);
		TopJawPart2.addBox(-1F, 0F, -5.7F, 2, 1, 6);
		TopJawPart2.setRotationPoint(2F, 0F, 0F);
		TopJawPart2.setTextureSize(256, 128);
		setRotation(TopJawPart2, 0F, 0.3346145F, 0F);

		// BottomJaw\\
		BottomJawCenter = new ModelRenderer(this, 0, 109);
		BottomJawCenter.addBox(-0.5F, 0F, -7.2F, 2, 1, 8);
		BottomJawCenter.setRotationPoint(0.5F, 1.5F, -8F);
		BottomJawCenter.setTextureSize(256, 128);
		setRotation(BottomJawCenter, 0.2288904F, 0F, 0F);
		LeftBottomJaw = new ModelRenderer(this, 0, 84);
		LeftBottomJaw.addBox(0F, 0F, -9F, 1, 2, 9);
		LeftBottomJaw.setRotationPoint(3F, -1.1F, 0F);
		LeftBottomJaw.setTextureSize(256, 128);
		setRotation(LeftBottomJaw, 0F, 0.3346075F, 0F);
		LeftBottomTeeth = new ModelRenderer(this, 21, 93);
		LeftBottomTeeth.addBox(0.5F, 0F, -9.3F, 0, 1, 5);
		LeftBottomTeeth.setRotationPoint(0.5F, -1.2F, 0F);
		LeftBottomTeeth.setTextureSize(256, 128);
		setRotation(LeftBottomTeeth, 0, 0.0346145F, 0F);
		RightBottomJaw = new ModelRenderer(this, 0, 97);
		RightBottomJaw.addBox(-1F, 0F, -9F, 1, 2, 9);
		RightBottomJaw.setRotationPoint(-2F, -1.1F, 0F);
		RightBottomJaw.setTextureSize(256, 128);
		setRotation(RightBottomJaw, 0, -0.3346075F, 0F);
		RightBottomTeeth = new ModelRenderer(this, 21, 93);
		RightBottomTeeth.addBox(-0.5F, 0F, -9.3F, 0, 1, 5);
		RightBottomTeeth.setRotationPoint(-0.5F, -1.2F, 0F);
		RightBottomTeeth.setTextureSize(256, 128);
		setRotation(RightBottomTeeth, 0, -0.0346075F, 0F);
		BottomJawPart1 = new ModelRenderer(this, 21, 84);
		BottomJawPart1.addBox(-1F, 0F, -6.5F, 2, 1, 7);
		BottomJawPart1.setRotationPoint(-1F, 0F, 0F);
		BottomJawPart1.setTextureSize(256, 128);
		setRotation(BottomJawPart1, 0, -0.3346075F, 0F);
		BottomJawPart2 = new ModelRenderer(this, 21, 84);
		BottomJawPart2.addBox(-1F, 0F, -6.5F, 2, 1, 7);
		BottomJawPart2.setRotationPoint(2F, 0F, 0F);
		BottomJawPart2.setTextureSize(256, 128);
		setRotation(BottomJawPart2, 0, 0.3346075F, 0F);
		EPad1 = new ModelRenderer(this, 110, 32);
		EPad1.addBox(-2F, 0F, 0F, 4, 1, 1);
		EPad1.setRotationPoint(0F, 4.5F, -5.5F);
		EPad1.setTextureSize(256, 128);
		setRotation(EPad1, 0F, 0F, 0F);
		EPad2 = new ModelRenderer(this, 110, 35);
		EPad2.addBox(-3F, 0F, 0F, 6, 1, 2);
		EPad2.setRotationPoint(0F, 4.5F, -4.5F);
		EPad2.setTextureSize(256, 128);
		setRotation(EPad2, 0F, 0F, 0F);
		EPad3 = new ModelRenderer(this, 110, 39);
		EPad3.addBox(-2F, 0F, 0F, 5, 1, 4);
		EPad3.setRotationPoint(-0.5F, 4.5F, -2.8F);
		EPad3.setTextureSize(256, 128);
		setRotation(EPad3, 0F, 0F, 0F);
		EPad4 = new ModelRenderer(this, 110, 48);
		EPad4.addBox(-1.5F, 0F, 0F, 3, 1, 1);
		EPad4.setRotationPoint(0F, 4.5F, 1.1F);
		EPad4.setTextureSize(256, 128);
		setRotation(EPad4, 0F, 0F, 0F);
		EPadPart1 = new ModelRenderer(this, 110, 29);
		EPadPart1.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		EPadPart1.setRotationPoint(1.5F, 4.5F, -6.5F);
		EPadPart1.setTextureSize(256, 128);
		setRotation(EPadPart1, 0F, 0F, 0F);
		EPadPart2 = new ModelRenderer(this, 110, 29);
		EPadPart2.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		EPadPart2.setRotationPoint(-1.5F, 4.5F, -6.5F);
		EPadPart2.setTextureSize(256, 128);
		EPadPart2.mirror = true;
		setRotation(EPadPart2, 0F, 0F, 0F);
		ERope1 = new ModelRenderer(this, 105, 32);
		ERope1.addBox(0F, 0F, -0.5F, 1, 6, 1);
		ERope1.setRotationPoint(2.8F, 4.6F, -3F);
		ERope1.setTextureSize(256, 128);
		setRotation(ERope1, 0F, 0F, 0F);
		EMetal1 = new ModelRenderer(this, 102, 41);
		EMetal1.addBox(0F, 0F, -1F, 1, 1, 2);
		EMetal1.setRotationPoint(2.8F, 10.6F, -3F);
		EMetal1.setTextureSize(256, 128);
		setRotation(EMetal1, 0F, 0F, 0F);
		ERope2 = new ModelRenderer(this, 105, 32);
		ERope2.addBox(-1F, 0F, -0.5F, 1, 6, 1);
		ERope2.setRotationPoint(-2.8F, 4.6F, -3F);
		ERope2.setTextureSize(256, 128);
		setRotation(ERope2, 0F, 0F, 0F);
		EMetal2 = new ModelRenderer(this, 102, 41);
		EMetal2.addBox(-1F, 0F, -1F, 1, 1, 2);
		EMetal2.setRotationPoint(-2.8F, 10.6F, -3F);
		EMetal2.setTextureSize(256, 128);
		setRotation(EMetal2, 0F, 0F, 0F);
		ESeat1 = new ModelRenderer(this, 100, 45);
		ESeat1.addBox(-1F, 0F, 0F, 2, 1, 1);
		ESeat1.setRotationPoint(0F, 4F, -4.5F);
		ESeat1.setTextureSize(256, 128);
		setRotation(ESeat1, 0F, 0F, 0F);
		ESeat2 = new ModelRenderer(this, 100, 49);
		ESeat2.addBox(-2F, 0F, 0F, 4, 1, 1);
		ESeat2.setRotationPoint(0F, 4F, 0.2F);
		ESeat2.setTextureSize(256, 128);
		setRotation(ESeat2, 0F, 0F, 0F);

		// Head\\
		this.Throat.addChild(TopJawCenter);
		this.Throat.addChild(BottomJawCenter);
		this.TopJawCenter.addChild(LeftEarPart1);
		this.TopJawCenter.addChild(RightEarPart1);
		this.TopJawCenter.addChild(TopJawPart1);
		this.TopJawCenter.addChild(TopJawPart2);
		this.TopJawCenter.addChild(LeftTopJaw);
		this.TopJawCenter.addChild(RightTopJaw);
		this.LeftTopJaw.addChild(LeftTopTeeth);
		this.RightTopJaw.addChild(RightTopTeeth);
		this.LeftEarPart1.addChild(LeftEarPart2);
		this.LeftEarPart2.addChild(LeftEarPart3);
		this.BottomJawCenter.addChild(BottomJawPart1);
		this.BottomJawCenter.addChild(BottomJawPart2);
		this.BottomJawCenter.addChild(LeftBottomJaw);
		this.BottomJawCenter.addChild(RightBottomJaw);
		this.LeftBottomJaw.addChild(LeftBottomTeeth);
		this.RightBottomJaw.addChild(RightBottomTeeth);
		this.RightEarPart1.addChild(RightEarPart2);
		this.RightEarPart2.addChild(RightEarPart3);

		// Front Legs\\
		this.LeftHumerus.addChild(LeftRadius);
		this.LeftRadius.addChild(LeftFClawLPart1);
		this.LeftRadius.addChild(LeftFClawMPart1);
		this.LeftRadius.addChild(LeftFClawRPart1);
		this.LeftFClawLPart1.addChild(LeftFClawLPart2);
		this.LeftFClawMPart1.addChild(LeftFClawMPart2);
		this.LeftFClawRPart1.addChild(LeftFClawRPart2);
		this.RightHumerus.addChild(RightRadius);
		this.RightRadius.addChild(RightFClawLPart1);
		this.RightRadius.addChild(RightFClawMPart1);
		this.RightRadius.addChild(RightFClawRPart1);
		this.RightFClawLPart1.addChild(RightFClawLPart2);
		this.RightFClawMPart1.addChild(RightFClawMPart2);
		this.RightFClawRPart1.addChild(RightFClawRPart2);

		// Back Legs\\\
		this.LeftFemur.addChild(LeftTibia);
		this.LeftTibia.addChild(LeftMetatarsus);
		this.LeftMetatarsus.addChild(LeftClawLPart1);
		this.LeftMetatarsus.addChild(LeftClawMPart1);
		this.LeftMetatarsus.addChild(LeftClawRPart1);
		this.LeftClawLPart1.addChild(LeftClawLPart2);
		this.LeftClawMPart1.addChild(LeftClawMPart2);
		this.LeftClawRPart1.addChild(LeftClawRPart2);
		this.RightFemur.addChild(RightTibia);
		this.RightTibia.addChild(RightMetatarsus);
		this.RightMetatarsus.addChild(RightClawLPart1);
		this.RightMetatarsus.addChild(RightClawMPart1);
		this.RightMetatarsus.addChild(RightClawRPart1);
		this.RightClawLPart1.addChild(RightClawLPart2);
		this.RightClawMPart1.addChild(RightClawMPart2);
		this.RightClawRPart1.addChild(RightClawRPart2);

		// Tail\\
		this.TailPart1.addChild(TailPart2);
		this.TailPart2.addChild(TailPart3);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		EntityZertumEntity entityzertum = (EntityZertumEntity) par1Entity;
		boolean flag = entityzertum.isSaddled();
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		if (!entityzertum.hasEvolved()) {
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
		else if (entityzertum.hasEvolved()) {
			if (this.isChild) {
				float f6 = 2.0F;
				GL11.glPushMatrix();
				GL11.glTranslatef(0.0F, 5.0F * par7, 6.0F * par7);
				Throat.render(par7);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
				GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
				UpSection.render(par7);
				MidSection.render(par7);
				Pelvis.render(par7);
				TailPart1.render(par7);
				LeftHumerus.render(par7);
				RightHumerus.render(par7);
				LeftFemur.render(par7);
				RightFemur.render(par7);
				GL11.glPopMatrix();
			}
			else {
				Throat.render(par7);
				UpSection.render(par7);
				MidSection.render(par7);
				Pelvis.render(par7);
				TailPart1.render(par7);
				LeftHumerus.render(par7);
				RightHumerus.render(par7);
				LeftFemur.render(par7);
				RightFemur.render(par7);
			}

			if (flag) {
				EPad1.render(par7);
				EPad2.render(par7);
				EPad3.render(par7);
				EPad4.render(par7);
				EPadPart1.render(par7);
				EPadPart2.render(par7);
				ERope1.render(par7);
				EMetal1.render(par7);
				ERope2.render(par7);
				EMetal2.render(par7);
				ESeat1.render(par7);
				ESeat2.render(par7);
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
		float f11 = entityzertum.func_110201_q(par4);

		if (entityzertum.isAngry()) {
			this.Ear1.rotateAngleX = -0.5948578F;
			this.Ear2.rotateAngleX = -0.5948578F;
		}
		else if (entityzertum.getHealth() <= 10) {
			this.Ear1.rotateAngleX = -0.9948578F;
			this.Ear2.rotateAngleX = -0.9948578F;
		}
		else {
			this.Ear1.rotateAngleX = 0.0F;
			this.Ear2.rotateAngleX = 0.0F;
		}
		if (!entityzertum.hasEvolved()) {
			if (entityzertum.isSitting()) // TODO
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
				// Saddle\\ TODO
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
		else if (entityzertum.hasEvolved()) {
			if (entityzertum.isSitting()) // TODO
			{
				MidSection.setRotationPoint(-1F, 7.5F, -8F);
				setRotation(MidSection, -0.3032477F, 0F, 0F);
				Pelvis.setRotationPoint(-1F, 9.8F, -1F);
				setRotation(Pelvis, -0.2974289F, 0F, 0F);
				TailPart1.setRotationPoint(0F, 11F, 6F);
				setRotation(TailPart1, 0.1346075F, 0F, 0F);
				TailPart2.setRotationPoint(0F, 0F, 10F);
				setRotation(TailPart2, 0.1346075F, 0F, 0F);
				TailPart3.setRotationPoint(0F, 0F, 10.8F);
				setRotation(TailPart3, 0.1346075F, 0F, 0F);

				LeftFemur.setRotationPoint(1.5F, 6.7F, 0F);
				LeftFemur.rotateAngleX = -0.3892433F;
				LeftTibia.setRotationPoint(0F, 7.5F, -0.2F);
				setRotation(LeftTibia, 0.3892433F, 0F, 0F);
				LeftMetatarsus.setRotationPoint(0F, 0F, 7.5F);
				setRotation(LeftMetatarsus, -0.1289922F, 0F, 0F);
				LeftClawRPart1.setRotationPoint(0F, 8.5F, 0F);
				setRotation(LeftClawRPart1, 0F, 0.5159687F, 0F);
				LeftClawRPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(LeftClawRPart2, 1.16093F, -0.0259717F, 0F);
				LeftClawMPart1.setRotationPoint(1.5F, 8.5F, 0F);
				setRotation(LeftClawMPart1, 0F, 0F, 0F);
				LeftClawMPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(LeftClawMPart2, 1.16093F, 0F, 0F);
				LeftClawLPart1.setRotationPoint(3F, 8.5F, 0F);
				setRotation(LeftClawLPart1, 0F, -0.5159717F, 0F);
				LeftClawLPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(LeftClawLPart2, 1.16093F, 0.0059717F, 0F);

				RightFemur.setRotationPoint(-2.5F, 6.7F, 0F);
				RightFemur.rotateAngleX = -0.3892433F;
				RightTibia.setRotationPoint(0F, 7.5F, -0.2F);
				setRotation(RightTibia, 0.3892433F, 0F, 0F);
				RightMetatarsus.setRotationPoint(0F, 0F, 7.5F);
				setRotation(RightMetatarsus, -0.1289922F, 0F, 0F);
				RightClawRPart1.setRotationPoint(-2F, 8.5F, 0F);
				setRotation(RightClawRPart1, 0F, 0.5159687F, 0F);
				RightClawRPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(RightClawRPart2, 1.16093F, -0.0259717F, 0F);
				RightClawMPart1.setRotationPoint(-0.5F, 8.5F, 0F);
				setRotation(RightClawMPart1, 0F, 0F, 0F);
				RightClawMPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(RightClawMPart2, 1.16093F, 0F, 0F);
				RightClawLPart1.setRotationPoint(1F, 8.5F, 0F);
				setRotation(RightClawLPart1, 0F, -0.5159717F, 0F);
				RightClawLPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(RightClawLPart2, 1.16093F, 0.0059717F, 0F);
				EPad1.setRotationPoint(0F, 4.7F, -5.5F);
				setRotation(EPad1, -0.2230717F, 0F, 0F);
				EPad2.setRotationPoint(0F, 5F, -4.5F);
				setRotation(EPad2, -0.2230717F, 0F, 0F);
				EPad3.setRotationPoint(-0.5F, 5.4F, -2.8F);
				setRotation(EPad3, -0.3346075F, 0F, 0F);
				EPad4.setRotationPoint(0F, 6.7F, 0.8F);
				setRotation(EPad4, -0.3346075F, 0F, 0F);
				EPadPart1.setRotationPoint(1.5F, 4.5F, -6.5F);
				setRotation(EPadPart1, -0.2230717F, 0F, 0F);
				EPadPart2.setRotationPoint(-1.5F, 4.5F, -6.5F);
				setRotation(EPadPart2, -0.2230717F, 0F, 0F);
				ERope1.setRotationPoint(2.8F, 5.6F, -3F);
				setRotation(ERope1, 0F, 0F, 0F);
				EMetal1.setRotationPoint(2.8F, 11.6F, -3F);
				setRotation(EMetal1, 0F, 0F, 0F);
				ERope2.setRotationPoint(-2.8F, 5.6F, -3F);
				setRotation(ERope2, 0F, 0F, 0F);
				EMetal2.setRotationPoint(-2.8F, 11.6F, -3F);
				setRotation(EMetal2, 0F, 0F, 0F);
				ESeat1.setRotationPoint(0F, 4.4F, -4.5F);
				setRotation(ESeat1, -0.2230717F, 0F, 0F);
				ESeat2.setRotationPoint(0F, 5.9F, 0.2F);
				setRotation(ESeat2, -0.3346075F, 0F, 0F);
			}
			else {
				MidSection.setRotationPoint(-1F, 7.5F, -8F);
				setRotation(MidSection, -0.0429974F, 0F, 0F);
				Pelvis.setRotationPoint(-1F, 7.8F, 0F);
				setRotation(Pelvis, 0F, 0F, 0F);
				TailPart1.setRotationPoint(0F, 7F, 6F);
				setRotation(TailPart1, -0.1346075F, 0F, 0F);
				TailPart2.setRotationPoint(0F, 0F, 10F);
				setRotation(TailPart2, -0.1346075F, 0F, 0F);
				TailPart3.setRotationPoint(0F, 0F, 10.8F);
				setRotation(TailPart3, -0.1346075F, 0F, 0F);
				LeftFemur.setRotationPoint(1.5F, 5.7F, 0F);
				setRotation(LeftFemur, 0F, 0F, 0F);
				LeftTibia.setRotationPoint(0F, 7.7F, 0F);
				setRotation(LeftTibia, 0F, 0F, 0F);
				LeftMetatarsus.setRotationPoint(0F, -0.02F, 8F);
				setRotation(LeftMetatarsus, -0.1289922F, 0F, 0F);
				LeftClawRPart1.setRotationPoint(0F, 9F, 0F);
				setRotation(LeftClawRPart1, 0F, 0.5159687F, 0F);
				LeftClawRPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(LeftClawRPart2, 1.16093F, -0.0259717F, 0F);
				LeftClawMPart1.setRotationPoint(1.5F, 9F, 0F);
				setRotation(LeftClawMPart1, 0F, 0F, 0F);
				LeftClawMPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(LeftClawMPart2, 1.16093F, 0F, 0F);
				LeftClawLPart1.setRotationPoint(3F, 9F, 0F);
				setRotation(LeftClawLPart1, 0F, -0.5159717F, 0F);
				LeftClawLPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(LeftClawLPart2, 1.16093F, 0.0059717F, 0F);
				RightFemur.setRotationPoint(-2.5F, 5.7F, 0F);
				RightFemur.rotateAngleX = 0F;
				RightTibia.setRotationPoint(0F, 7.7F, 0F);
				setRotation(RightTibia, 0F, 0F, 0F);
				RightMetatarsus.setRotationPoint(0F, -0.02F, 8F);
				setRotation(RightMetatarsus, -0.1289922F, 0F, 0F);
				RightClawRPart1.setRotationPoint(-2F, 9F, 0F);
				setRotation(RightClawRPart1, 0F, 0.5159687F, 0F);
				RightClawRPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(RightClawRPart2, 1.16093F, -0.0259717F, 0F);
				RightClawMPart1.setRotationPoint(-0.5F, 9F, 0F);
				setRotation(RightClawMPart1, 0F, 0F, 0F);
				RightClawMPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(RightClawMPart2, 1.16093F, 0F, 0F);
				RightClawLPart1.setRotationPoint(1F, 9F, 0F);
				setRotation(RightClawLPart1, 0F, -0.5159717F, 0F);
				RightClawLPart2.setRotationPoint(0F, 0F, 0F);
				setRotation(RightClawLPart2, 1.16093F, 0.0059717F, 0F);
				// Saddle\\
				EPad1.setRotationPoint(0F, 4.5F, -5.5F);
				setRotation(EPad1, 0F, 0F, 0F);
				EPad2.setRotationPoint(0F, 4.5F, -4.5F);
				setRotation(EPad2, 0F, 0F, 0F);
				EPad3.setRotationPoint(-0.5F, 4.5F, -2.8F);
				setRotation(EPad3, 0F, 0F, 0F);
				EPad4.setRotationPoint(0F, 4.5F, 1.1F);
				setRotation(EPad4, 0F, 0F, 0F);
				EPadPart1.setRotationPoint(1.5F, 4.5F, -6.5F);
				setRotation(EPadPart1, 0F, 0F, 0F);
				EPadPart2.setRotationPoint(-1.5F, 4.5F, -6.5F);
				setRotation(EPadPart2, 0F, 0F, 0F);
				ERope1.setRotationPoint(2.8F, 4.6F, -3F);
				setRotation(ERope1, 0F, 0F, 0F);
				EMetal1.setRotationPoint(2.8F, 10.6F, -3F);
				setRotation(EMetal1, 0F, 0F, 0F);
				ERope2.setRotationPoint(-2.8F, 4.6F, -3F);
				setRotation(ERope2, 0F, 0F, 0F);
				EMetal2.setRotationPoint(-2.8F, 10.6F, -3F);
				setRotation(EMetal2, 0F, 0F, 0F);
				ESeat1.setRotationPoint(0F, 4F, -4.5F);
				setRotation(ESeat1, 0F, 0F, 0F);
				ESeat2.setRotationPoint(0F, 4F, 0.2F);
				setRotation(ESeat2, 0F, 0F, 0F);

				this.RightHumerus.rotateAngleX = 0.3869765F + MathHelper.cos(par2 * 0.4962F) * 0.5F * par3;
				this.LeftHumerus.rotateAngleX = 0.3869765F + MathHelper.cos(par2 * 0.3962F + (float) Math.PI) * 0.5F * par3;
				this.RightFemur.rotateAngleX = MathHelper.cos(par2 * 0.4062F) * 0.5F * par3;
				this.LeftFemur.rotateAngleX = MathHelper.cos(par2 * 0.3362F + (float) Math.PI) * 0.5F * par3;
			}
		}

		this.Head.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
		this.Mane1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.08F);
		this.Torso.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Tail1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.32F);
		// Saddle\\
		this.Pad1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Pad2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Pad3.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Pad4.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.PadPart1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.PadPart2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Seat1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Seat2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Rope1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Rope2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Metal1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Metal2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);

		// Evolution Form\\
		this.TopJawCenter.rotateAngleX = 0.2288904F - 0.45707964F * f11;
		this.BottomJawCenter.rotateAngleX = 0.2288904F + 0.45707964F * f11;
		this.Throat.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
		this.UpSection.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.08F);
		this.MidSection.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.Pelvis.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.32F);
		this.TailPart1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.48F);
		// Saddle\\
		this.EPad1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EPad2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EPad3.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EPad4.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EPadPart1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EPadPart2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.ESeat1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.ESeat2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.ERope1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.ERope2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EMetal1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
		this.EMetal2.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);

		if ((entityzertum.isSitting() || (entityzertum.motionX == 0.0F && entityzertum.motionZ == 0.0F)) && entityzertum.getHealth() > 10) { // TODO
			float wagAngleY = entityzertum.getWagAngle(par4, 0.0F);
			float wagAngleY2 = entityzertum.getWagAngle(par4, 0.0F);
			float wagAngleY3 = entityzertum.getWagAngle(par4, 0.0F);
			if (wagAngleY == 0.0F && wagAngleY2 == 0.0F && wagAngleY3 == 0.0F) {
				wagAngleY = MathHelper.cos(par2 * 0.0062F) * 1.4F * par3;
				wagAngleY2 = MathHelper.cos(par2 * 0.0002F) * 1.4F * par3;
				wagAngleY3 = MathHelper.cos(par2 * 0.0002F) * 1.4F * par3;

			}
			this.TailPart1.rotateAngleY = wagAngleY;
			this.TailPart2.rotateAngleY = wagAngleY2;
			this.TailPart3.rotateAngleY = wagAngleY3;
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

		// Evolved Form\\
		this.Throat.rotateAngleX = -0.2288904F + par5 / (280F / (float) Math.PI);
		this.Throat.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.UpSection.rotateAngleX = -0.1917118F + par5 / (360F / (float) Math.PI);
		this.UpSection.rotateAngleY = par4 / (360F / (float) Math.PI);
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
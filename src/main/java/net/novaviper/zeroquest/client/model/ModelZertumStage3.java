package net.novaviper.zeroquest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.lib.Constants;

import org.lwjgl.opengl.GL11;

public class ModelZertumStage3 extends ModelBase {
	// fields
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Tail3;
	ModelRenderer Tail4;
	ModelRenderer LS1;
	ModelRenderer LS2;
	ModelRenderer LS3;
	ModelRenderer LS4;
	ModelRenderer LS5;
	ModelRenderer LS6;
	ModelRenderer LS7;
	ModelRenderer LS8;
	ModelRenderer LS9;
	ModelRenderer LS10;
	ModelRenderer LS11;
	ModelRenderer LS12;
	ModelRenderer LS13;
	ModelRenderer LS14;
	ModelRenderer LS15;
	ModelRenderer LS16;
	ModelRenderer LS17;
	ModelRenderer LS18;
	ModelRenderer LS19;
	ModelRenderer RS1;
	ModelRenderer RS2;
	ModelRenderer RS3;
	ModelRenderer RS4;
	ModelRenderer RS5;
	ModelRenderer RS6;
	ModelRenderer RS7;
	ModelRenderer RS8;
	ModelRenderer RS9;
	ModelRenderer RS10;
	ModelRenderer RS11;
	ModelRenderer RS12;
	ModelRenderer RS13;
	ModelRenderer RS14;
	ModelRenderer RS15;
	ModelRenderer RS16;
	ModelRenderer RS17;
	ModelRenderer RS18;
	ModelRenderer RS19;
	ModelRenderer Neck2;
	ModelRenderer Neck1;
	ModelRenderer Belly;
	ModelRenderer Mid;
	ModelRenderer Hips;
	ModelRenderer Ear1;
	ModelRenderer Ear2;
	ModelRenderer LH1;
	ModelRenderer LH2;
	ModelRenderer RH1;
	ModelRenderer RH2;
	ModelRenderer TJ1;
	ModelRenderer TJ2;
	ModelRenderer TJ3;
	ModelRenderer BJ1;
	ModelRenderer BJ2;
	ModelRenderer BJ3;
	ModelRenderer JH1;
	ModelRenderer JH2;
	ModelRenderer LHL1;
	ModelRenderer LHL2;
	ModelRenderer LHL3;
	ModelRenderer LHL4;
	ModelRenderer LHLC1;
	ModelRenderer LHLC2;
	ModelRenderer LHLC3;
	ModelRenderer RHL1;
	ModelRenderer RHL2;
	ModelRenderer RHL3;
	ModelRenderer RHL4;
	ModelRenderer RHLC1;
	ModelRenderer RHLC2;
	ModelRenderer RHLC3;
	ModelRenderer LFL1;
	ModelRenderer LFL2;
	ModelRenderer LFL3;
	ModelRenderer LFLC1;
	ModelRenderer LFLC2;
	ModelRenderer LFLC3;
	ModelRenderer RFL1;
	ModelRenderer RFL2;
	ModelRenderer RFL3;
	ModelRenderer RFLC1;
	ModelRenderer RFLC2;
	ModelRenderer RFLC3;

	public ModelZertumStage3() {
		textureWidth = 256;
		textureHeight = 128;

		Tail1 = new ModelRenderer(this, 127, 30);
		Tail1.addBox(-4F, -3F, 0F, 8, 5, 12);
		Tail1.setRotationPoint(-1.5F, 6F, 12F);
		setRotation(Tail1, 0F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 127, 49);
		Tail2.addBox(-4F, -3F, 0F, 7, 4, 12);
		Tail2.setRotationPoint(0.5F, 0.5F, 9F);
		setRotation(Tail2, 0F, 0F, 0F);
		Tail3 = new ModelRenderer(this, 127, 68);
		Tail3.addBox(-4.5F, -3F, 0F, 6, 3, 12);
		Tail3.setRotationPoint(1F, 0.5F, 9F);
		setRotation(Tail3, 0F, 0F, 0F);
		Tail4 = new ModelRenderer(this, 127, 86);
		Tail4.addBox(-4F, -1F, 0F, 5, 2, 12);
		Tail4.setRotationPoint(0F, -1.5F, 10F);
		setRotation(Tail4, 0F, 0F, 0F);
		LS1 = new ModelRenderer(this, 81, 60);
		LS1.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS1.setRotationPoint(2.3F, 0F, 12F);
		setRotation(LS1, 0F, -1.010807F, 0F);
		LS2 = new ModelRenderer(this, 81, 60);
		LS2.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS2.setRotationPoint(2.3F, 0F, 10F);
		setRotation(LS2, 0F, -1.010807F, 0F);
		LS3 = new ModelRenderer(this, 81, 60);
		LS3.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS3.setRotationPoint(2.3F, 0F, 8F);
		setRotation(LS3, 0F, -1.003819F, 0F);
		LS4 = new ModelRenderer(this, 81, 60);
		LS4.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS4.setRotationPoint(2.3F, 0F, 6F);
		setRotation(LS4, 0F, -1.003819F, 0F);
		LS5 = new ModelRenderer(this, 81, 60);
		LS5.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS5.setRotationPoint(2.3F, 0F, 4F);
		setRotation(LS5, 0F, -1.003819F, 0F);
		LS6 = new ModelRenderer(this, 81, 60);
		LS6.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS6.setRotationPoint(2.5F, -1.5F, 12F);
		setRotation(LS6, 0F, -1.003819F, 0F);
		LS7 = new ModelRenderer(this, 81, 60);
		LS7.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS7.setRotationPoint(2.5F, -1.5F, 10F);
		setRotation(LS7, 0F, -1.003819F, 0F);
		LS8 = new ModelRenderer(this, 81, 60);
		LS8.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS8.setRotationPoint(2.5F, -1.5F, 8F);
		setRotation(LS8, 0F, -1.003819F, 0F);
		LS9 = new ModelRenderer(this, 81, 60);
		LS9.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS9.setRotationPoint(2.3F, -1.5F, 6F);
		setRotation(LS9, 0F, -1.003819F, 0F);
		LS10 = new ModelRenderer(this, 81, 60);
		LS10.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS10.setRotationPoint(2.5F, -1.5F, 4F);
		setRotation(LS10, 0F, -1.003819F, 0F);
		LS11 = new ModelRenderer(this, 81, 60);
		LS11.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS11.setRotationPoint(3.8F, -1F, 11F);
		setRotation(LS11, 0F, -1.003819F, 0F);
		LS12 = new ModelRenderer(this, 81, 60);
		LS12.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS12.setRotationPoint(3.8F, -1F, 9F);
		setRotation(LS12, 0F, -1.003819F, 0F);
		LS13 = new ModelRenderer(this, 81, 60);
		LS13.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS13.setRotationPoint(3.8F, -1F, 7F);
		setRotation(LS13, 0F, -1.003819F, 0F);
		LS14 = new ModelRenderer(this, 81, 60);
		LS14.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS14.setRotationPoint(3.8F, -1F, 5F);
		setRotation(LS14, 0F, -1.003819F, 0F);
		LS15 = new ModelRenderer(this, 81, 60);
		LS15.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS15.setRotationPoint(4.8F, -0.5F, 12F);
		setRotation(LS15, 0F, -1.003819F, 0F);
		LS16 = new ModelRenderer(this, 81, 60);
		LS16.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS16.setRotationPoint(4.8F, -0.5F, 10F);
		setRotation(LS16, 0F, -1.003819F, 0F);
		LS17 = new ModelRenderer(this, 81, 60);
		LS17.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS17.setRotationPoint(4.8F, -0.5F, 8F);
		setRotation(LS17, 0F, -1.003819F, 0F);
		LS18 = new ModelRenderer(this, 81, 60);
		LS18.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS18.setRotationPoint(4.8F, -0.5F, 6F);
		setRotation(LS18, 0F, -1.003819F, 0F);
		LS19 = new ModelRenderer(this, 81, 60);
		LS19.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		LS19.setRotationPoint(4.8F, -0.5F, 4F);
		setRotation(LS19, 0F, -1.003819F, 0F);
		RS1 = new ModelRenderer(this, 81, 60);
		RS1.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS1.setRotationPoint(-3.5F, 0F, 10F);
		setRotation(RS1, 0F, 1.003822F, 0F);
		RS2 = new ModelRenderer(this, 81, 60);
		RS2.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS2.setRotationPoint(-3.5F, 0F, 8F);
		setRotation(RS2, 0F, 1.003822F, 0F);
		RS3 = new ModelRenderer(this, 81, 60);
		RS3.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS3.setRotationPoint(-3.5F, 0F, 6F);
		setRotation(RS3, 0F, 1.003822F, 0F);
		RS4 = new ModelRenderer(this, 81, 60);
		RS4.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS4.setRotationPoint(-3.5F, 0F, 4F);
		setRotation(RS4, 0F, 1.003822F, 0F);
		RS5 = new ModelRenderer(this, 81, 60);
		RS5.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS5.setRotationPoint(-3.5F, 0F, 2F);
		setRotation(RS5, 0F, 1.003822F, 0F);
		RS6 = new ModelRenderer(this, 81, 60);
		RS6.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS6.setRotationPoint(-3.8F, -1.5F, 10F);
		setRotation(RS6, 0F, 1.003822F, 0F);
		RS7 = new ModelRenderer(this, 81, 60);
		RS7.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS7.setRotationPoint(-3.8F, -1.5F, 8F);
		setRotation(RS7, 0F, 1.003822F, 0F);
		RS8 = new ModelRenderer(this, 81, 60);
		RS8.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS8.setRotationPoint(-3.8F, -1.5F, 6F);
		setRotation(RS8, 0F, 1.003822F, 0F);
		RS9 = new ModelRenderer(this, 81, 60);
		RS9.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS9.setRotationPoint(-3.8F, -1.5F, 4F);
		setRotation(RS9, 0F, 1.003822F, 0F);
		RS10 = new ModelRenderer(this, 81, 60);
		RS10.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS10.setRotationPoint(-3.8F, -1.5F, 2F);
		setRotation(RS10, 0F, 1.003822F, 0F);
		RS11 = new ModelRenderer(this, 81, 60);
		RS11.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS11.setRotationPoint(-3.3F, -1F, 9F);
		setRotation(RS11, 0F, 1.003822F, 0F);
		RS12 = new ModelRenderer(this, 81, 60);
		RS12.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS12.setRotationPoint(-3.3F, -1F, 7F);
		setRotation(RS12, 0F, 1.003822F, 0F);
		RS13 = new ModelRenderer(this, 81, 60);
		RS13.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS13.setRotationPoint(-3.3F, -1F, 5F);
		setRotation(RS13, 0F, 1.003822F, 0F);
		RS14 = new ModelRenderer(this, 81, 60);
		RS14.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS14.setRotationPoint(-3.3F, -1F, 3F);
		setRotation(RS14, 0F, 1.003822F, 0F);
		RS15 = new ModelRenderer(this, 81, 60);
		RS15.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS15.setRotationPoint(-3.3F, -0.5F, 10F);
		setRotation(RS15, 0F, 1.003822F, 0F);
		RS16 = new ModelRenderer(this, 81, 60);
		RS16.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS16.setRotationPoint(-3.3F, -0.5F, 8F);
		setRotation(RS16, 0F, 1.003822F, 0F);
		RS17 = new ModelRenderer(this, 81, 60);
		RS17.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS17.setRotationPoint(-3.3F, -0.5F, 6F);
		setRotation(RS17, 0F, 1.003822F, 0F);
		RS18 = new ModelRenderer(this, 81, 60);
		RS18.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS18.setRotationPoint(-3.3F, -0.5F, 4F);
		setRotation(RS18, 0F, 1.003822F, 0F);
		RS19 = new ModelRenderer(this, 81, 60);
		RS19.addBox(-3F, -0.5F, -0.5F, 3, 1, 1);
		RS19.setRotationPoint(-3.3F, -0.5F, 2F);
		setRotation(RS19, 0F, 1.003822F, 0F);

		Neck1 = new ModelRenderer(this, 33, 0);
		Neck1.addBox(-5F, -5F, -9F, 7, 9, 10);
		Neck1.setRotationPoint(0F, 2.5F, -24F);
		setRotation(Neck1, -0.5205006F, 0F, 0F);
		Neck2 = new ModelRenderer(this, 0, 0);
		Neck2.addBox(-5F, -5F, -9F, 6, 7, 10);
		Neck2.setRotationPoint(0.5F, 1.5F, -6F);
		setRotation(Neck2, -0.1115358F, 0F, 0F);
		Belly = new ModelRenderer(this, 68, 0);
		Belly.addBox(-6F, -6F, 0F, 10, 13, 14);
		Belly.setRotationPoint(-0.5F, 1F, -25F);
		setRotation(Belly, -0.2602503F, 0F, 0F);
		Mid = new ModelRenderer(this, 117, 0);
		Mid.addBox(-6F, -6F, 0F, 10, 11, 17);
		Mid.setRotationPoint(-0.5F, 5.5F, -13F);
		setRotation(Mid, -0.1115358F, 0F, 0F);
		Hips = new ModelRenderer(this, 81, 37);
		Hips.addBox(-6F, -6F, 0F, 10, 11, 11);
		Hips.setRotationPoint(-0.5F, 7.5F, 3F);
		setRotation(Hips, 0F, 0F, 0F);

		TJ1 = new ModelRenderer(this, 25, 70);
		TJ1.addBox(-0.5F, -1.5F, -9F, 2, 2, 9);
		TJ1.setRotationPoint(2F, 0F, 0F);
		setRotation(TJ1, 0F, 0.2230655F, 0F);
		TJ2 = new ModelRenderer(this, 0, 70);
		TJ2.addBox(-0.5F, -1.5F, -10F, 2, 2, 10);
		TJ2.setRotationPoint(-2.5F, -3F, -8F);
		setRotation(TJ2, 0.6320364F, 0F, 0F);
		TJ3 = new ModelRenderer(this, 0, 83);
		TJ3.addBox(-1.5F, -1.5F, -9F, 2, 2, 9);
		TJ3.setRotationPoint(-1F, 0F, 0F);
		setRotation(TJ3, 0F, -0.2230717F, 0F);
		BJ1 = new ModelRenderer(this, 75, 63);
		BJ1.addBox(-0.5F, -1.5F, -9F, 2, 3, 9);
		BJ1.setRotationPoint(2F, 0F, 0F);
		setRotation(BJ1, 0F, 0.2230655F, 0F);
		BJ2 = new ModelRenderer(this, 55, 49);
		BJ2.addBox(-0.5F, -1.5F, -10F, 2, 3, 10);
		BJ2.setRotationPoint(-2.5F, -1.5F, -6.9F);
		setRotation(BJ2, 0.6320364F, 0F, 0F);
		BJ3 = new ModelRenderer(this, 52, 63);
		BJ3.addBox(-1.5F, -1.5F, -9F, 2, 3, 9);
		BJ3.setRotationPoint(-1F, 0F, 0F);
		setRotation(BJ3, 0F, -0.2230717F, 0F);

		JH1 = new ModelRenderer(this, 53, 20);
		JH1.addBox(-0.5F, 0F, -4F, 1, 2, 4);
		JH1.setRotationPoint(1.1F, 1F, -8F);
		setRotation(JH1, 0F, -0.2230655F, 0F);
		JH2 = new ModelRenderer(this, 53, 20);
		JH2.addBox(-0.5F, 0F, -4F, 1, 2, 4);
		JH2.setRotationPoint(-1.1F, 1F, -8F);
		setRotation(JH2, 0F, 0.2230717F, 0F);

		LH1 = new ModelRenderer(this, 36, 54);
		LH1.addBox(-0.5F, -1.5F, -5F, 2, 2, 5);
		LH1.setRotationPoint(0.2F, -1F, -1F);
		setRotation(LH1, -0.7063936F, -0.2230655F, 0F);
		LH2 = new ModelRenderer(this, 36, 62);
		LH2.addBox(-0.5F, -0.5F, -3F, 1, 1, 4);
		LH2.setRotationPoint(0.5F, -0.5F, -5F);
		setRotation(LH2, 0.2230717F, 0F, 0F);
		RH1 = new ModelRenderer(this, 36, 54);
		RH1.addBox(-1.5F, -1.5F, -5F, 2, 2, 5);
		RH1.setRotationPoint(-0.2F, -1F, -1F);
		setRotation(RH1, -0.7063936F, 0.2230717F, 0F);
		RH2 = new ModelRenderer(this, 36, 62);
		RH2.addBox(-0.5F, -0.5F, -3F, 1, 1, 4);
		RH2.setRotationPoint(-0.8F, -0.5F, -5F);
		setRotation(RH2, 0.2230717F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 99, 69);
		Ear1.addBox(0F, -1F, 0F, 1, 2, 6);
		Ear1.setRotationPoint(1F, -1F, -1F);
		setRotation(Ear1, 0.9666439F, -0.2230655F, 0F);
		Ear2 = new ModelRenderer(this, 99, 60);
		Ear2.addBox(-1F, -1F, 0F, 1, 2, 6);
		Ear2.setRotationPoint(-1F, -1F, -1F);
		setRotation(Ear2, 0.9666506F, 0.2230717F, 0F);

		LHL1 = new ModelRenderer(this, 67, 28);
		LHL1.addBox(-2F, 0F, -3F, 4, 10, 7);
		LHL1.setRotationPoint(4F, 5.8F, 9F);
		setRotation(LHL1, 0F, 0F, 0F);
		LHL2 = new ModelRenderer(this, 21, 38);
		LHL2.addBox(-2F, 0F, -3F, 4, 4, 11);
		LHL2.setRotationPoint(0F, 7.2F, 1.4F);
		setRotation(LHL2, -0.3717861F, 0F, 0F);
		LHL3 = new ModelRenderer(this, 19, 54);
		LHL3.addBox(-2F, 0F, -3F, 4, 8, 4);
		LHL3.setRotationPoint(0F, 0.4F, 7.2F);
		setRotation(LHL3, 0.3717861F, 0F, 0F);
		LHL4 = new ModelRenderer(this, 90, 28);
		LHL4.addBox(-2.5F, 0F, -4.5F, 5, 2, 6);
		LHL4.setRotationPoint(0F, 6F, 0F);
		setRotation(LHL4, 0F, 0F, 0F);
		LHLC1 = new ModelRenderer(this, 46, 20);
		LHLC1.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		LHLC1.setRotationPoint(-2F, 1F, -5F);
		setRotation(LHLC1, 0.3717861F, 0F, 0F);
		LHLC2 = new ModelRenderer(this, 46, 20);
		LHLC2.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		LHLC2.setRotationPoint(0F, 1F, -5F);
		setRotation(LHLC2, 0.3717861F, 0F, 0F);
		LHLC3 = new ModelRenderer(this, 46, 20);
		LHLC3.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		LHLC3.setRotationPoint(2F, 1F, -5F);
		setRotation(LHLC3, 0.3717861F, 0F, 0F);
		RHL1 = new ModelRenderer(this, 21, 20);
		RHL1.addBox(-2F, 0F, -3F, 4, 10, 7);
		RHL1.setRotationPoint(-7F, 5.8F, 9F);
		setRotation(RHL1, 0F, 0F, 0F);
		RHL2 = new ModelRenderer(this, 21, 38);
		RHL2.addBox(-2F, 0F, -3F, 4, 4, 11);
		RHL2.setRotationPoint(0F, 7.2F, 1.4F);
		setRotation(RHL2, -0.3717861F, 0F, 0F);
		RHL3 = new ModelRenderer(this, 19, 54);
		RHL3.addBox(-2F, 0F, -3F, 4, 8, 4);
		RHL3.setRotationPoint(0F, 0.4F, 7.2F);
		setRotation(RHL3, 0.3717861F, 0F, 0F);
		RHL4 = new ModelRenderer(this, 90, 28);
		RHL4.addBox(-2.5F, 0F, -4.5F, 5, 2, 6);
		RHL4.setRotationPoint(0F, 6F, 0F);
		setRotation(RHL4, 0F, 0F, 0F);
		RHLC1 = new ModelRenderer(this, 46, 20);
		RHLC1.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		RHLC1.setRotationPoint(-2F, 1F, -5F);
		setRotation(RHLC1, 0.3717861F, 0F, 0F);
		RHLC2 = new ModelRenderer(this, 46, 20);
		RHLC2.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		RHLC2.setRotationPoint(0F, 1F, -5F);
		setRotation(RHLC2, 0.3717861F, 0F, 0F);
		RHLC3 = new ModelRenderer(this, 46, 20);
		RHLC3.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		RHLC3.setRotationPoint(2F, 1F, -5F);
		setRotation(RHLC3, 0.3717861F, 0F, 0F);
		LFL1 = new ModelRenderer(this, 46, 29);
		LFL1.addBox(-2F, 0F, -3F, 4, 13, 6);
		LFL1.setRotationPoint(4F, 2F, -18F);
		setRotation(LFL1, 0.7063936F, 0F, 0F);
		LFL2 = new ModelRenderer(this, 0, 38);
		LFL2.addBox(-2F, 0F, -3F, 4, 14, 6);
		LFL2.setRotationPoint(0F, 11F, 0.7F);
		setRotation(LFL2, -0.7063936F, 0F, 0F);
		LFL3 = new ModelRenderer(this, 0, 59);
		LFL3.addBox(-2.5F, 0F, -4.5F, 5, 2, 8);
		LFL3.setRotationPoint(0F, 12F, 0F);
		setRotation(LFL3, 0F, 0F, 0F);
		LFLC1 = new ModelRenderer(this, 46, 20);
		LFLC1.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		LFLC1.setRotationPoint(-2F, 1F, -4.5F);
		setRotation(LFLC1, 0.3717861F, 0F, 0F);
		LFLC2 = new ModelRenderer(this, 46, 20);
		LFLC2.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		LFLC2.setRotationPoint(0F, 1F, -4.5F);
		setRotation(LFLC2, 0.3717861F, 0F, 0F);
		LFLC3 = new ModelRenderer(this, 46, 20);
		LFLC3.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		LFLC3.setRotationPoint(2F, 1F, -4.5F);
		setRotation(LFLC3, 0.3717861F, 0F, 0F);
		RFL1 = new ModelRenderer(this, 0, 18);
		RFL1.addBox(-2F, 0F, -3F, 4, 13, 6);
		RFL1.setRotationPoint(-7F, 2F, -18F);
		setRotation(RFL1, 0.7063936F, 0F, 0F);
		RFL2 = new ModelRenderer(this, 0, 38);
		RFL2.addBox(-2F, 0F, -3F, 4, 14, 6);
		RFL2.setRotationPoint(0F, 11F, 0.7F);
		setRotation(RFL2, -0.7063936F, 0F, 0F);
		RFL3 = new ModelRenderer(this, 0, 59);
		RFL3.addBox(-2.5F, 0F, -4.5F, 5, 2, 8);
		RFL3.setRotationPoint(0F, 12F, 0F);
		setRotation(RFL3, 0F, 0F, 0F);
		RFLC1 = new ModelRenderer(this, 46, 20);
		RFLC1.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		RFLC1.setRotationPoint(-2F, 1F, -4.5F);
		setRotation(RFLC1, 0.3717861F, 0F, 0F);
		RFLC2 = new ModelRenderer(this, 46, 20);
		RFLC2.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		RFLC2.setRotationPoint(0F, 1F, -4.5F);
		setRotation(RFLC2, 0.3717861F, 0F, 0F);
		RFLC3 = new ModelRenderer(this, 46, 20);
		RFLC3.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		RFLC3.setRotationPoint(2F, 1F, -4.5F);
		setRotation(RFLC3, 0.3717861F, 0F, 0F);

		// Head\\
		Neck1.addChild(Neck2);
		Neck2.addChild(BJ2);
		Neck2.addChild(TJ2);
		TJ2.addChild(TJ1);
		TJ2.addChild(TJ3);
		BJ2.addChild(BJ1);
		BJ2.addChild(BJ3);

		TJ1.addChild(LH1);
		TJ3.addChild(RH1);
		TJ1.addChild(Ear1);
		TJ3.addChild(Ear2);
		LH1.addChild(LH2);
		RH1.addChild(RH2);
		BJ1.addChild(JH1);
		BJ3.addChild(JH2);

		// Front Legs\\
		LFL1.addChild(LFL2);
		LFL2.addChild(LFL3);
		LFL3.addChild(LFLC1);
		LFL3.addChild(LFLC2);
		LFL3.addChild(LFLC3);
		RFL1.addChild(RFL2);
		RFL2.addChild(RFL3);
		RFL3.addChild(RFLC1);
		RFL3.addChild(RFLC2);
		RFL3.addChild(RFLC3);

		// Back Legs\\
		LHL1.addChild(LHL2);
		LHL2.addChild(LHL3);
		LHL3.addChild(LHL4);
		LHL4.addChild(LHLC1);
		LHL4.addChild(LHLC2);
		LHL4.addChild(LHLC3);
		RHL1.addChild(RHL2);
		RHL2.addChild(RHL3);
		RHL3.addChild(RHL4);
		RHL4.addChild(RHLC1);
		RHL4.addChild(RHLC2);
		RHL4.addChild(RHLC3);

		// Tail\\
		Tail1.addChild(Tail2);
		Tail2.addChild(Tail3);
		Tail3.addChild(Tail4);
		Tail4.addChild(LS1);
		Tail4.addChild(LS2);
		Tail4.addChild(LS3);
		Tail4.addChild(LS4);
		Tail4.addChild(LS5);
		Tail3.addChild(LS6);
		Tail3.addChild(LS7);
		Tail3.addChild(LS8);
		Tail3.addChild(LS9);
		Tail3.addChild(LS10);
		Tail2.addChild(LS11);
		Tail2.addChild(LS12);
		Tail2.addChild(LS13);
		Tail2.addChild(LS14);
		Tail1.addChild(LS15);
		Tail1.addChild(LS16);
		Tail1.addChild(LS17);
		Tail1.addChild(LS18);
		Tail1.addChild(LS19);
		Tail4.addChild(RS1);
		Tail4.addChild(RS2);
		Tail4.addChild(RS3);
		Tail4.addChild(RS4);
		Tail4.addChild(RS5);
		Tail3.addChild(RS6);
		Tail3.addChild(RS7);
		Tail3.addChild(RS8);
		Tail3.addChild(RS9);
		Tail3.addChild(RS10);
		Tail2.addChild(RS11);
		Tail2.addChild(RS12);
		Tail2.addChild(RS13);
		Tail2.addChild(RS14);
		Tail1.addChild(RS15);
		Tail1.addChild(RS16);
		Tail1.addChild(RS17);
		Tail1.addChild(RS18);
		Tail1.addChild(RS19);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glPushMatrix();
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		GL11.glTranslatef(0.0F, -0.5F, 0.0F);
		Neck1.render(f5);
		Belly.render(f5);
		Mid.render(f5);
		Hips.render(f5);
		Tail1.render(f5);
		LFL1.render(f5);
		RFL1.render(f5);
		LHL1.render(f5);
		RHL1.render(f5);
		GL11.glPopMatrix();
	}

	/**
	 * Used for easily adding entity-dependent animations. The second and third
	 * float params here are the same second and third as in the
	 * setRotationAngles method.
	 */
	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
		EntityZertumEntity entityzertum = (EntityZertumEntity) par1EntityLivingBase;
		float f11 = entityzertum.getMouthOpennessAngle(par4);
		if (entityzertum.isSitting()) {
			LHL1.setRotationPoint(4F, 5.4F, 9F);
			setRotation(LHL1, -0.7063936F, 0F, 0F);
			LHL2.setRotationPoint(0F, 6.4F, 0.2F);
			setRotation(LHL2, 0.0743572F, 0F, 0F);
			setRotation(LHL3, 0.6417861F, 0F, 0F);
			RHL1.setRotationPoint(-7F, 5.4F, 9F);
			setRotation(RHL1, -0.7063936F, 0F, 0F);
			RHL2.setRotationPoint(0F, 6.4F, 0.2F);
			setRotation(RHL2, 0.0743572F, 0F, 0F);
			setRotation(RHL3, 0.6417861F, 0F, 0F);
			setRotation(Hips, -0.1115358F, 0F, 0F);
			Tail1.setRotationPoint(-1.5F, 8F, 12F);
			setRotation(Tail1, -0.2230717F, 0F, 0F);
			setRotation(Tail2, -0.0602503F, 0F, 0F);
			setRotation(Tail3, -0.0346075F, 0F, 0F);
			setRotation(Tail4, -0.0461433F, 0F, 0F);

		}
		else {

			LFL1.setRotationPoint(4F, 2F, -18F);
			setRotation(LFL1, 0.7063936F, 0F, 0F);
			LFL2.setRotationPoint(0F, 11F, 0.7F);
			setRotation(LFL2, -0.7063936F, 0F, 0F);
			LFL3.setRotationPoint(0F, 12F, 0F);
			setRotation(LFL3, 0F, 0F, 0F);
			LFLC1.setRotationPoint(-2F, 1F, -4.5F);
			setRotation(LFLC1, 0.3717861F, 0F, 0F);
			LFLC2.setRotationPoint(0F, 1F, -4.5F);
			setRotation(LFLC2, 0.3717861F, 0F, 0F);
			LFLC3.setRotationPoint(2F, 1F, -4.5F);
			setRotation(LFLC3, 0.3717861F, 0F, 0F);
			RFL1.setRotationPoint(-7F, 2F, -18F);
			setRotation(RFL1, 0.7063936F, 0F, 0F);
			RFL2.setRotationPoint(0F, 11F, 0.7F);
			setRotation(RFL2, -0.7063936F, 0F, 0F);
			RFL3.setRotationPoint(0F, 12F, 0F);
			setRotation(RFL3, 0F, 0F, 0F);
			RFLC1.setRotationPoint(-2F, 1F, -4.5F);
			setRotation(RFLC1, 0.3717861F, 0F, 0F);
			RFLC2.setRotationPoint(0F, 1F, -4.5F);
			setRotation(RFLC2, 0.3717861F, 0F, 0F);
			RFLC3.setRotationPoint(2F, 1F, -4.5F);
			setRotation(RFLC3, 0.3717861F, 0F, 0F);
			LHL1.setRotationPoint(4F, 5.8F, 9F);
			setRotation(LHL1, 0F, 0F, 0F);
			LHL2.setRotationPoint(0F, 7.2F, 1.4F);
			setRotation(LHL2, -0.3717861F, 0F, 0F);
			LHL3.setRotationPoint(0F, 0.4F, 7.2F);
			setRotation(LHL3, 0.3717861F, 0F, 0F);
			LHL4.setRotationPoint(0F, 6F, 0F);
			setRotation(LHL4, 0F, 0F, 0F);
			LHLC1.setRotationPoint(-2F, 1F, -5F);
			setRotation(LHLC1, 0.3717861F, 0F, 0F);
			LHLC2.setRotationPoint(0F, 1F, -5F);
			setRotation(LHLC2, 0.3717861F, 0F, 0F);
			LHLC3.setRotationPoint(2F, 1F, -5F);
			setRotation(LHLC3, 0.3717861F, 0F, 0F);
			RHL1.setRotationPoint(-7F, 5.8F, 9F);
			setRotation(RHL1, 0F, 0F, 0F);
			RHL2.setRotationPoint(0F, 7.2F, 1.4F);
			setRotation(RHL2, -0.3717861F, 0F, 0F);
			RHL3.setRotationPoint(0F, 0.4F, 7.2F);
			setRotation(RHL3, 0.3717861F, 0F, 0F);
			RHL4.setRotationPoint(0F, 6F, 0F);
			setRotation(RHL4, 0F, 0F, 0F);
			RHLC1.setRotationPoint(-2F, 1F, -5F);
			setRotation(RHLC1, 0.3717861F, 0F, 0F);
			RHLC2.setRotationPoint(0F, 1F, -5F);
			setRotation(RHLC2, 0.3717861F, 0F, 0F);
			RHLC3.setRotationPoint(2F, 1F, -5F);
			setRotation(RHLC3, 0.3717861F, 0F, 0F);
			setRotation(Hips, 0F, 0F, 0F);
			Tail1.setRotationPoint(-1.5F, 6F, 12F);
			setRotation(Tail1, 0F, 0F, 0F);
			Tail2.setRotationPoint(0.5F, 0.5F, 9F);
			setRotation(Tail2, 0F, 0F, 0F);
			Tail3.setRotationPoint(1F, 0.5F, 9F);
			setRotation(Tail3, 0F, 0F, 0F);
			Tail4.setRotationPoint(0F, -1.5F, 10F);
			setRotation(Tail4, 0F, 0F, 0F);

			LFL1.rotateAngleX = 0.7063936F + MathHelper.cos(par2 * 0.3962F + (float) Math.PI) * 0.5F * par3;
			RFL1.rotateAngleX = 0.7063936F + MathHelper.cos(par2 * 0.4962F) * 0.5F * par3;
			LHL1.rotateAngleX = MathHelper.cos(par2 * 0.3362F + (float) Math.PI) * 0.5F * par3;
			RHL1.rotateAngleX = MathHelper.cos(par2 * 0.4062F) * 0.5F * par3;

			Neck1.rotateAngleZ = entityzertum.getInterestedAngle(par4) + entityzertum.getShakeAngle(par4, 0.0F);
			Belly.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.08F);
			Mid.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.16F);
			Hips.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.32F);
			Tail1.rotateAngleZ = entityzertum.getShakeAngle(par4, -0.48F);
		}

		TJ2.rotateAngleX = 0.6320364F - 0.45707964F * f11;
		BJ2.rotateAngleX = 0.6320364F + 0.45707964F * f11;

		if ((entityzertum.isSitting() || (entityzertum.motionX == 0.0F && entityzertum.motionZ == 0.0F)) && entityzertum.getHealth() > Constants.lowHP) {
			float wagAngleY = entityzertum.getWagAngle(par4, 0.0F);
			float wagAngleY2 = entityzertum.getWagAngle(par4, 0.0F);
			float wagAngleY3 = entityzertum.getWagAngle(par4, 0.0F);
			float wagAngleY4 = entityzertum.getWagAngle(par4, 0.0F);
			if (wagAngleY == 0.0F && wagAngleY2 == 0.0F && wagAngleY3 == 0.0F) {
				wagAngleY = MathHelper.cos(par2 * 0.0062F) * 1.4F * par3;
				wagAngleY2 = MathHelper.cos(par2 * 0.0002F) * 1.4F * par3;
				wagAngleY3 = MathHelper.cos(par2 * 0.0001F) * 1.4F * par3;
				wagAngleY4 = MathHelper.cos(par2 * 0.00009F) * 1.4F * par3;

			}
			this.Tail1.rotateAngleY = wagAngleY;
			this.Tail2.rotateAngleY = wagAngleY2;
			this.Tail3.rotateAngleY = wagAngleY3;
			this.Tail4.rotateAngleY = wagAngleY4;
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
		this.Neck1.rotateAngleX = -0.5205006F + par5 / (280F / (float) Math.PI);
		this.Neck1.rotateAngleY = par4 / (180F / (float) Math.PI);
	}

}

package net.novaviper.zeroquest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelExtruder extends ModelBase {
	// fields
	ModelRenderer Top;
	ModelRenderer Pole;
	ModelRenderer Turner2;
	ModelRenderer Turner1;
	ModelRenderer HoldPole1;
	ModelRenderer HoldPole2;
	ModelRenderer HoldPole3;
	ModelRenderer HoldPole4;
	ModelRenderer Base;

	public ModelExtruder() {
		textureWidth = 128;
		textureHeight = 64;

		Top = new ModelRenderer(this, 50, 23);
		Top.addBox(-8F, 0F, -8F, 16, 5, 16);
		Top.setRotationPoint(0F, 8F, 0F);
		Top.setTextureSize(128, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Pole = new ModelRenderer(this, 12, 23);
		Pole.addBox(-4F, 0F, -4F, 8, 13, 8);
		Pole.setRotationPoint(0F, 10F, 0F);
		Pole.setTextureSize(128, 64);
		Pole.mirror = true;
		setRotation(Pole, 0F, 0F, 0F);
		Turner2 = new ModelRenderer(this, 0, 51);
		Turner2.addBox(-5.5F, 0F, -5.5F, 11, 2, 11);
		Turner2.setRotationPoint(0F, 15F, 0F);
		Turner2.setTextureSize(128, 64);
		Turner2.mirror = true;
		setRotation(Turner2, 0F, 0F, 0F);
		Turner1 = new ModelRenderer(this, 0, 51);
		Turner1.addBox(-5.5F, 0F, -5.5F, 11, 2, 11);
		Turner1.setRotationPoint(0F, 19F, 0F);
		Turner1.setTextureSize(128, 64);
		Turner1.mirror = true;
		setRotation(Turner1, 0F, 0F, 0F);
		HoldPole1 = new ModelRenderer(this, 0, 23);
		HoldPole1.addBox(-1F, 0F, -1F, 2, 10, 2);
		HoldPole1.setRotationPoint(-7F, 13F, -7F);
		HoldPole1.setTextureSize(128, 64);
		HoldPole1.mirror = true;
		setRotation(HoldPole1, 0F, 0F, 0F);
		HoldPole2 = new ModelRenderer(this, 0, 23);
		HoldPole2.addBox(-1F, 0F, -1F, 2, 10, 2);
		HoldPole2.setRotationPoint(7F, 13F, -7F);
		HoldPole2.setTextureSize(128, 64);
		HoldPole2.mirror = true;
		setRotation(HoldPole2, 0F, 0F, 0F);
		HoldPole3 = new ModelRenderer(this, 0, 23);
		HoldPole3.addBox(-1F, 0F, -1F, 2, 10, 2);
		HoldPole3.setRotationPoint(-7F, 13F, 7F);
		HoldPole3.setTextureSize(128, 64);
		HoldPole3.mirror = true;
		setRotation(HoldPole3, 0F, 0F, 0F);
		HoldPole4 = new ModelRenderer(this, 0, 23);
		HoldPole4.addBox(-1F, 0F, -1F, 2, 10, 2);
		HoldPole4.setRotationPoint(7F, 13F, 7F);
		HoldPole4.setTextureSize(128, 64);
		HoldPole4.mirror = true;
		setRotation(HoldPole4, 0F, 0F, 0F);
		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(-8F, 0F, -8F, 16, 1, 16);
		Base.setRotationPoint(0F, 23F, 0F);
		Base.setTextureSize(128, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Top.render(f5);
		Pole.render(f5);
		Turner2.render(f5);
		Turner1.render(f5);
		HoldPole1.render(f5);
		HoldPole2.render(f5);
		HoldPole3.render(f5);
		HoldPole4.render(f5);
		Base.render(f5);
	}

	public void renderModel(float f) {
		Top.render(f);
		Pole.render(f);
		Turner2.render(f);
		Turner1.render(f);
		HoldPole1.render(f);
		HoldPole2.render(f);
		HoldPole3.render(f);
		HoldPole4.render(f);
		Base.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
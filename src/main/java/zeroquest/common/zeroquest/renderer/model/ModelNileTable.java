package common.zeroquest.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNileTable extends ModelBase
{
  //fields
    ModelRenderer Pole;
    ModelRenderer Base;
    ModelRenderer Top;
  
  public ModelNileTable()
  {
    textureWidth = 128;
    textureHeight = 64;
    
    Pole = new ModelRenderer(this, 0, 0);
    Pole.addBox(0F, 0F, 0F, 12, 6, 12);
    Pole.setRotationPoint(-6F, 15F, -6F);
    Pole.setTextureSize(128, 64);
    Pole.mirror = true;
    setRotation(Pole, 0F, 0F, 0F);
    Base = new ModelRenderer(this, 0, 19);
    Base.addBox(0F, 0F, 0F, 16, 3, 16);
    Base.setRotationPoint(-8F, 21F, -8F);
    Base.setTextureSize(128, 64);
    Base.mirror = true;
    setRotation(Base, 0F, 0F, 0F);
    Top = new ModelRenderer(this, 55, 7);
    Top.addBox(0F, 0F, 0F, 16, 7, 16);
    Top.setRotationPoint(-8F, 9F, -8F);
    Top.setTextureSize(128, 64);
    Top.mirror = true;
    setRotation(Top, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Pole.render(f5);
    Base.render(f5);
    Top.render(f5);
  }
  
  public void renderModel(float f){
	    Pole.render(f);
	    Base.render(f);
	    Top.render(f);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}

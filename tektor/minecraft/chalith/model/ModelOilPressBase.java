package tektor.minecraft.chalith.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOilPressBase extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public ModelOilPressBase()
  {
    textureWidth = 256;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 10, 14, 32);
      Shape1.setRotationPoint(-8F, 10F, -8F);
      Shape1.setTextureSize(256, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 10, 14, 32);
      Shape2.setRotationPoint(14F, 10F, -8F);
      Shape2.setTextureSize(256, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 54, 0);
      Shape3.addBox(0F, 0F, 0F, 12, 14, 10);
      Shape3.setRotationPoint(2F, 10F, -8F);
      Shape3.setTextureSize(256, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 99, 0);
      Shape4.addBox(0F, 0F, 0F, 12, 14, 10);
      Shape4.setRotationPoint(2F, 10F, 14F);
      Shape4.setTextureSize(256, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 85, 25);
      Shape5.addBox(0F, 0F, 0F, 12, 5, 12);
      Shape5.setRotationPoint(2F, 19F, 2F);
      Shape5.setTextureSize(256, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity ent,float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
  }

}

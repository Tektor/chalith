package tektor.minecraft.chalith.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DryStandModelT extends ModelBase
{
  //fields
    ModelRenderer Shape1a, Shape1b, Shape1c,Shape1d;
    ModelRenderer Shape2a, Shape2b;
    ModelRenderer Shape3a,Shape3b;
    ModelRenderer Shape4a, Shape4b, Shape4c, Shape4d;
  
  public DryStandModelT()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Shape1a = new ModelRenderer(this, 0, 0);
      Shape1a.addBox(0F, 0F, 0F, 1, 31, 1);
      Shape1a.setRotationPoint(-8F, -7F, 7F);
      Shape1a.setTextureSize(128, 64);
      Shape1a.mirror = true;
      setRotation(Shape1a, 0F, 0F, 0F);
      Shape1b = new ModelRenderer(this, 0, 0);
      Shape1b.addBox(0F, 0F, 0F, 1, 31, 1);
      Shape1b.setRotationPoint(7F, -7F, 7F);
      Shape1b.setTextureSize(128, 64);
      Shape1b.mirror = true;
      setRotation(Shape1b, 0F, 0F, 0F);
      Shape1c = new ModelRenderer(this, 0, 0);
      Shape1c.addBox(0F, 0F, 0F, 1, 31, 1);
      Shape1c.setRotationPoint(7F, -7F, -8F);
      Shape1c.setTextureSize(128, 64);
      Shape1c.mirror = true;
      setRotation(Shape1c, 0F, 0F, 0F);
      Shape1d = new ModelRenderer(this, 0, 0);
      Shape1d.addBox(0F, 0F, 0F, 1, 31, 1);
      Shape1d.setRotationPoint(-8F, -7F, -8F);
      Shape1d.setTextureSize(128, 64);
      Shape1d.mirror = true;
      setRotation(Shape1d, 0F, 0F, 0F);
      Shape2a = new ModelRenderer(this, 32, 2);
      Shape2a.addBox(0F, 0F, 0F, 16, 1, 1);
      Shape2a.setRotationPoint(-8F, -8F, -8F);
      Shape2a.setTextureSize(128, 64);
      Shape2a.mirror = true;
      setRotation(Shape2a, 0F, 0F, 0F);
      Shape2b = new ModelRenderer(this, 32, 4);
      Shape2b.addBox(0F, 0F, 0F, 16, 1, 1);
      Shape2b.setRotationPoint(-8F, -8F, 7F);
      Shape2b.setTextureSize(128, 64);
      Shape2b.mirror = true;
      setRotation(Shape2b, 0F, 0F, 0F);
      Shape3a = new ModelRenderer(this, 6, 16);
      Shape3a.addBox(0F, 0F, 0F, 1, 1, 14);
      Shape3a.setRotationPoint(7F, -8F, -7F);
      Shape3a.setTextureSize(128, 64);
      Shape3a.mirror = true;
      setRotation(Shape3a, 0F, 0F, 0F);
      Shape3b = new ModelRenderer(this, 4, 15);
      Shape3b.addBox(0F, 0F, 0F, 1, 1, 14);
      Shape3b.setRotationPoint(-8F, -8F, -7F);
      Shape3b.setTextureSize(128, 64);
      Shape3b.mirror = true;
      setRotation(Shape3b, 0F, 0F, 0F);
      Shape4a = new ModelRenderer(this, 5, 6);
      Shape4a.addBox(0F, 0F, 0F, 14, 1, 1);
      Shape4a.setRotationPoint(-7F, -8F, -5F);
      Shape4a.setTextureSize(128, 64);
      Shape4a.mirror = true;
      setRotation(Shape4a, 0F, 0F, 0F);
      Shape4b = new ModelRenderer(this, 5, 6);
      Shape4b.addBox(0F, 0F, 0F, 14, 1,1);
      Shape4b.setRotationPoint(-7F, -8F, -2F);
      Shape4b.setTextureSize(128, 64);
      Shape4b.mirror = true;
      setRotation(Shape4b, 0F, 0F, 0F);
      Shape4c = new ModelRenderer(this, 5, 6);
      Shape4c.addBox(0F, 0F, 0F, 14, 1, 1);
      Shape4c.setRotationPoint(-7F, -8F, 1F);
      Shape4c.setTextureSize(128, 64);
      Shape4c.mirror = true;
      setRotation(Shape4c, 0F, 0F, 0F);
      Shape4d = new ModelRenderer(this, 5, 6);
      Shape4d.addBox(0F, 0F, 0F, 14, 1, 1);
      Shape4d.setRotationPoint(-7F, -8F, 4F);
      Shape4d.setTextureSize(128, 64);
      Shape4d.mirror = true;
      setRotation(Shape4d, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    Shape1a.render(f5);
    Shape1b.render(f5);
    Shape1c.render(f5);
    Shape1d.render(f5);
    Shape2a.render(f5);
    Shape2b.render(f5);
    Shape3a.render(f5);
    Shape3b.render(f5);
    Shape4a.render(f5);
    Shape4b.render(f5);
    Shape4c.render(f5);
    Shape4d.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity ent, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
  }

}

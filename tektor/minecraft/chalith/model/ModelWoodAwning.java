package tektor.minecraft.chalith.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWoodAwning extends ModelBase
{
  //fields
    ModelRenderer Shape1a;
    ModelRenderer Shape1b;
    ModelRenderer Shape1c;
    ModelRenderer Shape1d;
    ModelRenderer Shape2a;
    ModelRenderer Shape2b;
    ModelRenderer Shape3a;
    ModelRenderer Shape3b;
    ModelRenderer Shape4b;
    ModelRenderer Shape4a;
    ModelRenderer Shape4c;
    ModelRenderer Shape4d;
    ModelRenderer Shape5a;
    ModelRenderer Shape5b;
    ModelRenderer Shape6a;
    ModelRenderer Shape6b;
    ModelRenderer Shape7a;
    ModelRenderer Shape7b;
    ModelRenderer Shape7c;
    ModelRenderer Shape7e;
    ModelRenderer Shape7g;
    ModelRenderer Shape7d;
    ModelRenderer Shape7f;
    ModelRenderer Shape7h;
    ModelRenderer Shape8a;
    ModelRenderer Shape8b;
    ModelRenderer Shape8c;
    ModelRenderer Shape8d;
    ModelRenderer Shape9a;
    ModelRenderer Shape9b;
    ModelRenderer Shape9c;
  
  public ModelWoodAwning()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      Shape1a = new ModelRenderer(this, 0, 0);
      Shape1a.addBox(0F, 0F, 0F, 2, 32, 2);
      Shape1a.setRotationPoint(-8F, -8F, -8F);
      Shape1a.setTextureSize(256, 128);
      Shape1a.mirror = true;
      setRotation(Shape1a, 0F, 0F, 0F);
      Shape1b = new ModelRenderer(this, 0, 0);
      Shape1b.addBox(0F, 0F, 0F, 2, 32, 2);
      Shape1b.setRotationPoint(-8F, -8F, 38F);
      Shape1b.setTextureSize(256, 128);
      Shape1b.mirror = true;
      setRotation(Shape1b, 0F, 0F, 0F);
      Shape1c = new ModelRenderer(this, 0, 0);
      Shape1c.addBox(0F, 0F, 0F, 2, 32, 2);
      Shape1c.setRotationPoint(22F, -8F, 38F);
      Shape1c.setTextureSize(256, 128);
      Shape1c.mirror = true;
      setRotation(Shape1c, 0F, 0F, 0F);
      Shape1d = new ModelRenderer(this, 0, 0);
      Shape1d.addBox(0F, 0F, 0F, 2, 32, 2);
      Shape1d.setRotationPoint(22F, -8F, -8F);
      Shape1d.setTextureSize(256, 128);
      Shape1d.mirror = true;
      setRotation(Shape1d, 0F, 0F, 0F);
      Shape2a = new ModelRenderer(this, 9, 0);
      Shape2a.addBox(0F, 0F, 0F, 28, 2, 1);
      Shape2a.setRotationPoint(-6F, -8F, -8F);
      Shape2a.setTextureSize(256, 128);
      Shape2a.mirror = true;
      setRotation(Shape2a, 0F, 0F, 0F);
      Shape2b = new ModelRenderer(this, 9, 0);
      Shape2b.addBox(0F, 0F, 0F, 28, 2, 1);
      Shape2b.setRotationPoint(-6F, -8F, 39F);
      Shape2b.setTextureSize(256, 128);
      Shape2b.mirror = true;
      setRotation(Shape2b, 0F, 0F, 0F);
      Shape3a = new ModelRenderer(this, 0, 35);
      Shape3a.addBox(0F, 0F, 0F, 1, 2, 44);
      Shape3a.setRotationPoint(-8F, -8F, -6F);
      Shape3a.setTextureSize(256, 128);
      Shape3a.mirror = true;
      setRotation(Shape3a, 0F, 0F, 0F);
      Shape3b = new ModelRenderer(this, 0, 35);
      Shape3b.addBox(0F, 0F, 0F, 1, 2, 44);
      Shape3b.setRotationPoint(23F, -8F, -6F);
      Shape3b.setTextureSize(256, 128);
      Shape3b.mirror = true;
      setRotation(Shape3b, 0F, 0F, 0F);
      Shape4b = new ModelRenderer(this, 67, 0);
      Shape4b.addBox(0F, 0F, 0F, 19, 2, 2);
      Shape4b.setRotationPoint(-5F, -7F, -7F);
      Shape4b.setTextureSize(256, 128);
      Shape4b.mirror = true;
      setRotation(Shape4b, 0.7853982F, -1.134464F, -0.4363323F);
      Shape4a = new ModelRenderer(this, 67, 0);
      Shape4a.addBox(0F, 0F, 0F, 19, 2, 2);
      Shape4a.setRotationPoint(-8F, -7F, 38F);
      Shape4a.setTextureSize(256, 128);
      Shape4a.mirror = true;
      setRotation(Shape4a, 0.7853982F, 1.134464F, -0.4363323F);
      Shape4c = new ModelRenderer(this, 67, 0);
      Shape4c.addBox(0F, 0F, 0F, 19, 2, 2);
      Shape4c.setRotationPoint(21F, -7F, 39F);
      Shape4c.setTextureSize(256, 128);
      Shape4c.mirror = true;
      setRotation(Shape4c, 0.7853982F, 2.007129F, -0.4363323F);
      Shape4d = new ModelRenderer(this, 67, 0);
      Shape4d.addBox(0F, 0F, 0F, 19, 2, 2);
      Shape4d.setRotationPoint(24F, -7F, -6F);
      Shape4d.setTextureSize(256, 128);
      Shape4d.mirror = true;
      setRotation(Shape4d, 0.7853982F, -2.007129F, -0.4363323F);
      Shape5a = new ModelRenderer(this, 8, 3);
      Shape5a.addBox(0F, 0F, 0F, 2, 2, 16);
      Shape5a.setRotationPoint(15F, -16.25F, 8F);
      Shape5a.setTextureSize(256, 128);
      Shape5a.mirror = true;
      setRotation(Shape5a, 0F, 0F, 0F);
      Shape5b = new ModelRenderer(this, 8, 3);
      Shape5b.addBox(0F, 0F, 0F, 2, 2, 16);
      Shape5b.setRotationPoint(0F, -16.25F, 8F);
      Shape5b.setTextureSize(256, 128);
      Shape5b.mirror = true;
      setRotation(Shape5b, 0F, 0F, 0F);
      Shape6a = new ModelRenderer(this, 44, 5);
      Shape6a.addBox(0F, 0F, 0F, 13, 2, 2);
      Shape6a.setRotationPoint(2F, -16.25F, 8F);
      Shape6a.setTextureSize(256, 128);
      Shape6a.mirror = true;
      setRotation(Shape6a, 0F, 0F, 0F);
      Shape6b = new ModelRenderer(this, 44, 5);
      Shape6b.addBox(0F, 0F, 0F, 13, 2, 2);
      Shape6b.setRotationPoint(2F, -16.3F, 22F);
      Shape6b.setTextureSize(256, 128);
      Shape6b.mirror = true;
      setRotation(Shape6b, 0F, 0F, 0F);
      Shape7a = new ModelRenderer(this, 8, 21);
      Shape7a.addBox(0F, 0F, 0F, 18, 1, 1);
      Shape7a.setRotationPoint(-1F, -14F, 5F);
      Shape7a.setTextureSize(256, 128);
      Shape7a.mirror = true;
      setRotation(Shape7a, 0F, 0F, 0F);
      Shape7b = new ModelRenderer(this, 8, 21);
      Shape7b.addBox(0F, 0F, 0F, 18, 1, 1);
      Shape7b.setRotationPoint(-1F, -14F, 26F);
      Shape7b.setTextureSize(256, 128);
      Shape7b.mirror = true;
      setRotation(Shape7b, 0F, 0F, 0F);
      Shape7c = new ModelRenderer(this, 8, 23);
      Shape7c.addBox(0F, 0.6666667F, 0F, 20, 1, 1);
      Shape7c.setRotationPoint(-2F, -13F, 2F);
      Shape7c.setTextureSize(256, 128);
      Shape7c.mirror = true;
      setRotation(Shape7c, 0F, 0F, 0F);
      Shape7e = new ModelRenderer(this, 8, 25);
      Shape7e.addBox(0F, 0.4F, 0F, 23, 1, 1);
      Shape7e.setRotationPoint(-3F, -11F, -1.4F);
      Shape7e.setTextureSize(256, 128);
      Shape7e.mirror = true;
      setRotation(Shape7e, 0F, 0F, 0F);
      Shape7g = new ModelRenderer(this, 8, 27);
      Shape7g.addBox(-0.7333333F, 0F, 0F, 26, 1, 1);
      Shape7g.setRotationPoint(-4F, -9F, -5F);
      Shape7g.setTextureSize(256, 128);
      Shape7g.mirror = true;
      setRotation(Shape7g, 0F, 0F, 0F);
      Shape7d = new ModelRenderer(this, 8, 23);
      Shape7d.addBox(0F, 0.7F, 0F, 20, 1, 1);
      Shape7d.setRotationPoint(-2F, -13F, 29F);
      Shape7d.setTextureSize(256, 128);
      Shape7d.mirror = true;
      setRotation(Shape7d, 0F, 0F, 0F);
      Shape7f = new ModelRenderer(this, 8, 25);
      Shape7f.addBox(0F, 0.4F, 0F, 23, 1, 1);
      Shape7f.setRotationPoint(-3F, -11F, 32.4F);
      Shape7f.setTextureSize(256, 128);
      Shape7f.mirror = true;
      setRotation(Shape7f, 0F, 0F, 0F);
      Shape7h = new ModelRenderer(this, 8, 27);
      Shape7h.addBox(0F, 0F, 0F, 26, 1, 1);
      Shape7h.setRotationPoint(-4.9F, -8.9F, 36F);
      Shape7h.setTextureSize(256, 128);
      Shape7h.mirror = true;
      setRotation(Shape7h, 0F, 0F, 0F);
      Shape8a = new ModelRenderer(this, 0, 81);
      Shape8a.addBox(0F, 0F, 0F, 1, 1, 23);
      Shape8a.setRotationPoint(-2F, -13F, 4F);
      Shape8a.setTextureSize(256, 128);
      Shape8a.mirror = true;
      setRotation(Shape8a, 0F, 0F, 0F);
      Shape8b = new ModelRenderer(this, 25, 80);
      Shape8b.addBox(0F, 0F, 0F, 1, 1, 25);
      Shape8b.setRotationPoint(17.5F, -12F, 3.6F);
      Shape8b.setTextureSize(256, 128);
      Shape8b.mirror = true;
      setRotation(Shape8b, 0F, 0F, 0F);
      Shape8c = new ModelRenderer(this, 46, 9);
      Shape8c.addBox(0F, 0F, 0F, 1, 1, 34);
      Shape8c.setRotationPoint(20F, -10F, -1F);
      Shape8c.setTextureSize(256, 128);
      Shape8c.mirror = true;
      setRotation(Shape8c, 0F, 0F, 0F);
      Shape8d = new ModelRenderer(this, 46, 9);
      Shape8d.addBox(0F, 0F, 0F, 1, 1, 34);
      Shape8d.setRotationPoint(-5F, -10F, 0F);
      Shape8d.setTextureSize(256, 128);
      Shape8d.mirror = true;
      setRotation(Shape8d, 0F, 0F, 0F);
      Shape9a = new ModelRenderer(this, 75, 5);
      Shape9a.addBox(0F, 0F, 0F, 13, 1, 1);
      Shape9a.setRotationPoint(2F, -16F, 12F);
      Shape9a.setTextureSize(256, 128);
      Shape9a.mirror = true;
      setRotation(Shape9a, 0F, 0F, 0F);
      Shape9b = new ModelRenderer(this, 75, 5);
      Shape9b.addBox(0F, 0F, 0F, 13, 1, 1);
      Shape9b.setRotationPoint(2F, -16F, 15.5F);
      Shape9b.setTextureSize(256, 128);
      Shape9b.mirror = true;
      setRotation(Shape9b, 0F, 0F, 0F);
      Shape9c = new ModelRenderer(this, 75, 5);
      Shape9c.addBox(0F, 0F, 0F, 13, 1, 1);
      Shape9c.setRotationPoint(2F, -16F, 19F);
      Shape9c.setTextureSize(256, 128);
      Shape9c.mirror = true;
      setRotation(Shape9c, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, 1f, f1, f2, f3, f4, f5);
    Shape1a.render(f5);
    Shape1b.render(f5);
    Shape1c.render(f5);
    Shape1d.render(f5);
    Shape2a.render(f5);
    Shape2b.render(f5);
    Shape3a.render(f5);
    Shape3b.render(f5);
    Shape4b.render(f5);
    Shape4a.render(f5);
    Shape4c.render(f5);
    Shape4d.render(f5);
    Shape5a.render(f5);
    Shape5b.render(f5);
    Shape6a.render(f5);
    Shape6b.render(f5);
    Shape7a.render(f5);
    Shape7b.render(f5);
    Shape7c.render(f5);
    Shape7e.render(f5);
    Shape7g.render(f5);
    Shape7d.render(f5);
    Shape7f.render(f5);
    Shape7g.render(f5);
    Shape8a.render(f5);
    Shape8b.render(f5);
    Shape8c.render(f5);
    Shape8d.render(f5);
    Shape9a.render(f5);
    Shape9b.render(f5);
    Shape9c.render(f5);
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

package tektor.minecraft.chalith.renderer.oilPress;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.chalith.entity.oilPress.OilPressOutFilling;
import tektor.minecraft.chalith.model.oilPress.ModelOilPressOut;
import tektor.minecraft.chalith.model.oilPress.ModelOilPressOutFilling;

public class RenderOilPressOutFilling extends Render {

	public RenderOilPressOutFilling()
	{
		modelOilPressOutFilling = new ModelOilPressOutFilling();
	}

	protected ModelOilPressOutFilling modelOilPressOutFilling;
	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float f, float f1) {
		// Push a blank matrix onto the stack
	    GL11.glPushMatrix();
	 
	    // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
	    GL11.glTranslatef((float)x + 0.5f, (float) ((float)y + 1.5f + 0.000045 * ((OilPressOutFilling)entity).amount), (float)z + 0.5f);
	 
	    GL11.glRotatef(180f, 0f, 0f, 1f);
	    // Scale our object to about half-size in all directions (the OBJ file is a little large)
	    GL11.glScalef(1f, 1f, 1f);
		this.func_110776_a(this.func_110775_a(entity));
		this.modelOilPressOutFilling.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation("chalith:textures/entities/filling.png");
	}

}

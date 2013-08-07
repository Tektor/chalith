package tektor.minecraft.chalith.renderer.oilPress;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.chalith.entity.oilPress.OilPress;
import tektor.minecraft.chalith.entity.oilPress.OilPressPresser;
import tektor.minecraft.chalith.model.oilPress.ModelOilPressMiddle;
import tektor.minecraft.chalith.model.oilPress.ModelOilPressPresser;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderOilPressPresser extends Render {

	public RenderOilPressPresser()
	{
		modelOilPressPresser = new ModelOilPressPresser();
	}

	protected ModelOilPressPresser modelOilPressPresser;
	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float f, float f1) {
		// Push a blank matrix onto the stack
	    GL11.glPushMatrix();
	 
	    // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
	    GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f-0.0625f*((OilPressPresser)entity).rot, (float)z + 0.5f);
	 
	    GL11.glRotatef(180f,0f,0f,1f);
	    GL11.glRotatef(((OilPressPresser)entity).rot*45f, 0f, 1f, 0f);
	    // Scale our object to about half-size in all directions (the OBJ file is a little large)
	    GL11.glScalef(1f, 1f, 1f);
		this.func_110776_a(this.func_110775_a(entity));
		this.modelOilPressPresser.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation("chalith:textures/entities/oilPressBase.png");
	}

}

package tektor.minecraft.chalith.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;

import tektor.minecraft.chalith.ChalithBase;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

public class RenderShrinkPotion extends Render {
	
	public RenderShrinkPotion()
	{
		
	}

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslated(d0, d1, d2);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		this.func_110776_a(this.func_110775_a(entity));
		Tessellator tessellator = Tessellator.instance;
		this.func_77026_a(tessellator, new ItemStack(ChalithBase.shrinkPotion.itemID,1,0).getItem().getIconFromDamage(0));
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

	}
	
	private void func_77026_a(Tessellator par1Tessellator, Icon par2Icon)
	{
	float f = par2Icon.getMinU();
	float f1 = par2Icon.getMaxU();
	float f2 = par2Icon.getMinV();
	float f3 = par2Icon.getMaxV();
	float f4 = 1.0F;
	float f5 = 0.5F;
	float f6 = 0.25F;
	GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	par1Tessellator.startDrawingQuads();
	par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
	par1Tessellator.addVertexWithUV((double)(0.0F - f5), (double)(0.0F - f6), 0.0D, (double)f, (double)f3);
	par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(0.0F - f6), 0.0D, (double)f1, (double)f3);
	par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0D, (double)f1, (double)f2);
	par1Tessellator.addVertexWithUV((double)(0.0F - f5), (double)(f4 - f6), 0.0D, (double)f, (double)f2);
	par1Tessellator.draw();
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation("chalith:textures/items/shrinkpotion.png");
	}

}

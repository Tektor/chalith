package tektor.minecraft.chalith.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.MinecraftForgeClient;
import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.ChalithCommonProxy;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.ShrinkPotionEntity;
import tektor.minecraft.chalith.entity.WoodAwning;
import tektor.minecraft.chalith.entity.tileentity.TrapRuneTileEntity;
import tektor.minecraft.chalith.renderer.RenderDryStand;
import tektor.minecraft.chalith.renderer.RenderShrinkPotion;
import tektor.minecraft.chalith.renderer.RenderWoodAwning;

public class ChalithClientProxy extends ChalithCommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(DryStand.class, new RenderDryStand());
		RenderingRegistry.registerEntityRenderingHandler(ShrinkPotionEntity.class, new RenderSnowball(ChalithBase.shrinkPotion));
		RenderingRegistry.registerEntityRenderingHandler(WoodAwning.class, new RenderWoodAwning());
		
		}
}
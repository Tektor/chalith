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
import tektor.minecraft.chalith.entity.DryIsrakLeaf;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.ShrinkPotionEntity;
import tektor.minecraft.chalith.entity.WoodAwning;
import tektor.minecraft.chalith.entity.oilPress.OilPress;
import tektor.minecraft.chalith.entity.oilPress.OilPressMiddle;
import tektor.minecraft.chalith.entity.oilPress.OilPressOut;
import tektor.minecraft.chalith.entity.oilPress.OilPressOutFilling;
import tektor.minecraft.chalith.entity.oilPress.OilPressPresser;
import tektor.minecraft.chalith.entity.oilPress.OilPressStair;
import tektor.minecraft.chalith.entity.tileentity.TrapRuneTileEntity;
import tektor.minecraft.chalith.renderer.RenderDryIsrakLeaf;
import tektor.minecraft.chalith.renderer.RenderDryStand;
import tektor.minecraft.chalith.renderer.RenderShrinkPotion;
import tektor.minecraft.chalith.renderer.RenderWoodAwning;
import tektor.minecraft.chalith.renderer.oilPress.RenderOilPress;
import tektor.minecraft.chalith.renderer.oilPress.RenderOilPressMiddle;
import tektor.minecraft.chalith.renderer.oilPress.RenderOilPressOut;
import tektor.minecraft.chalith.renderer.oilPress.RenderOilPressOutFilling;
import tektor.minecraft.chalith.renderer.oilPress.RenderOilPressPresser;
import tektor.minecraft.chalith.renderer.oilPress.RenderOilPressStair;

public class ChalithClientProxy extends ChalithCommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(DryStand.class, new RenderDryStand());
		RenderingRegistry.registerEntityRenderingHandler(ShrinkPotionEntity.class, new RenderSnowball(ChalithBase.shrinkPotion));
		RenderingRegistry.registerEntityRenderingHandler(WoodAwning.class, new RenderWoodAwning());
		RenderingRegistry.registerEntityRenderingHandler(DryIsrakLeaf.class, new RenderDryIsrakLeaf());
		RenderingRegistry.registerEntityRenderingHandler(OilPress.class, new RenderOilPress());
		RenderingRegistry.registerEntityRenderingHandler(OilPressStair.class, new RenderOilPressStair());
		RenderingRegistry.registerEntityRenderingHandler(OilPressOut.class, new RenderOilPressOut());
		RenderingRegistry.registerEntityRenderingHandler(OilPressMiddle.class, new RenderOilPressMiddle());
		RenderingRegistry.registerEntityRenderingHandler(OilPressPresser.class, new RenderOilPressPresser());
		RenderingRegistry.registerEntityRenderingHandler(OilPressOutFilling.class, new RenderOilPressOutFilling());
		
		}
}
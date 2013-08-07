package tektor.minecraft.chalith.gui;

import java.util.List;

import tektor.minecraft.chalith.container.ChalithWorkplaceContainer;
import tektor.minecraft.chalith.container.DryStandContainer;
import tektor.minecraft.chalith.container.OilPressContainer;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.oilPress.OilPress;
import tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ChalithGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		if (id == 0) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof ChalithWorkplaceTileEntity) {
				return new ChalithWorkplaceContainer(player.inventory,
						(ChalithWorkplaceTileEntity) tileEntity);
			}
		}
		if (id == 1) {
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class,
					AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
			if (!list.isEmpty()) {
				for (Entity ent : list) {
					if (ent instanceof DryStand) {
						return new DryStandContainer(player.inventory,
								(DryStand) ent);
					}
				}

			}
		}
		if (id == 2) {
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class,
					AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
			if (!list.isEmpty()) {
				for (Entity ent : list) {
					if (ent instanceof OilPress) {
						return new OilPressContainer(player.inventory,
								(OilPress) ent);
					}
				}

			}
		}
		return null;
	}

	// returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		if (id == 0) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof ChalithWorkplaceTileEntity) {
				return new ChalithWorkplaceGui(player, player.inventory,
						(ChalithWorkplaceTileEntity) tileEntity);
			}
		}
		if (id == 1) {
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class,
					AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
			if (!list.isEmpty()) {
				for (Entity ent : list) {
					if (ent instanceof DryStand) {
						return new DryStandGui(player.inventory, (DryStand) ent);
					}
				}

			}
		}
		if (id == 2) {
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class,
					AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
			if (!list.isEmpty()) {
				for (Entity ent : list) {
					if (ent instanceof OilPress) {
						return new OilPressGui(player.inventory, (OilPress) ent);
					}
				}

			}
		}
		return null;

	}

}

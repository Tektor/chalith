package tektor.minecraft.chalith.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class FireTrapRune extends Item {

	public FireTrapRune(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		func_111206_d("chalith:fireTrapRune");
	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		if (par7 == 0) {
			return false;
		} else if (par7 == 1) {
			return false;
		} else {
			int i1 = Direction.facingToDirection[par7];
			EntityHanging entityhanging = this.createHangingEntity(par3World,
					par4, par5, par6, i1);

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
					par1ItemStack)) {
				return false;
			} else {
				if (entityhanging != null && entityhanging.onValidSurface()) {
					if (!par3World.isRemote) {
						par3World.spawnEntityInWorld(entityhanging);
					}

					--par1ItemStack.stackSize;
				}

				return true;
			}
		}
	}

	/**
	 * Create the hanging entity associated to this item.
	 */
	private EntityHanging createHangingEntity(World par1World, int par2,
			int par3, int par4, int par5) {
		return new EntityPainting(par1World, par2, par3, par4, par5);
	}

}

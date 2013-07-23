package tektor.minecraft.chalith.items;

import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.entity.FireTrapRuneTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class FireTrapRune extends Item {

	Icon itemIcon;

	public FireTrapRune(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(false);
		this.setMaxStackSize(1);
		func_111206_d("chalith:fireTrapRune");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setUnlocalizedName("fireTrapRune2");
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		if (par7 == 0) {
			return false;
		} else if (!par3World.getBlockMaterial(par4, par5, par6).isSolid()) {
			return false;
		} else {
			if (par7 == 1) {
				++par5;
			}

			if (par7 == 2) {
				--par6;
			}

			if (par7 == 3) {
				++par6;
			}

			if (par7 == 4) {
				--par4;
			}

			if (par7 == 5) {
				++par4;
			}

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
					par1ItemStack)) {
				return false;
			} else if (par3World.isRemote) {
				return true;
			} else {

				par3World.setBlock(par4, par5, par6,
						ChalithBase.fireTrapRune.blockID, par7, 3);

				--par1ItemStack.stackSize;
				
				System.out.println("++++++++++++++++++++test++++++++++++++++++" + par4 + par5 + par6);
				FireTrapRuneTileEntity entity = (FireTrapRuneTileEntity) par3World
						.getBlockTileEntity(par4, par5, par6);
				if (entity != null) {
					System.out.println("++++++++++++++++++++test++++++++++++++++++");
					entity.owner = par2EntityPlayer.getEntityName();
					System.out.println(entity.owner);
				}

				return true;
			}
		}
	}
}

package tektor.minecraft.chalith.items;

import java.util.List;

import tektor.minecraft.chalith.entity.DryIsrakLeaf;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.OilPress;
import tektor.minecraft.chalith.entity.WoodAwning;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class EntityPlacer extends Item {

	private Icon[] icon = new Icon[3];

	public EntityPlacer(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:dryStand");
		icon[1] = par1IconRegister.registerIcon("chalith:woodAwning");
		icon[2] = par1IconRegister.registerIcon("chalith:oilPress");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "dryStand";
		case 1:
			return "woodAwning";
		case 2:
			return "oilPress";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Dry Stand";
		case 1:
			return "Wood Awning";
		case 2:
			return "Oil Press";
		default:
			return "??";
		}
	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {
			--par1ItemStack.stackSize;
			if (par1ItemStack.getItemDamage() == 0) {
				DryStand stand = new DryStand(par3World);
				stand.setLocationAndAngles(par4, par5 + 1, par6, 0.0F, 0.0F);

				par3World.spawnEntityInWorld(stand);
			} else if (par1ItemStack.getItemDamage() == 1) {
				WoodAwning aw = new WoodAwning(par3World);
				aw.setLocationAndAngles(par4, par5 + 1, par6, 0.0F, 0.0F);

				par3World.spawnEntityInWorld(aw);
			} else if (par1ItemStack.getItemDamage() == 2) {
				OilPress aw = new OilPress(par3World);
				aw.setLocationAndAngles(par4, par5 + 1, par6, 0.0F, 0.0F);
				aw.createSubEntities(par3World);
				par3World.spawnEntityInWorld(aw);
			}

		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		// subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
	}

}

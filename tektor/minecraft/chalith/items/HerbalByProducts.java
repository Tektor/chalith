package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.chalith.ChalithBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class HerbalByProducts extends Item{

	Icon[] icon = new Icon[3];

	public HerbalByProducts(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabFood);
		setUnlocalizedName("herbalByProduct");
		this.setHasSubtypes(true);

	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		int id = stack.getItemDamage();
		return icon[id];
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icon[par1];
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "israkLeaf";
		case 1:
			return "utaniNut";
		case 2:
			return "driedIsrakLeaf";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "Israk Leaf";
		case 1:
			return "Utani Nut";
		case 2:
			return "Dried Israk Leaf";
		default:
			return "??";
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:israkLeaf");
		icon[1] = par1IconRegister.registerIcon("chalith:utaniNut");
		icon[2] = par1IconRegister.registerIcon("chalith:israkLeafDried");
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
	}

}

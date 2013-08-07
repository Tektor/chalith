package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.model.AdvancedModelLoader;

public class CraftingStuff extends Item{

	Icon[] icon = new Icon[5];

	public CraftingStuff(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("craftingStuff");
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
			return "stringGrid";
		case 1:
			return "utaniNutOilBottle";
		case 2:
			return "oilPressOut";
		case 3: return "oilPressPresser";
		case 4: return "oilPressMiddle";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "String Grid";
		case 1:
			return "Utani Nut Oil Bottle";
		case 2: return "Oil Press Basin";
		case 3: return "Oil Press Presser";
		case 4: return "Oil Press Middle";
		default:
			return "??";
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:stringGrid");
		icon[1] = par1IconRegister.registerIcon("chalith:utaniNutOilBottle");
		icon[2] = par1IconRegister.registerIcon("chalith:oilPressBasin");
		icon[3] = par1IconRegister.registerIcon("chalith:oilPressPresser");
		icon[4] = par1IconRegister.registerIcon("chalith:oilPressMiddle");
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
		subItems.add(new ItemStack(this, 1, 4));
	}

}

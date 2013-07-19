package tektor.minecraft.chalith.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseRune extends Item{

	public BaseRune(int id) {
		super(id);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("baseRune");
		func_111206_d("chalith:baseRune");
	}

}

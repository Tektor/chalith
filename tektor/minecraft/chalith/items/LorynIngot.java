package tektor.minecraft.chalith.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LorynIngot extends Item {

	public LorynIngot(int id) {
		super(id);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("lorynIngot");
		func_111206_d("chalith:lorynIngot");
	}

}

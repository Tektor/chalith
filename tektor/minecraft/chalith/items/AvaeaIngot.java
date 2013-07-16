package tektor.minecraft.chalith.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AvaeaIngot extends Item{

	public AvaeaIngot(int id) {
		super(id);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("avaeaIngot");
		func_111206_d("chalith:avaeaIngot");
	}

}

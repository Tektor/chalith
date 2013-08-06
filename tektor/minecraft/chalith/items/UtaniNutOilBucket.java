package tektor.minecraft.chalith.items;

import tektor.minecraft.chalith.ChalithBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.ItemFluidContainer;

public class UtaniNutOilBucket extends ItemBucket{

	public UtaniNutOilBucket(int itemID) {
		super(itemID, ChalithBase.utaniNutOilBlock.blockID);
		this.setCreativeTab(CreativeTabs.tabMisc);
		// TODO Auto-generated constructor stub
	}
	public UtaniNutOilBucket(int itemID, int capacity)
    {
        super(itemID,ChalithBase.utaniNutOilBlock.blockID);
    }
	
}

package tektor.minecraft.chalith.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;

public class LorynOre extends BlockOre{

	public LorynOre(int id) {
		super(id);
		// TODO Auto-generated constructor stub
		setHardness(3.2F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("lorynOre");
		setCreativeTab(CreativeTabs.tabBlock);
		func_111022_d("chalith:lorynOre");
	}

}

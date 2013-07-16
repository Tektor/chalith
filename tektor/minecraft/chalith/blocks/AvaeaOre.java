package tektor.minecraft.chalith.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;

public class AvaeaOre extends BlockOre{

	public AvaeaOre(int id) {
		super(id);

		setHardness(2.2F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("avaeaOre");
		setCreativeTab(CreativeTabs.tabBlock);
		func_111022_d("chalit:avaeaOre");
	}
	
	

}

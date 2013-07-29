package tektor.minecraft.chalith.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Bloodstone extends Block{

	public Bloodstone(int id) {
		super(id,Material.rock);

		setHardness(2.2F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("bloodstone");
		setCreativeTab(CreativeTabs.tabBlock);
		func_111022_d("chalith:bloodstone");
	}
	
	

}

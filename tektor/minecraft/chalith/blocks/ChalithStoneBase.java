package tektor.minecraft.chalith.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ChalithStoneBase extends Block{

	private Icon[] icon = new Icon[4];
	public ChalithStoneBase(int id) {
		super(id,Material.rock);
		setHardness(2.2F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("Stone");
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:bloodstone");
		icon[1] = par1IconRegister.registerIcon("chalith:bloodstoneCobble");
		icon[2] = par1IconRegister.registerIcon("chalith:corinnstone");
		icon[3] = par1IconRegister.registerIcon("chalith:corinnstoneCobble");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1,
	           int par2)
	{
		return icon[par2];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
	}
	
	@Override
	public int damageDropped(int par1)
	{
		 switch(par1)
		 {
		 case 0:
		 case 1: return 1;
		 case 2:
		 case 3: return 3;
		 default: return 3;
		 }
	}
	
	

}

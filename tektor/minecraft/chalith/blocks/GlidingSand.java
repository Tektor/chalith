package tektor.minecraft.chalith.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GlidingSand extends Block {

	Icon icon;
	public GlidingSand(int par1) {
		super(par1, Material.sand);
		setHardness(1.2F);
		setStepSound(Block.soundSandFootstep);
		setUnlocalizedName("glidingSand");
		setCreativeTab(CreativeTabs.tabBlock);
		this.slipperiness = 1.1f;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon = par1IconRegister.registerIcon("chalith:glidingSand");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1,
	           int par2)
	{
		return icon;
	}
	
	@Override
	public int damageDropped(int par1)
	{
		 return 0;
	}
	
	

}

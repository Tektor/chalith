package tektor.minecraft.chalith.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.chalith.entity.ChalithWorkplaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ChalithWorkplaces extends BlockContainer{

	private Icon[] icon = new Icon[3];
	public ChalithWorkplaces(int id) {
		super(id,Material.rock);
		setHardness(4.2F);
		setUnlocalizedName("workplace");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:runeWorkbenchTop");
		icon[1] = par1IconRegister.registerIcon("chalith:runeWorkbenchSide");
		icon[2] = par1IconRegister.registerIcon("chalith:runeWorkbenchBottom");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1,
	           int par2)
	{
		if(par2 == 0)
		{
			switch(par1){
			case 0: return icon[0];
			case 1:return icon[1];
			case 2:return icon[2];}
		}
		return icon[0];
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new ChalithWorkplaceTileEntity();
	}

}

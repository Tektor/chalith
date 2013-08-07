package tektor.minecraft.chalith.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidUtaniNutOil extends BlockFluidClassic{

	Icon[] icons = new Icon[2];
	public BlockFluidUtaniNutOil(int id, Fluid fluid) {
		super(id, fluid, new MaterialLiquid(MapColor.foliageColor));
		this.setCreativeTab(CreativeTabs.tabBlock);
		
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons[0] = (par1IconRegister.registerIcon("chalith:utaniNutOilStill"));
		icons[1] = (par1IconRegister.registerIcon("chalith:utaniNutOilFlowing"));
		getFluid().setIcons( icons[0], icons[1] );
	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon( int side, int meta )
	{
		if ( side <= 1 )
			return icons[0];
		else
			return icons[1];
	}
	

}

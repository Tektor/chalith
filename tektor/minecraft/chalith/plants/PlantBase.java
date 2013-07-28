package tektor.minecraft.chalith.plants;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class PlantBase extends Block{

	private Icon[] icon = new Icon[2];
	
	public PlantBase(int par1) {
		super(par1, Material.plants);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F , 1.5F, 1.0F);
		this.setTickRandomly(true);
		
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }
       
    public int getRenderType() {
        return 6;
    }
   
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:israkPlant");
		icon[1] = par1IconRegister.registerIcon("chalith:israkPlantGrown");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icon[meta];
	}
	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(world.getBlockMetadata(x,y,z) == 1) return;
		if(world.getLightBrightness(x, y, z)<9) return;
		int grow = 25 - world.getBlockMetadata(x, y-1, z);
		if(random.nextInt(25) == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		}
		else return;
	}

}

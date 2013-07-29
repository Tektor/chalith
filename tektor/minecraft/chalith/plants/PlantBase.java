package tektor.minecraft.chalith.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tektor.minecraft.chalith.ChalithBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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
		if(world.getBlockLightValue(x, y+1, z)<9) return;
		int grow = 25 - world.getBlockMetadata(x, y-1, z);
		if(random.nextInt(grow) == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		}
		else return;
	}
	
    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return ChalithBase.seedBase.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
        return ChalithBase.herbalByProduct.itemID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

        if (metadata == 1)
        {
            for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(5) <= metadata)
                {
                    ret.add(new ItemStack(this.getSeedItem(), 1, 0));
                }
            }
        }

        return ret;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 == 1 ? this.getCropItem() : this.getSeedItem();
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(1)+1;
    }

}

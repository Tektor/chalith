package tektor.minecraft.chalith.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.entity.FireTrapRuneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFireTrapRune extends Block {

	private Icon icon;

	public BlockFireTrapRune(int par1) {
		super(par1, Material.rock);
		setUnlocalizedName("fireTrapRuneBlock");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon = par1IconRegister.registerIcon("chalith:fireTrapRune");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icon;
	}

	// And this tell it that you can see through this block, and neighbor blocks
	// should be rendered.
	public boolean isOpaqueCube() {
		return false;
	}
    
	public TileEntity createTileEntity(World world, int metadata) {
		return new FireTrapRuneTileEntity();
	}
	
	/**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float f2 = 0.0F;
		float f3 = 1.0F;
		float f5 = 0.003F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		if (l == 1) {
			this.setBlockBounds(f2, f2, f2, f3, f5, f3);
		}
		if (l == 2) {
			this.setBlockBounds(f2, f2, 1.0F - f5, f3, f3, f3);
		}

		if (l == 3) {
			this.setBlockBounds(f2, f2, f2, f3, f3, f5);
		}

		if (l == 4) {
			this.setBlockBounds(1.0F - f5, f2, f2, f3, f3, f3);
		}

		if (l == 5) {
			this.setBlockBounds(f2, f2, f2, f5, f3, f3);
		}
	}
	
    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
    
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return true;
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ChalithBase.fireTrapRune2.itemID;
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        boolean flag = true;
        int i1 = par1World.getBlockMetadata(par2, par3, par4);

            if (i1 == 1 && par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid())
            {
                flag = false;
            }
     

            if (i1 == 2 && par1World.getBlockMaterial(par2, par3, par4 + 1).isSolid())
            {
                flag = false;
            }

            if (i1 == 3 && par1World.getBlockMaterial(par2, par3, par4 - 1).isSolid())
            {
                flag = false;
            }

            if (i1 == 4 && par1World.getBlockMaterial(par2 + 1, par3, par4).isSolid())
            {
                flag = false;
            }

            if (i1 == 5 && par1World.getBlockMaterial(par2 - 1, par3, par4).isSolid())
            {
                flag = false;
            }
        

        if (flag)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

}

package tektor.minecraft.chalith.blocks;

import tektor.minecraft.chalith.entity.GateBlockTileEntity;
import tektor.minecraft.chalith.entity.TrapRuneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class GateBlock extends BlockContainer {

	public GateBlock(int par1) {
		super(par1, Material.portal);
		setUnlocalizedName("gateBlock");
		func_111022_d("chalith:gateBlock");
		this.setBlockUnbreakable();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new GateBlockTileEntity();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {

		return new GateBlockTileEntity();
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity)
	{
		GateBlockTileEntity ent = (GateBlockTileEntity) par1World.getBlockTileEntity(par2, par3, par4);
		if (ent != null)
		{
			par5Entity.setWorld(WorldProvider.getProviderForDimension(ent.dimension).worldObj);
			par5Entity.setPosition(ent.goalX, ent.goalY, ent.goalZ);
		}
	}

}

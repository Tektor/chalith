package tektor.minecraft.chalith.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFireTrapRune extends EntityHanging{
	
	public int uses;

	public EntityFireTrapRune(World par1World) {
		super(par1World);
	}
	public EntityFireTrapRune(World par1World, int par2, int par3, int par4, int par5)
    {
		super(par1World, par2, par3, par4, par5);
		this.setDirection(par5);
		
    }
    
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("Uses", this.uses);
        super.writeEntityToNBT(par1NBTTagCompound);
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	uses = par1NBTTagCompound.getInteger("Uses");
    	super.readEntityFromNBT(par1NBTTagCompound);
    }

	@Override
	public int func_82329_d() {
		return 16;
	}

	@Override
	public int func_82330_g() {
		return 16;
	}

	@Override
	public void func_110128_b(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}

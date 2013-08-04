package tektor.minecraft.chalith.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class DryStand extends Entity {

	public DryStand(World par1World, EntityPlayer par3EntityPlayer) {
		super(par1World);
	}
	
	public DryStand(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		this.posX = nbt.getDouble("posX");
		this.posY = nbt.getDouble("posY");
		this.posZ = nbt.getDouble("posZ");
		

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setDouble("posX", posX);
		nbt.setDouble("posY", posY);
		nbt.setDouble("posZ", posZ);

	}

	@Override
	public boolean func_130002_c(EntityPlayer par1EntityPlayer) {
		return true;
	}

}

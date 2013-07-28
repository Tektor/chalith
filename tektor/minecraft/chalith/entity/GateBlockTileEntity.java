package tektor.minecraft.chalith.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class GateBlockTileEntity extends TileEntity {

	public int dimension;
	public double goalX;
	public double goalY;
	public double goalZ;
	public int restTime;
	
	public GateBlockTileEntity()
	{
		restTime = 300;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("dimension", dimension);
		par1.setDouble("goalX",goalX);
		par1.setDouble("goalY",goalY);
		par1.setDouble("goalZ",goalZ);
		par1.setInteger("restTime", restTime);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		dimension = par1.getInteger("dimension");
		goalX = par1.getDouble("goalX");
		goalY = par1.getDouble("goalY");
		goalZ = par1.getDouble("goalZ");
		restTime = par1.getInteger("restTime");
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
	}
	
	@Override
	public boolean canUpdate()
	{
		return true;
	}
	
	@Override
	public void updateEntity()
	{
		restTime--;
		if(restTime == 0)
		{
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			worldObj.markTileEntityForDespawn(this);
		}
	}

	public void setGoal(double double1, double double2, double double3, int d) {
		this.goalX = double1;
		this.goalY = double2;
		this.goalZ = double3;
		this.dimension = d;
		
	}
}

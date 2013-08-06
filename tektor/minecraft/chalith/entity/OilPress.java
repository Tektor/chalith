package tektor.minecraft.chalith.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.container.DryStandContainer;
import tektor.minecraft.chalith.container.OilPressContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OilPress extends Entity implements IInventory{

	OilPressStair stair;
	OilPressOut out;
	OilPressMiddle middle;
	OilPressPresser presser;
	public OilPressContainer container;

	public OilPress(World par1World) {
		super(par1World);

		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.setSize(2f, 0.875f);
		

	}

	public OilPress(World par1World, int par2, int par3, int par4) {
		super(par1World);
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.setSize(2f, 0.875f);
	}

	public void createSubEntities(World par1World) {
		if (!worldObj.isRemote) {
			stair = new OilPressStair(par1World, this);
			stair.setPosition(posX, posY, posZ - 1);
			par1World.spawnEntityInWorld(stair);

			out = new OilPressOut(par1World, this);
			out.setPosition(posX + 1, posY, posZ);
			par1World.spawnEntityInWorld(out);
			
			middle = new OilPressMiddle(par1World, this);
			middle.setPosition(posX - 0.5D, posY + 0.3125D, posZ + 0.5D);
			par1World.spawnEntityInWorld(middle);
			
			presser = new OilPressPresser(par1World, this);
			presser.setPositionParent(posX - 0.5D, posY + 0.875D, posZ + 0.5D);
			par1World.spawnEntityInWorld(presser);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return null;
	}

	public void applyEntityCollision(Entity par1Entity) {
	}

	public float getCollisionBorderSize() {
		return 0.0F;
	}

	protected void entityInit() {
	}

	@SideOnly(Side.CLIENT)
	protected void preparePlayerToSpawn() {

	}

	private float func_70517_b(int par1) {
		return par1 == 32 ? 0.5F : (par1 == 64 ? 0.5F : 0.0F);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		super.onUpdate();

		if (stair == null) {
			if (!worldObj.isRemote) {
				stair = new OilPressStair(worldObj, this);
				stair.setPosition(posX, posY, posZ - 1);
				worldObj.spawnEntityInWorld(stair);
			}
		}

		if (out == null && !worldObj.isRemote) {
			out = new OilPressOut(worldObj, this);
			out.setPosition(posX + 1, posY, posZ);
			worldObj.spawnEntityInWorld(out);
		}
		if (middle == null && !worldObj.isRemote) {
			middle = new OilPressMiddle(worldObj, this);
			middle.setPosition(posX - 0.5D, posY + 0.3125D, posZ + 0.5D);
			worldObj.spawnEntityInWorld(middle);
		}
		if (presser == null && !worldObj.isRemote) {
			presser = new OilPressPresser(worldObj, this);
			presser.setPositionParent(posX - 0.5D, posY + 0.875D, posZ + 0.5D);
			worldObj.spawnEntityInWorld(presser);
		}

	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	public boolean canBeCollidedWith() {
		return true;
	}

	public boolean func_85031_j(Entity par1Entity) {
		return par1Entity instanceof EntityPlayer ? this
				.attackEntityFrom(DamageSource
						.causePlayerDamage((EntityPlayer) par1Entity), 0.0F)
				: false;
	}

	/**
	 * Tries to moves the entity by the passed in displacement. Args: x, y, z
	 */
	public void moveEntity(double par1, double par3, double par5) {
		if (!this.worldObj.isRemote && !this.isDead
				&& par1 * par1 + par3 * par3 + par5 * par5 > 0.0D) {
			this.setDead();
			this.func_110128_b((Entity) null);
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else {
			if (!this.isDead && !this.worldObj.isRemote) {
				
				this.worldObj.removeEntity(stair);
				this.worldObj.removeEntity(out);
				this.worldObj.removeEntity(middle);
				this.worldObj.removeEntity(presser);
				this.setDead();
				this.setBeenAttacked();
				this.func_110128_b(par1DamageSource.getEntity());
			}

			return true;
		}
	}

	/**
	 * Adds to the current velocity of the entity. Args: x, y, z
	 */
	public void addVelocity(double par1, double par3, double par5) {
		if (!this.worldObj.isRemote && !this.isDead
				&& par1 * par1 + par3 * par3 + par5 * par5 > 0.0D) {
			this.setDead();
			this.func_110128_b((Entity) null);
		}
	}

	public int func_82329_d() {
		return 32;
	}

	public int func_82330_g() {
		return 14;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound nbt) {

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound nbt) {

	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		stair = (OilPressStair) worldObj.getEntityByID(nbt.getInteger("stair"));
		out = (OilPressOut) worldObj.getEntityByID(nbt.getInteger("out"));
		middle = (OilPressMiddle) worldObj.getEntityByID(nbt.getInteger("middle"));
		presser = (OilPressPresser) worldObj.getEntityByID(nbt.getInteger("presser"));

	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("stair", this.stair.entityId);
		nbt.setInteger("out", this.out.entityId);
		nbt.setInteger("middle", this.middle.entityId);
		nbt.setInteger("presser", this.presser.entityId);

	}

	protected boolean func_142008_O() {
		return false;
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;

			if (entityplayer.capabilities.isCreativeMode) {

				return;
			}
		}

		this.entityDropItem(new ItemStack(ChalithBase.entityPlacer, 1, 2), 0.0F);
	}

	public boolean func_130002_c(EntityPlayer player) {
		player.openGui(ChalithBase.instance, 2, worldObj, (int) posX,
				(int) posY, (int) posZ);
		return true;
	}

	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float f = this.width / 2.0F;
		float f1 = this.height;
		this.boundingBox.setBounds(par1 - f, par3 - (double) this.yOffset
				+ (double) this.ySize, par5 - f + 1D, par1 + (double) f, par3
				- (double) this.yOffset + (double) this.ySize + (double) f1,
				par5 + (double) f + 1D);

	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
}

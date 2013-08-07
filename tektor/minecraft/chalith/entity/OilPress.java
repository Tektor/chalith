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
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.container.DryStandContainer;
import tektor.minecraft.chalith.container.OilPressContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OilPress extends Entity implements IInventory, IFluidTank {

	OilPressStair stair;
	OilPressOut out;
	OilPressMiddle middle;
	OilPressPresser presser;
	OilPressOutFilling fill;
	public OilPressContainer container;
	public boolean isLocked = false;
	private ItemStack[] inv = new ItemStack[1];
	private int amount;
	public int pressings;
	private FluidStack fluidStack;

	public OilPress(World par1World) {
		super(par1World);

		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.setSize(2f, 0.875f);
		amount = 0;
		pressings = 0;

	}

	public OilPress(World par1World, int par2, int par3, int par4) {
		super(par1World);
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.setSize(2f, 0.875f);
		amount = 0;

	}

	public void createSubEntities(World par1World) {
		if (!worldObj.isRemote) {
			stair = (OilPressStair) this.worldObj.findNearestEntityWithinAABB(
					OilPressStair.class, boundingBox.getBoundingBox(posX + 1,
							posY + 1, posZ + 1, posX - 1, posY - 1, posZ - 1),
					this);
			if (stair == null) {
				stair = new OilPressStair(par1World, this);
				stair.setPosition(posX, posY, posZ - 1);
				par1World.spawnEntityInWorld(stair);
			}
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
		dataWatcher.addObject(30, 0);

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
		if (this.worldObj.isRemote) {
			isLocked = this.dataWatcher.getWatchableObjectInt(30) > 0 ? true
					: false;
		}
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
		if (!this.worldObj.isRemote) {
			if (this.getFluidAmount() > 0) {
				if (this.fill == null) {
					fill = new OilPressOutFilling(worldObj, this);
					fill.setPositionParent(this.posX + 1, this.posY + 0.125D,
							this.posZ);
					fill.setAmount(this.getFluidAmount());
					worldObj.spawnEntityInWorld(fill);
				} else {
					fill.setPositionParent(this.posX + 1, this.posY + 0.125D,
							this.posZ);
					fill.setAmount(this.getFluidAmount());
				}
			} else if (!(this.getFluidAmount() > 0) && this.fill != null) {
				this.fill.setDead();
				this.fill = null;
			}
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
				this.worldObj.removeEntity(fill);
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
		middle = (OilPressMiddle) worldObj.getEntityByID(nbt
				.getInteger("middle"));
		presser = (OilPressPresser) worldObj.getEntityByID(nbt
				.getInteger("presser"));

		NBTTagList tagList = nbt.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.isLocked = nbt.getBoolean("lock");
		int i = isLocked ? 1 : 0;
		this.dataWatcher.updateObject(30, i);
		this.amount = nbt.getInteger("amount");
		this.pressings = nbt.getInteger("pressings");
		if (!nbt.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

			if (fluid != null) {
				setFluid(fluid);
			}
		}

	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("stair", this.stair.entityId);
		nbt.setInteger("out", this.out.entityId);
		nbt.setInteger("middle", this.middle.entityId);
		nbt.setInteger("presser", this.presser.entityId);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", itemList);
		nbt.setBoolean("lock", this.isLocked);
		nbt.setInteger("amount", this.amount);
		nbt.setInteger("pressings", pressings);
		if (fluidStack != null) {
			fluidStack.writeToNBT(nbt);
		} else {
			nbt.setString("Empty", "");
		}
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
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(posX + 0.5, posY + 0.5, posZ + 0.5) < 64;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public String getInvName() {
		return "chalith.oilPress";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return true;
	}

	public void pressing() {
		if (!this.worldObj.isRemote && this.container != null && this.isLocked) {
			this.fill(new FluidStack(ChalithBase.utaniNutOil, amount * 20),
					true);
			this.pressings++;
			this.presser.rotate(pressings);
			if (pressings > 8) {
				this.amount = 0;
				this.isLocked = false;
				this.dataWatcher.updateObject(30, 0);
				this.pressings = 0;
				this.presser.rotate(0);
			}

		} else if (!this.worldObj.isRemote
				&& (this.inv[0] != null
						&& this.inv[0].itemID == ChalithBase.herbalByProduct.itemID && this.inv[0]
						.getItemDamage() == 1)) {
			this.isLocked = true;
			this.dataWatcher.updateObject(30, 1);
			this.amount = this.inv[0].stackSize;
			this.inv[0] = null;
			this.fill(new FluidStack(ChalithBase.utaniNutOil, amount * 20),
					true);
			this.pressings = 1;
			this.presser.rotate(1);
		}

	}

	@Override
	public FluidStack getFluid() {
		return fluidStack;
	}

	@Override
	public int getFluidAmount() {
		if (fluidStack != null)
			return this.fluidStack.amount;
		else
			return 0;
	}

	@Override
	public int getCapacity() {
		return 10000;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(fluidStack, getCapacity());
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		if (!doFill) {
			if (fluidStack == null) {
				return Math.min(getCapacity(), resource.amount);
			}

			if (!fluidStack.isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(getCapacity() - fluidStack.amount, resource.amount);
		}

		if (fluidStack == null) {
			fluidStack = new FluidStack(resource, Math.min(getCapacity(),
					resource.amount));

			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluidStack,
					this.worldObj, (int) this.posX, (int) this.posY,
					(int) this.posZ, this));

			return fluidStack.amount;
		}

		if (!fluidStack.isFluidEqual(resource)) {
			return 0;
		}
		int filled = getCapacity() - fluidStack.amount;

		if (resource.amount < filled) {
			fluidStack.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluidStack.amount = getCapacity();
		}

		FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluidStack,
				this.worldObj, (int) this.posX, (int) this.posY,
				(int) this.posZ, this));

		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluidStack == null) {
			return null;
		}

		int drained = maxDrain;
		if (fluidStack.amount < drained) {
			drained = fluidStack.amount;
		}

		FluidStack stack = new FluidStack(fluidStack, drained);
		if (doDrain) {
			fluidStack.amount -= drained;
			if (fluidStack.amount <= 0) {
				fluidStack = null;
			}

			FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluidStack,
					this.worldObj, (int) this.posX, (int) this.posY,
					(int) this.posZ, this));

		}
		return stack;
	}

	private void setFluid(FluidStack fluid) {
		this.fluidStack = fluid;

	}
}

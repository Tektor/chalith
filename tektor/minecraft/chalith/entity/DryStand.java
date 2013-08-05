package tektor.minecraft.chalith.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.container.DryStandContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class DryStand extends Entity implements IInventory {

	private int tickCounter1;
	private boolean calledOnce;
	public int hangingDirection;
	public ItemStack[] inv = new ItemStack[2];
	public DryStandContainer container;

	public DryStand(World par1World) {
		super(par1World);

		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.setSize(0.40f, 2.0f);
		this.stepHeight = 0;
		calledOnce = false;

	}

	public DryStand(World par1World, int par2, int par3, int par4) {
		super(par1World);
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.setSize(0.4f, 2.0f);
		this.stepHeight = 0;
		calledOnce = false;
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
		
		Random rand = worldObj.rand;
		if(!worldObj.isRemote && rand.nextInt(500) == 0)
		{
			if(this.inv[0] != null && inv[1] != null && inv[1].stackSize < this.getInventoryStackLimit())
			{
				this.decrStackSize(0, 1);
				this.inv[1].stackSize++;
			}
			else if(this.inv[0] != null && inv[1] == null)
			{
				if(inv[0].getItem().itemID == ChalithBase.herbalByProduct.itemID)
				{
					switch(inv[0].getItemDamage())
					{
					case 0: inv[1] = new ItemStack(ChalithBase.herbalByProduct.itemID,1,2);
					break;
					
					}
					this.decrStackSize(0, 1);
				}
			}
		}

	}

	public void onEntityUpdate() {

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
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList tagList = nbt.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < inv.length) {
                        inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
        }
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
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
	}

	protected boolean func_142008_O() {
		return false;
	}

	public int func_82329_d() {
		return 14;
	}

	public int func_82330_g() {
		return 32;
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;

			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}

		this.entityDropItem(new ItemStack(ChalithBase.entityPlacer,1,0), 0.0F);
	}

	public boolean func_130002_c(EntityPlayer player) {

		player.openGui(ChalithBase.instance, 1, worldObj, (int)posX, (int)posY, (int)posZ);

		return true;
	}

	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float f = this.width / 2.0F;
		float f1 = this.height;
		this.boundingBox.setBounds(par1 - f + 0.5D, par3
				- (double) this.yOffset + (double) this.ySize, par5 - f + 0.5D,
				par1 + (double) f + 0.5D, par3 - (double) this.yOffset
						+ (double) this.ySize + (double) f1, par5 + (double) f
						+ 0.5D);

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
    public void openChest() {}

    @Override
    public void closeChest() {}
    
    @Override
    public String getInvName() {
            return "chalith.dryStand";
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
}

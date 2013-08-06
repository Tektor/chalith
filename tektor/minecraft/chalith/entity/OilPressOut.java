package tektor.minecraft.chalith.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OilPressOut extends Entity {

	OilPress parent;

	public OilPressOut(World par1World) {
		super(par1World);

		this.ignoreFrustumCheck = true;
		this.noClip = true;
		this.stepHeight = 0;
		this.isDead = false;
		this.setSize(2f, 0.875f);

	}

	public OilPressOut(World par1World, int par2, int par3, int par4) {
		super(par1World);
		this.ignoreFrustumCheck = true;
		this.noClip = true;
		this.setSize(2f, 0.875f);
	}

	public OilPressOut(World worldObj, OilPress press) {
		this(worldObj);
		this.parent = press;
		this.isDead = false;
	}
	
	 protected void setSize(float par1, float par2)
	    {
	        float f2;

	        if (par1 != this.width || par2 != this.height)
	        {
	            f2 = this.width;
	            this.width = par1;
	            this.height = par2;
	            this.boundingBox.maxX = this.boundingBox.minX + (double)this.width/2.0D;
	            this.boundingBox.maxZ = this.boundingBox.minZ + (double)this.width;
	            this.boundingBox.maxY = this.boundingBox.minY + (double)this.height;

	            if (this.width > f2 && !this.worldObj.isRemote)
	            {
	                this.moveEntity((double)(f2 - this.width/2.0D), 0.0D, (double)(f2 - this.width));
	            }
	        }

	        f2 = par1 % 2.0F;

	        if ((double)f2 < 0.375D)
	        {
	            this.myEntitySize = EnumEntitySize.SIZE_1;
	        }
	        else if ((double)f2 < 0.75D)
	        {
	            this.myEntitySize = EnumEntitySize.SIZE_2;
	        }
	        else if ((double)f2 < 1.0D)
	        {
	            this.myEntitySize = EnumEntitySize.SIZE_3;
	        }
	        else if ((double)f2 < 1.375D)
	        {
	            this.myEntitySize = EnumEntitySize.SIZE_4;
	        }
	        else if ((double)f2 < 1.75D)
	        {
	            this.myEntitySize = EnumEntitySize.SIZE_5;
	        }
	        else
	        {
	            this.myEntitySize = EnumEntitySize.SIZE_6;
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
		if (parent == null && !worldObj.isRemote) {
			this.setDead();
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

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		parent = (OilPress) worldObj.getEntityByID(nbt.getInteger("parent"));

	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("parent",parent.entityId);
	}

	protected boolean func_142008_O() {
		return false;
	}

	public int func_82329_d() {
		return 32;
	}

	public int func_82330_g() {
		return 14;
	}

	public void func_110128_b(Entity par1Entity) {
	}


	public void setPosition(double par1, double par3, double par5) {

		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float f = this.width / 2.0F;
		float f2 = this.width/ 4.0F;
		float f1 = this.height;
		this.boundingBox.setBounds(par1 - f2 +0.5D , par3
				- (double) this.yOffset + (double) this.ySize, par5 - f + 1D,
				par1 + (double) f2 +0.5D, par3 - (double) this.yOffset
						+ (double) this.ySize + (double) f1, par5 + (double) f
						+ 1D);
	}
}

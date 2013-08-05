package tektor.minecraft.chalith.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tektor.minecraft.chalith.ChalithBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DryIsrakLeaf extends Entity {


	private DryStand parent;


	public DryIsrakLeaf(World par1World) {
		super(par1World);

		this.ignoreFrustumCheck = true;
		this.noClip = true;
		this.stepHeight = 0;
		this.setSize(0.1f, 0.1f);

	}

	public DryIsrakLeaf(World par1World, int par2, int par3, int par4) {
		super(par1World);
		this.ignoreFrustumCheck = true;
		this.noClip = true;
		this.setSize(0.1f, 0.1f);
	}
	


	public DryIsrakLeaf(World worldObj, DryStand dryStand) {
		this(worldObj);
		this.parent = dryStand;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return null;
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
		if(parent == null && !worldObj.isRemote)
		{
			this.setDead();
		}
		}

	public void onEntityUpdate() {

	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	public boolean canBeCollidedWith() {
		return false;
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
		
		
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
	}

	protected boolean func_142008_O() {
		return false;
	}

	public int func_82329_d() {
		return 16;
	}

	public int func_82330_g() {
		return 16;
	}

	public void func_110128_b(Entity par1Entity) {
		}
	
	public void setPositionParent(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;

	}


	public void setPosition(double par1, double par3, double par5) {
		

	}
}
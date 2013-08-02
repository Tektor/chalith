package tektor.minecraft.chalith.entity;

import java.util.Iterator;
import java.util.List;

import tektor.minecraft.chalith.ChalithBase;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ShrinkPotionEntity extends EntityThrowable {

	public ShrinkPotionEntity(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);

	}

	public ShrinkPotionEntity(World par1World) {
		super(par1World);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {

		if (!this.worldObj.isRemote) {
			AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 1.0D,
					4.0D);
			List list1 = this.worldObj.getEntitiesWithinAABB(
					EntityLivingBase.class, axisalignedbb);
			if (list1 != null && !list1.isEmpty()) {
				Iterator iterator = list1.iterator();

				while (iterator.hasNext()) {
					EntityLivingBase entitylivingbase = (EntityLivingBase) iterator
							.next();
					double d0 = this.getDistanceSqToEntity(entitylivingbase);

					if (d0 < 16.0D) {
						double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

						if (entitylivingbase == movingobjectposition.entityHit) {
							d1 = 1.0D;
						}

						ItemStack stack = new ItemStack(
								ChalithBase.shrinkStatue, 1, 0);
						stack.stackTagCompound = new NBTTagCompound();
						if (entitylivingbase instanceof EntityPig) {
							stack.setItemDamage(0);
							stack.stackTagCompound.setInteger("age",
									((EntityPig) entitylivingbase).getGrowingAge());
							entitylivingbase.entityDropItem(stack, 0.0f);
							entitylivingbase.setDead();
							break;
						}
						else if(entitylivingbase instanceof EntitySheep) {
							stack.setItemDamage(1);
							stack.stackTagCompound.setInteger("age",
									((EntityAgeable) entitylivingbase).getGrowingAge());
							EntitySheep sheep = (EntitySheep) entitylivingbase;
							stack.stackTagCompound.setBoolean("sheared",sheep.getSheared());
							stack.stackTagCompound.setInteger("fleece",sheep.getFleeceColor());
							entitylivingbase.entityDropItem(stack, 0.0f);
							entitylivingbase.setDead();
							break;
						}
						else if(entitylivingbase instanceof EntityChicken) {
							stack.setItemDamage(2);
							stack.stackTagCompound.setInteger("age",
									((EntityAgeable) entitylivingbase).getGrowingAge());
							entitylivingbase.entityDropItem(stack, 0.0f);
							entitylivingbase.setDead();
							break;
						}
						else if(entitylivingbase instanceof EntityCow) {
							stack.setItemDamage(3);
							stack.stackTagCompound.setInteger("age",
									((EntityAgeable) entitylivingbase).getGrowingAge());
							entitylivingbase.entityDropItem(stack, 0.0f);
							entitylivingbase.setDead();
							break;
						}
						else if(entitylivingbase instanceof EntityWolf) {
							stack.setItemDamage(4);
							stack.stackTagCompound.setInteger("age",
									((EntityAgeable) entitylivingbase).getGrowingAge());
							EntityWolf wolf = (EntityWolf) entitylivingbase;
							stack.stackTagCompound.setString("owner",wolf.getOwnerName());
							stack.stackTagCompound.setBoolean("tamed",wolf.isTamed());
							entitylivingbase.entityDropItem(stack, 0.0f);
							entitylivingbase.setDead();
							break;
							
						}
						else if(entitylivingbase instanceof EntityHorse) {
							stack.setItemDamage(5);
							stack.stackTagCompound.setInteger("age",
									((EntityAgeable) entitylivingbase).getGrowingAge());
							EntityHorse horse = (EntityHorse) entitylivingbase;
							stack.stackTagCompound.setBoolean("EatingHaystack", horse.func_110204_cc());
							stack.stackTagCompound.setBoolean("ChestedHorse", horse.func_110261_ca());
							stack.stackTagCompound.setBoolean("HasReproduced", horse.func_110243_cf());
							stack.stackTagCompound.setBoolean("Bred", horse.func_110205_ce());
							stack.stackTagCompound.setInteger("Type", horse.func_110265_bP());
							stack.stackTagCompound.setInteger("Variant", horse.func_110202_bQ());
							stack.stackTagCompound.setInteger("Temper", horse.func_110252_cg());
							stack.stackTagCompound.setBoolean("Tame", horse.func_110248_bS());
							stack.stackTagCompound.setString("OwnerName", horse.func_142019_cb());
							entitylivingbase.entityDropItem(stack, 0.0f);
							entitylivingbase.setDead();
							break;
							
						}

					}
				}

			}
		}
		this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX),
				(int) Math.round(this.posY), (int) Math.round(this.posZ), 1);
		this.setDead();

	}

	protected float getGravityVelocity() {
		return 0.05F;
	}

	protected float func_70182_d() {
		return 0.5F;
	}

	protected float func_70183_g() {
		return -20.0F;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
	}

}

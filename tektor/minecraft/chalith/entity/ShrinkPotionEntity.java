package tektor.minecraft.chalith.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
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

		AxisAlignedBB axisalignedbb = this.boundingBox.expand(1.0D, 1.0D, 1.0D);
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

					// entitylivingbase.entityDropItem(par1ItemStack, par2)

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

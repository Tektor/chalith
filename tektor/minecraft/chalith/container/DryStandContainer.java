package tektor.minecraft.chalith.container;

import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DryStandContainer extends Container {

	protected DryStand ent;

	public DryStandContainer(InventoryPlayer inventoryPlayer, DryStand e) {
		ent = e;
		e.container = this;
		addSlotToContainer(new Slot(this.ent, 0, 50, 36));
		ItemStack[] slot2 = new ItemStack[0];
		addSlotToContainer(new RestrictingSlot(this.ent, 1, 111, 36, slot2, null));
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return ent.isUseableByPlayer(entityplayer);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks
		if (slotObject != null) {
			ItemStack stackInSlot = slotObject.getStack();
			if (stackInSlot != null) {
				stack = stackInSlot.copy();
				// merges the item into player inventory since its in the
				// tileEntity
				if (slot < 2) {
					if (!this.mergeItemStack(stackInSlot, 2, 38, true)) {
						return null;
					}
				}
				// places it into the tileEntity is possible since its in the
				// player
				// inventory
				else if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
					return null;
				}
				if (stackInSlot.stackSize == 0) {
					slotObject.putStack(null);
				} else {
					slotObject.onSlotChanged();
				}
				if (stackInSlot.stackSize == stack.stackSize) {
					return null;
				}
				slotObject.onPickupFromSlot(player, stackInSlot);
			}
		}

		return stack;
	}

	@Override
	protected boolean mergeItemStack(ItemStack par1ItemStack, int par2,
			int par3, boolean par4) {
		boolean success = false;
		for (int i = par2; i < par3; i++) {
			if (!this.getSlot(i).isItemValid(par1ItemStack)) {
				continue;
			}
			success = success
					|| super.mergeItemStack(par1ItemStack, i, i + 1, par4);
		}
		return success;

	}

}

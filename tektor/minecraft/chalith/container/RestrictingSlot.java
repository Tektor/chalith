package tektor.minecraft.chalith.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class RestrictingSlot extends Slot{

	private ItemStack[] items;
	private ChalithWorkplaceContainer container;
	public RestrictingSlot(IInventory par1iInventory, int par2, int par3,
			int par4, ItemStack[] item, ChalithWorkplaceContainer cont) {
		super(par1iInventory, par2, par3, par4);
		items = item;
		container = cont;
	}
	
	@Override
	   public boolean isItemValid(ItemStack itemstack) {
	      for(ItemStack item: items)
	      {
	    	  if(item.itemID == itemstack.itemID && item.getItemDamage() == itemstack.getItemDamage()) return true;
	      }
	      return false;
	   }
	
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
		if(container != null)
		{
			if (container.lastOp) {
				container.inputSlots.setInventorySlotContents(0, null);
			} else {
				container.inputSlots.setInventorySlotContents(1, null);
			}
    }
    }

}

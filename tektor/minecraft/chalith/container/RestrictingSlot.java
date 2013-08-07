package tektor.minecraft.chalith.container;

import tektor.minecraft.chalith.entity.oilPress.OilPress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class RestrictingSlot extends Slot{

	private boolean lockable = false;
	private ItemStack[] items;
	private ChalithWorkplaceContainer workPlaceContainer;
	private Container container;
	public RestrictingSlot(IInventory par1iInventory, int par2, int par3,
			int par4, ItemStack[] item, ChalithWorkplaceContainer cont) {
		super(par1iInventory, par2, par3, par4);
		items = item;
		workPlaceContainer = cont;
	}
	
	public RestrictingSlot(IInventory ent, int i, int j, int k,
			ItemStack[] slot2, Container object, boolean b) {
		this(ent,i,j,k,slot2,null);
		lockable = b;
		container = object;
	}

	@Override
	   public boolean isItemValid(ItemStack itemstack) {
		if(lockable && container instanceof OilPressContainer)
		{
			if(!((OilPressContainer)container).ent.isLocked)
			{
				for(ItemStack item: items)
			      {
			    	  if(item.itemID == itemstack.itemID && item.getItemDamage() == itemstack.getItemDamage()) return true;
			      }
				return false;
			}
			else return false;
		}
		else
	      for(ItemStack item: items)
	      {
	    	  if(item.itemID == itemstack.itemID && item.getItemDamage() == itemstack.getItemDamage()) return true;
	      }
	      return false;
	   }
	
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
		if(workPlaceContainer != null)
		{
			if (workPlaceContainer.lastOp) {
				workPlaceContainer.inputSlots.setInventorySlotContents(0, null);
			} else {
				workPlaceContainer.inputSlots.setInventorySlotContents(1, null);
			}
    }
    }

}

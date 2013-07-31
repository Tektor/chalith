package tektor.minecraft.chalith.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class RestrictingSlot extends Slot{

	private ItemStack[] items;
	public RestrictingSlot(IInventory par1iInventory, int par2, int par3,
			int par4, ItemStack[] item) {
		super(par1iInventory, par2, par3, par4);
		items = item;
	}
	
	@Override
	   public boolean isItemValid(ItemStack itemstack) {
	      for(ItemStack item: items)
	      {
	    	  if(item.itemID == itemstack.itemID && item.getItemDamage() == itemstack.getItemDamage()) return true;
	      }
	      return false;
	   }

}

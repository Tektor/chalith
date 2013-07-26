package tektor.minecraft.chalith.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemStackFactory {

	
	public static ItemStack getItemStack(ItemStack stack, String name, int meta)
	{
		stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setInteger(name, meta);
		return stack;
	}
}

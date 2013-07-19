package tektor.minecraft.chalith.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.world.World;

public class RecallRune extends Item {

	public RecallRune(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("recallRune");
		func_111206_d("chalith:recallRune");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (par1ItemStack.stackTagCompound != null) {
			par3EntityPlayer.setPosition(
					par1ItemStack.stackTagCompound.getDouble("x"),
					par1ItemStack.stackTagCompound.getDouble("y"),
					par1ItemStack.stackTagCompound.getDouble("z"));

		} else {
			par1ItemStack.stackTagCompound = new NBTTagCompound();
			par1ItemStack.stackTagCompound.setDouble("x",par3EntityPlayer.posX);
			par1ItemStack.stackTagCompound.setDouble("y",par3EntityPlayer.posY);
			par1ItemStack.stackTagCompound.setDouble("z",par3EntityPlayer.posZ);
		}
		return par1ItemStack;

	}

}

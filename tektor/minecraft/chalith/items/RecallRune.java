package tektor.minecraft.chalith.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecallRune extends Item{

	private double x,y,z;
	private boolean placed;
	public RecallRune(int id) {
		super(id);
		placed = false;
		x = y = z = 0.0;
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("recallRune");
		func_111206_d("chalith:recallRune");
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack,
            World par2World,
            EntityPlayer par3EntityPlayer)
	{
		if(placed)
		{
			par3EntityPlayer.setPosition(x, y, z);
			
		}
		else
		{
			x = par3EntityPlayer.posX;
			y = par3EntityPlayer.posY;
			z = par3EntityPlayer.posZ;
			System.out.println("Teleported to: " + x + " " + y + " " + z);
			placed = true;
		}
		return par1ItemStack;
		
	}

}

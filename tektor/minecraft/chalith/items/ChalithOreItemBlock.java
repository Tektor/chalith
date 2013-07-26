package tektor.minecraft.chalith.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ChalithOreItemBlock extends ItemBlock{

	public ChalithOreItemBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name = "";
		
		switch(stack.getItemDamage())
		{
		case 0: name = "Loryn Ore";break;
		case 1: name = "Sorfyn Ore";break;
		}
		return name;
		
	}
	
	public int getMetadata(int met)
	{
		return met;
	}

}

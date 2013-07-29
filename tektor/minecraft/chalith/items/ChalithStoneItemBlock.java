package tektor.minecraft.chalith.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ChalithStoneItemBlock extends ItemBlock{

	public ChalithStoneItemBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name = "";
		
		switch(stack.getItemDamage())
		{
		case 0: name = "Bloodstone";break;
		case 1: name = "Bloodstone Cobble";break;
		case 2: name = "Corinnstone";break;
		case 3: name = "Corinnstone Cobble";break;
		}
		return name;
		
	}
	
	public int getMetadata(int met)
	{
		return met;
	}
}

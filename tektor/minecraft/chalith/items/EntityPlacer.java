package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class EntityPlacer extends Item{
	
	private Icon[] icon = new Icon[1];

	public EntityPlacer(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return icon[par1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:dryStand");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		switch(stack.getItemDamage())
		{
		case 0: return "dryStand";
		default: return "??";
		}
		
	}
	@Override
 	public String getItemDisplayName(ItemStack par1ItemStack)
 	{
 		switch(par1ItemStack.getItemDamage())
 		{
 		case 0: return "Dry Stand";
		default: return "??";
 		}
 	}
	
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		return true;
	}
	
	
@SideOnly(Side.CLIENT)
@Override
public void getSubItems(int par1, CreativeTabs tab, List subItems) {
	
		subItems.add(new ItemStack(this, 1, 0));
}

}

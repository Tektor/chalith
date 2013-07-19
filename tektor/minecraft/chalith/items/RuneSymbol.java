package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class RuneSymbol extends Item{
	
	public static final String[] runeSymbolNames = new String[] {"Di", "In", "Nom", "Xen", "Yok", "Al", "Bor", "Ces", "Gof", "Hir", "La", "On", "Te", "Vo", "Za", "Wel"};
	private Icon[] icons;
	
	public RuneSymbol(int id) {
		super(id);
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("runeSymbol");
        this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 15);
        return this.icons[j];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + runeSymbolNames[i];
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 16; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[runeSymbolNames.length];

        for (int i = 0; i < runeSymbolNames.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("chalith:runeSymbol" + "_" + runeSymbolNames[i]);
        }
    }

}

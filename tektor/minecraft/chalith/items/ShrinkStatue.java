package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ShrinkStatue extends Item {

	private Icon[] icon = new Icon[2];

	public ShrinkStatue(int par1) {
		super(par1);
		setMaxStackSize(1);
		setUnlocalizedName("shrinkStatue");
		this.setHasSubtypes(false);
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		return icon[stack.getItemDamage()];
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icon[par1];
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:pigStatue");
		icon[1] = par1IconRegister.registerIcon("chalith:sheepStatue");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.stackTagCompound != null) {
			String s = "";
			switch (par1ItemStack.getItemDamage()) {
			case 0:
				s = "Pig";
				break;
			case 1:
				s = "Sheep";
				break;
			}
			par3List.add("Type: " + s);
			par3List.add("Age: "
					+ par1ItemStack.stackTagCompound.getInteger("age"));
		}

	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par3EntityPlayer, World par2World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		if (!par3EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
				par1ItemStack)) {
			return false;
		}
		if (!par2World.isRemote) {
			if (!par3EntityPlayer.capabilities.isCreativeMode) {
				--par1ItemStack.stackSize;
			}
			EntityAnimal ea = null;
			switch(par1ItemStack.getItemDamage())
						{
			case 0: ea = new EntityPig(par2World);break;
			case 1: ea = new EntitySheep(par2World);break;
			}
			ea.setGrowingAge(par1ItemStack.stackTagCompound.getInteger("age"));
			ea.setLocationAndAngles(par4, par5+1, par6, 0.0F, 0.0F);
			par2World.spawnEntityInWorld(ea);
		}
		return true;
	}

}

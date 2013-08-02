package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ShrinkStatue extends Item {

	private static String[] fleeceColor = { "White", "Orange", "Magenta",
			"Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray",
			"Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };

	private Icon[] icon = new Icon[6];

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
		icon[2] = par1IconRegister.registerIcon("chalith:chickenStatue");
		icon[3] = par1IconRegister.registerIcon("chalith:cowStatue");
		icon[4] = par1IconRegister.registerIcon("chalith:wolfStatue");
		icon[5] = par1IconRegister.registerIcon("chalith:horseStatue");
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
			case 2:
				s = "Chicken";
				break;
			case 3:
				s = "Cow";
				break;
			case 4:
				s = "Wolf";
				break;
			case 5:
				s = "Horse";
				break;
			}
			par3List.add("Type: " + s);
			if(s.equals("Sheep"))
			{
				String c;
				
				par3List.add("Color: " + fleeceColor[par1ItemStack.stackTagCompound.getInteger("fleece")]);
			}
			else if(s.equals("Wolf"))
			{
				par3List.add("Owner: " + par1ItemStack.stackTagCompound.getString("owner"));
			}
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
			switch (par1ItemStack.getItemDamage()) {
			case 0:
				ea = new EntityPig(par2World);
				break;
			case 1:
				ea = new EntitySheep(par2World);
				break;
			case 2:
				ea = new EntityChicken(par2World);
				break;
			case 3: 
				ea = new EntityCow(par2World);
				break;
			case 4:
				ea = new EntityWolf(par2World);
				break;
			case 5:
				ea = new EntityHorse(par2World);
				break;
			}
			ea.setGrowingAge(par1ItemStack.stackTagCompound.getInteger("age"));
			// special values
			if (ea instanceof EntitySheep) {
				((EntitySheep) ea).setSheared(par1ItemStack.stackTagCompound
						.getBoolean("sheared"));
				((EntitySheep) ea)
						.setFleeceColor(par1ItemStack.stackTagCompound
								.getInteger("fleece"));
			}
			else if(ea instanceof EntityWolf)
			{
				((EntityWolf) ea).setTamed(true);
				((EntityWolf) ea).setOwner(par1ItemStack.stackTagCompound.getString("owner"));
			}
			else if(ea instanceof EntityHorse)
			{
				 ((EntityHorse) ea).func_110227_p(par1ItemStack.stackTagCompound.getBoolean("EatingHaystack"));
				((EntityHorse) ea).func_110242_l(par1ItemStack.stackTagCompound.getBoolean("Bred"));
				((EntityHorse) ea).func_110207_m(par1ItemStack.stackTagCompound.getBoolean("ChestedHorse"));
				((EntityHorse) ea).func_110221_n(par1ItemStack.stackTagCompound.getBoolean("HasReproduced"));
				((EntityHorse) ea).func_110214_p(par1ItemStack.stackTagCompound.getInteger("Type"));
				((EntityHorse) ea).func_110235_q(par1ItemStack.stackTagCompound.getInteger("Variant"));
				((EntityHorse) ea).func_110238_s(par1ItemStack.stackTagCompound.getInteger("Temper"));
				((EntityHorse) ea).func_110234_j(par1ItemStack.stackTagCompound.getBoolean("Tame"));
				((EntityHorse) ea).func_110213_b(par1ItemStack.stackTagCompound.getString("OwnerName"));
			}

			ea.setLocationAndAngles(par4, par5 + 1, par6, 0.0F, 0.0F);
			par2World.spawnEntityInWorld(ea);
		}
		return true;
	}

}

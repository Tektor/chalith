package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.chalith.ChalithBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class SeedBase extends Item implements IPlantable {

	Icon[] icon = new Icon[2];

	public SeedBase(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabFood);
		setUnlocalizedName("seedBase");
		this.setHasSubtypes(true);

	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		int id = stack.getItemDamage();
		return icon[id];
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
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return ChalithBase.plantBase.blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "israkRoot";
		case 1:
			return "utaniSapling";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "Israk Root";
		case 1: 
			return "Utani Sapling";
		default:
			return "??";
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:israkRoot");
		icon[1] = par1IconRegister.registerIcon("chalith:utaniSapling");
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int bx, int by, int bz, int side, float px, float py,
			float pz) {
		if (side != 1 || !(player.canPlayerEdit(bx, by, bz, side, itemStack))
				|| world.getBlockId(bx, by, bz) != 60
				|| world.getBlockId(bx, by + 1, bz) != 0) {
			return false;
		} else {
			if (itemStack.getItemDamage() == 0) {
				world.setBlock(bx, by + 1, bz, ChalithBase.plantBase.blockID,
						0, 3);
			}
			else if(itemStack.getItemDamage() == 1)
			{
				world.setBlock(bx, by + 1, bz, ChalithBase.plantBase.blockID,
						2, 3);
			}
			itemStack.stackSize--;
			return true;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}

}

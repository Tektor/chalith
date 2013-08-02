package tektor.minecraft.chalith.items;

import tektor.minecraft.chalith.entity.ShrinkPotionEntity;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ShrinkPotion extends Item {

	private Icon icon;
	public ShrinkPotion(int par1) {
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabBrewing);
		setUnlocalizedName("shrinkPotion");
		this.setHasSubtypes(false);
		this.func_111206_d("chalith:shrinkpotion");
	}
	
	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		int id = stack.getItemDamage();
		return icon;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icon;
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icon;
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon = par1IconRegister.registerIcon("chalith:shrinkpotion");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
				0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!par2World.isRemote) {
			par2World.spawnEntityInWorld(new ShrinkPotionEntity(par2World,
					par3EntityPlayer));
		}

		return par1ItemStack;
	}
}

package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.entity.GateBlockTileEntity;
import tektor.minecraft.chalith.util.ItemStackFactory;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class UtilRune extends Item {

	private int timer;

	private Icon[] icon = new Icon[4];

	public UtilRune(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("recallRune");
		timer = 0;
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
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "recallRune";
		case 1:
		case 2:
			return "inventoryStatusRune";
		case 3:
			return "gateRune";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "Recall Rune";
		case 1:
		case 2:
			return "Rune of Inventory";
		case 3:
			return "Gate Rune";
		default:
			return "??";
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:recallRune");
		icon[1] = par1IconRegister.registerIcon("chalith:inventoryRune");
		icon[2] = par1IconRegister.registerIcon("chalith:inventoryRuneAct");
		icon[3] = par1IconRegister.registerIcon("chalith:gateRune");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		int id = par1ItemStack.getItemDamage();
		if (0 == id) {
			if (timer == 0) {
				if ((!(par1ItemStack.stackTagCompound==null))&&par1ItemStack.stackTagCompound.getBoolean("active")) {
					if (par3EntityPlayer.inventory
							.consumeInventoryItem(ChalithBase.avaeaIngot.itemID)) {
						par3EntityPlayer.setPositionAndUpdate(
								par1ItemStack.stackTagCompound.getDouble("x"),
								par1ItemStack.stackTagCompound.getDouble("y"),
								par1ItemStack.stackTagCompound.getDouble("z"));

					} else {
						par3EntityPlayer
								.addChatMessage("Not enough material to use this.");
					}
					timer = 100;

				} else {
					par1ItemStack.stackTagCompound = new NBTTagCompound();
					par1ItemStack.stackTagCompound.setDouble("x",
							par3EntityPlayer.posX);
					par1ItemStack.stackTagCompound.setDouble("y",
							par3EntityPlayer.posY);
					par1ItemStack.stackTagCompound.setDouble("z",
							par3EntityPlayer.posZ);
					par1ItemStack.stackTagCompound.setBoolean("active", true);
				}
			}
		} else if (3 == id) {
			if ((!(par1ItemStack.stackTagCompound==null))&&par1ItemStack.stackTagCompound.getBoolean("active")) {
				if (par3EntityPlayer.inventory
						.consumeInventoryItem(ChalithBase.lorynIngot.itemID)) {
					int startx, starty, startz;
					int startdimension = par3EntityPlayer.dimension;
					startx = (int) par3EntityPlayer.posX;
					starty = (int) par3EntityPlayer.posY;
					startz = (int) par3EntityPlayer.posZ;
					int goalx = (int) par1ItemStack.stackTagCompound
							.getDouble("x");
					int goaly = (int) par1ItemStack.stackTagCompound
							.getDouble("y");
					int goalz = (int) par1ItemStack.stackTagCompound
							.getDouble("z");
					// set Position
					par3EntityPlayer.dimension = par1ItemStack.stackTagCompound
							.getInteger("dimension");
					par3EntityPlayer.setPositionAndUpdate(goalx, goaly, goalz);
					// place start gate
					par2World.setBlock(startx, starty, startz,
							ChalithBase.gateBlock.blockID);
					par2World.setBlock(startx, starty + 1, startz,
							ChalithBase.gateBlock.blockID);
					GateBlockTileEntity ent = (GateBlockTileEntity) par2World
							.getBlockTileEntity(startx, starty, startz);
					GateBlockTileEntity ent2 = (GateBlockTileEntity) par2World
							.getBlockTileEntity(startx, starty+1, startz);

					ent.setGoal(par1ItemStack.stackTagCompound.getDouble("x"),
							par1ItemStack.stackTagCompound.getDouble("y"),
							par1ItemStack.stackTagCompound.getDouble("z"),
							par1ItemStack.stackTagCompound
									.getInteger("dimension"));
					ent2.setGoal(par1ItemStack.stackTagCompound.getDouble("x"),
							par1ItemStack.stackTagCompound.getDouble("y"),
							par1ItemStack.stackTagCompound.getDouble("z"),
							par1ItemStack.stackTagCompound
									.getInteger("dimension"));
					// place endgate
					boolean placed =false;
					boolean placed2 = false;
					if (par2World.getBlockId(goalx + 1, goaly, goalz) == 0
							&& par2World
									.getBlockId(goalx + 1, goaly + 1, goalz) == 0) {
						par2World.setBlock(goalx + 1, goaly, goalz,
								ChalithBase.gateBlock.blockID);
						par2World.setBlock(goalx + 1, goaly + 1, goalz,
								ChalithBase.gateBlock.blockID);
						goalx++;
						placed = true;
					} else if (par2World.getBlockId(goalx - 1, goaly, goalz) == 0
							&& par2World
									.getBlockId(goalx - 1, goaly + 1, goalz) == 0) {
						par2World.setBlock(goalx - 1, goaly, goalz,
								ChalithBase.gateBlock.blockID);
						par2World.setBlock(goalx - 1, goaly + 1, goalz,
								ChalithBase.gateBlock.blockID);
						placed = true;
						goalx--;
					}
					else if (par2World.getBlockId(goalx, goaly, goalz+1) == 0
							&& par2World
									.getBlockId(goalx, goaly + 1, goalz+1) == 0) {
						par2World.setBlock(goalx, goaly, goalz+1,
								ChalithBase.gateBlock.blockID);
						par2World.setBlock(goalx, goaly + 1, goalz+1,
								ChalithBase.gateBlock.blockID);
						placed = true;
						goalz++;
					}
					else if (par2World.getBlockId(goalx, goaly, goalz-1) == 0
							&& par2World
									.getBlockId(goalx, goaly + 1, goalz-1) == 0) {
						par2World.setBlock(goalx, goaly, goalz-1,
								ChalithBase.gateBlock.blockID);
						par2World.setBlock(goalx, goaly + 1, goalz-1,
								ChalithBase.gateBlock.blockID);
						placed = true;
						goalz--;
					}
					if (par2World.getBlockId(startx + 1, starty, startz) == 0
							&& par2World
									.getBlockId(startx + 1, starty + 1, startz) == 0) {
						startx++;
						placed2 = true;
					} else if (par2World.getBlockId(startx - 1, starty, startz) == 0
							&& par2World
									.getBlockId(startx - 1, starty + 1, startz) == 0) {
						
						startx--;
						placed2 = true;
					}
					else if (par2World.getBlockId(startx, starty, startz+1) == 0
							&& par2World
									.getBlockId(startx, starty + 1, startz+1) == 0) {
						placed2 = true;
						startz++;
					}
					else if (par2World.getBlockId(startx, starty,startz-1) == 0
							&& par2World
									.getBlockId(startx, starty + 1, startz-1) == 0) {
						placed2 = true;
						startz--;
					}
					
					if(placed && placed2)
					{
						
						ent = (GateBlockTileEntity) par2World
								.getBlockTileEntity(goalx, goaly, goalz);
						ent2 = (GateBlockTileEntity) par2World
								.getBlockTileEntity(goalx, goaly+1, goalz);

						ent.setGoal(startx,
								starty,
								startz,
								startdimension);
						ent2.setGoal(startx,
								starty,
								startz,
								startdimension);
					}
				} else {
					par3EntityPlayer
							.addChatMessage("Not enough material to use this.");
				}
				timer = 100;
			} else {
				
				par1ItemStack.stackTagCompound = new NBTTagCompound();
				par1ItemStack.stackTagCompound.setDouble("x",
						par3EntityPlayer.posX);
				par1ItemStack.stackTagCompound.setDouble("y",
						par3EntityPlayer.posY);
				par1ItemStack.stackTagCompound.setDouble("z",
						par3EntityPlayer.posZ);
				par1ItemStack.stackTagCompound.setInteger("dimension",
						par3EntityPlayer.dimension);
				par1ItemStack.stackTagCompound.setBoolean("active", true);

			}

		}
		return par1ItemStack;

	}

	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		int id = par1ItemStack.getItemDamage();
		if (id == 0) {
			if (timer > 0) {
				timer--;
			}
		} else if (id == 1 || id == 2) {
			if (par3Entity instanceof EntityPlayer) {
				int firstFree = ((EntityPlayer) par3Entity).inventory
						.getFirstEmptyStack();
				if (firstFree == -1) {
					par1ItemStack.setItemDamage(2);
				} else {
					par1ItemStack.setItemDamage(1);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 3));
	}

}

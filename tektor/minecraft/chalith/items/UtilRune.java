package tektor.minecraft.chalith.items;

import java.util.List;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.entity.GateBlockTileEntity;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldProvider;

public class UtilRune extends Item {

	private int timer;

	private Icon[] icon = new Icon[4];

	public UtilRune(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("recallRune");
		this.setHasSubtypes(true);
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
		if (stack.stackTagCompound == null
				|| stack.stackTagCompound.getString("name").equals("")) {
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
		} else {
			return stack.stackTagCompound.getString("name");
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("chalith:recallRune");
		icon[1] = par1IconRegister.registerIcon("chalith:inventoryRune");
		icon[2] = par1IconRegister.registerIcon("chalith:inventoryRuneAct");
		icon[3] = par1IconRegister.registerIcon("chalith:recallGateRune");
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par3EntityPlayer, World par2World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		int id = par1ItemStack.getItemDamage();

		if (0 == id) {
			if (timer == 0) {
				if ((!(par1ItemStack.stackTagCompound == null))
						&& par1ItemStack.stackTagCompound.getBoolean("active")) {
					ItemStack[] inv = par3EntityPlayer.inventory.mainInventory;
					boolean foundA = false;
					boolean foundB = false;
					int first = 0;
					int second = 0;
					for (int i = 0; i < 32; i++) {
						// Israk Root
						if (inv[i] != null
								&& inv[i].itemID == ChalithBase.seedBase.itemID
								&& inv[i].getItemDamage() == 0) {
							foundA = true;
							first = i;
						}
						// Israk Leaf
						else if (inv[i] != null
								&& inv[i].itemID == ChalithBase.herbalByProduct.itemID
								&& inv[i].getItemDamage() == 0) {
							foundB = true;
							second = i;
						}
						if (foundA && foundB)
							break;
					}

					if (foundA && foundB) {
						par3EntityPlayer.inventory.decrStackSize(first, 1);
						par3EntityPlayer.inventory.decrStackSize(second, 1);
						if (!par2World.isRemote) {

							int goalx = (int) par1ItemStack.stackTagCompound
									.getDouble("x");
							int goaly = (int) par1ItemStack.stackTagCompound
									.getDouble("y");
							int goalz = (int) par1ItemStack.stackTagCompound
									.getDouble("z");
							int startdimension = par3EntityPlayer.dimension;

							// set Position
							EntityPlayerMP player = (EntityPlayerMP) par3EntityPlayer;
							if (startdimension != par1ItemStack.stackTagCompound
									.getInteger("dimension")) {
								player.mcServer
										.getConfigurationManager()
										.transferPlayerToDimension(
												player,
												par1ItemStack.stackTagCompound
														.getInteger("dimension"));
								par3EntityPlayer.setPositionAndUpdate(goalx,
										goaly, goalz);
							} else {
								par3EntityPlayer.setPositionAndUpdate(goalx,
										goaly, goalz);
							}

						}
					} else {
						par3EntityPlayer
								.addChatMessage("Not enough material to use this.");
					}

				} else {
					par1ItemStack.stackTagCompound = new NBTTagCompound();
					par1ItemStack.stackTagCompound.setInteger("dimension",
							par3EntityPlayer.dimension);
					par1ItemStack.stackTagCompound.setDouble("x",
							par3EntityPlayer.posX);
					par1ItemStack.stackTagCompound.setDouble("y",
							par3EntityPlayer.posY);
					par1ItemStack.stackTagCompound.setDouble("z",
							par3EntityPlayer.posZ);
					par1ItemStack.stackTagCompound.setBoolean("active", true);
				}
			}
		} else if (3 == id && timer == 0) {
			if ((!(par1ItemStack.stackTagCompound == null))
					&& par1ItemStack.stackTagCompound.getBoolean("active")) {
				ItemStack[] inv = par3EntityPlayer.inventory.mainInventory;
				boolean foundA = false;
				boolean foundB = false;
				int first = 0;
				int second = 0;
				for (int i = 0; i < 32; i++) {
					// Israk Root
					if (inv[i] != null
							&& inv[i].itemID == ChalithBase.seedBase.itemID
							&& inv[i].getItemDamage() == 0) {
						foundA = true;
						first = i;
					}
					// Israk Leaf
					else if (inv[i] != null
							&& inv[i].itemID == ChalithBase.herbalByProduct.itemID
							&& inv[i].getItemDamage() == 0) {
						foundB = true;
						second = i;
					}
					if (foundA && foundB)
						break;
				}

				if (foundA && foundB) {
					par3EntityPlayer.inventory.decrStackSize(first, 1);
					par3EntityPlayer.inventory.decrStackSize(second, 1);
					if (!par2World.isRemote) {
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
						EntityPlayerMP player = (EntityPlayerMP) par3EntityPlayer;
						if (startdimension != par1ItemStack.stackTagCompound
								.getInteger("dimension")) {
							player.mcServer.getConfigurationManager()
									.transferPlayerToDimension(
											player,
											par1ItemStack.stackTagCompound
													.getInteger("dimension"));
							par3EntityPlayer.setPositionAndUpdate(goalx, goaly,
									goalz);
						} else {
							par3EntityPlayer.setPositionAndUpdate(goalx, goaly,
									goalz);
						}
						timer = 10;

						// place start gate
						par2World.setBlock(startx, starty, startz,
								ChalithBase.gateBlock.blockID);
						par2World.setBlock(startx, starty + 1, startz,
								ChalithBase.gateBlock.blockID);
						GateBlockTileEntity ent = (GateBlockTileEntity) par2World
								.getBlockTileEntity(startx, starty, startz);
						GateBlockTileEntity ent2 = (GateBlockTileEntity) par2World
								.getBlockTileEntity(startx, starty + 1, startz);
						ent.setGoal(par1ItemStack.stackTagCompound
								.getDouble("x"), par1ItemStack.stackTagCompound
								.getDouble("y"), par1ItemStack.stackTagCompound
								.getDouble("z"), par1ItemStack.stackTagCompound
								.getInteger("dimension"));
						ent2.setGoal(par1ItemStack.stackTagCompound
								.getDouble("x"), par1ItemStack.stackTagCompound
								.getDouble("y"), par1ItemStack.stackTagCompound
								.getDouble("z"), par1ItemStack.stackTagCompound
								.getInteger("dimension"));
						// place endgate
						World endworld = player.worldObj;
						boolean placed = false;
						boolean placed2 = false;
						if (endworld.getBlockId(goalx + 2, goaly, goalz) == 0
								&& endworld.getBlockId(goalx + 2, goaly + 1,
										goalz) == 0) {
							endworld.setBlock(goalx + 2, goaly, goalz,
									ChalithBase.gateBlock.blockID);
							endworld.setBlock(goalx + 2, goaly + 1, goalz,
									ChalithBase.gateBlock.blockID);
							goalx = goalx + 2;
							placed = true;
						} else if (endworld.getBlockId(goalx - 2, goaly, goalz) == 0
								&& endworld.getBlockId(goalx - 2, goaly + 1,
										goalz) == 0) {
							endworld.setBlock(goalx - 2, goaly, goalz,
									ChalithBase.gateBlock.blockID);
							endworld.setBlock(goalx - 2, goaly + 1, goalz,
									ChalithBase.gateBlock.blockID);
							placed = true;
							goalx = goalx - 2;
						} else if (endworld.getBlockId(goalx, goaly, goalz + 2) == 0
								&& endworld.getBlockId(goalx, goaly + 1,
										goalz + 2) == 0) {
							endworld.setBlock(goalx, goaly, goalz + 2,
									ChalithBase.gateBlock.blockID);
							endworld.setBlock(goalx, goaly + 1, goalz + 2,
									ChalithBase.gateBlock.blockID);
							placed = true;
							goalz = goalz + 2;
						} else if (endworld.getBlockId(goalx, goaly, goalz - 2) == 0
								&& endworld.getBlockId(goalx, goaly + 1,
										goalz - 2) == 0) {
							endworld.setBlock(goalx, goaly, goalz - 2,
									ChalithBase.gateBlock.blockID);
							endworld.setBlock(goalx, goaly + 1, goalz - 2,
									ChalithBase.gateBlock.blockID);
							placed = true;
							goalz = goalz - 2;
						}
						if (endworld.getBlockId(startx + 2, starty, startz) == 0
								&& endworld.getBlockId(startx + 2, starty + 1,
										startz) == 0) {
							startx = startx + 2;
							placed2 = true;
						} else if (endworld.getBlockId(startx - 2, starty,
								startz) == 0
								&& endworld.getBlockId(startx - 2, starty + 1,
										startz) == 0) {

							startx = startx - 2;
							placed2 = true;
						} else if (endworld.getBlockId(startx, starty,
								startz + 2) == 0
								&& endworld.getBlockId(startx, starty + 1,
										startz + 2) == 0) {
							placed2 = true;
							startz = startz + 2;
						} else if (endworld.getBlockId(startx, starty,
								startz - 2) == 0
								&& endworld.getBlockId(startx, starty + 1,
										startz - 2) == 0) {
							placed2 = true;
							startz = startz - 2;
						}
						if (placed && placed2) {

							ent = (GateBlockTileEntity) endworld
									.getBlockTileEntity(goalx, goaly, goalz);
							ent2 = (GateBlockTileEntity) endworld
									.getBlockTileEntity(goalx, goaly + 1, goalz);

							ent.setGoal(startx, starty, startz, startdimension);
							ent2.setGoal(startx, starty, startz, startdimension);
							par3EntityPlayer.setItemInUse(par1ItemStack,
									par1ItemStack.getMaxItemUseDuration());
						}
					}
				} else {
					par3EntityPlayer
							.addChatMessage("Not enough material to use this.");
				}
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
		return true;

	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				par1ItemStack.getMaxItemUseDuration());
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

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.stackTagCompound != null) {
			if (par1ItemStack.getItemDamage() == 0
					|| par1ItemStack.getItemDamage() == 3) {
				par3List.add("X:"
						+ (int) par1ItemStack.stackTagCompound.getDouble("x"));
				par3List.add("Y:"
						+ (int) par1ItemStack.stackTagCompound.getDouble("y"));
				par3List.add("Z:"
						+ (int) par1ItemStack.stackTagCompound.getDouble("z"));
			}
		}

	}

}

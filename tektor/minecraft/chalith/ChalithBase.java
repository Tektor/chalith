package tektor.minecraft.chalith;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tektor.minecraft.chalith.blocks.ChalithStoneBase;
import tektor.minecraft.chalith.blocks.BlockTrapRune;
import tektor.minecraft.chalith.blocks.ChalithOreBase;
import tektor.minecraft.chalith.blocks.ChalithWorkplaces;
import tektor.minecraft.chalith.blocks.GateBlock;
import tektor.minecraft.chalith.blocks.GlidingSand;
import tektor.minecraft.chalith.entity.DryIsrakLeaf;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.OilPress;
import tektor.minecraft.chalith.entity.OilPressMiddle;
import tektor.minecraft.chalith.entity.OilPressOut;
import tektor.minecraft.chalith.entity.OilPressOutFilling;
import tektor.minecraft.chalith.entity.OilPressPresser;
import tektor.minecraft.chalith.entity.OilPressStair;
import tektor.minecraft.chalith.entity.ShrinkPotionEntity;
import tektor.minecraft.chalith.entity.WoodAwning;
import tektor.minecraft.chalith.fluids.BlockFluidUtaniNutOil;
import tektor.minecraft.chalith.fluids.ChalithBucketHandler;
import tektor.minecraft.chalith.fluids.FluidUtaniNutOil;
import tektor.minecraft.chalith.gui.ChalithGuiHandler;
import tektor.minecraft.chalith.items.BaseRune;
import tektor.minecraft.chalith.items.ChalithOreItemBlock;
import tektor.minecraft.chalith.items.ChalithStoneItemBlock;
import tektor.minecraft.chalith.items.CraftingStuff;
import tektor.minecraft.chalith.items.EntityPlacer;
import tektor.minecraft.chalith.items.HerbalByProducts;
import tektor.minecraft.chalith.items.SeedBase;
import tektor.minecraft.chalith.items.ShrinkPotion;
import tektor.minecraft.chalith.items.ShrinkStatue;
import tektor.minecraft.chalith.items.TrapRune;
import tektor.minecraft.chalith.items.ChalithIngotBase;
import tektor.minecraft.chalith.items.UtaniNutOilBucket;
import tektor.minecraft.chalith.items.UtilRune;
import tektor.minecraft.chalith.items.RuneSymbol;
import tektor.minecraft.chalith.plants.PlantBase;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Chalith", name = "Chalith", version = "0.7.10")
@NetworkMod(channels = { "Chalith" }, packetHandler = ChalithPacketHandler.class, clientSideRequired = true)
public class ChalithBase {

	// instance
	@Instance("Chalith")
	public static ChalithBase instance;

	// blocks
	public static int blockID1, blockID2, blockID3, blockID4, blockID5,
			blockID6, blockID7, blockID8;
	public static Block bloodstone;
	public static Block chalithBaseOre;
	public static Block trapRune;
	public static Block gateBlock;
	public static Block plantBase;
	public static Block workbench;
	public static Block utaniNutOilBlock;
	public static Block glidingSand;

	// items
	public static int itemID1, itemID2, itemID3, itemID4, itemID5, itemID6,
			itemID7, itemID8, itemID9, itemID10, itemID11, itemID12;
	public static Item lorynIngot;
	public static Item utilRune;
	public static Item trapRuneItem;
	public static Item baseRune;
	public static Item runeSymbol;
	public static Item seedBase;
	public static Item herbalByProduct;
	public static Item shrinkPotion;
	public static Item shrinkStatue;
	public static Item entityPlacer;
	public static Item craftingStuff;
	public static Item utaniNutOilBucket;
	// fluids
	public static Fluid utaniNutOil;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.chalith.client.ChalithClientProxy", serverSide = "tektor.minecraft.chalith.ChalithCommonProxy")
	public static ChalithCommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		blockID1 = config.get(Configuration.CATEGORY_BLOCK, "blockID1", 980)
				.getInt();
		blockID2 = config.get(Configuration.CATEGORY_BLOCK, "blockID2", 981)
				.getInt();
		blockID3 = config.get(Configuration.CATEGORY_BLOCK, "blockID3", 982)
				.getInt();
		blockID4 = config.get(Configuration.CATEGORY_BLOCK, "blockID4", 983)
				.getInt();
		blockID5 = config.get(Configuration.CATEGORY_BLOCK, "blockID5", 984)
				.getInt();
		blockID6 = config.get(Configuration.CATEGORY_BLOCK, "blockID6", 985)
				.getInt();
		blockID7 = config.get(Configuration.CATEGORY_BLOCK, "blockID7", 986)
				.getInt();
		blockID8 = config.get(Configuration.CATEGORY_BLOCK, "blockID8", 987)
				.getInt();

		itemID1 = config.get(Configuration.CATEGORY_ITEM, "itemID1", 7000)
				.getInt();
		itemID2 = config.get(Configuration.CATEGORY_ITEM, "itemID2", 7001)
				.getInt();
		itemID3 = config.get(Configuration.CATEGORY_ITEM, "itemID3", 7002)
				.getInt();
		itemID4 = config.get(Configuration.CATEGORY_ITEM, "itemID4", 7003)
				.getInt();
		itemID5 = config.get(Configuration.CATEGORY_ITEM, "itemID5", 7004)
				.getInt();
		itemID6 = config.get(Configuration.CATEGORY_ITEM, "itemID6", 7005)
				.getInt();
		itemID7 = config.get(Configuration.CATEGORY_ITEM, "itemID7", 7006)
				.getInt();
		itemID8 = config.get(Configuration.CATEGORY_ITEM, "itemID8", 7007)
				.getInt();
		itemID9 = config.get(Configuration.CATEGORY_ITEM, "itemID9", 7008)
				.getInt();
		itemID10 = config.get(Configuration.CATEGORY_ITEM, "itemID10", 7009)
				.getInt();
		itemID11 = config.get(Configuration.CATEGORY_ITEM, "itemID11", 7010)
				.getInt();
		itemID12 = config.get(Configuration.CATEGORY_ITEM, "itemID12", 7011)
				.getInt();

		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		initializeID();
		proxy.registerRenderers();
		registerOres();
		registerIngots();
		registerRunes();
		registerTileEntities();
		registerPlants();
		registerMisc();
		smeltingRecipes();
		
		GameRegistry.registerWorldGenerator(new ChalithWorldGen());
		registerEntities();
		registerFluids();
		runeCrafting();
		NetworkRegistry.instance().registerGuiHandler(this,
				new ChalithGuiHandler());
	}

	private void registerFluids() {

		utaniNutOil = new FluidUtaniNutOil("utaniNutOil");
		FluidRegistry.registerFluid(utaniNutOil);
		utaniNutOilBlock = new BlockFluidUtaniNutOil(blockID7, utaniNutOil);
		utaniNutOilBucket = new UtaniNutOilBucket(itemID12);

		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(
				FluidRegistry.getFluidStack(ChalithBase.utaniNutOil.getName(),
						FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(
						ChalithBase.utaniNutOilBucket), new ItemStack(
						Item.bucketEmpty)));
		MinecraftForge.EVENT_BUS.register(new ChalithBucketHandler());
	}

	private void registerEntities() {
		// DryStand
		EntityRegistry.registerGlobalEntityID(DryStand.class, "DryStand",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DryStand.class, "DryStand", 0,
				this.instance, 40, 5, true);
		// /Sub: DryIsrakLeaf
		EntityRegistry.registerGlobalEntityID(DryIsrakLeaf.class,
				"DryIsrakLeaf", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DryIsrakLeaf.class, "DryIsrakLeaf", 3,
				this.instance, 40, 5, true);

		// Oil Press
		// /Oil Press Base
		EntityRegistry.registerGlobalEntityID(OilPress.class, "OilPress",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(OilPress.class, "OilPress", 4,
				this.instance, 40, 5, true);
		// /Oil Press Stair
		EntityRegistry.registerGlobalEntityID(OilPressStair.class,
				"OilPressStair", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(OilPressStair.class, "OilPressStair",
				5, this.instance, 40, 5, true);
		// /Oil Press Out
		EntityRegistry.registerGlobalEntityID(OilPressOut.class, "OilPressOut",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(OilPressOut.class, "OilPressOut", 6,
				this.instance, 40, 5, true);
		// /Oil Press Middle
		EntityRegistry.registerGlobalEntityID(OilPressMiddle.class,
				"OilPressMiddle", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(OilPressMiddle.class,
				"OilPressMiddle", 7, this.instance, 40, 5, true);
		// /Oil Press Presser
		EntityRegistry.registerGlobalEntityID(OilPressPresser.class,
				"OilPressPresser", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(OilPressPresser.class,
				"OilPressPresser", 8, this.instance, 40, 5, true);
		// /Oil Press Filler
		EntityRegistry
				.registerGlobalEntityID(OilPressOutFilling.class,
						"OilPressOutFilling",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(OilPressOutFilling.class,
				"OilPressOutFilling", 9, this.instance, 40, 5, true);

		// Wood Awning
		EntityRegistry.registerGlobalEntityID(WoodAwning.class, "WoodAwning",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(WoodAwning.class, "WoodAwning", 2,
				this.instance, 40, 5, true);

		// Shrink Potion
		EntityRegistry.registerGlobalEntityID(ShrinkPotionEntity.class,
				"ShrinkPotion", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(ShrinkPotionEntity.class,
				"ShrinkPotion", 1, this.instance, 20, 5, true);

	}

	private void registerMisc() {
		// bloodstone
		MinecraftForge.setBlockHarvestLevel(bloodstone, "pickaxe", 1);
		GameRegistry.registerBlock(bloodstone, ChalithStoneItemBlock.class,
				"bloodstone");
		LanguageRegistry.addName(new ItemStack(bloodstone, 1, 0), "Bloodstone");
		LanguageRegistry.addName(new ItemStack(bloodstone, 1, 1),
				"Bloodstone Cobble");
		// corinnstone
		LanguageRegistry
				.addName(new ItemStack(bloodstone, 1, 2), "Corinnstone");
		LanguageRegistry.addName(new ItemStack(bloodstone, 1, 3),
				"Corinnstone Cobble");

		// gliding sand
		MinecraftForge.setBlockHarvestLevel(glidingSand, "shovel", 1);
		GameRegistry.registerBlock(glidingSand, "glidingSand");
		LanguageRegistry.addName(new ItemStack(glidingSand, 1, 0),
				"Gliding Sand");
		
		// rune renamer
		GameRegistry.registerBlock(workbench, "runeWorkbench");
		LanguageRegistry.addName(new ItemStack(workbench, 1, 0),
				"Rune Workbench");

		// shrink stuff
		GameRegistry.registerItem(shrinkPotion, "shrinkPotion");
		LanguageRegistry.addName(new ItemStack(shrinkPotion, 1, 0),
				"Shrink Potion");
		GameRegistry.registerItem(shrinkStatue, "shrinkStatue");
		LanguageRegistry.addName(new ItemStack(shrinkStatue, 1, 0),
				"Shrink Statue");
		// entity placer base
		GameRegistry.registerItem(entityPlacer, "entityPlacer");
		LanguageRegistry
				.addName(new ItemStack(entityPlacer, 1, 0), "Dry Stand");
		// crafting stuff
		GameRegistry.registerItem(craftingStuff, "craftingStuff");
		LanguageRegistry.addName(new ItemStack(craftingStuff, 1, 0),
				"String Grid");
		LanguageRegistry.addName(new ItemStack(craftingStuff, 1, 1),
				"Utani Nut Oil Bottle");

	}

	private void registerPlants() {
		GameRegistry.registerItem(seedBase, "seedBase");
		GameRegistry.registerBlock(plantBase, "plantBase");
		GameRegistry.registerItem(herbalByProduct, "herbalByProduct");
		// Israk
		LanguageRegistry.addName(new ItemStack(plantBase, 1, 0), "Israk Root");
		LanguageRegistry.addName(new ItemStack(seedBase, 1, 0), "Israk Root");
		MinecraftForge.addGrassSeed(new ItemStack(seedBase, 1, 0), 7);
		// Utani
		LanguageRegistry.addName(new ItemStack(plantBase, 1, 1),
				"Utani Sapling");
		LanguageRegistry.addName(new ItemStack(seedBase, 1, 1), "Utani Nut");
		MinecraftForge.addGrassSeed(new ItemStack(seedBase, 1, 1), 4);

	}

	private void initializeID() {
		// blocks
		bloodstone = new ChalithStoneBase(blockID1);
		chalithBaseOre = new ChalithOreBase(blockID2);
		trapRune = new BlockTrapRune(blockID3);
		gateBlock = new GateBlock(blockID4);
		plantBase = new PlantBase(blockID5);
		workbench = new ChalithWorkplaces(blockID6);
		glidingSand = new GlidingSand(blockID8);
		// items
		seedBase = new SeedBase(itemID7);
		herbalByProduct = new HerbalByProducts(itemID8);

		shrinkPotion = new ShrinkPotion(itemID9);
		shrinkStatue = new ShrinkStatue(itemID10);

		entityPlacer = new EntityPlacer(itemID11);
		craftingStuff = new CraftingStuff(itemID1);

		lorynIngot = new ChalithIngotBase(itemID2);

		utilRune = new UtilRune(itemID3);
		trapRuneItem = new TrapRune(itemID6);

		baseRune = new BaseRune(itemID4);
		runeSymbol = new RuneSymbol(itemID5);
	}

	private void registerTileEntities() {
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.chalith.entity.tileentity.TrapRuneTileEntity.class,
						"Fire Trap Rune");
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.chalith.entity.tileentity.GateBlockTileEntity.class,
						"Gate Block");
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity.class,
						"Workplace");

	}

	private void runeCrafting() {
		ItemStack diStack = new ItemStack(this.runeSymbol, 1, 0);
		ItemStack diStack2 = new ItemStack(this.runeSymbol, 1, 0);
		ItemStack inStack = new ItemStack(this.runeSymbol, 1, 1);
		ItemStack nomStack = new ItemStack(this.runeSymbol, 1, 2);
		ItemStack xenStack = new ItemStack(this.runeSymbol, 1, 3);
		ItemStack alStack = new ItemStack(this.runeSymbol, 1, 5);
		ItemStack borStack = new ItemStack(this.runeSymbol, 1, 6);
		ItemStack cesStack = new ItemStack(this.runeSymbol, 1, 7);
		ItemStack hirStack = new ItemStack(this.runeSymbol, 1, 9);
		ItemStack onStack = new ItemStack(this.runeSymbol, 1, 11);
		ItemStack voStack = new ItemStack(this.runeSymbol, 1, 13);
		ItemStack welStack = new ItemStack(this.runeSymbol, 1, 15);

		ItemStack avaeaIngotStack = new ItemStack(this.lorynIngot, 1, 2);
		ItemStack lorynIngotStack = new ItemStack(this.lorynIngot, 1, 0);
		ItemStack sorfynIngotStack = new ItemStack(this.lorynIngot, 1, 1);
		ItemStack goldIngotStack = new ItemStack(Item.ingotGold, 1);

		ItemStack baseRuneStack = new ItemStack(this.baseRune, 1, 0);
		ItemStack wildRuneStack = new ItemStack(this.baseRune, 1, 1);
		ItemStack switchingRuneStack = new ItemStack(this.baseRune, 1, 2);

		ItemStack bloodstoneStack = new ItemStack(this.bloodstone, 1, 0);
		ItemStack corinnstoneStack = new ItemStack(this.bloodstone, 1, 2);

		ItemStack israkRootStack = new ItemStack(this.seedBase, 1, 0);
		ItemStack israkLeafStack = new ItemStack(this.herbalByProduct, 1, 0);
		ItemStack israkLeafDriedStack = new ItemStack(this.herbalByProduct, 1,
				2);

		ItemStack utaniBucket = new ItemStack(this.utaniNutOilBucket.setContainerItem(Item.bucketEmpty),1,0);
		
		ItemStack sand = new ItemStack(Block.sand,1,0);
		ItemStack woodStick = new ItemStack(Item.stick, 1, 0);
		ItemStack string = new ItemStack(Item.silk, 1, 0);
		
		//Gliding Sand
		GameRegistry.addShapedRecipe(new ItemStack(this.glidingSand, 1, 0),
				new Object[] { "XY", 'X', sand, 'Y',utaniBucket });

		// Base Runes
		GameRegistry.addShapedRecipe(new ItemStack(this.baseRune, 1, 0),
				new Object[] { "XXX", "X X", "XXX", 'X', avaeaIngotStack });
		GameRegistry.addShapedRecipe(new ItemStack(this.baseRune, 1, 1),
				new Object[] { "XXX", "X X", "XXX", 'X', sorfynIngotStack });
		GameRegistry.addShapedRecipe(new ItemStack(this.baseRune, 1, 2),
				new Object[] { "XY", 'X', baseRuneStack, 'Y', wildRuneStack });

		// Workbenches
		// / Rune Renamer
		GameRegistry.addShapedRecipe(new ItemStack(this.workbench, 1, 0),
				new Object[] { "XYX", "ZYZ", "ZZZ", 'X', lorynIngotStack, 'Y',
						corinnstoneStack, 'Z', bloodstoneStack });
		// / Dry Stand
		GameRegistry.addShapedRecipe(new ItemStack(this.entityPlacer, 1, 0),
				new Object[] { "X X", " Y ", "X X", 'X', woodStick, 'Y',
						new ItemStack(this.craftingStuff, 1, 0) });

		// Crafting Stuff
		// / String Grid
		GameRegistry
				.addShapedRecipe(new ItemStack(this.craftingStuff, 1, 0),
						new Object[] { "XYX", "XYX", "XYX", 'X', woodStick,
								'Y', string });

		// ShrinkPotion
		GameRegistry.addShapedRecipe(new ItemStack(this.shrinkPotion, 1, 0),
				new Object[] { "XYX", "YZY", "XYX", 'X', israkRootStack, 'Y',
						israkLeafDriedStack, 'Z',
						new ItemStack(this.craftingStuff, 1, 1) });
		// Recall
		GameRegistry.addShapedRecipe(new ItemStack(this.utilRune, 1, 0),
				new Object[] { "ABC", " X ", "   ", 'A', diStack, 'B', inStack,
						'C', nomStack, 'X', baseRuneStack });
		// Recall Gate
		GameRegistry.addShapedRecipe(new ItemStack(this.utilRune, 1, 3),
				new Object[] { "ABC", " X ", "   ", 'A', diStack, 'B', alStack,
						'C', nomStack, 'X', wildRuneStack });
		// Rune of Inventory
		GameRegistry.addShapedRecipe(new ItemStack(this.utilRune, 1, 1),
				new Object[] { "ABC", " X ", "   ", 'A', welStack, 'B',
						onStack, 'C', alStack, 'X', switchingRuneStack });

		// FireTrap
		GameRegistry.addShapedRecipe(new ItemStack(this.trapRuneItem, 1, 0),
				new Object[] { "ABC", " X ", "   ", 'A', xenStack, 'B',
						voStack, 'C', borStack, 'X', baseRuneStack });
		// IceTrap
		GameRegistry.addShapedRecipe(new ItemStack(this.trapRuneItem, 1, 1),
				new Object[] { "ABC", " X ", "   ", 'A', xenStack, 'B',
						cesStack, 'C', hirStack, 'X', baseRuneStack });

		// Di
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 0),
				new Object[] { "AA ", "  A", "AA ", 'A', lorynIngotStack });
		// In
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 1),
				new Object[] { "A  ", " A ", "  A", 'A', lorynIngotStack });
		// Nom
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 2),
				new Object[] { "AAA", "  A", "  A", 'A', lorynIngotStack });
		// Xen
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 3),
				new Object[] { "A A", " B ", "A A", 'A', lorynIngotStack, 'B',
						goldIngotStack });
		// Yok
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 4),
				new Object[] { "A A", " A ", "A  ", 'A', lorynIngotStack });
		// Al
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 5),
				new Object[] { "   ", " A ", "A A", 'A', lorynIngotStack });
		// Bor
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 6),
				new Object[] { "A  ", "AA ", "A  ", 'A', lorynIngotStack });
		// Ces
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 7),
				new Object[] { "  A", " A ", "  A", 'A', lorynIngotStack });
		// Hir
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 9),
				new Object[] { "A A", " A ", "A A", 'A', lorynIngotStack });
		// On
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 11),
				new Object[] { "AAA", "A A", "AAA", 'A', lorynIngotStack });
		// Vo
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 13),
				new Object[] { "A A", "A A", " A ", 'A', lorynIngotStack });

		// Wel
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 15),
				new Object[] { "  A", " AA", "AA ", 'A', lorynIngotStack });

	}

	private void registerRunes() {
		// Symbols
		for (int ix = 0; ix < 16; ix++) {
			ItemStack runeSymbolStack = new ItemStack(runeSymbol, 1, ix);
			LanguageRegistry
					.addName(runeSymbolStack,
							RuneSymbol.runeSymbolNames[runeSymbolStack
									.getItemDamage()]);
		}
		// Base
		LanguageRegistry.addName(baseRune, "Base Rune");
		GameRegistry.registerItem(baseRune, "baseRune");

		// Recall
		LanguageRegistry.addName(utilRune, "Recall Rune");
		GameRegistry.registerItem(utilRune, "recallRune");
		// FireTrap
		GameRegistry.registerItem(trapRuneItem, "fireTrapRune");
		GameRegistry.registerBlock(trapRune, "fireTrapRuneBlock");
		LanguageRegistry.addName(new ItemStack(trapRune, 1, 0),
				"Fire Trap Rune");
		// Ice Trap
		LanguageRegistry
				.addName(new ItemStack(trapRune, 1, 1), "Ice Trap Rune");

		// Gate Rune
		GameRegistry.registerBlock(gateBlock, "goalBlock");

	}

	private void registerIngots() {

		// loryn

		GameRegistry.registerItem(lorynIngot, "lorynIngot");
		LanguageRegistry
				.addName(new ItemStack(lorynIngot, 1, 0), "Loryn Ingot");

		OreDictionary
				.registerOre("ingotLoryn", new ItemStack(lorynIngot, 1, 0));
		// sorfyn
		LanguageRegistry.addName(new ItemStack(lorynIngot, 1, 1),
				"Sorfyn Ingot");
		OreDictionary.registerOre("ingotSorfyn",
				new ItemStack(lorynIngot, 1, 1));
		// avaea
		LanguageRegistry
				.addName(new ItemStack(lorynIngot, 1, 2), "Avaea Ingot");
		OreDictionary
				.registerOre("ingotAvaea", new ItemStack(lorynIngot, 1, 2));
	}

	private void smeltingRecipes() {
		FurnaceRecipes.smelting().addSmelting(
				ChalithBase.chalithBaseOre.blockID, 0,
				new ItemStack(ChalithBase.lorynIngot, 1, 0), 0.4F);
		FurnaceRecipes.smelting().addSmelting(
				ChalithBase.chalithBaseOre.blockID, 1,
				new ItemStack(ChalithBase.lorynIngot, 1, 1), 0.6F);
		FurnaceRecipes.smelting().addSmelting(
				ChalithBase.chalithBaseOre.blockID, 2,
				new ItemStack(ChalithBase.lorynIngot, 1, 2), 0.3F);
		FurnaceRecipes.smelting().addSmelting(ChalithBase.bloodstone.blockID,
				1, new ItemStack(ChalithBase.bloodstone, 1, 0), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ChalithBase.bloodstone.blockID,
				3, new ItemStack(ChalithBase.bloodstone, 1, 2), 0.2F);

	}

	private void registerOres() {

		// loryn

		MinecraftForge.setBlockHarvestLevel(chalithBaseOre, "pickaxe", 1);
		GameRegistry.registerBlock(chalithBaseOre, ChalithOreItemBlock.class,
				"lorynOre");

		OreDictionary.registerOre("oreLoryn", new ItemStack(chalithBaseOre, 1,
				0));
		LanguageRegistry.addName(new ItemStack(chalithBaseOre, 1, 0),
				"Loryn Ore");
		// sorfyn
		OreDictionary.registerOre("oreSorfyn", new ItemStack(chalithBaseOre, 1,
				1));
		LanguageRegistry.addName(new ItemStack(chalithBaseOre, 1, 1),
				"Sorfyn Ore");
		// avaea
		LanguageRegistry.addName(new ItemStack(chalithBaseOre, 1, 2),
				"Avaea Ore");
		OreDictionary.registerOre("oreAvaea", new ItemStack(chalithBaseOre, 1,
				2));

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
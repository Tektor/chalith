package tektor.minecraft.chalith;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import tektor.minecraft.chalith.blocks.AvaeaOre;
import tektor.minecraft.chalith.blocks.LorynOre;
import tektor.minecraft.chalith.items.AvaeaIngot;
import tektor.minecraft.chalith.items.BaseRune;
import tektor.minecraft.chalith.items.FireTrapRune;
import tektor.minecraft.chalith.items.LorynIngot;
import tektor.minecraft.chalith.items.RecallRune;
import tektor.minecraft.chalith.items.RuneSymbol;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Chalith", name = "Chalith", version = "0.1.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ChalithBase {

	// The instance of your mod that Forge uses.
	@Instance("ChalithBase")
	public static ChalithBase instance;

	// blocks
	public final static Block avaeaOre = new AvaeaOre(980);
	public final static Block lorynOre = new LorynOre(981);

	// items
	public final static Item avaeaIngot = new AvaeaIngot(7000);
	public final static Item lorynIngot = new LorynIngot(7001);
	public final static Item recallRune = new RecallRune(7002);
	public final static Item fireTrapRune = new FireTrapRune(7005);
	public static final Item baseRune = new BaseRune(7003);
	private static final Item runeSymbol = new RuneSymbol(7004);

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.chalith.client.ClientProxy", serverSide = "tektor.minecraft.chalith.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		registerOres();
		registerIngots();
		registerRunes();
		smeltingRecipes();
		runeCrafting();
		GameRegistry.registerWorldGenerator(new ChalithWorldGen());
	}

	private void runeCrafting() {
		ItemStack diStack = new ItemStack(this.runeSymbol, 1, 0);
		ItemStack inStack = new ItemStack(this.runeSymbol, 1, 1);
		ItemStack nomStack = new ItemStack(this.runeSymbol, 1, 2);
		ItemStack avaeaIngotStack = new ItemStack(this.avaeaIngot, 1);
		ItemStack lorynIngotStack = new ItemStack(this.lorynIngot, 1);
		ItemStack baseRuneStack = new ItemStack(this.baseRune, 1);

		GameRegistry.addShapedRecipe(new ItemStack(this.baseRune, 1),
				new Object[] { "XXX", "X X", "XXX", 'X', avaeaIngotStack });
		GameRegistry.addShapedRecipe(new ItemStack(this.recallRune, 1),
				new Object[] { "ABC", " X ", "   ", 'A', diStack, 'B', inStack,
						'C', nomStack, 'X', baseRuneStack });
		//Di
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 0),
				new Object[] { "A  ", " A ", "A  ", 'A', lorynIngotStack});
		//In
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 1),
				new Object[] { "A  ", " A ", "  A", 'A', lorynIngotStack});
		//Nom
		GameRegistry.addShapedRecipe(new ItemStack(this.runeSymbol, 1, 2),
				new Object[] { "AAA", "  A", "  A", 'A', lorynIngotStack});

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
		LanguageRegistry.addName(recallRune, "Recall Rune");
		GameRegistry.registerItem(recallRune, "recallRune");
		// FireTrap
				LanguageRegistry.addName(fireTrapRune, "Fire Trap Rune");
				GameRegistry.registerItem(fireTrapRune, "fireTrapRune");

	}

	private void registerIngots() {
		// avaea
		LanguageRegistry.addName(avaeaIngot, "Avaea Ingot");
		GameRegistry.registerItem(avaeaIngot, "avaeaIngot");
		OreDictionary.registerOre("ingotAvaea", new ItemStack(avaeaIngot));
		// loryn
		LanguageRegistry.addName(lorynIngot, "Loryn Ingot");
		GameRegistry.registerItem(lorynIngot, "lorynIngot");
		OreDictionary.registerOre("ingotLoryn", new ItemStack(lorynIngot));

	}

	private void smeltingRecipes() {
		GameRegistry.addSmelting(ChalithBase.avaeaOre.blockID, new ItemStack(
				ChalithBase.avaeaIngot), 0.4f);
		GameRegistry.addSmelting(ChalithBase.lorynOre.blockID, new ItemStack(
				ChalithBase.lorynIngot), 0.4f);

	}

	private void registerOres() {
		// avaea
		LanguageRegistry.addName(avaeaOre, "Avaea Ore");
		MinecraftForge.setBlockHarvestLevel(avaeaOre, "pickaxe", 1);
		GameRegistry.registerBlock(avaeaOre, "avaeaOre");
		OreDictionary.registerOre("oreAvaea", new ItemStack(avaeaOre));
		// loryn
		LanguageRegistry.addName(lorynOre, "Loryn Ore");
		MinecraftForge.setBlockHarvestLevel(lorynOre, "pickaxe", 1);
		GameRegistry.registerBlock(lorynOre, "lorynOre");
		OreDictionary.registerOre("oreLoryn", new ItemStack(lorynOre));

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
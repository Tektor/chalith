package tektor.minecraft.chalith;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import tektor.minecraft.chalith.blocks.AvaeaOre;
import tektor.minecraft.chalith.items.AvaeaIngot;
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

@Mod(modid="Chalith", name="Chalith", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ChalithBase {

        // The instance of your mod that Forge uses.
        @Instance("ChalithBase")
        public static ChalithBase instance;
        
        //blocks
        public final static Block avaeaOre = new AvaeaOre(980);
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="tektor.minecraft.chalith.client.ClientProxy", serverSide="tektor.minecraft.chalith.CommonProxy")
        public static CommonProxy proxy;

		private static Item avaeaIngot = new AvaeaIngot(7000);
       
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                registerOres();
                registerIngots();
                smeltingRecipes();
        }
       
        private void registerIngots() {
        	//avaea
			LanguageRegistry.addName(avaeaIngot, "Avaea Ingot");
			GameRegistry.registerItem(avaeaIngot, "avaeaIngot");
			OreDictionary.registerOre("ingotAvaea", new ItemStack(avaeaIngot));
			
		}

		private void smeltingRecipes() {
			GameRegistry.addSmelting(ChalithBase.avaeaOre.blockID, new ItemStack(ChalithBase.avaeaIngot), 0.3f);
			
		}

		private void registerOres() {
			//avaea
        	LanguageRegistry.addName(avaeaOre, "Avaea Ore");
			MinecraftForge.setBlockHarvestLevel(avaeaOre, "pickaxe", 1);
			GameRegistry.registerBlock(avaeaOre,"avaeaOre");
			OreDictionary.registerOre("oreAvaea", new ItemStack(avaeaOre));
			
		}

		@EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}
package com.csjschmoe20.followtutorial;

import com.csjschmoe20.followtutorial.init.BlockInit;
import com.csjschmoe20.followtutorial.init.BlockInitNew;
import com.csjschmoe20.followtutorial.init.ItemInitNew;
import com.csjschmoe20.followtutorial.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("followtutorial")
@Mod.EventBusSubscriber(modid=FollowTutorial.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class FollowTutorial
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // making the modid accessible from elsewhere
    public static final String MOD_ID = "followtutorial";
    // creating an instance of the main class
    public static FollowTutorial instance;

    public FollowTutorial() {
        // creating an instance of the main class for convenience
        instance = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ItemInitNew.ITEMS.register(modEventBus);
        BlockInitNew.BLOCKS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        BlockInitNew.BLOCKS.getEntries().stream()
            .filter(block -> true)
            .map(RegistryObject::get).forEach(block -> {
                final Item.Properties properties = new Item.Properties().group(TutorialItemGroup.instance);
                final BlockItem blockItem = new BlockItem(block, properties);
                blockItem.setRegistryName(block.getRegistryName());
                registry.register(blockItem);
            });
        LOGGER.info("Registered BlockItems");
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static class TutorialItemGroup extends ItemGroup {

        public static final TutorialItemGroup instance = new TutorialItemGroup(ItemGroup.GROUPS.length, "tutorialtab");

        private TutorialItemGroup(int index, String label){
            super(index, label);
        }
        @Override
        public ItemStack createIcon(){
            return new ItemStack(BlockInitNew.EXAMPLE_BLOCK.get());
        }
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}

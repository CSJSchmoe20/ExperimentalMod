package com.csjschmoe20.followtutorial.init;

import com.csjschmoe20.followtutorial.FollowTutorial;
import com.csjschmoe20.followtutorial.FollowTutorial.TutorialItemGroup;
import com.csjschmoe20.followtutorial.objects.blocks.BlockQuarry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@ObjectHolder(FollowTutorial.MOD_ID)
@Mod.EventBusSubscriber(modid=FollowTutorial.MOD_ID, bus=Bus.MOD)
public class BlockInit {
    public static final Block example_block = null;
    public static final Block quarry_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event){
        event.getRegistry().register(new Block(
                Block.Properties.create(Material.IRON).hardnessAndResistance(0.5f, 15.0f).sound(SoundType.SAND)
        ).setRegistryName("example_block"));

        event.getRegistry().register(new BlockQuarry().setRegistryName("quarry_block"));


    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(example_block, new Item.Properties().maxStackSize(16).group(TutorialItemGroup.instance)).setRegistryName("example_block"));
        event.getRegistry().register(new BlockItem(quarry_block, BlockQuarry.getItemProperties()).setRegistryName("quarry_block"));
    }
}

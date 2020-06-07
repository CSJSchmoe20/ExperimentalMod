package com.csjschmoe20.followtutorial.init;
import com.csjschmoe20.followtutorial.FollowTutorial;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = FollowTutorial.MOD_ID, bus = Bus.MOD)
@ObjectHolder(FollowTutorial.MOD_ID)
public class iteminit {

    public static final Item example_item = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        //event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_item"));
        registerItem(event, ItemGroup.MISC, "example_item");

    }

    public static void registerItem(final RegistryEvent.Register<Item> event, ItemGroup group, String registry_name){
        event.getRegistry().register(new Item(new Item.Properties().group(group)).setRegistryName(registry_name));
    }
}

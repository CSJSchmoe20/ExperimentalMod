package com.csjschmoe20.followtutorial.init;
import com.csjschmoe20.followtutorial.FollowTutorial;
import com.csjschmoe20.followtutorial.FollowTutorial.TutorialItemGroup;
import com.csjschmoe20.followtutorial.objects.items.AdvancedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = FollowTutorial.MOD_ID, bus = Bus.MOD)
@ObjectHolder(FollowTutorial.MOD_ID)
public class ItemInit {

    public static final Item example_item = null;
    public static final Item example_food = null;
    public static final Item special_item = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        //event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_item"));
        registerItem(event, TutorialItemGroup.instance, "example_item");
        event.getRegistry().register(new Item(new Item.Properties().group(TutorialItemGroup.instance).food(
                new Food.Builder().hunger(6).saturation(3).setAlwaysEdible().effect(new Supplier<EffectInstance>() {
                    @Override
                    public EffectInstance get() {
                        return new EffectInstance(Effects.REGENERATION, 100, 3);
                    }
                }, 1.0f).build()
        )).setRegistryName("example_food"));

        event.getRegistry().register(new AdvancedItem(new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("advanced_item"));


    }

    public static void registerItem(final RegistryEvent.Register<Item> event, ItemGroup group, String registry_name){
        event.getRegistry().register(new Item(new Item.Properties().group(group)).setRegistryName(registry_name));
    }
}

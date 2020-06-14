package com.csjschmoe20.followtutorial.init;

import com.csjschmoe20.followtutorial.FollowTutorial;
import com.csjschmoe20.followtutorial.FollowTutorial.TutorialItemGroup;
import com.csjschmoe20.followtutorial.objects.items.AdvancedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemInitNew {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FollowTutorial.MOD_ID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EXAMPLE_FOOD = ITEMS.register("example_food", () -> new Item(new Item.Properties().group(TutorialItemGroup.instance).food(
                            new Food.Builder().hunger(6).saturation(3).setAlwaysEdible().effect(new Supplier<EffectInstance>() {
                                @Override
                                public EffectInstance get() {
                                    return new EffectInstance(Effects.REGENERATION, 100, 3);
                                }
                            }, 1.0f).build())));
    public static final RegistryObject<Item> ADVANCED_ITEM = ITEMS.register("advanced_item", ()-> new AdvancedItem(new Item.Properties().group(TutorialItemGroup.instance)));
}

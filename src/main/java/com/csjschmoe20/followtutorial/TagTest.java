package com.csjschmoe20.followtutorial;

import com.csjschmoe20.followtutorial.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class TagTest {
    public TagTest() {
        ResourceLocation testTagItem = new ResourceLocation(FollowTutorial.MOD_ID, "testtagitem");
        Item item = ItemInit.example_item;
        boolean isInTag = ItemTags.getCollection().get(testTagItem).contains(item);
        if (isInTag) {
            item.setDamage(item.getDefaultInstance(), 15);
        }
    }
}

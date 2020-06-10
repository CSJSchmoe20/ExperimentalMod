package com.csjschmoe20.followtutorial.init;

import com.csjschmoe20.followtutorial.FollowTutorial;
import com.csjschmoe20.followtutorial.tileentity.QuarryTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, FollowTutorial.MOD_ID);
    public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry_block", () -> TileEntityType.Builder.create(QuarryTileEntity::new, BlockInit.quarry_block).build(null));
}

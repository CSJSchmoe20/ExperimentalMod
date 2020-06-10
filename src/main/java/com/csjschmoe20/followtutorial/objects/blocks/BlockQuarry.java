package com.csjschmoe20.followtutorial.objects.blocks;

import com.csjschmoe20.followtutorial.FollowTutorial;
import com.csjschmoe20.followtutorial.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

public class BlockQuarry extends Block {
    public BlockQuarry(){
        super(Block.Properties.create(Material.IRON).sound(SoundType.METAL));
    }

    public static Item.Properties getItemProperties(){
        return new Item.Properties().group(FollowTutorial.TutorialItemGroup.instance);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return ModTileEntityTypes.QUARRY.get().create();
    }
}

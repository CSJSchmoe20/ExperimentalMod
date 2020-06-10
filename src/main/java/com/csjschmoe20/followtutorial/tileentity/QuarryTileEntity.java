package com.csjschmoe20.followtutorial.tileentity;

import com.csjschmoe20.followtutorial.FollowTutorial;
import com.csjschmoe20.followtutorial.init.ModTileEntityTypes;
import com.csjschmoe20.followtutorial.util.helpers.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

    public int x, y, z, tick;
    boolean initialized = false;

    public QuarryTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public QuarryTileEntity() {
        this(ModTileEntityTypes.QUARRY.get());
    }

    @Override
    public void tick() {
        if(!initialized){
            init();
        }
        tick++;
        if (tick == 40){
            tick = 0;
            if(y > 2)
                execute();
        }
    }

    private void execute(){
        int index = 0;
        Block[] blocksRemoved = new Block[9];
        //FollowTutorial.LOGGER.warn("Quarried one layer!");
        //FollowTutorial.LOGGER.info(String.format("Quarried layer at y=%d", this.y));
        for(int x = 0; x < 3; x++){
            for(int z = 0; z < 3; z++){
                BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
                blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
                destroyBlock(posToBreak, true, null);
                index++;
            }
        }
        this.y--;
    }

    public boolean destroyBlock(BlockPos pos, boolean drop, @Nullable Entity entity) {
        BlockState blockstate = this.world.getBlockState(pos);
        if (blockstate.isAir(this.world, pos)){
            return false;
        }else{
            IFluidState ifluidstate = this.world.getFluidState(pos);
            // to play the sound of the breaking???
            this.world.playEvent(2001, pos, Block.getStateId(blockstate));
            if(drop){
                TileEntity tileentity = blockstate.hasTileEntity() ? world.getTileEntity(pos) : null;
                Block.spawnDrops(blockstate, this.world, this.pos.add(0, 1.5, 0), tileentity, entity, ItemStack.EMPTY);
            }
            return world.setBlockState(pos, ifluidstate.getBlockState(), 3);
        }
    }

    private void init(){
        initialized = true;
        x = this.pos.getX() - 1;
        y = this.pos.getY() - 1;
        z = this.pos.getZ() - 1;
        tick = 0;
        //FollowTutorial.LOGGER.warn("initialized a quarry");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("initvalues", NBTHelper.toNBT(this));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound){
        super.read(compound);
        CompoundNBT initvalues = compound.getCompound("initvalues");
        if(initvalues != null){
            this.x = initvalues.getInt("x");
            this.y = initvalues.getInt("y");
            this.z = initvalues.getInt("z");

            this.tick = 0;
            initialized = true;
            return;
        }else{
            init();
        }
    }
}
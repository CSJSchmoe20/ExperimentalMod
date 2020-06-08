package com.csjschmoe20.followtutorial.events;

import com.csjschmoe20.followtutorial.FollowTutorial;

import com.csjschmoe20.followtutorial.init.BlockInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
//Bus is only mod when doing a custom event, value is only necessary if event is only client or only server side
@Mod.EventBusSubscriber(modid=FollowTutorial.MOD_ID, bus=Bus.FORGE/*| Bus.MOD, value= Dist.CLIENT|Dist.DEDICATED_SERVER*/)
public class TestJumpEvent {
    @SubscribeEvent
    public static void testJumpEvent(LivingEvent.LivingJumpEvent event){
        //FollowTutorial.LOGGER.info("test jump event fired");
        LivingEntity entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        //world.setBlockState(entity.getPosition()/*.add(x, y, z)*/, BlockInit.example_block.getDefaultState());
        //entity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20));
        //entity.setGlowing(true);
        EntityType type = entity.getType();
        if (type == EntityType.PLAYER){
            FollowTutorial.LOGGER.info("A player has jumped.");
        }
    }
}

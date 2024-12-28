package net.lostreaper03.mc_v_addons.enchantment.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class OreRadarEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<OreRadarEnchantmentEffect> CODEC = MapCodec.unit(OreRadarEnchantmentEffect::new);
    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 origin) {
        System.out.println((int) origin.x);
        System.out.println((int) origin.y);
        System.out.println((int) origin.z);
        BlockPos blockPos = new BlockPos((int) origin.x, (int) origin.y, (int) origin.z);
        if(isNearOre(pLevel, blockPos)){
            pEntity.level().playSound(null, pEntity.getX(), pEntity.getY(), pEntity.getZ(),
                    SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    private static boolean isNearOre(ServerLevel level, BlockPos pos) {
        for (int dx = -5; dx <= 5; dx++) {
            for (int dy = -5; dy <= 5; dy++) {
                for (int dz = -5; dz <= 5; dz++) {
                    BlockPos nearbyPos = new BlockPos((int) (pos.getX()) + dx, (int) (pos.getY()) + dy, (int) (pos.getZ()) + dz);
                    BlockState blockState = level.getBlockState(nearbyPos);
                    if (blockState.is(Tags.Blocks.ORES)) {
                        System.out.println(blockState);
                        System.out.println(nearbyPos);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

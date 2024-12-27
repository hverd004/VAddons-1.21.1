package net.lostreaper03.mc_v_addons.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.effects.DamageItem;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ReverseThornsEnchantmentEffect() implements EnchantmentEntityEffect {
    public static boolean enchantTriggered = false;
    public static final MapCodec<ReverseThornsEnchantmentEffect> CODEC = MapCodec.unit(ReverseThornsEnchantmentEffect::new);
    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        System.out.println("applied reverse thorns");
        ItemStack item = pItem.itemStack();
        System.out.println(item.getMaxDamage() * .1);
        System.out.println(item.getMaxDamage() * .01);
        System.out.println(item.getDamageValue());
        if (item.getDamageValue() > (item.getMaxDamage() * .1) || enchantTriggered) {
            System.out.println("Damaged");
            enchantTriggered = true;
            if (pEnchantmentLevel == 1) {
                item.setDamageValue(item.getDamageValue() - 1);
                System.out.println("Level 1 effect triggered: Healed the player armor");
                healEnemy(pEntity, 10);
            }
            if (pEnchantmentLevel == 2) {
                item.setDamageValue(item.getDamageValue() - 5);
                System.out.println("Level 2 effect triggered: Healed the player armor");
                healEnemy(pEntity, 8);
            }
            if (pEnchantmentLevel == 3) {
                item.setDamageValue(item.getDamageValue() - 10);
                System.out.println("Level 3 effect triggered: Healed the player armor");
                healEnemy(pEntity, 5);
            }
        }
        if(item.getDamageValue() == 0){
            enchantTriggered = false;
            System.out.println("Back to full, restarting");
        }
    }

    public void healEnemy(Entity pE, int heal){
        if(pE instanceof LivingEntity player){
            if(player.getLastHurtByMob() instanceof LivingEntity enemy){
                System.out.println(enemy.getHealth());
                enemy.heal(heal);
                System.out.println("healed enemy");
                System.out.println(enemy.getHealth());
            }
        }
    }
    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

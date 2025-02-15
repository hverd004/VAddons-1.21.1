package net.lostreaper03.mc_v_addons.enchantment;

import com.mojang.serialization.MapCodec;
import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.lostreaper03.mc_v_addons.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.lostreaper03.mc_v_addons.enchantment.custom.OreRadarEnchantmentEffect;
import net.lostreaper03.mc_v_addons.enchantment.custom.ReverseThornsEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
     public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
             DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MCVAddonsMod.MOD_ID);

     public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
             ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantmentEffect.CODEC);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> REVERSE_THORNS =
            ENTITY_ENCHANTMENT_EFFECTS.register("reverse_thorns", ()  -> ReverseThornsEnchantmentEffect.CODEC);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> ORE_RADAR =
            ENTITY_ENCHANTMENT_EFFECTS.register("ore_radar", ()  -> OreRadarEnchantmentEffect.CODEC);

     public static void register(IEventBus eventBus){
         ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
     }
}

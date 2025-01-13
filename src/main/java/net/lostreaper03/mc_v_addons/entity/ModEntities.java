package net.lostreaper03.mc_v_addons.entity;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceLocation;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MCVAddonsMod.MOD_ID);
    public static final RegistryObject<EntityType<LassoProjectileEntity>> LASSO_PROJECTILE =
            ENTITIES.register("lasso_projectile", () -> EntityType.Builder.<LassoProjectileEntity>of(LassoProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build(ResourceLocation.fromNamespaceAndPath(MCVAddonsMod.MOD_ID, "lasso_projectile").toString()));
}


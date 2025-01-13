package net.lostreaper03.mc_v_addons.util;


import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.lostreaper03.mc_v_addons.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties(){
        makeCustomBow(ModItems.V_LASSO.get());
    }

    private static void makeCustomBow(Item item) {
        ItemProperties.register(item, ResourceLocation.fromNamespaceAndPath(MCVAddonsMod.MOD_ID, "pull"),
                (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, ResourceLocation.fromNamespaceAndPath(MCVAddonsMod.MOD_ID, "pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });
    }
}

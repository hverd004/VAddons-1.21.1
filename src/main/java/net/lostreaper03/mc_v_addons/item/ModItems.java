package net.lostreaper03.mc_v_addons.item;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.lostreaper03.mc_v_addons.item.custom.LassoItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCVAddonsMod.MOD_ID);

    public static final RegistryObject<Item> PHANTOM_GEM = ITEMS.register("phantom_gem",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> V_LASSO = ITEMS.register("v_lasso",
            () -> new LassoItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

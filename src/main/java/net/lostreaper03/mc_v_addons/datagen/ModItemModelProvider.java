package net.lostreaper03.mc_v_addons.datagen;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.lostreaper03.mc_v_addons.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MCVAddonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.PHANTOM_GEM.get());
        //basicItem(ModItems.V_LASSO.get());
    }
}

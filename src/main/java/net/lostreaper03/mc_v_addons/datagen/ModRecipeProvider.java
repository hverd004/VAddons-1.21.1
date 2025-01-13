package net.lostreaper03.mc_v_addons.datagen;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.lostreaper03.mc_v_addons.block.ModBlocks;
import net.lostreaper03.mc_v_addons.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        //shaped
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PHANTOM_GEM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PHANTOM_GEM.get())
                .unlockedBy(getHasName(ModItems.PHANTOM_GEM.get()), has(ModItems.PHANTOM_GEM.get())).save(recipeOutput);
        //lasso
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.V_LASSO.get())
                .pattern(" A ")
                .pattern(" BA")
                .pattern(" A ")
                .define('A', Items.LEAD)
                .define('B', Items.SLIME_BALL)
                .unlockedBy(getHasName(ModItems.PHANTOM_GEM.get()), has(ModItems.PHANTOM_GEM.get())).save(recipeOutput);

         ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PHANTOM_GEM.get(), 9)
             .requires(ModBlocks.PHANTOM_GEM_BLOCK.get())
             .unlockedBy(getHasName(ModBlocks.PHANTOM_GEM_BLOCK.get()), has(ModBlocks.PHANTOM_GEM_BLOCK.get())).save(recipeOutput/*,MODID + :name to prevent dupes*/);

        //List<ItemList> X_SMELTABLE = list.of(everything you need.get());
        //oreSmelting(pRecipeOutput, X_SMELTABLE, RecipeCategory.MISC, resulted item, .25(xp), 200(time), category name);
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, MCVAddonsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

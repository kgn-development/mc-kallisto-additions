package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.persons.noel.AppleJuice
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(registries: HolderLookup.Provider, output: RecipeOutput) : RecipeProvider(registries, output) {
    override fun buildRecipes() {
        shapeless(RecipeCategory.FOOD, AppleJuice.ITEM.get())
            .requires(Items.GLASS_BOTTLE)
            .requires(Items.APPLE)
            .unlockedBy("has_apple", has(Items.APPLE))
            .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
            .save(output)
    }

    class Runner(packOutput: PackOutput, provider: CompletableFuture<HolderLookup.Provider?>) :
        RecipeProvider.Runner(packOutput, provider) {
        override fun createRecipeProvider(
            provider: HolderLookup.Provider,
            recipeOutput: RecipeOutput
        ): RecipeProvider {
            return ModRecipeProvider(provider, recipeOutput)
        }

        override fun getName(): String {
            return "Kallisto Additions Recipes"
        }
    }
}
package de.jonas.ggrecipes.handler;

import de.jonas.GGRecipes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Mithilfe dieses {@link RecipeHandler} lässt sich ein Rezept mit einfachen Mitteln registrieren, da man nur einen
 * {@link ItemStack} benötigt, welcher das Produkt des Rezepts darstellt, das Rezept an sich und die Definition des
 * Rezepts, wobei die einzelnen Materialien zugewiesen werden.
 */
@NotNull
public final class RecipeHandler {

    //<editor-fold desc="STATIC FIELDS">
    /** Die Anzahl an Rezepten, die insgesamt bereits registriert worden sind. */
    private static int count;
    //</editor-fold>


    /**
     * Registriert ein neues Rezept mithilfe eines {@link ItemStack}, welcher das Produkt des Rezepts darstellt, einem
     * Rezept und einer Definition des Rezepts, wobei die einzelnen Materialien zugewiesen werden.
     *
     * @param stack     Der {@link ItemStack}, welcher das Produkt dieses Rezepts darstellt.
     * @param recipe    Das Rezept an sich.
     * @param materials Die Definition des Rezepts und dessen Materialien.
     */
    public static void registerRecipe(
        @NotNull final ItemStack stack,
        @NotNull final String[] recipe,
        @NotNull final Map<Character, Material> materials
    ) {
        count++;

        final ShapedRecipe shapedRecipe = new ShapedRecipe(
            new NamespacedKey(GGRecipes.getInstance(), "recipe" + count),
            stack
        );

        shapedRecipe.shape(recipe);

        for (final Map.Entry<Character, Material> material : materials.entrySet()) {
            final char key = material.getKey();
            final Material value = material.getValue();

            shapedRecipe.setIngredient(key, value);
        }

        Bukkit.addRecipe(shapedRecipe);
    }

}

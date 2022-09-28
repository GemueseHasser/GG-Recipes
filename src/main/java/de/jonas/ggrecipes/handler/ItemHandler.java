package de.jonas.ggrecipes.handler;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Arrays;

/**
 * Mithilfe eines {@link ItemHandler} lässt sich ein {@link ItemStack} ganz einfach mittels weniger Parameter erzeugen.
 */
@NotNull
public final class ItemHandler {

    /**
     * Erzeugt einen {@link ItemStack} mittels weniger Parameter.
     *
     * @param material         Das {@link Material} aus dem dieser {@link ItemStack} bestehen soll.
     * @param name             Der Name, den dieser {@link ItemStack} erhalten soll.
     * @param lore             Die lore, die dieser {@link ItemStack} erhalten soll.
     * @param enchantment      Die Verzauberung, die dieser {@link ItemStack} erhalten soll.
     * @param enchantmentLevel Das Level der Verzauberung - wenn vorhanden.
     *
     * @return Einen einfach erzeugten {@link ItemStack} mittels weniger Parameter.
     */
    public static ItemStack getStack(
        @NotNull final Material material,
        @NotNull final String name,
        @NotNull final String lore,
        @Nullable final Enchantment enchantment,
        @Range(from = 0, to = Integer.MAX_VALUE) final int enchantmentLevel
    ) {
        final ItemStack stack = new ItemStack(material);
        final ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore.split("/n")));
        stack.setItemMeta(meta);

        if (enchantment == null) return stack;

        meta.addEnchant(enchantment, enchantmentLevel, true);
        stack.setItemMeta(meta);

        return stack;
    }

}

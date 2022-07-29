package de.jonas.ggrecipes.handler;

import de.jonas.GGRecipes;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Mithilfe des {@link YeezyHandler} kann man prüfen, ob ein Spieler Yeezys trägt und dann auch entweder die Effekte
 * hinzufügen, die man durch die Yeezys erhalten soll, oder auch entfernen.
 */
@NotNull
public final class YeezyHandler {

    //<editor-fold desc="CONSTANTS">
    /** Eine Liste mit allen Spielern, die aktuell Yeezys tragen. */
    @NotNull
    private static final List<Player> YEEZY_USERS = new ArrayList<>();
    //</editor-fold>


    /**
     * Prüft, ob ein bestimmter Spieler aktuell keine Yeezys trägt.
     *
     * @param player Der Spieler, dessen Schuhe auf Yeezys überprüft werden sollen.
     *
     * @return Wenn ein bestimmter Spieler aktuell keine Yeezys trägt {@code true}, ansonsten {@code false}.
     */
    public static boolean isNotWearingYeezys(@NotNull final Player player) {
        final ItemStack boots = player.getInventory().getBoots();

        if (boots == null) {
            return true;
        }

        final ItemMeta bootsMeta = boots.getItemMeta();

        assert bootsMeta != null;
        return !bootsMeta.getDisplayName().equals(GGRecipes.getConfigString("yeezyName"));
    }

    /**
     * Fügt einem bestimmten Spieler die Effekte hinzu, die man durch das Tragen von Yeezys erhalten soll.
     *
     * @param player Der Spieler, dem die Effekte, die man durch das Tragen von Yeezys erhalten soll hinzugefügt
     *               werden.
     */
    public static void addYeezyEffect(@NotNull final Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));

        YEEZY_USERS.add(player);
    }

    /**
     * Entfernt einem bestimmten Spieler die Effekte, die man durch das Tragen von Yeezys erhalten soll, wenn er sich
     * aktuell in der Liste befindet, die alle Spieler führt, die Yeezys tragen.
     *
     * @param player Der Spieler, dem die Effekte, die man durch das Tragen von Yeezys erhalten soll entfernt werden.
     */
    public static void removeYeezyEffect(@NotNull final Player player) {
        if (!YEEZY_USERS.contains(player)) return;

        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.JUMP);

        YEEZY_USERS.remove(player);
    }

}

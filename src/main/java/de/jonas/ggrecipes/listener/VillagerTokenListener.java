package de.jonas.ggrecipes.listener;

import de.jonas.GGRecipes;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe eines {@link VillagerTokenListener} werden alle Events abgefangen, die dazu dienen ein Villager-Token
 * einzusetzen.
 */
public final class VillagerTokenListener implements Listener {

    //<editor-fold desc="CONSTANTS">
    /** Die Metadata, die ein Villager besitzt, der ein Token bekommen hat. */
    private static final String TOKEN_META = "villagerToken";
    //</editor-fold>


    //<editor-fold desc="implementation">
    @EventHandler
    public void onInteractWithVillager(@NotNull final PlayerInteractAtEntityEvent e) {
        // check if player interacts with villager
        if (e.getRightClicked().getType() != EntityType.VILLAGER) return;

        // get player
        final Player player = e.getPlayer();

        // get item in main hand
        final ItemStack hand = player.getInventory().getItemInMainHand();
        final ItemMeta handMeta = hand.getItemMeta();

        // check display name
        if (handMeta == null) return;
        if (!handMeta.getDisplayName().equals(GGRecipes.getConfigString("villagerTokenName"))) return;

        final Villager villager = (Villager) e.getRightClicked();

        // check if villager already has a token
        if (villager.hasMetadata(TOKEN_META)) return;

        // add properties
        villager.setCustomName(GGRecipes.getConfigString("villagerTokenName"));
        villager.setCustomNameVisible(true);
        villager.setMetadata(TOKEN_META, new FixedMetadataValue(GGRecipes.getInstance(), "value"));

        // reduce tokens
        if (hand.getAmount() > 1) {
            hand.setAmount(hand.getAmount() - 1);
            return;
        }

        player.getInventory().remove(hand);
    }
    //</editor-fold>

}

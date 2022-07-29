package de.jonas.ggrecipes.listener;

import de.jonas.ggrecipes.handler.YeezyHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe dieses {@link YeezyListener} werden alle Events abgefangen, die für das Hinzufügen oder das Entfernen der
 * Effekte, die man durch Yeezys erhalten soll, benötigt werden.
 */
@NotNull
public final class YeezyListener implements Listener {

    //<editor-fold desc="implementation">
    @EventHandler
    public void onJoin(@NotNull final PlayerJoinEvent e) {
        final Player player = e.getPlayer();

        if (YeezyHandler.isNotWearingYeezys(player)) return;

        YeezyHandler.addYeezyEffect(player);
    }

    @EventHandler
    public void onInventoryClose(@NotNull final InventoryCloseEvent e) {
        yeezyAction((Player) e.getPlayer());
    }

    @EventHandler
    public void onToggleSneak(@NotNull final PlayerToggleSneakEvent e) {
        yeezyAction(e.getPlayer());
    }
    //</editor-fold>

    /**
     * Prüft, ob ein Spieler keine Yeezys trägt. Wenn das der Fall ist, werden dem Spieler alle Effekte entfernt und
     * ansonsten werden dem Spieler die Effekte hinzugefügt, die man durch das Tragen von Yeezys erhalten soll.
     *
     * @param player Der Spieler, für den die Yeezy-Aktion ausgeführt werden soll.
     */
    private void yeezyAction(@NotNull final Player player) {
        if (YeezyHandler.isNotWearingYeezys(player)) {
            YeezyHandler.removeYeezyEffect(player);
            return;
        }

        YeezyHandler.addYeezyEffect(player);
    }

}

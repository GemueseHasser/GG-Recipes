package de.jonas;

import de.jonas.ggrecipes.handler.RecipeHandler;
import de.jonas.ggrecipes.handler.YeezyHandler;
import de.jonas.ggrecipes.listener.YeezyListener;
import de.jonas.ggrecipes.handler.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Die Haupt- und Main-Klasse dieses Plugins. {@link GGRecipes} stellt eine Instanz eines {@link JavaPlugin} dar,
 * welches ein Plugin für ein Minecraft Server Netzwerk ist. In dieser Hauptklasse wird das Plugin sowohl initialisiert,
 * als auch deaktiviert bei einem Stoppen des Netzwerks.
 */
@NotNull
public final class GGRecipes extends JavaPlugin {

    //<editor-fold desc="STATIC FIELDS">
    /** Die Instanz dieses {@link GGRecipes Plugins}. */
    private static GGRecipes instance;
    //</editor-fold>


    //<editor-fold desc="setup and start">
    @Override
    public void onEnable() {
        super.onEnable();

        // initialize plugin instance
        instance = this;

        // register yeezy event
        Bukkit.getPluginManager().registerEvents(new YeezyListener(), this);

        // register all recipes
        registerRecipes();
        getLogger().info("Alle Rezepte wurden erfolgreich geladen!");

        // load all player
        for (@NotNull final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (YeezyHandler.isNotWearingYeezys(onlinePlayer)) continue;

            YeezyHandler.addYeezyEffect(onlinePlayer);
        }

        getLogger().info("Alle Nutzer dieses Netzwerks wurden geladen!");
        getLogger().info("Das Plugin wurde erfolgreich aktiviert!");
    }
    //</editor-fold>

    //<editor-fold desc="stop">
    @Override
    public void onDisable() {
        super.onDisable();

        getLogger().info("Das Plugin wurde deaktiviert.");
    }
    //</editor-fold>


    /**
     * Registriert alle Rezepte dieses Plugins.
     */
    private static void registerRecipes() {
        // yeezys
        RecipeHandler.registerRecipe(
            ItemHandler.getStack(
                Material.DIAMOND_BOOTS,
                ChatColor.YELLOW.toString() + ChatColor.BOLD + "Yeezys",
                new String[]{ChatColor.DARK_PURPLE + "Abgenutzt will diese Schuhe keiner anziehen!"},
                Enchantment.DURABILITY,
                3
            ),
            new String[]{"EEE", "EBE", "EEE"},
            Map.of(
                'E', Material.EMERALD,
                'B', Material.LEATHER_BOOTS
            )
        );

        // antique helmet
        registerAntique(
            Material.DIAMOND_HELMET,
            new String[]{"BBB", "BAB", "AAA"},
            Map.of(
                'A', Material.AIR,
                'B', Material.BEACON
            )
        );

        // antique chestplate
        registerAntique(
            Material.DIAMOND_CHESTPLATE,
            new String[]{"BAB", "BBB", "BBB"},
            Map.of(
                'A', Material.AIR,
                'B', Material.BEACON
            )
        );

        // antique leggings
        registerAntique(
            Material.DIAMOND_LEGGINGS,
            new String[]{"BBB", "BAB", "BAB"},
            Map.of(
                'A', Material.AIR,
                'B', Material.BEACON
            )
        );

        // antique boots
        registerAntique(
            Material.DIAMOND_BOOTS,
            new String[]{"AAA", "BAB", "BAB"},
            Map.of(
                'A', Material.AIR,
                'B', Material.BEACON
            )
        );

        // antique sword
        RecipeHandler.registerRecipe(
            ItemHandler.getStack(
                Material.DIAMOND_SWORD,
                ChatColor.YELLOW.toString() + ChatColor.BOLD + "Antikes Schwert",
                new String[]{
                    ChatColor.WHITE + "Dieses Schwert ist Antik.",
                    ChatColor.WHITE + "Es ist über " + ChatColor.RED + "1.000 Jahre" + ChatColor.WHITE + " alt."
                },
                Enchantment.PROTECTION_ENVIRONMENTAL,
                8
            ),
            new String[]{"DBD", "BEB", "DBD"},
            Map.of(
                'D', Material.DRAGON_EGG,
                'B', Material.BEACON,
                'E', Material.END_PORTAL_FRAME
            )
        );

        // cola
        final ItemStack cola = ItemHandler.getStack(
            Material.POTION,
            ChatColor.DARK_PURPLE + "Cola",
            new String[]{ChatColor.WHITE + "Gibt dir einen Energieschub!"},
            null,
            0
        );

        final PotionMeta colaMeta = (PotionMeta) cola.getItemMeta();
        assert colaMeta != null;
        colaMeta.setBasePotionData(new PotionData(PotionType.SPEED));

        cola.setItemMeta(colaMeta);

        RecipeHandler.registerRecipe(
            cola,
            new String[]{"ZZZ", "ZWZ", "MMM"},
            Map.of(
                'Z', Material.SUGAR,
                'W', Material.WATER_BUCKET,
                'M', Material.MAGMA_CREAM
            )
        );

        // stone
        RecipeHandler.registerRecipe(
            new ItemStack(Material.STONE, 8),
            new String[]{"CCC", "CKC", "CCC"},
            Map.of(
                'C', Material.COBBLESTONE,
                'K', Material.COAL
            )
        );
    }

    /**
     * Registriert das Rezept für ein bestimmtes Teil der antiken Rüstung.
     *
     * @param material  Das {@link Material}, aus dem dieses Teil der antiken Rüstung besteht.
     * @param recipe    Das Rezept, mit dem dieses Teil gecraftet werden kann.
     * @param materials Die Definition der Materialien, die in dem Rezept vorkommen.
     */
    private static void registerAntique(
        @NotNull final Material material,
        @NotNull final String[] recipe,
        @NotNull final Map<Character, Material> materials
    ) {
        RecipeHandler.registerRecipe(
            ItemHandler.getStack(
                material,
                ChatColor.YELLOW.toString() + ChatColor.BOLD + "Antike Rüstung",
                new String[]{ChatColor.DARK_PURPLE + "Nur wahre Krieger tragen diese Rüstung!"},
                Enchantment.PROTECTION_ENVIRONMENTAL,
                6
            ),
            recipe,
            materials
        );
    }

    /**
     * Gibt die Instanz dieses {@link GGRecipes Plugins} zurück.
     *
     * @return Die Instanz dieses {@link GGRecipes Plugins}.
     */
    @NotNull
    public static GGRecipes getInstance() {
        return instance;
    }
}

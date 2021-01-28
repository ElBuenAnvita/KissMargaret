package xyz.anvi.kissmargaret;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.anvi.kissmargaret.listeners.PlayerListener;

public final class KissMargaret extends JavaPlugin {
    public static KissMargaret instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info(ChatColor.GREEN + "[KissMargaret] Enabling KissMargaret...");
        try {
            instance = this;
            Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
            Bukkit.getLogger().info(ChatColor.GREEN + "[KissMargaret] Enabled KissMargaret successfully");
        } catch (Exception ex) {
            Bukkit.getLogger().info(ChatColor.RED + "[KissMargaret] NANI?!");
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(ChatColor.RED + "[KissMargaret] Disabling KissMargaret...");
    }
}

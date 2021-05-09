package com.github.nishitox.deadspectators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeadSpectators extends JavaPlugin implements Listener {
    static DeadSpectators plugin;
    static FileConfiguration config;

    static DeadSpectators getPlugin() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        config = this.getConfig();

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("deadspectators").setExecutor(new CommandManager());
    }

    public void onDisable() {
    }

    @EventHandler
    void playerDeath(PlayerDeathEvent event) {
        if (config.getBoolean("deadSpectators")) {
            Player player = event.getEntity().getPlayer();

            player.setGameMode(GameMode.SPECTATOR);
            player.sendTitle(config.getString("deathTitle"), "", 0, 80, 20);
            if (config.getBoolean("skipDeathMenu")) {
                event.setCancelled(true);
            }
            if (config.getBoolean("updateSpawnpoint")) {
                player.setBedSpawnLocation(player.getLocation(), true);
            }
        }
    }

    List<String> getStatus() {
        List<String> result = new ArrayList();
        Iterator var2 = config.getKeys(true).iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            key = "§7 - " + key + ": §c" + config.getString(key);
            result.add(key);
        }
        return result;
    }

    void resetConfig() {
        Configuration defaultConfig = config.getDefaults();
        Iterator var2 = config.getKeys(true).iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            if (key.equals("deathTitle")) {
                config.set(key, defaultConfig.getString(key));
            } else {
                config.set(key, defaultConfig.getBoolean(key));
            }
        }
        saveConfig();
    }

    void setConfig(String key, Object val) {
        config.set(key, val);
        saveConfig();
    }
}

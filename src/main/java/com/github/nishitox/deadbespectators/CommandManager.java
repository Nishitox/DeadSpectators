package com.github.nishitox.deadbespectators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandManager implements CommandExecutor, TabCompleter {
    DeadBeSpectators plugin = DeadBeSpectators.getPlugin();
    String msgHeader = "\n§7[DeadBeSpectators] ";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0)
            return false;

        // config: dead be spectators
        if (args[0].equalsIgnoreCase("enable")) {
            this.plugin.setConfig("deadBeSpectators", true);
            sender.sendMessage(this.msgHeader + "plugin has been enabled!");
            return true;
        }
        if (args[0].equalsIgnoreCase("disable")) {
            this.plugin.setConfig("deadBeSpectators", false);
            sender.sendMessage(this.msgHeader + "plugin has been disabled.");
            return true;
        }

        if (args[0].equalsIgnoreCase("config")) {
            // config: show config
            if (args.length == 1) {
                List<String> status = this.plugin.getStatus();
                sender.sendMessage(this.msgHeader + "output the config: ");
                sender.sendMessage((String[])status.toArray(new String[status.size()]));
                return true;
            }

            // config: reset
            if (args[1].equalsIgnoreCase("reset")) {
                this.plugin.resetConfig();
                sender.sendMessage(this.msgHeader + "config has been reset.");
                return true;
            }

            // config: death title
            if (args[1].equalsIgnoreCase("deathTitle")) {
                StringBuilder title = new StringBuilder();
                for(int i = 2; i < args.length; ++i) {
                    title.append(args[i]);
                    if (i < args.length - 1)
                        title.append(" ");
                }
                this.plugin.setConfig("deathTitle", title.toString());
                sender.sendMessage(this.msgHeader + "deathTitle has been set.");
                return true;
            }

            if (args.length == 3) {
                // config: skip death menu
                if (args[1].equalsIgnoreCase("skipDeathMenu")) {
                    boolean bool = Boolean.parseBoolean(args[2]);
                    this.plugin.setConfig("skipDeathMenu", bool);
                    sender.sendMessage(this.msgHeader + "skipDeathMenu has been set to " + bool + ".");
                    return true;
                }

                // config: update spawnpoint
                if (args[1].equalsIgnoreCase("updateSpawnpoint")) {
                    boolean bool = Boolean.parseBoolean(args[2]);
                    this.plugin.setConfig("updateSpawnpoint", bool);
                    sender.sendMessage(this.msgHeader + "updateSpawnpoint has been set to " + bool + ".");
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> result = new ArrayList();
        String[] cmds = new String[]{
                "enable",
                "disable",
                "config"
        };
        String[] config = new String[]{
                "deathTitle",
                "reset",
                "skipDeathMenu",
                "updateSpawnpoint"
        };
        String[] bool = new String[]{
                "true",
                "false"
        };

        if (args.length == 1) {
            Collections.addAll(result, cmds);
        }

        if (args[0].equalsIgnoreCase("config")) {
            if (args.length == 2) {
                Collections.addAll(result, config);
            }

            if (args.length == 3 && (
                    args[1].equalsIgnoreCase("skipDeathMenu") ||
                    args[1].equalsIgnoreCase("updateSpawnpoint"))) {
                Collections.addAll(result, bool);
            }
        }
        return result;
    }
}

package org.xomakdev.foredocuments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * This file is part of ForeDocuments.
 *
 * ForeDocuments is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Additional Condition: This software and all derivative works may not be used
 * for commercial purposes, including selling, licensing, or distributing for profit.
 *
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/agpl-3.0.html>.
 */

public class ForeDocumentsCommand implements CommandExecutor, TabCompleter {
    private final ForeDocuments plugin;

    public ForeDocumentsCommand(ForeDocuments plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getInstance().getConfig().getString("prefix");
        if (!(sender instanceof Player) || sender.hasPermission("foredocument.use-admin")) {
            if (args.length < 1) {
                sender.sendMessage(cr.color(this.plugin.getInstance().getConfig().getString("usage_cmd", "%PREFIX% &fИспользование: /foredocuments <&egive&f/&etake&f/&eupdatedata&f> [&eдокумент&f] [&eигрок&f]")
                        .replace("%PREFIX%", this.plugin.getInstance().getConfig().getString("prefix"))));
                return true;
            }

            Data data = this.plugin.getData();
            String subCommand = args[0].toLowerCase();

            switch (subCommand) {
                case "updatedata" -> {
                    data.updateAllPlayers();
                    sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("updatedata_message", "%PREFIX% &aДанные обновлены!")
                            .replace("%PREFIX%", prefix)));
                    return true;
                }
                case "reload" -> {
                    plugin.getInstance().reloadConfig();
                    if (plugin.getInstance().getConfig().getBoolean("reload_with_updatedata", false)) {
                        data.updateAllPlayers();
                    }
                    sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("reload_message", "%PREFIX% &aКонфигурация успешно перезагружена.")
                            .replace("%PREFIX%", prefix)));
                    return true;
                }
                case "give", "take" -> {
                    if (args.length < 3) {
                        sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("usage_cmd_length", "%PREFIX% &fИспользование: /foredocuments <&egive&f/&etake&f> <&eдокумент&f> <&eигрок&f>")
                                .replace("%PREFIX%", prefix)));
                        return true;
                    }

                    String document = args[1];
                    Player target = Bukkit.getPlayer(args[2]);

                    if (target == null) {
                        sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("", "%PREFIX% &cИгрок не найден!")
                                .replace("%PREFIX%", prefix)));
                        return true;
                    }

                    if (!plugin.getConfig().getConfigurationSection("documents").contains(document)) {
                        sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("document_not_found", "%PREFIX% &cДокумент &o&n%DOCUMENT%&r &cне найден в конфигурации!")
                                .replace("%PREFIX%", prefix)
                                .replace("%DOCUMENT%", document)));
                        return true;
                    }

                    boolean give = subCommand.equals("give");
                    data.setDocument(target, document, give);

                    String commandToExecute = plugin.getConfig().getString("documents." + document + (give ? ".give_execute" : ".take_execute"));
                    if (commandToExecute != null && !commandToExecute.isEmpty()) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToExecute.replace("%PLAYER%", target.getName()));
                    }

                    sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("document_message", "%PREFIX% &fДокумент &b%DOCUMENT% &r%STATUS% &fу игрока &2%PLAYER%")
                                    .replace("%PREFIX%", prefix)
                                    .replace("%DOCUMENT%", document)
                                    .replace("%STATUS%", give ? this.plugin.getInstance().getConfig().getString("give_document", "&aвыдан") :
                                            plugin.getInstance().getConfig().getString("withdrawn_document", "&cизъят"))
                                    .replace("%PLAYER%", target.getName())));
                    return true;
                }
                default -> {
                    sender.sendMessage(cr.color(plugin.getInstance().getConfig().getString("usage_cmd", "%PREFIX% &fИспользование: /foredocuments <&egive&f/&etake&f/&eupdatedata&f> [&eдокумент&f] [&eигрок&f]")
                            .replace("%PREFIX%", prefix)));
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("give");
            completions.add("take");
            completions.add("updatedata");
            completions.add("reload");
        } else if (args.length == 2 && (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take"))) {
            completions.addAll(plugin.getConfig().getConfigurationSection("documents").getKeys(false));
        } else if (args.length == 3 && (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take"))) {
            completions.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
        }

        return completions;
    }
}

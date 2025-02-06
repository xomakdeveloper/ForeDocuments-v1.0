package org.xomakdev.foredocuments;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

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

public class ForeDocuments extends JavaPlugin {
    private static ForeDocuments instance;
    private Data data;
    private ForeDocumentsPlaceholder placeholderExpansion;

    @Override
    public void onEnable() {
        instance = this;
        data = new Data(this);
        saveDefaultConfig();

        Bukkit.getLogger().info(cr.color(" &2███████╗ ██████╗ ██████╗ ███████╗  ██████╗  ██████╗  ██████╗██╗   ██╗███╗   ███╗███████╗███╗   ██╗████████╗███████╗"));
        Bukkit.getLogger().info(cr.color(" &2██╔════╝██╔═══██╗██╔══██╗██╔════╝  ██╔══██╗██╔═══██╗██╔════╝██║   ██║████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██╔════╝"));
        Bukkit.getLogger().info(cr.color(" &2█████╗  ██║   ██║██████╔╝█████╗    ██║  ██║██║   ██║██║     ██║   ██║██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ███████╗"));
        Bukkit.getLogger().info(cr.color(" &2██╔══╝  ██║   ██║██╔══██╗██╔══╝    ██║  ██║██║   ██║██║     ██║   ██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ╚════██║"));
        Bukkit.getLogger().info(cr.color(" &2██║     ╚██████╔╝██║  ██║███████╗  ██████╔╝╚██████╔╝╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ███████║"));
        Bukkit.getLogger().info(cr.color(" &2╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝"));
        Bukkit.getLogger().info(cr.color(" &2Fore Documents &7& &2v" + getDescription().getVersion() + " &fby xomakdev.exe"));
        //Bukkit.getLogger().info(cr.color(" &fStatus: &aEnabled"));
        Bukkit.getLogger().info(cr.color(" &fSupport? - &9Discord: xomakdeveloperr &7& &bTelegram: @kofead"));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getLogger().severe(cr.color("&7[&c-&7]&cPlaceholderAPI не найден! Плагин выключается..."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        placeholderExpansion = new ForeDocumentsPlaceholder(this);
        placeholderExpansion.register();

        getCommand("foredocuments").setExecutor(new ForeDocumentsCommand(this));
        getCommand("foredocuments").setTabCompleter(new ForeDocumentsCommand(this));
    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info(cr.color(" &2███████╗ ██████╗ ██████╗ ███████╗  ██████╗  ██████╗  ██████╗██╗   ██╗███╗   ███╗███████╗███╗   ██╗████████╗███████╗"));
        Bukkit.getLogger().info(cr.color(" &2██╔════╝██╔═══██╗██╔══██╗██╔════╝  ██╔══██╗██╔═══██╗██╔════╝██║   ██║████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██╔════╝"));
        Bukkit.getLogger().info(cr.color(" &2█████╗  ██║   ██║██████╔╝█████╗    ██║  ██║██║   ██║██║     ██║   ██║██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ███████╗"));
        Bukkit.getLogger().info(cr.color(" &2██╔══╝  ██║   ██║██╔══██╗██╔══╝    ██║  ██║██║   ██║██║     ██║   ██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ╚════██║"));
        Bukkit.getLogger().info(cr.color(" &2██║     ╚██████╔╝██║  ██║███████╗  ██████╔╝╚██████╔╝╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ███████║"));
        Bukkit.getLogger().info(cr.color(" &2╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝"));
        Bukkit.getLogger().info(cr.color(" &2Fore Documents &7& &2v" + getDescription().getVersion() + " &fby xomakdev.exe"));
        //Bukkit.getLogger().info(cr.color(" &fStatus: &4Disabled"));
        Bukkit.getLogger().info(cr.color(" &fSupport? - &9Discord: xomakdeveloperr &7& &bTelegram: @kofead"));

        if (placeholderExpansion != null) {
            placeholderExpansion.unregister();
        }
        data.saveData();
    }

    public ForeDocuments getInstance() {
        return instance;
    }

    public Data getData() {
        return data;
    }
}
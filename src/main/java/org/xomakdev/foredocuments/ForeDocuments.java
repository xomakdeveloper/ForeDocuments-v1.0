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
    private File licenseFile;
    private FileConfiguration licenseConfig;
    private ForeDocumentsPlaceholder placeholderExpansion;

    @Override
    public void onEnable() {
        instance = this;
        data = new Data(this);
        saveDefaultConfig();
        saveDefaultLicense();

        Bukkit.getLogger().info(cr.color("&2███████╗ ██████╗ ██████╗ ███████╗  ██████╗  ██████╗  ██████╗██╗   ██╗███╗   ███╗███████╗███╗   ██╗████████╗███████╗"));
        Bukkit.getLogger().info(cr.color("&2██╔════╝██╔═══██╗██╔══██╗██╔════╝  ██╔══██╗██╔═══██╗██╔════╝██║   ██║████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██╔════╝"));
        Bukkit.getLogger().info(cr.color("&2█████╗  ██║   ██║██████╔╝█████╗    ██║  ██║██║   ██║██║     ██║   ██║██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ███████╗"));
        Bukkit.getLogger().info(cr.color("&2██╔══╝  ██║   ██║██╔══██╗██╔══╝    ██║  ██║██║   ██║██║     ██║   ██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ╚════██║"));
        Bukkit.getLogger().info(cr.color("&2██║     ╚██████╔╝██║  ██║███████╗  ██████╔╝╚██████╔╝╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ███████║"));
        Bukkit.getLogger().info(cr.color("&2╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color("&2Fore Documents &7& &2v" + getDescription().getVersion()));
        Bukkit.getLogger().info(cr.color("&fCreated by xomakdev.exe ( xomakdeveloper ) with &clove"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color("&fStatus: &aEnabled"));
        Bukkit.getLogger().info(cr.color("&fSupport? - &9Discord: xomakdeveloperr &7& &bTelegram: @kofead"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Copyright (c) 2025 &cXomakDeveloper."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" This software is released under the AGPLv3 License with an additional non-commercial clause."));
        Bukkit.getLogger().info(cr.color(" You are free to use, modify, and distribute it as long as it is not for commercial purposes."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" See the LICENSE file for more details on the AGPLv3 and the additional clause."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" ------------------------------------------------------------------"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Авторские права (c) 2025 &cXomakDeveloper."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Это программное обеспечение распространяется под лицензией AGPLv3 с дополнительным условием о запрете коммерческого использования."));
        Bukkit.getLogger().info(cr.color(" Вы можете свободно использовать, изменять и распространять его, при условии, что это не будет происходить в коммерческих целях."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Подробнее читайте в файле LICENSE об условиях AGPLv3 и дополнительном условии."));
        Bukkit.getLogger().info(cr.color(" "));

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


        Bukkit.getLogger().info(cr.color("&2███████╗ ██████╗ ██████╗ ███████╗  ██████╗  ██████╗  ██████╗██╗   ██╗███╗   ███╗███████╗███╗   ██╗████████╗███████╗"));
        Bukkit.getLogger().info(cr.color("&2██╔════╝██╔═══██╗██╔══██╗██╔════╝  ██╔══██╗██╔═══██╗██╔════╝██║   ██║████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██╔════╝"));
        Bukkit.getLogger().info(cr.color("&2█████╗  ██║   ██║██████╔╝█████╗    ██║  ██║██║   ██║██║     ██║   ██║██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ███████╗"));
        Bukkit.getLogger().info(cr.color("&2██╔══╝  ██║   ██║██╔══██╗██╔══╝    ██║  ██║██║   ██║██║     ██║   ██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ╚════██║"));
        Bukkit.getLogger().info(cr.color("&2██║     ╚██████╔╝██║  ██║███████╗  ██████╔╝╚██████╔╝╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ███████║"));
        Bukkit.getLogger().info(cr.color("&2╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color("&2Fore Documents &7& &2v" + getDescription().getVersion()));
        Bukkit.getLogger().info(cr.color("&fCreated by xomakdev.exe ( xomakdeveloper ) with &clove"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color("&fStatus: &4Disabled"));
        Bukkit.getLogger().info(cr.color("&fSupport? - &9Discord: xomakdeveloperr &7& &bTelegram: @kofead"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Copyright (c) 2025 &cXomakDeveloper."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" This software is released under the AGPLv3 License with an additional non-commercial clause."));
        Bukkit.getLogger().info(cr.color(" You are free to use, modify, and distribute it as long as it is not for commercial purposes."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" See the LICENSE file for more details on the AGPLv3 and the additional clause."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" ------------------------------------------------------------------"));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Авторские права (c) 2025 &cXomakDeveloper."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Это программное обеспечение распространяется под лицензией AGPLv3 с дополнительным условием о запрете коммерческого использования."));
        Bukkit.getLogger().info(cr.color(" Вы можете свободно использовать, изменять и распространять его, при условии, что это не будет происходить в коммерческих целях."));
        Bukkit.getLogger().info(cr.color(" "));
        Bukkit.getLogger().info(cr.color(" Подробнее читайте в файле LICENSE об условиях AGPLv3 и дополнительном условии."));
        Bukkit.getLogger().info(cr.color(" "));

        if (placeholderExpansion != null) {
            placeholderExpansion.unregister();
        }
        data.saveData();
    }

    private void saveDefaultLicense() {
        licenseFile = new File(getDataFolder(), "LICENSE");
        if (!licenseFile.exists()) {
            saveResource("LICENSE", false);
        }
        licenseConfig = YamlConfiguration.loadConfiguration(licenseFile);
    }

    public ForeDocuments getInstance() {
        return instance;
    }

    public Data getData() {
        return data;
    }
}
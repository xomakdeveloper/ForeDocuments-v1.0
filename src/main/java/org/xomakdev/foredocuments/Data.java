package org.xomakdev.foredocuments;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

public class Data {
    private final ForeDocuments plugin;
    private final File dataFile;
    private FileConfiguration dataConfig;

    public Data(ForeDocuments plugin) {
        this.plugin = plugin;
        dataFile = new File(plugin.getDataFolder(), "data.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void saveData() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasDocument(Player player, String document) {
        return dataConfig.getBoolean(player.getUniqueId() + ".has_documents." + document + ".value", false);
    }

    public void setDocument(Player player, String document, boolean value) {
        String path = player.getUniqueId() + ".has_documents." + document + ".value";
        dataConfig.set(path, value);
        dataConfig.set(player.getUniqueId() + ".player_name", player.getName());
        saveData();
    }

    public void updateAllPlayers() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            String path = uuid + ".has_documents";
            for (String doc : plugin.getConfig().getConfigurationSection("documents").getKeys(false)) {
                if (!dataConfig.contains(path + "." + doc)) {
                    dataConfig.set(path + "." + doc + ".value", false);
                }
            }
        }
        saveData();
    }
}

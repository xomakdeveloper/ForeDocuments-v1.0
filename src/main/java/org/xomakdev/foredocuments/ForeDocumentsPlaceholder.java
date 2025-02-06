package org.xomakdev.foredocuments;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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


public class ForeDocumentsPlaceholder extends PlaceholderExpansion {
    private final ForeDocuments plugin;

    public ForeDocumentsPlaceholder(ForeDocuments plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "foredocuments";
    }

    @Override
    public @NotNull String getAuthor() {
        return "xomakdev";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null || params.isEmpty()) {
            return null;
        }

        String document = params.toLowerCase();
        if (!plugin.getConfig().getConfigurationSection("documents").contains(document)) {
            return cr.color(plugin.getConfig().getString("placeholder_not_found", "&cДокумент не найден"));
        }

        return cr.color(plugin.getData().hasDocument(player.getPlayer(), document)
                ? plugin.getConfig().getString("documents." + document + ".value_if_has", "&aИмеется")
                : plugin.getConfig().getString("documents." + document + ".value_if_missing", "&cОтсутствует"));
    }
}

package com.ordwen.odailyquests.apis.hooks.items;

import org.bukkit.Bukkit;

public class OraxenHook {

    /**
     * Check if Oraxen is enabled.
     * @return true if Oraxen is enabled.
     */
    public static boolean isOraxenSetup() {
        return Bukkit.getPluginManager().isPluginEnabled("Oraxen");
    }
}

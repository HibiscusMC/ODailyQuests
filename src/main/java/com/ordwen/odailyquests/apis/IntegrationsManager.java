package com.ordwen.odailyquests.apis;

import com.ordwen.odailyquests.ODailyQuests;
import com.ordwen.odailyquests.apis.hooks.items.OraxenHook;
import com.ordwen.odailyquests.apis.hooks.eco.VaultHook;
import com.ordwen.odailyquests.apis.hooks.holograms.HolographicDisplaysHook;
import com.ordwen.odailyquests.apis.hooks.mobs.EliteMobsHook;
import com.ordwen.odailyquests.apis.hooks.mobs.MythicMobsHook;
import com.ordwen.odailyquests.apis.hooks.npcs.CitizensHook;
import com.ordwen.odailyquests.apis.hooks.placeholders.PlaceholderAPIHook;
import com.ordwen.odailyquests.apis.hooks.points.PlayerPointsHook;
import com.ordwen.odailyquests.apis.hooks.points.TokenManagerHook;
import com.ordwen.odailyquests.apis.hooks.stackers.WildStackerHook;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getServer;

public class IntegrationsManager {

    private final ODailyQuests oDailyQuests;

    public IntegrationsManager(ODailyQuests oDailyQuests) {
        this.oDailyQuests = oDailyQuests;
    }

    /**
     * Load all dependencies.
     */
    public void loadAllDependencies() {
        loadVault();
        loadEliteMobs();
        loadMythicMobs();
        loadPointsPlugin();
        loadCitizens();
        loadHolographicDisplays();
        loadPAPI();
        loadWildStacker();
        loadRoseStacker();
        loadOraxen();
    }

    /**
     * Load WildStacker.
     */
    private void loadWildStacker() {
        if (WildStackerHook.isWildStackerSetup()) {
            PluginLogger.info("WildStacke successfully hooked.");
        }
    }

    /**
     * Load RoseStacker.
     */
    private void loadRoseStacker() {
        if (WildStackerHook.isWildStackerSetup()) {
            PluginLogger.info("RoseStacker successfully hooked.");
        }
    }

    /**
     * Hook - MythicMobs
     */
    private void loadMythicMobs() {
        if (MythicMobsHook.isMythicMobsSetup()) {
            PluginLogger.info("MythicMobs successfully hooked.");
        }
    }

    /**
     * Hook - EliteMobs
     */
    private void loadEliteMobs() {
        if (EliteMobsHook.isEliteMobsSetup()) {
            PluginLogger.info("EliteMobs successfully hooked.");
        }
    }

    /**
     * Hook - TokenManager / PlayerPoints
     */
    private void loadPointsPlugin() {
        if (!TokenManagerHook.setupTokenManager()) {
            PlayerPointsHook.setupPlayerPointsAPI();
            if (PlayerPointsHook.isPlayerPointsSetup()) {
                PluginLogger.info("PlayerPoints successfully hooked.");
            } else {
                PluginLogger.warn("No compatible plugin detected for reward type 'POINTS'.");
                PluginLogger.warn("Quests with reward type 'POINTS' will not work.");
            }
        } else {
            PluginLogger.info("TokenManager successfully hooked.");
        }
    }

    /**
     * Hook - Vault
     */
    private void loadVault() {
        if (!VaultHook.setupEconomy()) {
            PluginLogger.warn("No compatible plugin detected for reward type 'MONEY'.");
            PluginLogger.warn("Quests with reward type 'MONEY' will not work.");
        } else {
            PluginLogger.info("Vault successfully hooked.");
        }
    }

    /**
     * Hook - Citizens
     */
    private void loadCitizens() {
        if (CitizensHook.setupCitizens()) {
            getServer().getPluginManager().registerEvents(new CitizensHook(), oDailyQuests);
            PluginLogger.info("Citizens successfully hooked.");
        } else
            PluginLogger.warn("Citizens not detected. NPCs will not work.");
    }

    /**
     * Hook - HolographicDisplays
     */
    private void loadHolographicDisplays() {
        if (HolographicDisplaysHook.isHolographicDisplaysSetup()) {
            PluginLogger.info("HolographicDisplays successfully hooked.");
        } else
            PluginLogger.warn("HolographicDisplays not detected. Holograms will not work.");
    }

    /**
     * Hook - PlaceholderAPI
     */
    private void loadPAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook(oDailyQuests).register();
            PluginLogger.info("PlaceholderAPI successfully hooked.");
        } else
            PluginLogger.warn("PlaceholderAPI not detected. Placeholders will not work.");
    }

    /**
     * Hook - Oraxen
     */
    private void loadOraxen() {
        if (OraxenHook.isOraxenSetup()) {
            PluginLogger.info("Oraxen successfully hooked.");
        }
    }
}

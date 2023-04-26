package org.frontleaves.xf_tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerActivityListener implements Listener {
    @EventHandler
    public void onJoinServer(PlayerJoinEvent Player) {
        Player.setJoinMessage(String.format("§7[§2+§7]§3 %s", Player.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onQuitServer(PlayerQuitEvent Player) {
        Player.setQuitMessage(String.format("§7[§c-§7]§3 %s", Player.getPlayer().getDisplayName()));
    }
}

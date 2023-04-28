package org.frontleaves.xf_tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.frontleaves.xf_tools.XF_Tools;

public class PlayerActivityListener implements Listener {

    protected XF_Tools Tools;

    public PlayerActivityListener(XF_Tools Tools) {
        this.Tools = Tools;
    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent Player) {
        Object[] PlayerData = {Player.getPlayer().getName(), 0};
        Tools.XF_Player.add(PlayerData);
        Player.setJoinMessage(String.format("§7[§2+§7]§3 %s", Player.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onQuitServer(PlayerQuitEvent Player) {
        for (int i = 0; Tools.XF_Player.get(i).length != 0; i++) {
            Object[] User = Tools.XF_Player.get(i);
            if (User[0] == Player.getPlayer().getName()) {
                Tools.XF_Player.remove(i);
                break;
            }
        }
        Player.setQuitMessage(String.format("§7[§c-§7]§3 %s", Player.getPlayer().getDisplayName()));
    }
}

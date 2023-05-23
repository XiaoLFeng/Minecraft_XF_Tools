package org.frontleaves.xf_tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.frontleaves.xf_tools.XF_Tools;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public class PlayerActivityListener implements Listener {

    protected XF_Tools tools;

    public PlayerActivityListener(XF_Tools tools) {
        this.tools = tools;
    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent player) {
        Object[] playerData = {player.getPlayer().getName(), 0};
        tools.xfPlayer.add(playerData);
        player.setJoinMessage(String.format("§7[§2+§7]§3 %s", player.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onQuitServer(PlayerQuitEvent player) {
        for (int i = 0; tools.xfPlayer.get(i).length != 0; i++) {
            Object[] user = tools.xfPlayer.get(i);
            if (user[0] == player.getPlayer().getName()) {
                tools.xfPlayer.remove(i);
                break;
            }
        }
        player.setQuitMessage(String.format("§7[§c-§7]§3 %s", player.getPlayer().getDisplayName()));
    }
}

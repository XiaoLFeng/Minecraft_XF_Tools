package org.frontleaves.xf_tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.frontleaves.xf_tools.XF_Tools;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public class DropEvent implements Listener {

    protected XF_Tools tools;

    public DropEvent(XF_Tools plugins) {
        this.tools = plugins;
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent player) {
        if (tools.getConfig().getBoolean("Plugin_Drop")) {
            int i = 0;
            Object[] user = new Object[2];
            for (; tools.xfPlayer.get(i).length != 0; i++) {
                user = tools.xfPlayer.get(i);
                if (user[0] == player.getPlayer().getName()) {
                    break;
                }
            }
            if ((int) user[1] == 0) {
                player.setCancelled(true);
                if (player.isCancelled()) {
                    player.getPlayer().sendMessage(String.format("%s§r%s §r§c丢东西保护，如果确定需要丢物品输入 §6/xf-drop §c开启丢物品", tools.xfPrefix, tools.xfPrefixArrow));
                }
            }
        } else {
            player.getPlayer().sendMessage(String.format("%s§r%s §r§c服务器没有开启服务器掉落保护", tools.xfPrefix, tools.xfPrefixArrow));
        }
    }
}

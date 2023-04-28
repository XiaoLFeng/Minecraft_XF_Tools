package org.frontleaves.xf_tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.frontleaves.xf_tools.XF_Tools;

public class DropEvent implements Listener {

    protected XF_Tools Tools;

    public DropEvent(XF_Tools Tools) {
        this.Tools = Tools;
    }

    @EventHandler
    public void Drop(PlayerDropItemEvent Player) {
        int i = 0;
        Object[] User = new Object[2];
        for (; Tools.XF_Player.get(i).length != 0; i++) {
            User = Tools.XF_Player.get(i);
            if (User[0] == Player.getPlayer().getName()) {
                break;
            }
        }
        if ((int) User[1] == 0) {
            Player.setCancelled(true);
            if (Player.isCancelled())
                Player.getPlayer().sendMessage(String.format("%s§r%s §r§c丢东西保护，如果确定需要丢物品输入 §6/xf-drop §c开启丢物品", Tools.XF_Prefix, Tools.XF_PrefixArrow));
        }
    }
}

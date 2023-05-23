package org.frontleaves.xf_tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.frontleaves.xf_tools.XF_Tools;

public class CommandEvent implements Listener {

    private final XF_Tools Tools;

    public CommandEvent(XF_Tools Tools) {
        this.Tools = Tools;
    }

    @EventHandler
    public void PlayerCommandEvent(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        // 将数据输出并载入数据库
        if (Tools.UseSQL) {

        } else {
            Tools.getLogger().warning(String.format("§r玩家 %s 输入了 %s", playerCommandPreprocessEvent.getPlayer().getDisplayName(), playerCommandPreprocessEvent.getMessage()));
        }
    }

    @EventHandler
    public void PlayerCommandForDropEvent(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        // 检查数据内容
        if (playerCommandPreprocessEvent.getMessage().equals("/drop")) {
            playerCommandPreprocessEvent.setMessage("/xf-drop off");
        }
        if (playerCommandPreprocessEvent.getMessage().equals("/drop-on")) {
            playerCommandPreprocessEvent.setMessage("/xf-drop on");
        }
    }
}

package org.frontleaves.xf_tools.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.frontleaves.xf_tools.XF_Tools;

import java.util.regex.Pattern;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public class CommandEvent implements Listener {

    private final XF_Tools tools;

    public CommandEvent(XF_Tools plugins) {
        this.tools = plugins;
    }

    @EventHandler
    public void playerCommandEvent(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        // 将数据输出并载入数据库
        if (tools.useSql) {
            tools.getLogger().warning(String.format("§r玩家 %s 输入了 %s", playerCommandPreprocessEvent.getPlayer().getDisplayName(), playerCommandPreprocessEvent.getMessage()));
        } else {
            tools.getLogger().warning(String.format("§r玩家 %s 输入了 %s", playerCommandPreprocessEvent.getPlayer().getDisplayName(), playerCommandPreprocessEvent.getMessage()));
        }
    }

    @EventHandler
    public void playerCommandForDropEvent(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        // 检查数据内容
        if ("/drop".equals(playerCommandPreprocessEvent.getMessage())) {
            playerCommandPreprocessEvent.setMessage("/xf-drop off");
        }
        if ("/drop-on".equals(playerCommandPreprocessEvent.getMessage())) {
            playerCommandPreprocessEvent.setMessage("/xf-drop on");
        }
    }

    /**
     * 检查玩家是否输入了 <b>//calc</b> 指令
     * <br>
     * 注意：这里是玩家，管理员默认被允许操作
     *
     * @param playerCommandPreprocessEvent 输入指令的玩家
     */
    @EventHandler
    public void playerCommandForCalcEvent(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        if (Pattern.matches("^//calc",playerCommandPreprocessEvent.getMessage())) {
            if (playerCommandPreprocessEvent instanceof Player && !playerCommandPreprocessEvent.getPlayer().isOp()) {
                playerCommandPreprocessEvent.setCancelled(true);
                if (playerCommandPreprocessEvent.isCancelled()) {
                    playerCommandPreprocessEvent.getPlayer().sendMessage(String.format("%s§r%s §r§c玩家不能够直接执行此命令！", tools.xfPrefix, tools.xfPrefixArrow));
                }
            }
        }
    }
}

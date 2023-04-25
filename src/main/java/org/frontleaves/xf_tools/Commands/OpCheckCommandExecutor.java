package org.frontleaves.xf_tools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.frontleaves.xf_tools.XF_Tools;

public class OpCheckCommandExecutor implements CommandExecutor {

    public XF_Tools Tools;

    public OpCheckCommandExecutor(XF_Tools plugins) {
        this.Tools = plugins;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("op")) {
            if (strings.length == 0) {
                if (commandSender.isOp()) {
                    commandSender.sendMessage(String.format("%s§r%s §r§c我不建议你赋予管理员！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                } else {
                    commandSender.sendMessage(String.format("%s§r%s §r§c你可不是管理员哦！这个指令不要用，别怪我没提醒你！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                }
            } else {
                if (commandSender instanceof ConsoleCommandSender) {
                    commandSender.sendMessage(String.format("%s§r%s §r§c此命令被禁止，请使用 §6/xf-op §c查看帮助！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                } else {
                    commandSender.sendMessage(String.format("%s§r%s §r§c禁止游戏内赋予管理员！此命令被禁止", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                }
            }
        }
        return true;
    }
}

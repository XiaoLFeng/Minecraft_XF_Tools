package org.frontleaves.xf_tools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.frontleaves.xf_tools.XF_Tools;

import java.util.Objects;

public class MenuCommandExecutor implements CommandExecutor {

    public XF_Tools Tools;
    public MenuCommandExecutor(XF_Tools Tools) {
        this.Tools = Tools;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("xftool")
                || command.getName().equalsIgnoreCase("xftl")
                || command.getName().equalsIgnoreCase("xf-tool")
                || command.getName().equalsIgnoreCase("xf-tl")) {
            if (strings.length == 0) {
                commandSender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==",Tools.XF_Prefix));
                commandSender.sendMessage(String.format("§3筱锋工具箱[XF_Tool]（版本号：%s）", Tools.XF_Version));
                commandSender.sendMessage(String.format("§3作者：%s", Tools.XF_Author));
                commandSender.sendMessage("§3博客：§ehttps://www.blog.x-lf.com/");
                commandSender.sendMessage("§2输入 §6/xf-tool help §2 查看插件帮助吧");
                commandSender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==",Tools.XF_Prefix));
            } else {
                if (Objects.equals(strings[0], "help")) {
                    if (strings.length == 1) {
                        commandSender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==",Tools.XF_Prefix));
                        commandSender.sendMessage("§6/xf-tool §7- 查看插件信息");
                        commandSender.sendMessage("§6/xf-tool help [page] §7- 查看指令帮助");
                        commandSender.sendMessage("§6/xf-tool setting §7- 插件基础设置");
                        commandSender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==",Tools.XF_Prefix));
                        commandSender.sendMessage("§a页码： §71§8/§71");
                    } else if (strings.length == 2) {
                        if (Objects.equals(strings[1], "1")) {
                            commandSender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==",Tools.XF_Prefix));
                            commandSender.sendMessage("§6/xf-tool §7- 查看插件信息");
                            commandSender.sendMessage("§6/xf-tool help [page] §7- 查看指令帮助");
                            commandSender.sendMessage("§6/xf-tool op §7- 赋予OP操作");
                            commandSender.sendMessage("§6/xf-tool setting §7- 插件基础设置");
                            commandSender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==",Tools.XF_Prefix));
                            commandSender.sendMessage("§a页码： §71§8/§71");
                        } else commandSender.sendMessage(String.format("%s§r%s §r§c页码超出范围！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    } else {
                        commandSender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    }
                } else if (Objects.equals(strings[0], "setting")) {

                }
            }
        }
        return true;
    }
}

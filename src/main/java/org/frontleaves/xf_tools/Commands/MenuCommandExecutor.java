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
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("xftool")
                || command.getName().equalsIgnoreCase("xftl")
                || command.getName().equalsIgnoreCase("xf-tool")
                || command.getName().equalsIgnoreCase("xf-tl")) {
            System.out.println();
            if (strings.length == 0) {
                sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                sender.sendMessage(String.format("§3筱锋工具箱[XF_Tool]（版本号：%s）", Tools.XF_Version));
                sender.sendMessage(String.format("§3作者：%s", Tools.XF_Author));
                sender.sendMessage("§3博客：§ehttps://blog.x-lf.com/");
                sender.sendMessage("§2输入 §6/xf-tool help §2 查看插件帮助吧");
                sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
            } else {
                if (Objects.equals(strings[0], "help")) {
                    if (strings.length == 1) {
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                        sender.sendMessage("§6/xf-tool §7- 查看插件信息");
                        sender.sendMessage("§6/xf-tool help [page] §7- 查看指令帮助");
                        sender.sendMessage("§6/xf-tool drop §7- 丢弃管理");
                        sender.sendMessage("§6/xf-op §7- OP管理");
                        sender.sendMessage("§6/xf-tool setting §7- 插件基础设置");
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                        sender.sendMessage("§a页码： §71§8/§71");
                    } else if (strings.length == 2) {
                        if (Objects.equals(strings[1], "1")) {
                            sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                            sender.sendMessage("§6/xf-tool §7- 查看插件信息");
                            sender.sendMessage("§6/xf-tool help [page] §7- 查看指令帮助");
                            sender.sendMessage("§6/xf-op §7- OP管理");
                            sender.sendMessage("§6/xf-tool setting §7- 插件基础设置");
                            sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                            sender.sendMessage("§a页码： §71§8/§71");
                        } else sender.sendMessage(String.format("%s§r%s §r§c页码超出范围！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    }
                } else if (Objects.equals(strings[0], "setting")) {
                    sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                    sender.sendMessage("§6/xf-tool update §7- 查看是否有可选更新");
                    sender.sendMessage("§6/xf-tool version §7- 查看插件版本");
                    sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                } else if (Objects.equals(strings[0], "update"))
                    sender.sendMessage(String.format("%s§r%s §r§c很抱歉，我还没有做接口哈！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                else if (Objects.equals(strings[0], "drop")) {
                    if (Tools.getConfig().getBoolean("Plugin_Drop")) {
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                        sender.sendMessage("§6/xf-drop §7- 解除丢弃物品");
                        sender.sendMessage("§6/xf-drop <on or off> §7- 设置是否允许丢弃物品");
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==",Tools.XF_Prefix));
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c服务器没有开启服务器掉落保护", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    }
                } else if (Objects.equals(strings[0],"version"))
                    sender.sendMessage(String.format("%s§r%s §r§3版本号：%s", Tools.XF_Prefix, Tools.XF_PrefixArrow,Tools.XF_Version));
                else sender.sendMessage(String.format("%s§r%s §r§c参数错误请输入 §6/xf-tool help §c查看帮助", Tools.XF_Prefix, Tools.XF_PrefixArrow));
            }
        }
        return true;
    }
}

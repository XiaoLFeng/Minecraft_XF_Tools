package org.frontleaves.xf_tools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.frontleaves.xf_tools.XF_Tools;

import java.util.Objects;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public class MenuCommandExecutor implements CommandExecutor {
    public XF_Tools tools;
    public MenuCommandExecutor(XF_Tools plugins) {
        this.tools = plugins;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if ("xftool".equalsIgnoreCase(command.getName())
                || "xftl".equalsIgnoreCase(command.getName())
                || "xf-tool".equalsIgnoreCase(command.getName())
                || "xf-tl".equalsIgnoreCase(command.getName())) {
            System.out.println();
            if (strings.length == 0) {
                sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                sender.sendMessage(String.format("§3筱锋工具箱[XF_Tool]（版本号：%s）", tools.xfVersion));
                sender.sendMessage(String.format("§3作者：%s", tools.xfAuthor));
                sender.sendMessage("§3博客：§ehttps://blog.x-lf.com/");
                sender.sendMessage("§2输入 §6/xf-tool help §2 查看插件帮助吧");
                sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
            } else {
                if (Objects.equals(strings[0], "help")) {
                    if (strings.length == 1) {
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                        sender.sendMessage("§6/xf-tool §7- 查看插件信息");
                        sender.sendMessage("§6/xf-tool help [page] §7- 查看指令帮助");
                        sender.sendMessage("§6/xf-tool drop §7- 丢弃管理");
                        sender.sendMessage("§6/xf-op §7- OP管理");
                        sender.sendMessage("§6/xf-tool setting §7- 插件基础设置");
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                        sender.sendMessage("§a页码： §71§8/§71");
                    } else if (strings.length == 2) {
                        if (Objects.equals(strings[1], "1")) {
                            sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                            sender.sendMessage("§6/xf-tool §7- 查看插件信息");
                            sender.sendMessage("§6/xf-tool help [page] §7- 查看指令帮助");
                            sender.sendMessage("§6/xf-op §7- OP管理");
                            sender.sendMessage("§6/xf-tool setting §7- 插件基础设置");
                            sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                            sender.sendMessage("§a页码： §71§8/§71");
                        } else {
                            sender.sendMessage(String.format("%s§r%s §r§c页码超出范围！", tools.xfPrefix, tools.xfPrefixArrow));
                        }
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", tools.xfPrefix, tools.xfPrefixArrow));
                    }
                } else if (Objects.equals(strings[0], "setting")) {
                    sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                    sender.sendMessage("§6/xf-tool update §7- 查看是否有可选更新");
                    sender.sendMessage("§6/xf-tool version §7- 查看插件版本");
                    sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                } else if (Objects.equals(strings[0], "update")) {
                    sender.sendMessage(String.format("%s§r%s §r§c很抱歉，我还没有做接口哈！", tools.xfPrefix, tools.xfPrefixArrow));
                } else if (Objects.equals(strings[0], "drop")) {
                    if (tools.getConfig().getBoolean("Plugin_Drop")) {
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                        sender.sendMessage("§6/xf-drop §7- 解除丢弃物品");
                        sender.sendMessage("§6/xf-drop <on or off> §7- 设置是否允许丢弃物品");
                        sender.sendMessage(String.format("§7§l§m==]==========§r %s §7§l§m==========[==", tools.xfPrefix));
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c服务器没有开启服务器掉落保护", tools.xfPrefix, tools.xfPrefixArrow));
                    }
                } else if (Objects.equals(strings[0],"version")) {
                    sender.sendMessage(String.format("%s§r%s §r§3版本号：%s", tools.xfPrefix, tools.xfPrefixArrow, tools.xfVersion));
                } else {
                    sender.sendMessage(String.format("%s§r%s §r§c参数错误请输入 §6/xf-tool help §c查看帮助", tools.xfPrefix, tools.xfPrefixArrow));
                }
            }
        }
        return true;
    }
}

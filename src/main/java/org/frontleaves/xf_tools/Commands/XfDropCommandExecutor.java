package org.frontleaves.xf_tools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.frontleaves.xf_tools.XF_Tools;

import java.util.Objects;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public class XfDropCommandExecutor implements CommandExecutor {

    protected XF_Tools tools;

    public XfDropCommandExecutor(XF_Tools plugins) {
        this.tools = plugins;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("xf-drop".equalsIgnoreCase(command.getName())) {
            if (tools.getConfig().getBoolean("Plugin_Drop")) {
                if (sender instanceof Player) {
                    if (args.length == 0) {
                        int i = 0;
                        Object[] user = new Object[2];
                        for (; tools.xfPlayer.get(i).length != 0; i++) {
                            user = tools.xfPlayer.get(i);
                            if (user[0] == sender.getName()) {
                                break;
                            }
                        }
                        if ((int) user[1] == 0) {
                            user[1] = 1;
                            sender.sendMessage(String.format("%s§r%s §r§2解除丢弃物品设置成功", tools.xfPrefix, tools.xfPrefixArrow));
                        } else {
                            sender.sendMessage(String.format("%s§r%s §r§c你已经解除过绑定了", tools.xfPrefix, tools.xfPrefixArrow));
                        }
                    } else if (args.length == 1) {
                        if (Objects.equals(args[0], "on")) {
                            int i = 0;
                            Object[] user = new Object[2];
                            for (; tools.xfPlayer.get(i).length != 0; i++) {
                                user = tools.xfPlayer.get(i);
                                if (user[0] == sender.getName()) {
                                    break;
                                }
                            }
                            if ((int) user[1] == 1) {
                                user[1] = 0;
                                sender.sendMessage(String.format("%s§r%s §r§2禁止丢弃物品设置成功", tools.xfPrefix, tools.xfPrefixArrow));
                            } else {
                                sender.sendMessage(String.format("%s§r%s §r§c你已经上锁了", tools.xfPrefix, tools.xfPrefixArrow));
                            }
                        } else if (Objects.equals(args[0], "off")) {
                            int i = 0;
                            Object[] user = new Object[2];
                            for (; tools.xfPlayer.get(i).length != 0; i++) {
                                user = tools.xfPlayer.get(i);
                                if (user[0] == sender.getName()) {
                                    break;
                                }
                            }
                            if ((int) user[1] == 0) {
                                user[1] = 1;
                                sender.sendMessage(String.format("%s§r%s §r§2解除丢弃物品设置成功", tools.xfPrefix, tools.xfPrefixArrow));
                            } else {
                                sender.sendMessage(String.format("%s§r%s §r§c你已经解除过绑定了", tools.xfPrefix, tools.xfPrefixArrow));
                            }
                        } else {
                            sender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", tools.xfPrefix, tools.xfPrefixArrow));
                        }
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", tools.xfPrefix, tools.xfPrefixArrow));
                    }
                } else {
                    sender.sendMessage(String.format("%s§3§r%s §r§c此指令只允许玩家进行操作！", tools.xfPrefix, tools.xfPrefixArrow));
                }
            } else {
                sender.sendMessage(String.format("%s§r%s §r§c服务器没有开启服务器掉落保护", tools.xfPrefix, tools.xfPrefixArrow));
            }
        }
        return true;
    }
}

package org.frontleaves.xf_tools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.frontleaves.xf_tools.XF_Tools;

import java.util.Objects;

public class XfDropCommandExecutor implements CommandExecutor {

    protected XF_Tools Tools;

    public XfDropCommandExecutor(XF_Tools Tools) {
        this.Tools = Tools;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("xf-drop")) {
            if (args.length == 0) {
                int i = 0;
                Object[] User = new Object[2];
                for (; Tools.XF_Player.get(i).length != 0; i++) {
                    User = Tools.XF_Player.get(i);
                    if (User[0] == sender.getName()) {
                        break;
                    }
                }
                if ((int) User[1] == 0) {
                    User[1] = 1;
                    sender.sendMessage(String.format("%s§r%s §r§2解除丢弃物品设置成功", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                } else {
                    sender.sendMessage(String.format("%s§r%s §r§c你已经解除过绑定了", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                }
            } else if (args.length == 1) {
                if (Objects.equals(args[0], "on")) {
                    int i = 0;
                    Object[] User = new Object[2];
                    for (; Tools.XF_Player.get(i).length != 0; i++) {
                        User = Tools.XF_Player.get(i);
                        if (User[0] == sender.getName()) {
                            break;
                        }
                    }
                    if ((int) User[1] == 1) {
                        User[1] = 0;
                        sender.sendMessage(String.format("%s§r%s §r§2禁止丢弃物品设置成功", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c你已经上锁了", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    }
                } else if (Objects.equals(args[0], "off")) {
                    int i = 0;
                    Object[] User = new Object[2];
                    for (; Tools.XF_Player.get(i).length != 0; i++) {
                        User = Tools.XF_Player.get(i);
                        if (User[0] == sender.getName()) {
                            break;
                        }
                    }
                    if ((int) User[1] == 0) {
                        User[1] = 1;
                        sender.sendMessage(String.format("%s§r%s §r§2解除丢弃物品设置成功", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    } else {
                        sender.sendMessage(String.format("%s§r%s §r§c你已经解除过绑定了", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    }
                } else {
                    sender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                }
            } else {
                sender.sendMessage(String.format("%s§r%s §r§c参数不正确，请输入 §6/xf-tool help §c查看指令帮助", Tools.XF_Prefix, Tools.XF_PrefixArrow));
            }
        }
        return true;
    }
}

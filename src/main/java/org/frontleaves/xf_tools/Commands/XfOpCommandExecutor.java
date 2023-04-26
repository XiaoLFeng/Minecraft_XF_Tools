package org.frontleaves.xf_tools.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.frontleaves.xf_tools.XF_Tools;

import javax.tools.Tool;
import java.util.Objects;
import java.util.Set;

public class XfOpCommandExecutor implements CommandExecutor {

    public XF_Tools Tools;

    public XfOpCommandExecutor(XF_Tools Tools) {
        this.Tools = Tools;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("xf-op")) {
            if (sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", Tools.XF_Prefix));
                    sender.sendMessage("§6/xf-op add <player> <password> §7- 添加一位OP");
                    sender.sendMessage("§6/xf-op del <player> <password> §7- 删除一位OP");
                    sender.sendMessage("§6/xf-op list §7- 查看目前的OP");
                    sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", Tools.XF_Prefix));
                } else if (args.length <= 2) {
                    if (Objects.equals(args[0], "add")) {
                        sender.sendMessage(String.format("%s§3§r%s §r§c参数错误", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", Tools.XF_Prefix));
                        sender.sendMessage("§r§3方法：§6/xf-op add <player> <password>");
                        sender.sendMessage("§r§3作用：§7添加一个OP");
                        sender.sendMessage("§r§3示例：§a/xf-op add xiao_lfeng 123456");
                        sender.sendMessage("§r§3备注：§c你的 <password> 是根据你在 config 中的配置决定");
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", Tools.XF_Prefix));
                    } else if (Objects.equals(args[0], "del")) {
                        sender.sendMessage(String.format("%s§3§r%s §r§c参数错误", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", Tools.XF_Prefix));
                        sender.sendMessage("§r§3方法：§6/xf-op del <player> <password>");
                        sender.sendMessage("§r§3作用：§7删除一个OP");
                        sender.sendMessage("§r§3示例：§a/xf-op del xiao_lfeng 123456");
                        sender.sendMessage("§r§3备注：§c你的 <password> 是根据你在 config 中的配置决定");
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", Tools.XF_Prefix));
                    } else if (Objects.equals(args[0], "list")) {
                        Set<OfflinePlayer> GetOP = Tools.getServer().getOperators();
                        String AllOperatorPlayer = null;
                        for (OfflinePlayer player : GetOP) {
                            if (AllOperatorPlayer == null) AllOperatorPlayer = player.getName();
                            else AllOperatorPlayer += ',' + player.getName();
                        }
                        sender.sendMessage(String.format("%s§3§r%s §r§3服内管理员如下：", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                        sender.sendMessage(String.format("%s§3§r%s §r§3%s", Tools.XF_Prefix, Tools.XF_PrefixArrow, AllOperatorPlayer));
                    }
                } else if (args.length == 3) {
                    // 获取文件参数
                    FileConfiguration FileData = Tools.getConfig();
                    if (Objects.equals(args[0], "add")) {
                        if (Objects.equals(args[2], FileData.getString("OP_Password"))) {
                            Player player = Tools.getServer().getPlayer(args[1]);
                            if (player != null) {
                                player.setOp(true);
                                player.sendMessage(String.format("%s§3§r%s §r§2恭喜你，加入管理员团队！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                                sender.sendMessage(String.format("%s§3§r%s §r§2用户 %s 加入管理员团队！", Tools.XF_Prefix, Tools.XF_PrefixArrow, player.getName()));
                            } else {
                                OfflinePlayer SetOpPlayer = Tools.getServer().getOfflinePlayer(args[1]);
                                if (SetOpPlayer.hasPlayedBefore()) {
                                    SetOpPlayer.setOp(true);
                                    sender.sendMessage(String.format("%s§3§r%s §r§2用户 %s 加入管理员团队！", Tools.XF_Prefix, Tools.XF_PrefixArrow, SetOpPlayer.getName()));
                                } else
                                    sender.sendMessage(String.format("%s§3§r%s §r§c这个用户还没加入过服务器，你还是检查一下叭！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                            }
                        } else
                            sender.sendMessage(String.format("%s§3§r%s §r§c无法操作，密码错误！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                    } else if (Objects.equals(args[0], "del")) {
                        if (Objects.equals(args[2], FileData.getString("OP_Password"))) {
                            OfflinePlayer DelOpPlayer = Tools.getServer().getOfflinePlayer(args[1]);
                            if (DelOpPlayer.isOp()) {
                                DelOpPlayer.setOp(false);
                                if (DelOpPlayer.isOnline()) {
                                    Player player1 = DelOpPlayer.getPlayer();
                                    player1.sendMessage(String.format("%s§3§r%s §r§c很遗憾，你离开了管理员团队！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                                }
                                sender.sendMessage(String.format("%s§3§r%s §r§c用户 %s 离开管理员团队！", Tools.XF_Prefix, Tools.XF_PrefixArrow, DelOpPlayer.getName()));
                            } else
                                sender.sendMessage(String.format("%s§3§r%s §r§c这个用户本身不是管理员欸！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                        }
                    } else
                        sender.sendMessage(String.format("%s§3§r%s §r§c参数错误，请使用 §6/xf-op §c查看帮助！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
                } else
                    sender.sendMessage(String.format("%s§3§r%s §r§c参数错误，请使用 §6/xf-op §c查看帮助！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
            } else
                sender.sendMessage(String.format("%s§3§r%s §r§c此指令只允许后台进行操作！", Tools.XF_Prefix, Tools.XF_PrefixArrow));
        }
        return true;
    }
}

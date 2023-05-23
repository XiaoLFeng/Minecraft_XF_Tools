package org.frontleaves.xf_tools.Commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.frontleaves.xf_tools.XF_Tools;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class XfOpCommandExecutor implements CommandExecutor {

    public XF_Tools tools;

    public XfOpCommandExecutor(XF_Tools plugins) {
        this.tools = plugins;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("xf-op".equalsIgnoreCase(command.getName())) {
            if (sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", tools.xfPrefix));
                    sender.sendMessage("§6/xf-op add <player> <password> §7- 添加一位OP");
                    sender.sendMessage("§6/xf-op del <player> <password> §7- 删除一位OP");
                    sender.sendMessage("§6/xf-op list §7- 查看目前的OP");
                    sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", tools.xfPrefix));
                } else if (args.length <= 2) {
                    if (Objects.equals(args[0], "add")) {
                        sender.sendMessage(String.format("%s§3§r%s §r§c参数错误", tools.xfPrefix, tools.xfPrefixArrow));
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", tools.xfPrefix));
                        sender.sendMessage("§r§3方法：§6/xf-op add <player> <password>");
                        sender.sendMessage("§r§3作用：§7添加一个OP");
                        sender.sendMessage("§r§3示例：§a/xf-op add xiao_lfeng 123456");
                        sender.sendMessage("§r§3备注：§c你的 <password> 是根据你在 config 中的配置决定");
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", tools.xfPrefix));
                    } else if (Objects.equals(args[0], "del")) {
                        sender.sendMessage(String.format("%s§3§r%s §r§c参数错误", tools.xfPrefix, tools.xfPrefixArrow));
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", tools.xfPrefix));
                        sender.sendMessage("§r§3方法：§6/xf-op del <player> <password>");
                        sender.sendMessage("§r§3作用：§7删除一个OP");
                        sender.sendMessage("§r§3示例：§a/xf-op del xiao_lfeng 123456");
                        sender.sendMessage("§r§3备注：§c你的 <password> 是根据你在 config 中的配置决定");
                        sender.sendMessage(String.format("§7§l§m==]========== %s§7§l§m ==========[==", tools.xfPrefix));
                    } else if (Objects.equals(args[0], "list")) {
                        Set<OfflinePlayer> getOp = tools.getServer().getOperators();
                        StringBuilder allOperatorPlayer = null;
                        for (OfflinePlayer player : getOp) {
                            if (allOperatorPlayer == null) {
                                allOperatorPlayer = Optional.ofNullable(player.getName()).map(StringBuilder::new).orElse(null);
                            } else {
                                allOperatorPlayer.append(',').append(player.getName());
                            }
                        }
                        sender.sendMessage(String.format("%s§3§r%s §r§3服内管理员如下：", tools.xfPrefix, tools.xfPrefixArrow));
                        sender.sendMessage(String.format("%s§3§r%s §r§3%s", tools.xfPrefix, tools.xfPrefixArrow, allOperatorPlayer == null ? null : allOperatorPlayer.toString()));
                    }
                } else if (args.length == 3) {
                    // 获取文件参数
                    FileConfiguration fileData = tools.getConfig();
                    if (Objects.equals(args[0], "add")) {
                        if (Objects.equals(args[2], fileData.getString("OP_Password"))) {
                            Player player = tools.getServer().getPlayer(args[1]);
                            if (player != null) {
                                player.setOp(true);
                                player.sendMessage(String.format("%s§3§r%s §r§2恭喜你，加入管理员团队！", tools.xfPrefix, tools.xfPrefixArrow));
                                sender.sendMessage(String.format("%s§3§r%s §r§2用户 %s 加入管理员团队！", tools.xfPrefix, tools.xfPrefixArrow, player.getName()));
                            } else {
                                OfflinePlayer setOpPlayer = tools.getServer().getOfflinePlayer(args[1]);
                                if (setOpPlayer.hasPlayedBefore()) {
                                    setOpPlayer.setOp(true);
                                    sender.sendMessage(String.format("%s§3§r%s §r§2用户 %s 加入管理员团队！", tools.xfPrefix, tools.xfPrefixArrow, setOpPlayer.getName()));
                                } else {
                                    sender.sendMessage(String.format("%s§3§r%s §r§c这个用户还没加入过服务器，你还是检查一下叭！", tools.xfPrefix, tools.xfPrefixArrow));
                                }
                            }
                        } else {
                            sender.sendMessage(String.format("%s§3§r%s §r§c无法操作，密码错误！", tools.xfPrefix, tools.xfPrefixArrow));
                        }
                    } else if (Objects.equals(args[0], "del")) {
                        if (Objects.equals(args[2], fileData.getString("OP_Password"))) {
                            OfflinePlayer delOpPlayer = tools.getServer().getOfflinePlayer(args[1]);
                            if (delOpPlayer.isOp()) {
                                delOpPlayer.setOp(false);
                                if (delOpPlayer.isOnline()) {
                                    Player player1 = delOpPlayer.getPlayer();
                                    player1.sendMessage(String.format("%s§3§r%s §r§c很遗憾，你离开了管理员团队！", tools.xfPrefix, tools.xfPrefixArrow));
                                }
                                sender.sendMessage(String.format("%s§3§r%s §r§c用户 %s 离开管理员团队！", tools.xfPrefix, tools.xfPrefixArrow, delOpPlayer.getName()));
                            } else {
                                sender.sendMessage(String.format("%s§3§r%s §r§c这个用户本身不是管理员欸！", tools.xfPrefix, tools.xfPrefixArrow));
                            }
                        }
                    } else {
                        sender.sendMessage(String.format("%s§3§r%s §r§c参数错误，请使用 §6/xf-op §c查看帮助！", tools.xfPrefix, tools.xfPrefixArrow));
                    }
                } else {
                    sender.sendMessage(String.format("%s§3§r%s §r§c参数错误，请使用 §6/xf-op §c查看帮助！", tools.xfPrefix, tools.xfPrefixArrow));
                }
            } else {
                sender.sendMessage(String.format("%s§3§r%s §r§c此指令只允许后台进行操作！", tools.xfPrefix, tools.xfPrefixArrow));
            }
        }
        return true;
    }
}

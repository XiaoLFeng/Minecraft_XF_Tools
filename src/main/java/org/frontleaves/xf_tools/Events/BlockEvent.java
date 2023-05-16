package org.frontleaves.xf_tools.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.frontleaves.xf_tools.XF_Tools;

public class BlockEvent implements Listener {

    protected XF_Tools Tools;

    public BlockEvent(XF_Tools Tools) {
        this.Tools = Tools;
    }

    @EventHandler
    public void InventoryEvent(InventoryCloseEvent player) {
        if (!player.getPlayer().isOp()) {
            // 基岩检测
            if (player.getPlayer().getInventory().contains(Material.BEDROCK)) {
                player.getPlayer().getInventory().remove(Material.BEDROCK);
                player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
            }
        } else {
            Player players = (Player) player;
            Location playerLocation = players.getLocation();
            Tools.getLogger().warning(String.format("§7[%s§7] §r管理 %s 在 [%s][%s,%s,%s] 从背包获取了 %s", Tools.XF_Prefix, players.getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), Material.BEDROCK));
        }
    }

    @EventHandler
    public void DropEvent(PlayerDropItemEvent player) {
        if (!player.getPlayer().isOp()) {
            // 基岩检测
            if (player.getPlayer().getInventory().contains(Material.BEDROCK)) {
                player.setCancelled(true);
                player.getPlayer().getInventory().remove(Material.BEDROCK);
                player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
            }
        } else {
            Player players = (Player) player;
            Location playerLocation = players.getLocation();
            Tools.getLogger().warning(String.format("§7[%s§7] §r管理 %s 在 [%s][%s,%s,%s] 从丢弃了 %s", Tools.XF_Prefix, players.getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), Material.BEDROCK));
        }
    }


    @EventHandler
    public void EntityPickupItemEvent(LivingEntity entity, Item item, int remaining) {
        if (entity instanceof Player) {
            if (!entity.isOp()) {
                if (item.getItemStack().getType() == Material.BEDROCK) {
                    entity.setCanPickupItems(false);
                    entity.sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
                } else {
                    Location playerLocation = entity.getLocation();
                    Tools.getLogger().warning(String.format("§7[%s§7] §r管理 %s 在 [%s][%s,%s,%s] 捡起了 %s", Tools.XF_Prefix, ((Player) entity).getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), Material.BEDROCK));
                }
            }
        }
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent player) {
        if (!player.getPlayer().isOp()) {
            // 基岩检测
            if (player.getPlayer().getInventory().contains(Material.BEDROCK)) {
                player.setCancelled(true);
                player.getPlayer().getInventory().remove(Material.BEDROCK);
                player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
            } else {
                Player players = (Player) player;
                Location playerLocation = players.getLocation();
                Tools.getLogger().warning(String.format("§7[%s§7] §r管理 %s 在 [%s][%s,%s,%s] 位置使用了 %s", Tools.XF_Prefix, players.getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), Material.BEDROCK));
            }
        }
    }
}

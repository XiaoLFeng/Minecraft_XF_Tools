package org.frontleaves.xf_tools.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
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
        for (String value : Tools.BlockBanList) {
            if (!player.getPlayer().isOp()) {
                // 基岩检测
                if (player.getPlayer().getInventory().contains(Material.getMaterial(value))) {
                    player.getPlayer().getInventory().remove(Material.getMaterial(value));
                    player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, value));
                }
            } else {
                Tools.getLogger().warning(String.format("管理 %s 在 [%s][%s,%s,%s] 从背包获取了 %s", player.getPlayer().getName(), player.getPlayer().getLocation().getWorld().getName(), player.getPlayer().getLocation().getX(), player.getPlayer().getLocation().getY(), player.getPlayer().getLocation().getZ(), value));
            }
        }
    }

    @EventHandler
    public void DropEvent(PlayerDropItemEvent player) {
        for (String value : Tools.BlockBanList) {
            if (!player.getPlayer().isOp()) {
                // 基岩检测
                if (player.getPlayer().getInventory().contains(Material.getMaterial(value))) {
                    player.setCancelled(true);
                    player.getPlayer().getInventory().remove(Material.getMaterial(value));
                    player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.getMaterial(value)));
                }
            } else {
                Player players = (Player) player;
                Location playerLocation = players.getLocation();
                Tools.getLogger().warning(String.format("管理 %s 在 [%s][%s,%s,%s] 从丢弃了 %s", players.getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), value));
            }
        }
    }


    @EventHandler
    public void PickupEvent(EntityPickupItemEvent pickupItemEvent) {
        for (String value : Tools.BlockBanList) {
            Player entity = (Player) pickupItemEvent.getEntity();
            if (!entity.isOp()) {
                if (pickupItemEvent.getItem().getItemStack().getType() == Material.getMaterial(value)) {
                    entity.setCanPickupItems(false);
                    entity.sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, value));
                } else {
                    Location playerLocation = entity.getLocation();
                    Tools.getLogger().warning(String.format("管理 %s 在 [%s][%s,%s,%s] 捡起了 %s%n", entity.getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), value));
                }
            }
        }
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent player) {
        for (String value : Tools.BlockBanList) {
            if (!player.getPlayer().isOp()) {
                // 基岩检测
                if (player.getPlayer().getInventory().contains(Material.getMaterial(value))) {
                    player.setCancelled(true);
                    player.getPlayer().getInventory().remove(Material.getMaterial(value));
                    player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, value));
                } else {
                    Player players = (Player) player;
                    Location playerLocation = players.getLocation();
                    Tools.getLogger().warning(String.format("管理 %s 在 [%s][%s,%s,%s] 位置使用了 %s", players.getDisplayName(), playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), value));
                }
            }
        }
    }
}

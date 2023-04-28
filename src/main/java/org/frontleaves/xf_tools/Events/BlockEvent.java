package org.frontleaves.xf_tools.Events;

import org.bukkit.Material;
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
    public void InventoryEvent(InventoryCloseEvent Player) {
        if (!Player.getPlayer().isOp()) {
            // 基岩检测
            if (Player.getPlayer().getInventory().contains(Material.BEDROCK)) {
                Player.getPlayer().getInventory().remove(Material.BEDROCK);
                Player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
            }
        }
    }

    @EventHandler
    public void DropEvent(PlayerDropItemEvent Player) {
        if (!Player.getPlayer().isOp()) {
            // 基岩检测
            if (Player.getPlayer().getInventory().contains(Material.BEDROCK)) {
                Player.setCancelled(true);
                Player.getPlayer().getInventory().remove(Material.BEDROCK);
                Player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
            }
        }
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent Player) {
        if (!Player.getPlayer().isOp()) {
            // 基岩检测
            if (Player.getPlayer().getInventory().contains(Material.BEDROCK)) {
                Player.setCancelled(true);
                Player.getPlayer().getInventory().remove(Material.BEDROCK);
                Player.getPlayer().sendMessage(String.format("%s§r%s §r§c这个 %s 你不可以拥有", Tools.XF_Prefix, Tools.XF_PrefixArrow, Material.BEDROCK));
            }
        }
    }
}

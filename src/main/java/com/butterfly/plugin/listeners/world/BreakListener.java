package com.butterfly.plugin.listeners.world;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.commands.BuildCmd;
import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashSet;
import java.util.Set;

public class BreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!BuildCmd.build.contains(player) && (player.getGameMode() == GameMode.CREATIVE || PlayerManager.vanish.contains(player))) {
            MessageManager.sendMessage(player, Message.GENERAL_UNABLE_TO_BUILD);
            event.setCancelled(true);
        }

        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        Block block = event.getBlock();

        if (isOre(block.getType())) {
            if (block.hasMetadata("veinProcessed")) {
                return;
            }

            Set<Block> countedBlocks = new HashSet<>();
            countOreVein(block, countedBlocks);

            for (Block oreBlock : countedBlocks) {
                oreBlock.setMetadata("veinProcessed", new FixedMetadataValue(ButterflyCore.instance, true));
            }

            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("fractured.admin")) {
                    players.sendMessage(Utils.color("&c&l[XRAY] " + player.getDisplayName() + " &fhas broken a vein of &b&l" + countedBlocks.size() + " " + block.getType().name() + "&f!"));
                }
            }
        }
    }

    private boolean isOre(Material material) {
        return material == Material.IRON_ORE || material == Material.DEEPSLATE_IRON_ORE ||
                material == Material.GOLD_ORE || material == Material.DEEPSLATE_GOLD_ORE ||
                material == Material.DIAMOND_ORE || material == Material.DEEPSLATE_DIAMOND_ORE ||
                material == Material.NETHER_QUARTZ_ORE || material == Material.ANCIENT_DEBRIS;
    }

    private void countOreVein(Block block, Set<Block> countedBlocks) {
        if (!isOre(block.getType()) || countedBlocks.contains(block)) {
            return;
        }

        countedBlocks.add(block);

        for (BlockFace face : BlockFace.values()) {
            Block relative = block.getRelative(face);
            if (isOre(relative.getType()) && !countedBlocks.contains(relative)) {
                countOreVein(relative, countedBlocks);
            }
        }
    }
}
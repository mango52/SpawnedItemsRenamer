package com.eodomo.SpawnedItemsRenamer;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnedItemsRenamer extends JavaPlugin{
	public final Logger logger = this.getLogger();

	@Override
	public void onDisable() {
		this.logger.info(this.getDescription().getName() + " has been disabled!");
	}

	@Override
	public void onEnable() {
		this.logger.info(this.getDescription().getName() + " version " + this.getDescription().getVersion() + " has been enabled!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
		if (command.getName().equals("i")) {
			if (sender instanceof Player){
				Player player = (Player) sender;
				if (player.hasPermission("spawneditemsrenamer.rename")) {
					if (player.getItemInHand().getTypeId() != 0) {
						if (args.length > 0) {
							Material material = Material.matchMaterial(args[1]);
							player.getInventory().addItem(new ItemStack(material));
							ItemStack is = player.getItemInHand();
							ItemMeta im = is.getItemMeta();
							im.setDisplayName(player.getDisplayName() + " - " + args[1]);
							is.setItemMeta(im);
						}
					}
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Only players can use this command.");
			}
		}

		if (command.getName().equals("give")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("spawneditemsrenamer.rename")) {
					if (player.getItemInHand().getTypeId() != 0) {
						if (args.length > 0) {
							Material material = Material.matchMaterial(args[1]);
							player.getInventory().addItem(new ItemStack(material));
							ItemStack is = player.getItemInHand();
							ItemMeta im = is.getItemMeta();
							im.setDisplayName(player.getDisplayName() + " - " + args[1]);
							is.setItemMeta(im);
						}
					}
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Only players can use this command.");
			}
		}
		return false;
	}
}
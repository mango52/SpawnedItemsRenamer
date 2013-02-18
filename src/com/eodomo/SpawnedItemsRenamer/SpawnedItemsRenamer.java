package com.eodomo.SpawnedItemsRenamer;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnedItemsRenamer extends JavaPlugin {

	@Override
	public void onDisable() {
		this.getLogger().info(this.getDescription().getName() + " has been disabled!");
	}

	@Override
	public void onEnable() {
		this.getLogger().info(this.getDescription().getName() + " version " + this.getDescription().getVersion() + " has been enabled!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
		if (command.getName().equalsIgnoreCase("i")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("spawneditemsrenamer.item")) {
					Material material = Material.matchMaterial(args[0]);
					int amount;
					
					if (material == null) {
						player.sendMessage(ChatColor.DARK_RED + args[0] + " is not a valid item!");
						return false;
					}
					
					if (args.length == 1) {
						amount = 1;
					} else if (args.length == 2) {
						try {
							amount = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.DARK_RED + args[1] + " is not a valid number!");
							return false;
						}
					} else
						return false;
					
					ItemStack stack = new ItemStack(material, amount);
					ItemMeta meta = stack.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(player.getName());
					meta.setLore(lore);
					stack.setItemMeta(meta);
					player.getInventory().addItem(stack);
					
					return true;
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
					return false;
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Only players can use this command.");
				return false;
			}
		}

		if (command.getName().equalsIgnoreCase("give")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("spawneditemsrenamer.item")) {
					Player target;
					Material material = Material.matchMaterial(args[1]);
					int amount;
					
					ArrayList<Player> foundPlayers = new ArrayList<Player>();
					for (Player temp : Bukkit.getServer().getOnlinePlayers()) {
						if (temp.getName().contains(args[0]))
							foundPlayers.add(temp);
					}
					
					if (foundPlayers.isEmpty()) {
						player.sendMessage("Could not find any players matching that name.");
						return false;
					} else if (foundPlayers.size() > 1) {
						player.sendMessage("Found multiple players with that name - please be more specific.");
						return false;
					} else {
						target = foundPlayers.get(0);
					}
					
					if (material == null) {
						player.sendMessage(ChatColor.DARK_RED + args[0] + " is not a valid item!");
						return false;
					}
					
					if (args.length == 2) {
						amount = 1;
					} else if (args.length == 3) {
						try {
							amount = Integer.parseInt(args[2]);
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.DARK_RED + args[2] + " is not a valid number!");
							return false;
						}
					} else
						return false;
					
					ItemStack stack = new ItemStack(material, amount);
					ItemMeta meta = stack.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(player.getName());
					meta.setLore(lore);
					stack.setItemMeta(meta);
					target.getInventory().addItem(stack);
					
					return true;
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
					return false;
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Only players can use this command.");
				return false;
			}
		}
		return false;
	}
}
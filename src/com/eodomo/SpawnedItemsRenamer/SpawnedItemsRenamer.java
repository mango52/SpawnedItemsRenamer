package com.eodomo.SpawnedItemsRenamer;

import java.util.ArrayList;

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
					Material material;
					int amount;
					
					if (args.length == 1) {
						material = Material.matchMaterial(args[0]);
						amount = 1;
					} else if (args.length == 2) {
						material = Material.matchMaterial(args[0]);
						try {
							amount = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.DARK_RED + args[1] + " is not a valid number!");
							return false;
						}
					} else
						return false;
					
					if (material == null) {
						player.sendMessage(ChatColor.DARK_RED + args[0] + " is not a valid item!");
						return false;
					}
					
					ItemStack stack = new ItemStack(material, amount);
					ItemMeta meta = stack.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>(); //test start
					lore.add(player.getName());
					meta.setLore(lore);
					stack.setItemMeta(meta);
					player.getInventory().addItem(stack);
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
			if (sender instanceof Player){
				Player player = (Player) sender;
				if (player.hasPermission("spawneditemsrenamer.give")) {
					if ((args.length > 0) && (args.length < 3)) {
						Material material = Material.matchMaterial(args[0]);
						ItemStack stack = new ItemStack(material);
						stack.getItemMeta().setDisplayName("test - " + player.getName());
						player.getInventory().addItem(stack);
						return true;
					} else
						return false;
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
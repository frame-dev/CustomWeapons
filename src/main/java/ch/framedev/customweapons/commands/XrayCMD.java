package ch.framedev.customweapons.commands;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import ch.framedev.customweapons.main.Main;
import de.framedev.javautils.SpigotAPI;

public class XrayCMD implements CommandExecutor, Listener {

	private final Main plugin;

	public XrayCMD(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("xray").setExecutor(this);
		plugin.getCommand("resetxray").setExecutor(this);;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender.hasPermission("spigotplugin.xray")) {
			if(command.getName().equalsIgnoreCase("xray")) {
			if (sender instanceof Player) {
				((Player) sender).getInventory()
						.addItem(new SpigotAPI.ItemBuilder(Material.STICK).setDisplayName("§6Xray").build());
			}
			} else if(command.getName().equalsIgnoreCase("resetxray")) {
				((Player) sender).showDemoScreen();
			}
		}
		return false;
	}

	@EventHandler
	public void onXray(PlayerInteractEvent event) {
		if (event.getItem() != null && event.getItem().getType() == Material.STICK) {
			if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()
					&& event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Xray"))
				if (event.getAction() == Action.RIGHT_CLICK_AIR) {
					Chunk chunk = event.getPlayer().getWorld().getChunkAt(event.getPlayer().getLocation());
					for (int x = 0; x < 16; x++) {
						if (plugin.getServer().getVersion().contains("1.18")) {
							for (int y = -60; y < 255; y++) {
								for (int z = 0; z < 16; z++) {
									if (chunk.getBlock(x, y, z).getType() == Material.LAVA
											|| chunk.getBlock(x, y, z).getType() == Material.WATER
											|| chunk.getBlock(x, y, z).getType() == Material.AIR) {
										continue;
									} else {
										event.getPlayer().sendBlockChange(chunk.getBlock(x, y, z).getLocation(),
												Material.GLASS.createBlockData());
									}
								}
							}
						} else {
							for (int y = 0; y < 255; y++) {
								for (int z = 0; z < 16; z++) {
									if (chunk.getBlock(x, y, z).getType() == Material.LAVA
											|| chunk.getBlock(x, y, z).getType() == Material.WATER
											|| chunk.getBlock(x, y, z).getType() == Material.AIR) {
										continue;
									} else {
										event.getPlayer().sendBlockChange(chunk.getBlock(x, y, z).getLocation(),
												Material.GLASS.createBlockData());
									}
								}
							}
						}
					}
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (event.getClickedBlock() != null) {
						Chunk chunk = event.getClickedBlock().getChunk();
						for (int x = 0; x < 16; x++) {
							if (plugin.getServer().getVersion().contains("1.18")
									|| plugin.getServer().getVersion().contains("1.19")) {
								for (int y = -60; y < 255; y++) {
									for (int z = 0; z < 16; z++) {
										if (chunk.getBlock(x, y, z).getType() == Material.LAVA
												|| chunk.getBlock(x, y, z).getType() == Material.WATER
												|| chunk.getBlock(x, y, z).getType() == Material.AIR) {
											continue;
										} else {
											if (plugin.getConfig().getBoolean("Xray")) {
												if (chunk.getBlock(x, y, z).getType() == Material.COAL_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.IRON_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.GOLD_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.DIAMOND_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.LAPIS_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.EMERALD_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_COAL_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_IRON_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_GOLD_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_DIAMOND_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_EMERALD_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_LAPIS_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_REDSTONE_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.REDSTONE_ORE) {
													continue;
												}
											}
											event.getPlayer().sendBlockChange(chunk.getBlock(x, y, z).getLocation(),
													Material.GLASS.createBlockData());
										}
									}
								}
							} else {
								for (int y = 0; y < 255; y++) {
									for (int z = 0; z < 16; z++) {
										if (chunk.getBlock(x, y, z).getType() == Material.LAVA
												|| chunk.getBlock(x, y, z).getType() == Material.WATER
												|| chunk.getBlock(x, y, z).getType() == Material.AIR) {
											continue;
										} else {
											if (plugin.getConfig().getBoolean("Xray")) {
												if (chunk.getBlock(x, y, z).getType() == Material.COAL_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.IRON_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.GOLD_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.DIAMOND_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.LAPIS_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.EMERALD_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_COAL_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_IRON_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_GOLD_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_DIAMOND_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_EMERALD_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_LAPIS_ORE
														|| chunk.getBlock(x, y, z)
																.getType() == Material.DEEPSLATE_REDSTONE_ORE
														|| chunk.getBlock(x, y, z).getType() == Material.REDSTONE_ORE) {
													continue;
												}
											}
											event.getPlayer().sendBlockChange(chunk.getBlock(x, y, z).getLocation(),
													Material.GLASS.createBlockData());
										}
									}
								}
							}
						}
					}
				}
		}
	}

}

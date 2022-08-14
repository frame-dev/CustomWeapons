package ch.framedev.customweapons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.framedev.customweapons.main.Main;
import ch.framedev.customweapons.weapons.AbstractWeapon;
import de.framedev.javautils.SpigotAPI.InventoryManager;

public class WeaponCMD implements CommandExecutor {

	private final Main plugin;
	private InventoryManager weaponInventoryBuilder;

	public WeaponCMD(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("weapon").setExecutor(this);
		this.weaponInventoryBuilder = new InventoryManager("Weapons", 3*9);
		this.weaponInventoryBuilder.create();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("weapon")) {
				if (args[0].equalsIgnoreCase("inventory")) {
					this.weaponInventoryBuilder.getInventory().clear();
					for(AbstractWeapon abstractWeapon : plugin.getWeaponRegister().getWeapons()) {
						this.weaponInventoryBuilder.addItem(abstractWeapon.weapontype);
					}
					weaponInventoryBuilder.show((Player) sender);
				}
			}
		}
		return false;
	}

}

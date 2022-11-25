package ch.framedev.customweapons.commands;

import ch.framedev.customweapons.swords.AbstractSword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import ch.framedev.customweapons.main.Main;
import ch.framedev.customweapons.weapons.AbstractBow;
import de.framedev.javautils.SpigotAPI.InventoryManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeaponCMD implements CommandExecutor, TabCompleter {

    private final Main plugin;
    private InventoryManager weaponInventoryBuilder;

    public WeaponCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("weapon").setExecutor(this);
        plugin.getCommand("weapon").setTabCompleter(this);
        this.weaponInventoryBuilder = new InventoryManager("Weapons", 3 * 9);
        this.weaponInventoryBuilder.create();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("weapon")) {
                if (args[0].equalsIgnoreCase("inventory")) {
                    if (!sender.hasPermission("customweapons.inventory")) {
                        sender.sendMessage("§cNo Permissions");
                        return true;
                    }
                    this.weaponInventoryBuilder.getInventory().clear();
                    for (AbstractBow abstractBow : plugin.getWeaponRegister().getBows()) {
                        if (!abstractBow.name.equalsIgnoreCase("Example Bow"))
                        this.weaponInventoryBuilder.addItem(abstractBow.weapontype);
                    }
                    for (AbstractSword abstractSword : plugin.getWeaponRegister().getSwords()) {
                        if (!abstractSword.name.equalsIgnoreCase("Example Sword"))
                            this.weaponInventoryBuilder.addItem(abstractSword.getSword());
                    }
                    weaponInventoryBuilder.show((Player) sender);
                }
            }
        }
        if (command.getName().equalsIgnoreCase("weapon")) {
            if (args[0].equalsIgnoreCase("registered_packages")) {
                if (!sender.hasPermission("customweapons.registered_packages")) {
                    sender.sendMessage("§cNo Permissions!");
                    return true;
                }
                sender.sendMessage("§6Registered Packages");
                for (String packages : plugin.getConfig().getStringList("packages")) {
                    sender.sendMessage("§a» §6" + packages);
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("weapon")) {
            if (args.length == 1) {
                List<String> empty = new ArrayList<>();
                List<String> commands = new ArrayList<>(Arrays.asList("inventory", "registered_packages"));
                for (String cmd : commands) {
                    if (cmd.toLowerCase().startsWith(args[0].toLowerCase()))
                        empty.add(cmd);
                }

                Collections.sort(empty);
                return empty;
            }
        }
        return null;
    }
}

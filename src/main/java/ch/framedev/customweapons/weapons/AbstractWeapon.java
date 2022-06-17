package ch.framedev.customweapons.weapons;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.Gson;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.main.Main;

/**
 * 
 * @author FrameDev
 *
 * @param <T> T extends CustomArrow
 */
public abstract class AbstractWeapon<T extends CustomArrow> implements Listener {

	public String name;
	public double damage;
	public T munition;
	public ItemStack weaponType;
	public double speed;
	public boolean infinity;

	public AbstractWeapon() {
		Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
	}

	@EventHandler
	public void onPlayerShoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			if (event.getBow().hasItemMeta() && event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(name))
				event.setProjectile(munition.shoot((Player) event.getEntity(), damage, speed));
		}
	}

	public ItemStack create() {
		if (weaponType.getType() == Material.BOW) {
			ItemMeta meta = weaponType.getItemMeta();
			meta.setDisplayName(name);
			meta.setLore(Arrays.asList("§aDamage : §6" + damage,"§aMunition : §6" + munition, "§aSpeed : §6" + speed));
			if(infinity)
				meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			weaponType.setItemMeta(meta);
			Main.getPlugin(Main.class).getWeaponRegister().registerWeapon(this);
			return weaponType;
		} else if (weaponType.getType() == Material.CROSSBOW) {
			CrossbowMeta meta = (CrossbowMeta) weaponType.getItemMeta();
			meta.setDisplayName(name);
			meta.setLore(Arrays.asList("§aDamage : §6" + damage,"§aMunition : §6" + munition, "§aSpeed : §6" + speed));
			if(infinity)
				meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			weaponType.setItemMeta(meta);
			Main.getPlugin(Main.class).getWeaponRegister().registerWeapon(this);
			return weaponType;
		}
		return null;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

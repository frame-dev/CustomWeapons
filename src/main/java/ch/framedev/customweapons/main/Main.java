package ch.framedev.customweapons.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.arrows.TildeArrow;
import ch.framedev.customweapons.managers.RegisterManager;
import ch.framedev.customweapons.managers.WeaponRegister;
import ch.framedev.customweapons.weapons.AbstractWeapon;
import ch.framedev.customweapons.weapons.CrossFireWeapon;
import ch.framedev.customweapons.weapons.FireBow;

public class Main extends JavaPlugin {

	private WeaponRegister weaponRegister;
	private static Main instance;

	//
	@Override
	public void onEnable() {
		instance = this;
		this.weaponRegister = new WeaponRegister();
		new RegisterManager(this);

		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		if (!new File(getDataFolder(), "bows").exists()) {
			new File(getDataFolder(), "bows").mkdir();
			File file = new File(getDataFolder() + "/bows", "testbow.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			cfg.set("name", "TestBow");
			cfg.set("type", "CrossFireWeapon");
			cfg.set("damage", 1.0d);
			cfg.set("infinity", false);
			cfg.set("weapontype", Material.BOW.name());
			cfg.set("speed", 1.0d);
			cfg.set("munition.type", "CustomArrow");
			cfg.set("munition.name", "CustomArrow");
			cfg.set("munition.critical", false);
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (File file : getFiles()) {
			getWeaponFromFile(file);
		}

		new CrossFireWeapon("CrossFire", new ItemStack(Material.BOW), new CustomArrow("Arrow12", true), 2.5, 2);
		new FireBow("FireBow", new ItemStack(Material.BOW), new TildeArrow("TildeArr", true), 3.25, 0);

		new CustomWeaponsAPI(this);
	}

	@Override
	public void onLoad() {

	}

	@Override
	public void onDisable() {

	}

	public static Main getInstance() {
		return instance;
	}

	public WeaponRegister getWeaponRegister() {
		return weaponRegister;
	}

	public AbstractWeapon getWeaponFromFile(File file) {
		try {
			return AbstractWeapon.load(file);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public File[] getFiles() {
		File file = new File(getDataFolder(), "bows");
		if (file.exists()) {
			return file.listFiles();
		}
		return null;
	}
}

package ch.framedev.customweapons.main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.arrows.TildeArrow;
import ch.framedev.customweapons.classes.WeaponRegister;
import ch.framedev.customweapons.managers.RegisterManager;
import ch.framedev.customweapons.weapons.CrossFireWeapon;
import ch.framedev.customweapons.weapons.FireBow;

public class Main extends JavaPlugin {
	
	private WeaponRegister weaponRegister;
	
	
	@Override
	public void onEnable() {
		this.weaponRegister = new WeaponRegister();
		new RegisterManager(this);
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		new CrossFireWeapon("CrossFire", new ItemStack(Material.BOW), new CustomArrow("Arrow12", true), 2.5, 2).create();
		new FireBow("FireBow", new ItemStack(Material.BOW), new TildeArrow("TildeArr", true), 1.25, 2).create();
	}
	
	@Override
	public void onLoad() {
		
	}
	
	@Override
	public void onDisable() {

	}
	
	public WeaponRegister getWeaponRegister() {
		return weaponRegister;
	}
}

package ch.framedev.customweapons.weapons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import ch.framedev.customweapons.arrows.CustomArrow;

public class CrossFireWeapon extends AbstractWeapon {
	
	public CrossFireWeapon(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
		super(name, weaponType, munition, speed, speed, infinity);
		super.name = name;
		super.weapontype = weaponType;
		super.munition = munition;
		super.damage = damage;
		this.speed = speed;
		createRecipe(this, Material.BOW);
	}

}

package ch.framedev.customweapons.weapons;

import org.bukkit.inventory.ItemStack;

import ch.framedev.customweapons.arrows.CustomArrow;

public class CrossFireWeapon extends AbstractWeapon<CustomArrow> {
	
	public CrossFireWeapon(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed) {
		super();
		super.name = name;
		super.weaponType = weaponType;
		super.munition = munition;
		super.damage = damage;
		this.speed = speed;
	}

}
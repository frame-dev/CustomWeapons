package ch.framedev.customweapons.classes;

import org.bukkit.inventory.ItemStack;

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

package ch.framedev.customweapons.weapons;

import org.bukkit.inventory.ItemStack;

import ch.framedev.customweapons.arrows.CustomArrow;

public class FireBow extends AbstractWeapon {
	
	public FireBow(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
		super(name, weaponType, munition, damage, speed, infinity);
		super.name = name;
		super.weaponType = weaponType;
		super.munition = munition;
		super.damage = damage;
		super.speed = speed;
		super.infinity = true;
	}

}

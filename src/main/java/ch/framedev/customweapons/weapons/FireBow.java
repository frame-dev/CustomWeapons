package ch.framedev.customweapons.weapons;

import org.bukkit.inventory.ItemStack;

import ch.framedev.customweapons.arrows.TildeArrow;

public class FireBow extends AbstractWeapon<TildeArrow> {
	
	public FireBow(String name, ItemStack weaponType, TildeArrow munition, double damage, double speed) {
		super();
		super.name = name;
		super.weaponType = weaponType;
		super.munition = munition;
		super.damage = damage;
		super.speed = speed;
		super.infinity = true;
	}

}

package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.CustomArrow;
import org.bukkit.inventory.ItemStack;

public class TNTBow extends AbstractWeapon {

    public TNTBow(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
        super(name, weaponType, munition, damage, speed, infinity);
    }
}

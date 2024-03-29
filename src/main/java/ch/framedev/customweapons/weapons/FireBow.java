package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.CustomArrow;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FireBow extends AbstractBow {

    public FireBow(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
        super(name, weaponType, munition, damage, speed, infinity);
        super.name = name;
        super.weapontype = weaponType;
        super.munition = munition;
        super.damage = damage;
        super.speed = speed;
        super.infinity = true;

        // Create Recipe
        createRecipe(this, Material.FLINT_AND_STEEL);
    }

}

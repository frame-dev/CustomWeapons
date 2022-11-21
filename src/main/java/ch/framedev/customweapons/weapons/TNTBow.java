package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.main.Main;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;

public class TNTBow extends AbstractWeapon {

    public TNTBow(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
        super(name, weaponType, munition, damage, speed, infinity);
        Server server = Main.getInstance().getServer();
        createRecipe(this, Material.TNT);
    }
}

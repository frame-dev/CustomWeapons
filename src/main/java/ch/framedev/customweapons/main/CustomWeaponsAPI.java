package ch.framedev.customweapons.main;

import java.util.List;

import ch.framedev.customweapons.managers.WeaponRegister;
import ch.framedev.customweapons.weapons.AbstractWeapon;
import org.bukkit.Material;

public class CustomWeaponsAPI {

    private final Main plugin;
    private static CustomWeaponsAPI instance;

    protected CustomWeaponsAPI(Main plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public static CustomWeaponsAPI getInstance() {
        return instance;
    }

    public WeaponRegister getWeaponRegister() {
        return plugin.getWeaponRegister();
    }

    public List<AbstractWeapon> getWeapons() {
        return getWeaponRegister().getWeapons();
    }

    public void registerRecipe(AbstractWeapon weapon, Material material) {
        AbstractWeapon.createRecipe(weapon, material);
    }
    
}

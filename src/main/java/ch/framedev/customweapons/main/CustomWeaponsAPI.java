package ch.framedev.customweapons.main;

import java.util.List;

import ch.framedev.customweapons.managers.WeaponRegister;
import ch.framedev.customweapons.swords.AbstractSword;
import ch.framedev.customweapons.weapons.AbstractBow;
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

    public List<AbstractBow> getBows() {
        return getWeaponRegister().getBows();
    }

    public void registerBowRecipe(AbstractBow weapon, Material material) {
        AbstractBow.createRecipe(weapon, material);
    }

    public List<AbstractSword> getSwords() {
        return getWeaponRegister().getSwords();
    }

    public void registerSwordRecipe(AbstractSword abstractSword, Material material) {
        AbstractSword.createRecipe(abstractSword, material);
    }


}

package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Objects;

public class TNTBow extends AbstractWeapon {

    public TNTBow(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
        super(name, weaponType, munition, damage, speed, infinity);
        Server server = Main.getInstance().getServer();
        if(server.getRecipe(NamespacedKey.fromString("tntbow", Main.getInstance())) == null) {
            ShapelessRecipe shapedRecipe = new ShapelessRecipe(Objects.requireNonNull(NamespacedKey.fromString("tntbow", Main.getInstance())), this.weapontype);
            shapedRecipe.addIngredient(1, Material.BOW);
            shapedRecipe.addIngredient(1, Material.TNT);
            shapedRecipe.setGroup("customweapons");
            server.addRecipe(shapedRecipe);
        }
    }
}

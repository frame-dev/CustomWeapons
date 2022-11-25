package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;

import ch.framedev.customweapons.arrows.CustomArrow;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Objects;

public class FireBow extends AbstractBow {
	
	public FireBow(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
		super(name, weaponType, munition, damage, speed, infinity);
		super.name = name;
		super.weapontype = weaponType;
		super.munition = munition;
		super.damage = damage;
		super.speed = speed;
		super.infinity = true;

		Server server = Main.getInstance().getServer();
		if(server.getRecipe(NamespacedKey.fromString("firebow", Main.getInstance())) == null) {
			ShapelessRecipe shapedRecipe = new ShapelessRecipe(Objects.requireNonNull(NamespacedKey.fromString("firebow", Main.getInstance())), this.weapontype);
			shapedRecipe.addIngredient(1, Material.BOW);
			shapedRecipe.addIngredient(1, Material.FLINT_AND_STEEL);
			shapedRecipe.setGroup("customweapons");
			server.addRecipe(shapedRecipe);
		}
	}

}

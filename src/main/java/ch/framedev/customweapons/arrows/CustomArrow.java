package ch.framedev.customweapons.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ch.framedev.customweapons.main.Main;

public class CustomArrow implements Listener {

	public String name;
	public boolean critical;

	public CustomArrow(String name, boolean critical) {
		this.name = name;
		this.critical = critical;
		Main.getPlugin(Main.class).getServer().getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
	}
	
	public Entity shoot(Player player, double damage, double speed) {
		Arrow arrow = player.launchProjectile(Arrow.class);
		arrow.setDamage(damage);
		arrow.setCustomName(name);
		arrow.setCustomNameVisible(true);
		arrow.setCritical(critical);
		arrow.setVelocity(arrow.getVelocity().multiply(speed));
		return arrow;
	}
	
	@Override
	public String toString() {
		return name + " : " + critical;
	}
}

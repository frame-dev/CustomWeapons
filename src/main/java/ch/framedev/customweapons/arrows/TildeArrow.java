package ch.framedev.customweapons.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TildeArrow extends CustomArrow {

	public TildeArrow(String name, boolean critical) {
		super(name, critical);
	}
	
	@Override
	public Entity shoot(Player player, double damage, double speed) {
		Arrow arrow = player.launchProjectile(Arrow.class);
		arrow.setDamage(damage / 2);
		arrow.setCustomName(name);
		arrow.setCustomNameVisible(true);
		arrow.setFireTicks(20*240);
		arrow.setVisualFire(true);
		arrow.setCritical(critical);
		return arrow;
	}

}

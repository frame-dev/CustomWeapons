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
		Arrow arrow = (Arrow) super.shoot(player, damage, speed);
		arrow.setDamage(damage / 2);
		arrow.setVelocity(arrow.getVelocity().multiply(speed / 2));
		return arrow;
	}

}

package ch.framedev.customweapons.arrows;

import java.util.ArrayList;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ch.framedev.customweapons.main.Main;

public class PotionArrow extends CustomArrow {

	private ArrayList<PotionEffect> effects;

	public PotionArrow(String name, boolean critical) {
		super(name, critical);
		this.effects = new ArrayList<>();
		this.effects.add(new PotionEffect(PotionEffectType.HARM, 20 * 5, 2, true, true));
		this.effects.add(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 5, true, true));
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}

	@EventHandler
	public void onHitArrow(ProjectileHitEvent event) {
		if (event.getEntity().getType() == EntityType.ARROW) {
			if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase(name))
				if (event.getHitEntity() != null) {
					Entity entity = event.getHitEntity();
					if (entity instanceof LivingEntity) {
						LivingEntity liv = (LivingEntity) entity;
						for (PotionEffect effect : effects) {
							liv.addPotionEffect(effect);
						}
					}
				}
		}
	}

	@Override
	public Entity shoot(Player player, double damage, double speed) {
		Arrow arrow = player.launchProjectile(Arrow.class);
		arrow.setDamage(damage / 2);
		arrow.setCustomName(name);
		arrow.setCustomNameVisible(true);
		arrow.setFireTicks(20 * 240);
		arrow.setVisualFire(true);
		arrow.setCritical(critical);
		return arrow;
	}

}

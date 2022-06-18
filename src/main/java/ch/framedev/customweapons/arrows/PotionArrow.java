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
import org.bukkit.scheduler.BukkitRunnable;

import ch.framedev.customweapons.main.Main;

public class PotionArrow extends CustomArrow {

	public PotionArrow(String name, boolean critical) {
		super(name, critical);
	}
	
	public Class<CustomArrow> getClazz() {
		return CustomArrow.class;
	}


	@EventHandler
	public void onHitArrow(ProjectileHitEvent event) {
		ArrayList<PotionEffect> effects = new ArrayList<>();
		effects.add(new PotionEffect(PotionEffectType.GLOWING, 20*5, 1, true, true));
		effects.add(new PotionEffect(PotionEffectType.BLINDNESS, 20*5, 1, true, true));
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
			if(event.getHitBlock() != null) {
				new BukkitRunnable() {
					
					@Override
					public void run() {
						event.getEntity().remove();
					}
				}.runTaskLater(Main.getInstance(),20*15);
			}
		}
	}

	@Override
	public Entity shoot(Player player, double damage, double speed) {
		Arrow arrow = player.launchProjectile(Arrow.class);
		arrow.setDamage(damage);
		arrow.setCustomName(name);
		arrow.setCustomNameVisible(true);
		arrow.setCritical(critical);
		arrow.setVelocity(arrow.getVelocity().multiply(speed));
		return arrow;
	}

}

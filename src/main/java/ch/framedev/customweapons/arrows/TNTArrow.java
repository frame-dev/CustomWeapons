package ch.framedev.customweapons.arrows;

import ch.framedev.customweapons.main.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TNTArrow extends CustomArrow {

    public double delay;

    public TNTArrow(String name, boolean critical, double delay) {
        super(name, critical);
        this.delay = delay;
    }

    public TNTArrow(String name, boolean critical, double delay, ArrowType arrowType) {
        super(name, critical, arrowType);
        this.delay = delay;
    }

    BukkitTask runnable;

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase(name)) {
            if (event.getEntity().isOnGround() || event.getHitEntity() != null)
                runnable.cancel();
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        runnable = new BukkitRunnable() {
            int delayTime = (int) delay;

            @Override
            public void run() {
                if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase(name)) {
                    runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (delayTime > 0) {
                                delayTime--;
                            } else {
                                if (!event.getEntity().isOnGround()) {
                                    event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().subtract(0, 0.015, 0), EntityType.PRIMED_TNT);
                                } else {
                                    delayTime = (int) delay;
                                    this.cancel();
                                }
                            }
                        }
                    }.runTaskTimer(Main.getInstance(), 0, 3);
                }
            }
        }.runTaskLater(Main.getInstance(), 2);
    }
}

package ch.framedev.customweapons.arrows;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ExplodeArrow extends CustomArrow {

    public ExplodeArrow(String name, boolean critical) {
        super(name, critical);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase(name)) {
            TNTPrimed primed = (TNTPrimed) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            primed.setFuseTicks(2);
            primed.setYield(5);
            event.getEntity().remove();
            if (event.getHitEntity().equals(event.getEntity().getShooter()))
                event.setCancelled(true);
        }
    }
}

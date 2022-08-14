package ch.framedev.customweapons.arrows;

import static org.bukkit.entity.AbstractArrow.*;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ch.framedev.customweapons.main.Main;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class CustomArrow implements Listener {

    public String name;
    public boolean critical;
    private ArrowType arrowType;

    public CustomArrow(String name, boolean critical) {
        this.name = name;
        this.critical = critical;
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    public CustomArrow(String name, boolean critical, ArrowType arrowType) {
        this.name = name;
        this.critical = critical;
        this.arrowType = arrowType;
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    public ArrowType getArrowType() {
        return arrowType;
    }

    public Entity shoot(Player player, double damage, double speed) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setDamage(damage);
        arrow.setCustomName(name);
        arrow.setCustomNameVisible(true);
        arrow.setCritical(critical);
        arrow.setVelocity(arrow.getVelocity().multiply(speed));
        if (arrowType != null) {
            arrow.setColor(arrowType.getColor());
            if (arrowType == ArrowType.BLUE) {
                arrow.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
                arrow.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 10, 1, true, true), true);
                arrow.setPickupStatus(PickupStatus.DISALLOWED);
            } else {
                arrow.setPickupStatus(PickupStatus.ALLOWED);
            }
        }
        return arrow;
    }

    @Override
    public String toString() {
        return name + " : " + critical;
    }


    public Class<CustomArrow> getClazz() {
        return CustomArrow.class;
    }
}

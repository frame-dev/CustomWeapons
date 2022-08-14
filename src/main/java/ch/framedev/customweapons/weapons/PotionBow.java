package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.CustomArrow;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PotionBow extends AbstractWeapon {

    private List<PotionEffect> effects;

    public PotionBow(String name, ItemStack weapontype, CustomArrow munition, double damage, double speed, boolean infinity) {
        super(name, weapontype, munition, damage, speed, infinity);
        this.effects = new ArrayList<>();
        addPotionEffect(PotionEffectType.GLOWING, 1, 5);
        createRecipe(this, Material.GLASS_BOTTLE);
    }

    public void addPotionEffect(PotionEffectType potionEffectType, int amplifier, int seconds) {
        effects.add(new PotionEffect(potionEffectType, seconds * 20, amplifier));
    }

    @EventHandler
    public void onArrowHitEvent(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase(munition.name)) {
                if (event.getHitEntity() != null)
                    if (event.getHitEntity() instanceof LivingEntity) {
                        LivingEntity entity = (LivingEntity) event.getHitEntity();
                        entity.addPotionEffects(effects);
                    }
            }
        }
    }
}

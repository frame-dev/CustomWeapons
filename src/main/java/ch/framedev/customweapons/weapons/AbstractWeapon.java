package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.arrows.TNTArrow;
import ch.framedev.customweapons.main.Main;
import com.google.gson.Gson;
import de.framedev.javautils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author FrameDev
 */
public abstract class AbstractWeapon implements Listener {

    public String name;
    public double damage;
    public CustomArrow munition;
    public ItemStack weaponType;
    public double speed;
    public boolean infinity;
    public String bowType;
    public static HashMap<Player, ItemStack> arrowShoot = new HashMap<>();

    public AbstractWeapon(String name, ItemStack weaponType, CustomArrow munition, double damage, double speed, boolean infinity) {
        this.name = name;
        this.weaponType = weaponType;
        this.munition = munition;
        this.damage = damage;
        this.speed = speed;
        this.infinity = infinity;
        this.bowType = this.getClass().getSimpleName();
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        this.bowType = this.getClass().getSimpleName();
        create();
    }

    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getBow().hasItemMeta() && event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                arrowShoot.put((Player) event.getEntity(), this.weaponType);
                event.setProjectile(munition.shoot((Player) event.getEntity(), damage, speed));
                Arrow arrow = (Arrow) event.getProjectile();
                if (infinity)
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
            }
        }
    }

    public ItemStack create() {
        if (weaponType.getType() == Material.BOW) {
            ItemMeta meta = weaponType.getItemMeta();
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();
            lore.addAll(Arrays.asList("Bow Type : " + bowType, "§aDamage : §6" + damage, "Arrow Type : " + munition.getClass().getSimpleName(), "§aMunition : §6" + munition, "§aSpeed : §6" + speed, "§aInfinity : §6" + infinity));
            if (infinity)
                meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            if (munition instanceof TNTArrow) {
                lore.add("Delay : " + ((TNTArrow) munition).delay);
            }
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            weaponType.setItemMeta(meta);
            Main.getInstance().getWeaponRegister().registerWeapon(this);
            return weaponType;
        } else if (weaponType.getType() == Material.CROSSBOW) {
            CrossbowMeta meta = (CrossbowMeta) weaponType.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList("Bow Type : " + bowType, "§aDamage : §6" + damage, "Arrow Type : " + munition.getClass().getSimpleName(), "§aMunition : §6" + munition, "§aSpeed : §6" + speed));
            if (infinity)
                meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            weaponType.setItemMeta(meta);
            Main.getInstance().getWeaponRegister().registerWeapon(this);
            return weaponType;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractWeapon> T load(File file) throws ClassNotFoundException {
        ReflectionUtils reflectionUtils = new ReflectionUtils();
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Class<AbstractWeapon> clazz = (Class<AbstractWeapon>) getClassFromPackageList(cfg.getString("type"));
        List<Object> params = new ArrayList<>();
        params.add(cfg.getString("name"));
        params.add(new ItemStack(Material.getMaterial(cfg.getString("weapontype"))));
        List<Object> paramsV2 = new ArrayList<>();
        paramsV2.add(cfg.getString("munition.name"));
        paramsV2.add(cfg.getBoolean("munition.critical"));
        Object mun;
        if (cfg.contains("munition.delay")) {
            paramsV2.add(cfg.getDouble("munition.delay"));
            mun = reflectionUtils.newInstance(reflectionUtils.getClassName(getClassArrowFromPackageList(cfg.getString("munition.type"))), paramsV2, true, String.class, boolean.class, double.class);
        } else
            mun = reflectionUtils.newInstance(reflectionUtils.getClassName(getClassArrowFromPackageList(cfg.getString("munition.type"))), paramsV2, true, String.class, boolean.class);
        params.add(mun);
        params.add(cfg.getDouble("damage"));
        params.add(cfg.getDouble("speed"));
        params.add(cfg.getBoolean("infinity"));
        Object o = reflectionUtils.newInstance(reflectionUtils.getClassName(clazz), params, true, String.class, ItemStack.class, CustomArrow.class, double.class, double.class, boolean.class);
        T weapon = (T) o;
        return (T) weapon;
    }

    private static Class<?> getClassFromPackageList(String bowType) {
        for (String s : Main.getInstance().getConfig().getStringList("packages")) {
            try {
                Class<?> claZ = Class.forName(s + ".weapons." + bowType);
                return claZ;
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private static Class<?> getClassArrowFromPackageList(String arrowType) {
        for (String s : Main.getInstance().getConfig().getStringList("packages")) {
            try {
                Class<?> claZ = Class.forName(s + ".arrows." + arrowType);
                return claZ;
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

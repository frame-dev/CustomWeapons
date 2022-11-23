package ch.framedev.customweapons.weapons;

import ch.framedev.customweapons.arrows.ArrowType;
import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.arrows.TNTArrow;
import ch.framedev.customweapons.main.Main;
import com.google.gson.Gson;
import de.framedev.javautils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
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
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * @author FrameDev
 */
public abstract class AbstractWeapon implements Listener, Serializable {

    public String name;
    public double damage;
    public CustomArrow munition;
    public ItemStack weapontype;
    public double speed;
    public boolean infinity;
    public String bowType;
    public static HashMap<Player, ItemStack> arrowShoot = new HashMap<>();

    public AbstractWeapon(String name, ItemStack weapontype, CustomArrow munition, double damage, double speed, boolean infinity) {
        this.name = name;
        this.weapontype = weapontype;
        this.munition = munition;
        this.damage = damage;
        this.speed = speed;
        this.infinity = infinity;
        this.bowType = this.getClass().getSimpleName();
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        this.bowType = this.getClass().getSimpleName();
        create();
        if (!Main.getInstance().getWeaponRegister().getBows().contains(this))
            Main.getInstance().getWeaponRegister().registerWeapon(this);
    }

    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getBow() != null && event.getBow().hasItemMeta() && event.getBow().getItemMeta() != null &&
                    event.getBow().getItemMeta().hasDisplayName() && event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                arrowShoot.put((Player) event.getEntity(), this.weapontype);
                event.setProjectile(munition.shoot((Player) event.getEntity(), damage, speed));
                Arrow arrow = (Arrow) event.getProjectile();
                if (infinity)
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
            }
        }
    }

    /**
     * Create the ItemStack with the Name speed infinity, etc.
     *
     * @return returns the Created ItemStack
     */
    public ItemStack create() {
        if (weapontype.getType() == Material.BOW) {
            ItemMeta meta = weapontype.getItemMeta();
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();
            lore.addAll(Arrays.asList("Bow Type : " + bowType, "§aDamage : §6" + damage, "Arrow Type : " + munition.getClass().getSimpleName(), "§aMunition : §6" + munition, "§aSpeed : §6" + speed, "§aInfinity : §6" + infinity));
            if (infinity)
                meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            if (munition instanceof TNTArrow) {
                lore.add("Delay : " + ((TNTArrow) munition).delay);
            }
            if (munition.getArrowType() != null)
                lore.add("§aArrowType : §6" + munition.getArrowType());
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            weapontype.setItemMeta(meta);
            Main.getInstance().getWeaponRegister().registerWeapon(this);
            return weapontype;
        } else if (weapontype.getType() == Material.CROSSBOW) {
            CrossbowMeta meta = (CrossbowMeta) weapontype.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList("Bow Type : " + bowType, "§aDamage : §6" + damage, "Arrow Type : " + munition.getClass().getSimpleName(), "§aMunition : §6" + munition, "§aSpeed : §6" + speed));
            if (infinity)
                meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            weapontype.setItemMeta(meta);
            Main.getInstance().getWeaponRegister().registerWeapon(this);
            return weapontype;
        }
        System.out.println("null");
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractWeapon> T load(File file) throws ClassNotFoundException {
        ReflectionUtils reflectionUtils = new ReflectionUtils();
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Class<AbstractWeapon> clazz = (Class<AbstractWeapon>) getClassFromPackageList(cfg.getString("type"));
        List<Object> params = new ArrayList<>();
        params.add(cfg.getString("name"));
        params.add(new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(cfg.getString("weapontype"))))));
        List<Object> paramsV2 = new ArrayList<>();
        paramsV2.add(cfg.getString("munition.name"));
        paramsV2.add(cfg.getBoolean("munition.critical"));
        Object mun;
        if (cfg.contains("munition.arrowType")) {
            if (cfg.contains("munition.delay")) {
                paramsV2.add(cfg.getDouble("munition.delay"));
                paramsV2.add(ArrowType.valueOf(cfg.getString("munition.arrowType")));
                mun = reflectionUtils.newInstance(reflectionUtils.getClassName(getClassArrowFromPackageList(cfg.getString("munition.type"))), paramsV2, true, String.class, boolean.class, double.class);
            } else {
                paramsV2.add(ArrowType.valueOf(cfg.getString("munition.arrowType")));
                mun = reflectionUtils.newInstance(reflectionUtils.getClassName(getClassArrowFromPackageList(cfg.getString("munition.type"))), paramsV2, true, String.class, boolean.class, ArrowType.class);
            }
        } else if (cfg.contains("munition.delay")) {
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

    public static void createRecipe(AbstractWeapon abstractWeapon, Material ingredient) {
        Server server = Main.getInstance().getServer();
        if (server.getRecipe(Objects.requireNonNull(NamespacedKey.fromString(abstractWeapon.name.toLowerCase().replace(" ", "_").replace("-", "_"), Main.getInstance()))) == null) {
            ShapelessRecipe shapedRecipe = new ShapelessRecipe(Objects.requireNonNull(NamespacedKey.fromString(abstractWeapon.name.toLowerCase().replace(" ", "_").replace("-", "_"), Main.getInstance())), abstractWeapon.weapontype);
            shapedRecipe.addIngredient(1, Material.BOW);
            shapedRecipe.addIngredient(1, ingredient);
            shapedRecipe.setGroup("customweapons");
            server.addRecipe(shapedRecipe);
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

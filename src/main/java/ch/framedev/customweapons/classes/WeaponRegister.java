package ch.framedev.customweapons.classes;

import java.util.HashMap;

import de.framedev.javautils.FrameList;

public class WeaponRegister {
	
	private FrameList<AbstractWeapon<?>> weapons = new FrameList<>();
	private HashMap<Class<?>, CustomArrow> arrows = new HashMap<>();
	
	public WeaponRegister() {
	}
	
	public <T extends AbstractWeapon<?>> void registerWeapon(T t) {
		weapons.add(t);
		arrows.put(t.munition.getClass(), t.munition);
	}
	
	public FrameList<AbstractWeapon<?>> getWeapons() {
		return weapons;
	}
	
	public HashMap<Class<?>, CustomArrow> getArrows() {
		return arrows;
	}

}

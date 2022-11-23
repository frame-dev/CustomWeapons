package ch.framedev.customweapons.managers;

import java.util.HashMap;

import ch.framedev.customweapons.arrows.CustomArrow;
import ch.framedev.customweapons.swords.AbstractSword;
import ch.framedev.customweapons.weapons.AbstractWeapon;
import de.framedev.javautils.FrameList;

public class WeaponRegister {
	
	private FrameList<AbstractWeapon> bows = new FrameList<>();
	private HashMap<Class<?>, CustomArrow> arrows = new HashMap<>();
	private FrameList<AbstractSword> swords = new FrameList<>();
	
	public WeaponRegister() {
	}
	
	public <T extends AbstractWeapon> void registerWeapon(T t) {
		bows.add(t);
		arrows.put(t.munition.getClass(), t.munition);
	}
	
	public FrameList<AbstractWeapon> getBows() {
		return bows;
	}

	public FrameList<AbstractSword> getSwords() {
		return swords;
	}

	public <T extends AbstractSword> void registerSword(T t) {
		this.swords.add(t);
	}

	public HashMap<Class<?>, CustomArrow> getArrows() {
		return arrows;
	}

}

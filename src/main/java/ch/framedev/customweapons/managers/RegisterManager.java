package ch.framedev.customweapons.managers;

import ch.framedev.customweapons.commands.WeaponCMD;
import ch.framedev.customweapons.main.Main;

public class RegisterManager {
	
	public RegisterManager(Main plugin) {
		new WeaponCMD(plugin);
	}

}

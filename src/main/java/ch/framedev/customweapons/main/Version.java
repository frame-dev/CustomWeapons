package ch.framedev.customweapons.main;

import org.bukkit.Bukkit;

public enum Version {
    VERSION_1_19,
    VERSION_1_18,
    VERSION_1_17,
    VERSION_1_16,
    VERSION_1_15,
    VERSION_1_14,
    VERSION_1_13,
    VERSION_1_12,
    VERSION_1_11,
    VERSION_1_10,
    VERSION_1_9,
    VERSION_1_8,
    NONE;

    public static Version getVersion() {
        String version = Bukkit.getVersion();
        if (version.contains("1.19"))
            return VERSION_1_19;
        else if (version.contains("1.18"))
            return VERSION_1_18;
        else if (version.contains("1.17"))
            return VERSION_1_17;
        else if (version.contains("1.16"))
            return VERSION_1_16;
        else if (version.contains("1.15"))
            return VERSION_1_15;
        else if (version.contains("1.14"))
            return VERSION_1_14;
        else if (version.contains("1.13"))
            return VERSION_1_13;
        else if (version.contains("1.12"))
            return VERSION_1_12;
        else if (version.contains("1.11"))
            return VERSION_1_11;
        else if (version.contains("1.10"))
            return VERSION_1_10;
        else if (version.contains("1.9"))
            return VERSION_1_9;
        else if (version.contains("1.8"))
            return VERSION_1_8;
        else
            return NONE;
    }
}

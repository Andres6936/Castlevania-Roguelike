package co.castle.conf;

import sz.csi.CharKey;

import java.lang.reflect.Field;
import java.util.Properties;

public class KeyBindings extends Properties {
    public KeyBindings() {
        Properties keyConfig = new KeyConfiguration();

        put("WEAPON_KEY", readKeyString(keyConfig, "weapon"));
        put("DONOTHING1_KEY", readKeyString(keyConfig, "doNothing"));
        put("DONOTHING2_KEY", readKeyString(keyConfig, "doNothing2"));
        put("UP1_KEY", readKeyString(keyConfig, "up"));
        put("UP2_KEY", readKeyString(keyConfig, "up2"));
        put("LEFT1_KEY", readKeyString(keyConfig, "left"));
        put("LEFT2_KEY", readKeyString(keyConfig, "left2"));
        put("RIGHT1_KEY", readKeyString(keyConfig, "right"));
        put("RIGHT2_KEY", readKeyString(keyConfig, "right2"));
        put("DOWN1_KEY", readKeyString(keyConfig, "down"));
        put("DOWN2_KEY", readKeyString(keyConfig, "down2"));
        put("UPRIGHT1_KEY", readKeyString(keyConfig, "upRight"));
        put("UPRIGHT2_KEY", readKeyString(keyConfig, "upRight2"));
        put("UPLEFT1_KEY", readKeyString(keyConfig, "upLeft"));
        put("UPLEFT2_KEY", readKeyString(keyConfig, "upLeft2"));
        put("DOWNLEFT1_KEY", readKeyString(keyConfig, "downLeft"));
        put("DOWNLEFT2_KEY", readKeyString(keyConfig, "downLeft2"));
        put("DOWNRIGHT1_KEY", readKeyString(keyConfig, "downRight"));
        put("DOWNRIGHT2_KEY", readKeyString(keyConfig, "downRight2"));
        put("SELF1_KEY", readKeyString(keyConfig, "self"));
        put("SELF2_KEY", readKeyString(keyConfig, "self2"));
        put("ATTACK1_KEY", readKeyString(keyConfig, "attack1"));
        put("ATTACK2_KEY", readKeyString(keyConfig, "attack2"));
        put("JUMP_KEY", readKeyString(keyConfig, "jump"));
        put("THROW_KEY", readKeyString(keyConfig, "throw"));
        put("EQUIP_KEY", readKeyString(keyConfig, "equip"));
        put("UNEQUIP_KEY", readKeyString(keyConfig, "unequip"));
        put("RELOAD_KEY", readKeyString(keyConfig, "reload"));
        put("USE_KEY", readKeyString(keyConfig, "use"));
        put("GET_KEY", readKeyString(keyConfig, "get"));
        put("GET2_KEY", readKeyString(keyConfig, "get2"));
        put("DROP_KEY", readKeyString(keyConfig, "drop"));
        put("DIVE_KEY", readKeyString(keyConfig, "dive"));
        put("TARGET_KEY", readKeyString(keyConfig, "target"));
        put("SWITCH_WEAPONS_KEY", readKeyString(keyConfig, "switchWeapons"));
        put("QUIT_KEY", readKeyString(keyConfig, "PROMPTQUIT"));
        put("HELP1_KEY", readKeyString(keyConfig, "HELP1"));
        put("HELP2_KEY", readKeyString(keyConfig, "HELP2"));
        put("LOOK_KEY", readKeyString(keyConfig, "LOOK"));
        put("PROMPT_SAVE_KEY", readKeyString(keyConfig, "PROMPTSAVE"));
        put("SHOW_SKILLS_KEY", readKeyString(keyConfig, "SHOWSKILLS"));
        put("SHOW_INVENTORY_KEY", readKeyString(keyConfig, "SHOWINVEN"));
        put("SHOW_STATS_KEY", readKeyString(keyConfig, "SHOWSTATS"));
        put("CHARDUMP_KEY", readKeyString(keyConfig, "CHARDUMP"));
        put("SHOW_MESSAGE_HISTORY_KEY", readKeyString(keyConfig, "SHOWMESSAGEHISTORY"));
        put("SHOW_MAP_KEY", readKeyString(keyConfig, "SHOWMAP"));
        put("EXAMINE_LEVEL_MAP_KEY", readKeyString(keyConfig, "EXAMINELEVELMAP"));
        put("SWITCH_MUSIC_KEY", readKeyString(keyConfig, "SWITCHMUSIC"));
    }

    public int getIntProperty(final String key) {
        return Integer.parseInt(getProperty(key));
    }

    private static String readKeyString(Properties config, String keyName) {
        return readKey(config, keyName) + "";
    }

    private static int readKey(Properties config, String keyName) {
        String fieldName = config.getProperty(keyName).trim();
        if (fieldName == null)
            throw new RuntimeException("Invalid key.cfg file, property not found: " + keyName);
        try {
            Field field = CharKey.class.getField(fieldName);
            return field.getInt(CharKey.class);
        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading field : " + fieldName);
        }
    }
}

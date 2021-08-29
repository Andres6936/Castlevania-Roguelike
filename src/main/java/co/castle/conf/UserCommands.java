package co.castle.conf;

import co.castle.ui.CommandListener;
import co.castle.ui.UserCommand;

import java.util.ArrayList;

public class UserCommands extends ArrayList<UserCommand> {
    public UserCommands(KeyBindings keyBindings) {
        add(new UserCommand(CommandListener.PROMPTQUIT, keyBindings.getIntProperty("QUIT_KEY")));
        add(new UserCommand(CommandListener.HELP, keyBindings.getIntProperty("HELP1_KEY")));
        add(new UserCommand(CommandListener.LOOK, keyBindings.getIntProperty("LOOK_KEY")));
        add(new UserCommand(CommandListener.PROMPTSAVE, keyBindings.getIntProperty("PROMPT_SAVE_KEY")));
        add(new UserCommand(CommandListener.SHOWSKILLS, keyBindings.getIntProperty("SHOW_SKILLS_KEY")));
        add(new UserCommand(CommandListener.HELP, keyBindings.getIntProperty("HELP2_KEY")));
        add(new UserCommand(CommandListener.SHOWINVEN, keyBindings.getIntProperty("SHOW_INVENTORY_KEY")));
        add(new UserCommand(CommandListener.SHOWSTATS, keyBindings.getIntProperty("SHOW_STATS_KEY")));
        add(new UserCommand(CommandListener.CHARDUMP, keyBindings.getIntProperty("CHARDUMP_KEY")));
        add(new UserCommand(CommandListener.SHOWMESSAGEHISTORY, keyBindings.getIntProperty("SHOW_MESSAGE_HISTORY_KEY")));
        add(new UserCommand(CommandListener.SHOWMAP, keyBindings.getIntProperty("SHOW_MAP_KEY")));
        add(new UserCommand(CommandListener.EXAMINELEVELMAP, keyBindings.getIntProperty("EXAMINE_LEVEL_MAP_KEY")));
        add(new UserCommand(CommandListener.SWITCHMUSIC, keyBindings.getIntProperty("SWITCH_MUSIC_KEY")));
    }
}

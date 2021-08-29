package co.castle.conf;

import co.castle.action.*;
import co.castle.ui.UserAction;

import java.util.ArrayList;

public class UserActions extends ArrayList<UserAction> {
    private final Action get = new Get();
    private final Action attack = new Attack();
    private final Action target = new TargetPS();

    public UserActions(KeyBindings keyBindings) {
        add(new UserAction(attack, keyBindings.getIntProperty("ATTACK1_KEY")));
        add(new UserAction(attack, keyBindings.getIntProperty("ATTACK2_KEY")));
        add(new UserAction(new Jump(), keyBindings.getIntProperty("JUMP_KEY")));
        add(new UserAction(new Throw(), keyBindings.getIntProperty("THROW_KEY")));
        add(new UserAction(new Equip(), keyBindings.getIntProperty("EQUIP_KEY")));
        add(new UserAction(new Unequip(), keyBindings.getIntProperty("UNEQUIP_KEY")));
        add(new UserAction(new Reload(), keyBindings.getIntProperty("RELOAD_KEY")));
        add(new UserAction(new Use(), keyBindings.getIntProperty("USE_KEY")));
        add(new UserAction(get, keyBindings.getIntProperty("GET_KEY")));
        add(new UserAction(new Drop(), keyBindings.getIntProperty("DROP_KEY")));
        add(new UserAction(new Dive(), keyBindings.getIntProperty("DIVE_KEY")));
        add(new UserAction(target, keyBindings.getIntProperty("TARGET_KEY")));
        add(new UserAction(new SwitchWeapons(), keyBindings.getIntProperty("SWITCH_WEAPONS_KEY")));
        add(new UserAction(get, keyBindings.getIntProperty("GET2_KEY")));
    }
}

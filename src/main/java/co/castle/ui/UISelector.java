package co.castle.ui;

import java.util.Hashtable;
import java.util.Properties;

import co.castle.action.Action;
import co.castle.ai.ActionSelector;
import co.castle.conf.UserActions;
import co.castle.level.Level;
import co.castle.player.Player;
import sz.csi.KeyCode;
import sz.util.Debug;
import sz.util.Position;

public abstract class UISelector implements ActionSelector {
	public int DONOTHING1_KEY;

	public int DONOTHING2_KEY;
	private transient UserInterface ui;
	protected Action advance;
	protected Action attack;
	protected Position defaultTarget;
	protected Hashtable<String, UserAction> gameActions = new Hashtable<>();
	protected Level level;
	protected Player player;
	protected Action target;
	public static int UP1_KEY, UP2_KEY, LEFT1_KEY, LEFT2_KEY, RIGHT1_KEY, RIGHT2_KEY,
			DOWN1_KEY, DOWN2_KEY, UPRIGHT1_KEY, UPRIGHT2_KEY, UPLEFT1_KEY, UPLEFT2_KEY,
			DOWNLEFT1_KEY, DOWNLEFT2_KEY, DOWNRIGHT1_KEY, DOWNRIGHT2_KEY, SELF1_KEY,
			SELF2_KEY;

	public static int WEAPON_KEY;

    public static int toIntDirection(KeyCode ck) {
        if (isKey(ck, UP1_KEY, UP2_KEY))
            return Action.UP;
        else if (isKey(ck, LEFT1_KEY, LEFT2_KEY))
            return Action.LEFT;
        else if (isKey(ck, RIGHT1_KEY, RIGHT2_KEY))
            return Action.RIGHT;
        else if (isKey(ck, DOWN1_KEY, DOWN2_KEY))
            return Action.DOWN;
        else if (isKey(ck, UPRIGHT1_KEY, UPRIGHT2_KEY))
			return Action.UPRIGHT;
		else if ( isKey( ck, UPLEFT1_KEY, UPLEFT2_KEY ) )
			return Action.UPLEFT;
		else if ( isKey( ck, DOWNLEFT1_KEY, DOWNLEFT2_KEY ) )
			return Action.DOWNLEFT;
		else if ( isKey( ck, DOWNRIGHT1_KEY, DOWNRIGHT2_KEY ) )
			return Action.DOWNRIGHT;
		if ( isKey( ck, SELF1_KEY, SELF2_KEY ) )
			return Action.SELF;
		return -1;
	}

    private static boolean isKey(KeyCode ck, int key1, int key2) {
        return ck.code == key1 || ck.code == key2;
    }

	public UserInterface getUI( )
	{
		if ( ui == null )
			ui = UserInterface.getUI( );
		return ui;
	}

    public boolean isArrow(KeyCode input) {
        return toIntDirection(input) != -1;
    }

	public void setPlayer( Player p )
	{
		player = p;
		level = player.getLevel( );
	}

	protected Action getRelatedAction( int keyCode ) {
		Debug.enterMethod(this, "getRelatedAction", keyCode + "");
		UserAction ua = gameActions.get(keyCode + "");
		if (ua == null) {
			Debug.exitMethod("null");
			return null;
		}
		Action ret = ua.getAction();
		Debug.exitMethod(ret);
		return ret;
	}

	public UISelector(UserActions gameActions, UserInterface ui, Properties keyBindings) {
		this.ui = ui;
		this.advance = gameActions.getWalkAction();
		this.target = gameActions.getTargetAction();
		this.attack = gameActions.getAttackAction();
		for (UserAction gameAction : gameActions) {
			this.gameActions.put(gameAction.getKeyCode() + "", gameAction);
		}
		WEAPON_KEY = Integer.parseInt(keyBindings.getProperty("WEAPON_KEY"));
		DONOTHING1_KEY = Integer.parseInt(keyBindings.getProperty("DONOTHING1_KEY"));
		DONOTHING2_KEY = Integer.parseInt(keyBindings.getProperty("DONOTHING2_KEY"));
		UP1_KEY = Integer.parseInt(keyBindings.getProperty("UP1_KEY"));
		UP2_KEY = Integer.parseInt(keyBindings.getProperty("UP2_KEY"));
		LEFT1_KEY = Integer.parseInt(keyBindings.getProperty("LEFT1_KEY"));
		LEFT2_KEY = Integer.parseInt(keyBindings.getProperty("LEFT2_KEY"));
		RIGHT1_KEY = Integer.parseInt(keyBindings.getProperty("RIGHT1_KEY"));
		RIGHT2_KEY = Integer.parseInt(keyBindings.getProperty("RIGHT2_KEY"));
		DOWN1_KEY = Integer.parseInt( keyBindings.getProperty( "DOWN1_KEY" ) );
		DOWN2_KEY = Integer.parseInt( keyBindings.getProperty( "DOWN2_KEY" ) );
		UPRIGHT1_KEY = Integer.parseInt( keyBindings.getProperty( "UPRIGHT1_KEY" ) );
		UPRIGHT2_KEY = Integer.parseInt( keyBindings.getProperty( "UPRIGHT2_KEY" ) );
		UPLEFT1_KEY = Integer.parseInt( keyBindings.getProperty( "UPLEFT1_KEY" ) );
		UPLEFT2_KEY = Integer.parseInt( keyBindings.getProperty( "UPLEFT2_KEY" ) );
		DOWNLEFT1_KEY = Integer.parseInt( keyBindings.getProperty( "DOWNLEFT1_KEY" ) );
		DOWNLEFT2_KEY = Integer.parseInt( keyBindings.getProperty( "DOWNLEFT2_KEY" ) );
		DOWNRIGHT1_KEY = Integer.parseInt( keyBindings.getProperty( "DOWNRIGHT1_KEY" ) );
		DOWNRIGHT2_KEY = Integer.parseInt( keyBindings.getProperty( "DOWNRIGHT2_KEY" ) );
		SELF1_KEY = Integer.parseInt( keyBindings.getProperty( "SELF1_KEY" ) );
		SELF2_KEY = Integer.parseInt( keyBindings.getProperty( "SELF2_KEY" ) );
	}
}

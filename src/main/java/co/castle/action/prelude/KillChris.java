package co.castle.action.prelude;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class KillChris extends Action
{
	private String effectString;
	private String effectType;
	private String message;
	private int range;

	public void execute( )
	{

		Monster aMonster = (Monster) performer;
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		aLevel.addMessage( "Dracula invokes a deadly beam of chaos energy!!!" );
		drawEffect( EffectFactory.getSingleton( ).createLocatedEffect(
				new Position( aPlayer.getPosition( ).x, aPlayer.getPosition( ).y ),
				"SFX_KILL_CHRIS" ) );
	}

	public int getCost( )
	{
		return 50;
	}

	public String getID( )
	{
		return "PRELUDE_KILL_CHRIS";
	}

}
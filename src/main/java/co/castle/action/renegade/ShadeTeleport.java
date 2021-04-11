package co.castle.action.renegade;

import co.castle.action.HeartAction;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;

public class ShadeTeleport extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player player = (Player) performer;
		Level level = player.getLevel( );
		level.addMessage( "You wrap in your cape and dissapear!" );
		if ( targetPosition.equals( performer.getPosition( ) ) )
		{
			level.addMessage( "You appear in the same place!" );
			return;
		}

		// crl.ui.effects.FlashEffect x = new FlashEffect(player.getPosition(),
		// Appearance.GRAY);
		Line line = new Line( player.getPosition( ), targetPosition );
		Position runner = line.next( );
		int i = 0;
		for ( ; i < 8; i++ )
		{
			runner = line.next( );
			Cell destinationCell = performer.getLevel( ).getMapCell( runner );
			if ( level.isWalkable( runner ) && destinationCell.getHeight( ) == level
					.getMapCell( player.getPosition( ) ).getHeight( ) )
				;
			else
				break;
		}
		drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
				player.getPosition( ), targetPosition, "SFX_SHADETELEPORT", i ) );

		player.setPosition( new Position( runner ) );
		player.see( );
		UserInterface.getUI( ).refresh( );

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.4 );
	}

	public int getHeartCost( )
	{
		return 5;
	}

	public String getID( )
	{
		return "ShadeTeleport";
	}

	public String getPromptPosition( )
	{
		return "Where do you want to blink?";
	}

	public String getSFX( )
	{
		return "wav/scrch.wav";
	}

	public boolean needsPosition( )
	{
		return true;
	}
}
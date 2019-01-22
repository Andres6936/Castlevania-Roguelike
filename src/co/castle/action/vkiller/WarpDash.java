package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;

public class WarpDash extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player player = (Player) performer;
		Level level = player.getLevel( );
		level.addMessage( "You dash and disappear!" );
		if ( targetPosition.equals( performer.getPosition( ) ) )
		{
			level.addMessage( "You appear in the same place!" );
			return;
		}

		Line line = new Line( player.getPosition( ), targetPosition );
		Position runner = line.next( );
		int i = 0;
		for ( ; i < 5; i++ )
		{
			runner = line.next( );
			Cell destinationCell = performer.getLevel( ).getMapCell( runner );
			if ( destinationCell == null || destinationCell.isSolid( )
					|| destinationCell.getHeight( ) != level
							.getMapCell( player.getPosition( ) ).getHeight( ) )
				break;

		}
		player.landOn( new Position( runner ) );
		drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
				performer.getPosition( ), targetPosition, "SFX_WARPDASH", i ) );
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
		return 3;
	}

	public String getID( )
	{
		return "WarpDash";
	}

	public String getPromptPosition( )
	{
		return "Where do you want to dash?";
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
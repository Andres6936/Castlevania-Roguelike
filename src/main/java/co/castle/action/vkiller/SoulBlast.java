package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.monster.VMonster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class SoulBlast extends HeartAction
{

	public void execute( )
	{
		super.execute( );
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "Soul Blast!" );
		int damage = 50 + aLevel.getPlayer( ).getShotLevel( ) * 2
				+ aLevel.getPlayer( ).getSoulPower( ) * 3;
		Position blastPosition = performer.getPosition( );
		aLevel.addEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( blastPosition, "SFX_SOUL_BLAST" ) );

		VMonster monsters = aLevel.getMonsters( );
		for ( int i = 0; i < monsters.size( ); i++ )
		{
			if ( monsters.elementAt( i ).getPosition( ).z == performer.getPosition( ).z
					&& Position.distance( monsters.elementAt( i ).getPosition( ),
							performer.getPosition( ) ) < 5 )
			{
				StringBuffer buff = new StringBuffer( );
				if ( monsters.elementAt( i ).wasSeen( ) )
				{
					buff.append( "The " + monsters.elementAt( i ).getDescription( )
							+ " is hit by the holy blast!" );
				}
				monsters.elementAt( i ).damage( buff, damage );
				aLevel.addMessage( buff.toString( ) );
			}
		}

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 20;
	}

	public String getID( )
	{
		return "Soul Flame";
	}

	public String getPromptoPosition( )
	{
		return "Where do you want to throw the vial?";
	}

}
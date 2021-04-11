package co.castle.action;

import co.castle.actor.Actor;
import co.castle.player.Player;

public abstract class MorphAction extends HeartAction
{
	public boolean canPerform( Actor a )
	{
		if ( getPlayer( a ).isSwimming( ) )
		{
			invalidationMessage = "You can't morph here";
			return false;
		}
		return super.canPerform( a );
	}

	public final void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		aPlayer.getLevel( ).addMessage( getMorphMessage( ) );
		aPlayer.morph( getMorphID( ), getMorphTime( ), isSmallMorph( ), isBigMorph( ),
				getMorphStrength( ), getMadChance( ) );
	}

	public abstract int getMadChance( );

	public abstract String getMorphID( );

	public abstract String getMorphMessage( );

	public abstract int getMorphStrength( );

	public abstract int getMorphTime( );

	public abstract boolean isBigMorph( );

	public abstract boolean isSmallMorph( );

}
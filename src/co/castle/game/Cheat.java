package co.castle.game;

import co.castle.player.Player;
import sz.csi.CharKey;

public class Cheat
{
	private final static boolean enabled = false;

	public static boolean cheatConsole( Player player, int charCode )
	{
		if ( !enabled )
			return false;
		switch ( charCode )
		{
		case CharKey.F2:
			player.increaseWhip( );
			player.addHearts( 5 );
			// player.informPlayerEvent(Player.EVT_LEVELUP);
			player.addXP( player.getNextXP( ) - player.getXp( ) + 1 );

			break;
		case CharKey.F3:
			player.setInvincible( 250 );
			// player.increaseWeaponSkill(ItemDefinition.CAT_WHIPS);
			break;
		case CharKey.F4:
			String nextLevel = player.getLevel( ).getMetaData( ).getExit( "_NEXT" );
			player.informPlayerEvent( Player.EVT_GOTO_LEVEL, nextLevel );

			break;
		case CharKey.F5:
			player.heal( );
			break;
		case CharKey.F6:
			if ( player.getLevel( ).getBoss( ) != null )
				player.getLevel( ).getBoss( ).damage( new StringBuffer( ), 15 );
			break;
		case CharKey.F7:
			player.getLevel( ).setIsDay( !player.getLevel( ).isDay( ) );
			break;
		case CharKey.F8:
			// player.informPlayerEvent(Player.EVT_BACK_LEVEL);
			break;
		default:
			return false;
		}
		return true;
	}
}

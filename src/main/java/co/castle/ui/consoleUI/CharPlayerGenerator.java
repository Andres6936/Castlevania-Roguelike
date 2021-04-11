package co.castle.ui.consoleUI;

import co.castle.game.PlayerGenerator;
import co.castle.player.Player;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.Display;
import sz.csi.CharKey;
import sz.csi.ConsoleSystemInterface;
import sz.csi.textcomponents.TextBox;

public class CharPlayerGenerator extends PlayerGenerator
{
	private ConsoleSystemInterface si;

	public CharPlayerGenerator( ConsoleSystemInterface si )
	{
		this.si = si;
	}

	public Player generatePlayer( )
	{
		si.cls( );
		( (CharDisplay) Display.thus ).printBars( );
		si.print( 2, 3, "Hero name:", ConsoleSystemInterface.WHITE );
		si.refresh( );
		si.locateCaret( 3 + "Hero name:".length( ), 3 );
		String name = si.input( 10 );
		si.print( 2, 4, "Sex: [m/f]", ConsoleSystemInterface.WHITE );
		si.refresh( );
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code != CharKey.M && x.code != CharKey.m && x.code != CharKey.F
				&& x.code != CharKey.f )
			x = si.inkey( );
		int sex = 0;
		if ( x.code == CharKey.M || x.code == CharKey.m )
			sex = Player.MALE;
		else
			sex = Player.FEMALE;

		CharAppearance[ ] apps = new CharAppearance[ CLASS_APPEARANCES.length ];
		for ( int i = 0; i < CLASS_APPEARANCES.length; i++ )
		{
			apps[ i ] = (CharAppearance) AppearanceFactory.getAppearanceFactory( )
					.getAppearance( CLASS_APPEARANCES[ i ] );
		}

		si.print( 2, 5, "Choose your destiny:" );
		si.print( 4, 6, "Vampire Killer" );
		si.print( 4, 7, "Darkness Renegade" );
		si.print( 4, 8, "Vanquisher Wizard" );
		si.print( 4, 9, "Souls' Master" );
		si.print( 4, 10, "Man-Beast" );
		si.print( 4, 11, "Knight" );

		si.print( 53, 6, "Attack      " );
		si.print( 53, 7, "Soul Power  " );
		si.print( 53, 8, "Resistance  " );
		si.print( 53, 9, "Evasion     " );
		si.print( 53, 10, "Movement    " );
		si.print( 53, 11, "Combat      " );
		si.print( 53, 12, "Invokation  " );
		si.print( 53, 13, "Strength    " );
		si.print( 53, 14, "Sight       " );
		si.print( 53, 15, "Wealth      " );

		si.refresh( );
		TextBox txtClassDescription = new TextBox( si );
		txtClassDescription.setBounds( 22, 10, 30, 7 );
		x = new CharKey( CharKey.NONE );
		int choice = 0;
		while ( true )
		{
			txtClassDescription.clear( );
			txtClassDescription.setText( CLASS_DESCRIPTIONS[ choice ] );
			txtClassDescription.draw( );
			si.print( 2, 6 + choice, "*", ConsoleSystemInterface.RED );
			si.print( 35, 7, apps[ choice ].getChar( ), apps[ choice ].getColor( ) );
			for ( int i = 0; i < 10; i++ )
			{
				si.print( 65, 6 + i, "              " );
			}

			si.print( 65, 6, "+" + CLASS_STATS[ choice ][ 0 ] );
			si.print( 65, 7, "+" + CLASS_STATS[ choice ][ 1 ] );
			si.print( 65, 8, CLASS_STATS[ choice ][ 2 ] );
			si.print( 65, 9, CLASS_STATS[ choice ][ 3 ] + "%" );
			si.print( 65, 10, CLASS_STATS[ choice ][ 4 ] );
			si.print( 65, 11, CLASS_STATS[ choice ][ 5 ] );
			si.print( 65, 12, CLASS_STATS[ choice ][ 6 ] );
			si.print( 65, 13, CLASS_STATS[ choice ][ 7 ] );
			si.print( 65, 14, CLASS_STATS[ choice ][ 8 ] );
			si.print( 65, 15, CLASS_STATS[ choice ][ 9 ] );
			si.refresh( );
			while ( x.code != CharKey.UARROW && x.code != CharKey.DARROW
					&& x.code != CharKey.SPACE && x.code != CharKey.ENTER )
				x = si.inkey( );
			if ( x.code == CharKey.UARROW )
			{
				if ( choice > 0 )
				{
					si.print( 2, 6 + choice, " " );
					choice--;
				}
			}
			else if ( x.code == CharKey.DARROW )
			{
				if ( choice < 5 )
				{
					si.print( 2, 6 + choice, " " );
					choice++;
				}
			}
			else
				break;

			x.code = CharKey.NONE;
		}

		return getPlayer( name, sex, choice );
	}
}
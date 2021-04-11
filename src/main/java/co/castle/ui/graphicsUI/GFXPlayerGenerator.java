package co.castle.ui.graphicsUI;

import java.awt.Color;

import javax.swing.JTextArea;

import co.castle.conf.gfx.data.Asset;
import co.castle.game.PlayerGenerator;
import co.castle.main.ApplicationGraphics;
import co.castle.player.Player;
import co.castle.ui.AppearanceFactory;
import sz.csi.CharKey;

public class GFXPlayerGenerator extends PlayerGenerator {

	private final ApplicationGraphics si;
	private final JTextArea txtClassDescription;

	// Get instance of Asset
	protected Asset configuration = ApplicationGraphics.assets;

	public GFXPlayerGenerator(ApplicationGraphics si) {
		this.si = si;
		txtClassDescription = new JTextArea();
		txtClassDescription.setOpaque(false);
		txtClassDescription.setForeground(Color.WHITE);
		txtClassDescription.setVisible( false );
		txtClassDescription.setBounds( 345, 162, 302, 84 );
		txtClassDescription.setLineWrap( true );
		txtClassDescription.setWrapStyleWord( true );
		txtClassDescription.setFocusable( false );
		txtClassDescription.setEditable( false );
		txtClassDescription.setFont( configuration.FONT_TEXT );

		si.addComponentToPanel( txtClassDescription );
	}

	public Player generatePlayer( )
	{
		si.drawImage( configuration.IMAGE_BACKGROUND );
		si.printAtPixel( 69, 86, "CHOOSE YOUR DESTINY", configuration.COLOR_BOLD );
		si.getGraphics2D( ).setColor( Color.DARK_GRAY );
		si.getGraphics2D( ).fillRect( 70, 94, 661, 3 );
		si.refresh( );
		si.printAtPixel( 69, 118, "Hero Name:", Color.WHITE );
		String name = si.input( 143, 118, configuration.COLOR_BOLD, 10 );
		si.printAtPixel( 69, 133, "Sex: [m/f]", Color.WHITE );
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
		si.printAtPixel( 138, 133, x.toString( ), configuration.COLOR_BOLD );

		GFXAppearance[ ] apps = new GFXAppearance[ CLASS_APPEARANCES.length ];
		for ( int i = 0; i < CLASS_APPEARANCES.length; i++ )
		{
			if ( sex == Player.MALE )
				apps[ i ] = (GFXAppearance) AppearanceFactory.getAppearanceFactory( )
						.getAppearance( CLASS_APPEARANCES[ i ] );
			else
				apps[ i ] = (GFXAppearance) AppearanceFactory.getAppearanceFactory( )
						.getAppearance( CLASS_APPEARANCES[ i ] + "_W" );
		}

		si.printAtPixel( 80, 173, CLASS_NAMES[ 0 ], Color.WHITE );
		si.printAtPixel( 80, 191, CLASS_NAMES[ 1 ], Color.WHITE );
		si.printAtPixel( 80, 209, CLASS_NAMES[ 2 ], Color.WHITE );
		si.printAtPixel( 80, 227, CLASS_NAMES[ 3 ], Color.WHITE );
		si.printAtPixel( 80, 245, CLASS_NAMES[ 4 ], Color.WHITE );
		si.printAtPixel( 80, 263, CLASS_NAMES[ 5 ], Color.WHITE );

		si.printAtPixel( 350, 260, "Attack      ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 280, "Soul Power  ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 300, "Resistance  ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 320, "Evasion     ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 340, "Movement    ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 360, "Combat      ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 380, "Invokation  ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 400, "Strength    ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 420, "Sight       ", configuration.COLOR_BOLD );
		si.printAtPixel( 350, 440, "Wealth      ", configuration.COLOR_BOLD );
		txtClassDescription.setVisible( true );
		x = new CharKey( CharKey.NONE );
		int choice = 0;
		si.saveBuffer( );
		while ( true ) {
			si.restore();
			txtClassDescription.setText(CLASS_DESCRIPTIONS[choice]);
			si.drawImage(70, 158 + 18 * choice, "gfx/barrett-picker.gif");
			si.printAtPixel(80, 173 + 18 * choice, CLASS_NAMES[choice], Color.WHITE);
			si.printAtPixel(398, 142, CLASS_NAMES[choice], Color.WHITE);
			si.drawImage(353, 113, apps[choice].getImage());

			si.printAtPixel(440, 260, "+" + CLASS_STATS[choice][0], Color.WHITE);
			si.printAtPixel(440, 280, "+" + CLASS_STATS[choice][1], Color.WHITE);
			si.printAtPixel(440, 300, CLASS_STATS[choice][2], Color.WHITE);
			si.printAtPixel(440, 320, CLASS_STATS[choice][3] + "%", Color.WHITE);
			si.printAtPixel(440, 340, CLASS_STATS[choice][4], Color.WHITE);
			si.printAtPixel(440, 360, CLASS_STATS[choice][5], Color.WHITE);
			si.printAtPixel( 440, 380, CLASS_STATS[ choice ][ 6 ], Color.WHITE );
			si.printAtPixel( 440, 400, CLASS_STATS[ choice ][ 7 ], Color.WHITE );
			si.printAtPixel( 440, 420, CLASS_STATS[ choice ][ 8 ], Color.WHITE );
			si.printAtPixel( 440, 440, CLASS_STATS[ choice ][ 9 ], Color.WHITE );
			si.refresh( );
			while ( x.code != CharKey.UARROW && x.code != CharKey.DARROW
					&& x.code != CharKey.SPACE && x.code != CharKey.ENTER )
				x = si.inkey( );
			if ( x.code == CharKey.UARROW )
			{
				if ( choice > 0 )
				{
					choice--;
				}
			}
			else if ( x.code == CharKey.DARROW )
			{
				if ( choice < 5 )
				{
					choice++;
				}
			}
			else
				break;

			x.code = CharKey.NONE;
		}
		// si.remove(txtClassDescription);
		txtClassDescription.setVisible( false );
		return getPlayer( name, sex, choice );
	}
}
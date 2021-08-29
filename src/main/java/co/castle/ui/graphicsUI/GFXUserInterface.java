
package co.castle.ui.graphicsUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import co.castle.action.Action;
import co.castle.action.Drop;
import co.castle.action.Equip;
import co.castle.action.Throw;
import co.castle.action.Unequip;
import co.castle.action.Use;
import co.castle.actor.Actor;
import co.castle.actor.Message;
import co.castle.conf.UserActions;
import co.castle.conf.UserCommands;
import co.castle.conf.gfx.data.Asset;
import co.castle.feature.Feature;
import co.castle.feature.SmartFeature;
import co.castle.game.Game;
import co.castle.game.GameFiles;
import co.castle.item.Item;
import co.castle.item.ItemDefinition;
import co.castle.item.Merchant;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.main.ApplicationGraphics;
import co.castle.main.Service;
import co.castle.monster.Monster;
import co.castle.monster.VMonster;
import co.castle.npc.Hostage;
import co.castle.npc.NPC;
import co.castle.player.Consts;
import co.castle.player.Equipment;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.Skill;
import co.castle.player.advancements.Advancement;
import co.castle.system.FileLoader;
import co.castle.ui.ActionCancelException;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.CommandListener;
import co.castle.ui.Display;
import co.castle.ui.UserCommand;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.Effect;
import co.castle.ui.graphicsUI.components.GFXButton;
import co.castle.ui.graphicsUI.effects.GFXEffect;
import sz.csi.CharKey;
import sz.gadgets.AdditionalKeysSignal;
import sz.gadgets.BorderedMenuBox;
import sz.gadgets.MenuBox;
import sz.gadgets.SimpleGFXMenuItem;
import sz.util.Debug;
import sz.util.ImageUtils;
import sz.util.Line;
import sz.util.Position;
import sz.util.Util;

/**
 * Shows the level using characters. Informs the Actions and Commands of the
 * player. Must be listening to a System Interface
 */
public class GFXUserInterface extends UserInterface implements Runnable
{
	class HelpBox extends AddornedBorderPanel
	{
		private GFXButton btnOk;

		public HelpBox(	Image UPRIGHT, Image UPLEFT, Image DOWNRIGHT, Image DOWNLEFT,
						Color OUT_COLOR, Color IN_COLOR, int borderWidth,
						int borderHeight )
		{
			super( UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT, OUT_COLOR, IN_COLOR, borderWidth,
					borderHeight );
			setOpaque( false );
			setBorder( new EmptyBorder( STANDARD_WIDTH, STANDARD_WIDTH, STANDARD_WIDTH,
					STANDARD_WIDTH ) );

			btnOk = new GFXButton( IMG_OK_BTN );
			setLayout( new BorderLayout( ) );
			add( btnOk, BorderLayout.SOUTH );
			btnOk.addActionListener( new ActionListener( )
			{
				public void actionPerformed( ActionEvent arg0 )
				{
					doOk( );
				}
			} );

			addKeyListener( new KeyAdapter( )
			{
				public void keyPressed( KeyEvent e )
				{
					if ( e.getKeyCode( ) == KeyEvent.VK_SPACE
							|| e.getKeyCode( ) == KeyEvent.VK_ESCAPE )
					{
						setVisible( false );
						si.recoverFocus( );
					}
				}
			} );
		}

		public void paintComponent( Graphics g )
		{
			super.paintComponent( g );
			g.setFont( configuration.FONT_TITLE );
			print( g, 3, 2, "Help", configuration.COLOR_BOLD );
			g.setFont( configuration.FONT_TEXT );

			print( g, 3, 3,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "WEAPON_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 4,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "ATTACK1_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );
			print( g, 3, 5,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "DROP_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 6,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "EQUIP_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 7,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "TARGET_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 8,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "GET_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 9,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "JUMP_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 10,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "DIVE_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 11,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "RELOAD_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 12,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "SHOW_SKILLS_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );
			print( g, 3, 13,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "THROW_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 14,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "USE_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 3, 15,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "UNEQUIP_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );
			print( g, 3, 16,
					"(" + CharKey.getString( Display.getKeyBindings( )
							.getProperty( "SWITCH_WEAPONS_KEY" ) ) + ")",
					configuration.COLOR_BOLD );

			print( g, 6, 3, "Action: Uses a mystic weapon or aims weapon", Color.WHITE );
			print( g, 6, 4, "Attack: Uses a weapon in a given direction", Color.WHITE );
			print( g, 6, 5, "Drop: Drops an item", Color.WHITE );
			print( g, 6, 6, "Equip: Wears a weapon, armor or accesory", Color.WHITE );
			print( g, 6, 7, "Fire: Aims a weapon at a position", Color.WHITE );
			print( g, 6, 8, "Get: Picks up an item", Color.WHITE );
			print( g, 6, 9, "Jump: Jumps in a direction", Color.WHITE );
			print( g, 6, 10, "Plunge: Dive into the water", Color.WHITE );
			print( g, 6, 11, "Reload: Reloads a given weapon", Color.WHITE );
			print( g, 6, 12, "Skills: Allows to use your character skills", Color.WHITE );
			print( g, 6, 13, "Throw: Throws an Item", Color.WHITE );
			print( g, 6, 14, "Use: Uses an Item", Color.WHITE );
			print( g, 6, 15, "Unequip: Take off an item", Color.WHITE );
			print( g, 6, 16, "Switch weapons: Exchange primary for secondary weapon",
					Color.WHITE );

			print( g, 41, 3,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "SHOW_STATS_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );
			print( g, 41, 4,
					"(" + CharKey.getString( Display.getKeyBindings( )
							.getProperty( "SHOW_INVENTORY_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 41, 5,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "LOOK_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 41, 6,
					"(" + CharKey.getString( Display.getKeyBindings( )
							.getProperty( "SHOW_MESSAGE_HISTORY_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 41, 7,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "SHOW_MAP_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );
			print( g, 41, 8,
					"(" + CharKey.getString( Display.getKeyBindings( )
							.getProperty( "EXAMINE_LEVEL_MAP_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 41, 9,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "QUIT_KEY" ) ) + ")",
					configuration.COLOR_BOLD );
			print( g, 41, 10,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "PROMPT_SAVE_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );
			print( g, 41, 11,
					"(" + CharKey.getString(
							Display.getKeyBindings( ).getProperty( "SWITCH_MUSIC_KEY" ) )
							+ ")",
					configuration.COLOR_BOLD );

			print( g, 44, 3, "Character info: Shows your skills and attributes",
					Color.WHITE );
			print( g, 44, 4, "Inventory: Shows the inventory", Color.WHITE );
			print( g, 44, 5, "Look: Identifies map symbols and monsters", Color.WHITE );
			print( g, 44, 6, "Messages: Shows the latest messages", Color.WHITE );
			print( g, 44, 7, "Castle Map: Shows the castle map", Color.WHITE );
			print( g, 44, 8, "Area Map: Show the current area map", Color.WHITE );
			print( g, 44, 9, "Quit: Exits game", Color.WHITE );
			print( g, 44, 10, "Save: Saves game", Color.WHITE );
			print( g, 44, 11, "Switch Music: Turns music on/off", Color.WHITE );
		}

		public void setVisible( boolean val )
		{
			super.setVisible( val );
			if ( val )
			{
				requestFocus( );
			}
		}

		private void doOk( )
		{
			setVisible( false );
			messageBox.setVisible( true );
		}

		private void print( Graphics g, int x, int y, String text, Color color )
		{
			g.setColor( color );
			g.drawString( text, x * 10, y * 24 );
		}
	}

	class MerchantBox extends AddornedBorderPanel
	{
		class MerchandiseCellRenderer extends DefaultListCellRenderer
		{
			private JLabel ren;

			public MerchandiseCellRenderer( )
			{
				ren = new JLabel( );
				ren.setFont( configuration.FONT_MESSAGE_BOX );
				ren.setOpaque( false );
				ren.setForeground( Color.WHITE );
				ren.setBackground( configuration.COLOR_BOLD );

			}

			public Component getListCellRendererComponent(	JList list, Object value,
															int index, boolean isSelected,
															boolean cellHasFocus )
			{
				Item smi = (Item) value;
				ren.setIcon( new ImageIcon(
						( (GFXAppearance) smi.getAppearance( ) ).getImage( ) ) );
				// ren.setText(smi.getMenuDescription());
				ren.setText( smi.getAttributesDescription( ) + " ["
						+ smi.getDefinition( ).getMenuDescription( ) + "] ($"
						+ smi.getGoldPrice( ) + ")" );
				ren.setOpaque( isSelected );
				return ren;
			}

		}
		private Thread activeThread;
		private GFXButton btnBuy;
		private GFXButton btnExit;
		private GFXButton btnNo;
		private GFXButton btnYes;
		// private ShopMenuItem choice;
		private Item choice;

		private JLabel lblGold;
		private JList lstMerchandise;

		private JTextArea prompt;

		public MerchantBox(	Image UPRIGHT, Image UPLEFT, Image DOWNRIGHT, Image DOWNLEFT,
							Color OUT_COLOR, Color IN_COLOR, int borderWidth,
							int borderHeight )
		{
			super( UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT, OUT_COLOR, IN_COLOR, borderWidth,
					borderHeight );

			lstMerchandise = new JList( new DefaultListModel( ) );
			btnBuy = new GFXButton( IMG_BUY_BTN );
			btnExit = new GFXButton( IMG_EXIT_BTN );
			btnYes = new GFXButton( IMG_YES_BTN );
			btnNo = new GFXButton( IMG_NO_BTN );

			lblGold = new JLabel( );
			lblGold.setOpaque( false );
			lblGold.setForeground( Color.YELLOW );
			btnYes.setVisible( false );
			btnNo.setVisible( false );

			lstMerchandise.setCellRenderer( new MerchandiseCellRenderer( ) );
			lstMerchandise.setOpaque( false );

			addKeyListener( new KeyListener( )
			{

				public void keyPressed( KeyEvent e )
				{
					if ( e.getKeyCode( ) == KeyEvent.VK_ENTER
							|| e.getKeyCode( ) == KeyEvent.VK_SPACE
							|| e.getKeyCode( ) == KeyEvent.VK_Y )
					{
						if ( btnYes.isVisible( ) )
							doYes( );
						else
							doBuy( );
					}
					if ( e.getKeyCode( ) == KeyEvent.VK_ESCAPE
							|| e.getKeyCode( ) == KeyEvent.VK_N )
					{
						if ( btnYes.isVisible( ) )
							doNo( );
						else
							doExit( );
					}
				}

				public void keyReleased( KeyEvent e )
				{
				}

				public void keyTyped( KeyEvent e )
				{
				}

			} );

			lstMerchandise.addKeyListener( new KeyListener( )
			{

				public void keyPressed( KeyEvent e )
				{
					if ( e.getKeyCode( ) == KeyEvent.VK_ENTER
							|| e.getKeyCode( ) == KeyEvent.VK_SPACE )
					{
						if ( btnYes.isVisible( ) )
							doYes( );
						else
							doBuy( );
					}
					else if ( e.getKeyCode( ) == KeyEvent.VK_ESCAPE )
					{
						if ( btnYes.isVisible( ) )
							doNo( );
						else
							doExit( );
					}
					else if ( e.getKeyCode( ) == KeyEvent.VK_NUMPAD8 )
					{
						if ( lstMerchandise.getSelectedIndex( ) > 0 )
							lstMerchandise.setSelectedIndex(
									lstMerchandise.getSelectedIndex( ) - 1 );
					}
					else if ( e.getKeyCode( ) == KeyEvent.VK_NUMPAD2 )
					{
						if ( lstMerchandise.getSelectedIndex( ) < lstMerchandise
								.getModel( ).getSize( ) - 1 )
							lstMerchandise.setSelectedIndex(
									lstMerchandise.getSelectedIndex( ) + 1 );
					}
				}

				public void keyReleased( KeyEvent e )
				{
				}

				public void keyTyped( KeyEvent e )
				{
				}

			} );

			setOpaque( false );
			setBorder( new EmptyBorder( STANDARD_WIDTH, STANDARD_WIDTH, STANDARD_WIDTH,
					STANDARD_WIDTH ) );

			setLayout( new BorderLayout( ) );
			( (BorderLayout) getLayout( ) ).setHgap( 16 );
			( (BorderLayout) getLayout( ) ).setVgap( 16 );
			/*
			 * JLabel title = new JLabel("Skills"); title.setFont(UI_FONT);
			 * title.setForeground(GFXDisplay.GOLD);
			 */

			prompt = new JTextArea( );
			prompt.setFont( configuration.FONT_MESSAGE_BOX );
			prompt.setOpaque( false );
			prompt.setForeground( Color.WHITE );
			prompt.setLineWrap( true );
			prompt.setWrapStyleWord( true );
			prompt.setEditable( false );
			prompt.setFocusable( false );
			// prompt.setVisible(false);

			JPanel pnlButtons = new JPanel( );
			pnlButtons.add( lblGold );
			pnlButtons.add( btnBuy );
			pnlButtons.add( btnExit );
			pnlButtons.setOpaque( false );

			JPanel pnlSuperior = new JPanel( new BorderLayout( ) );
			pnlSuperior.add( prompt, BorderLayout.CENTER );
			JPanel miniPanelBotones = new JPanel( );
			miniPanelBotones.setOpaque( false );
			miniPanelBotones.add( btnYes );
			miniPanelBotones.add( btnNo );
			pnlSuperior.add( miniPanelBotones, BorderLayout.SOUTH );
			pnlSuperior.setOpaque( false );

			add( pnlSuperior, BorderLayout.NORTH );
			add( lstMerchandise, BorderLayout.CENTER );
			add( pnlButtons, BorderLayout.SOUTH );

			setBackground( Color.BLACK );

			btnYes.addActionListener( new ActionListener( )
			{
				public void actionPerformed( ActionEvent arg0 )
				{
					doYes( );
				}
			} );

			btnNo.addActionListener( new ActionListener( )
			{
				public void actionPerformed( ActionEvent arg0 )
				{
					doNo( );
				}
			} );

			btnBuy.addActionListener( new ActionListener( )
			{
				public void actionPerformed( ActionEvent arg0 )
				{
					doBuy( );
				}
			} );

			btnExit.addActionListener( new ActionListener( )
			{
				public void actionPerformed( ActionEvent arg0 )
				{
					doExit( );
				}
			} );

		}

		public Item getSelection( )
		{
			return choice;
		}

		public void informChoice( Thread who )
		{
			choice = null;
			activeThread = who;
		}

		public void setGold( int gold )
		{
			lblGold.setText( "Player gold: " + gold );
		}

		public void setMerchandise( Vector skills )
		{
			( (DefaultListModel) lstMerchandise.getModel( ) ).removeAllElements( );
			for ( int i = 0; i < skills.size( ); i++ )
			{
				( (DefaultListModel) lstMerchandise.getModel( ) )
						.addElement( skills.elementAt( i ) );
			}
		}

		public void setPrompt( String prompt )
		{
			this.prompt.setText( prompt );
		}

		public void setVisible( boolean val )
		{
			super.setVisible( val );
			if ( val )
			{
				lstMerchandise.requestFocus( );
				if ( lstMerchandise.getModel( ).getSize( ) > 0 )
					lstMerchandise.setSelectedIndex( 0 );
			}
		}

		private void doBuy( )
		{
			if ( activeThread != null )
			{
				// choice = (ShopMenuItem) lstMerchandise.getSelectedValue();
				choice = (Item) lstMerchandise.getSelectedValue( );
				setPrompt( "The " + choice.getDescription( ) + ", "
						+ choice.getShopDescription( ) + "; it costs "
						+ choice.getGoldPrice( ) + ", Do you want to buy it? (Y/n)" );
				btnBuy.setEnabled( false );
				btnExit.setEnabled( false );
				lstMerchandise.setEnabled( false );
				btnYes.setVisible( true );
				btnNo.setVisible( true );
				requestFocus( );
			}
		}

		private void doExit( )
		{
			if ( activeThread != null )
			{
				choice = null;
				activeThread.interrupt( );
			}

		}

		private void doNo( )
		{
			setPrompt( "Too bad... May I interest you in something else?" );
			btnBuy.setEnabled( true );
			btnExit.setEnabled( true );
			lstMerchandise.setEnabled( true );
			btnYes.setVisible( false );
			btnNo.setVisible( false );
			lstMerchandise.requestFocus( );
		}

		private void doYes( )
		{
			activeThread.interrupt( );
			btnBuy.setEnabled( true );
			btnExit.setEnabled( true );
			lstMerchandise.setEnabled( true );
			btnYes.setVisible( false );
			btnNo.setVisible( false );
			lstMerchandise.requestFocus( );
		}
	}
	class MultiItemsBox extends AddornedBorderPanel
	{
		class ItemsCellRenderer extends DefaultListCellRenderer
		{
			private JLabel ren;

			public ItemsCellRenderer( )
			{
				ren = new JLabel( );
				ren.setFont( configuration.FONT_MESSAGE_BOX );
				ren.setOpaque( false );
				ren.setForeground( Color.WHITE );
				ren.setBackground( configuration.COLOR_BOLD );

			}

			public Component getListCellRendererComponent(	JList list, Object value,
															int index, boolean isSelected,
															boolean cellHasFocus )
			{
				if ( value instanceof Equipment )
				{
					Equipment smi = (Equipment) value;
					ren.setText( smi.getMenuDescription( ) );
					ren.setIcon( new ImageIcon(
							( (GFXAppearance) smi.getItem( ).getAppearance( ) )
									.getImage( ) ) );
				}
				else
				{
					Item smi = (Item) value;
					ren.setText( smi.getMenuDescription( ) );
					ren.setIcon( new ImageIcon(
							( (GFXAppearance) smi.getAppearance( ) ).getImage( ) ) );
				}

				ren.setOpaque( isSelected );
				return ren;
			}
		}
		private Thread activeThread;
		private GFXButton btnExit;
		private GFXButton btnOk;

		private Vector choice;

		private Vector inventory;

		private JLabel lblPrompt;

		private JList lstInventory;

		public MultiItemsBox(	Image UPRIGHT, Image UPLEFT, Image DOWNRIGHT,
								Image DOWNLEFT, Color OUT_COLOR, Color IN_COLOR,
								int borderWidth, int borderHeight )
		{
			super( UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT, OUT_COLOR, IN_COLOR, borderWidth,
					borderHeight );

			lstInventory = new JList( new DefaultListModel( ) );
			lstInventory
					.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
			btnExit = new GFXButton( IMG_EXIT_BTN );
			btnOk = new GFXButton( IMG_OK_BTN );

			lstInventory.setOpaque( false );
			lstInventory.setCellRenderer( new ItemsCellRenderer( ) );

			setOpaque( false );
			setBorder( new EmptyBorder( STANDARD_WIDTH, STANDARD_WIDTH, STANDARD_WIDTH,
					STANDARD_WIDTH ) );

			setLayout( new BorderLayout( ) );

			lblPrompt = new JLabel( "Inventory" );
			lblPrompt.setFont( configuration.FONT_MESSAGE_BOX );
			lblPrompt.setForeground( configuration.COLOR_BOLD );

			JPanel pnlButtons = new JPanel( );
			pnlButtons.add( btnExit );
			pnlButtons.add( btnOk );
			pnlButtons.setOpaque( false );

			add( lblPrompt, BorderLayout.NORTH );
			add( lstInventory, BorderLayout.CENTER );
			add( pnlButtons, BorderLayout.SOUTH );

			setBackground( Color.BLACK );

			btnExit.addActionListener( new ActionListener( )
			{
				public void actionPerformed( ActionEvent arg0 )
				{
					doExit( );
				}
			} );
			btnOk.addActionListener( new ActionListener( )
			{

				public void actionPerformed( ActionEvent arg0 )
				{
					doOk( );
				}
			} );
			lstInventory.addKeyListener( new KeyListener( )
			{

				public void keyPressed( KeyEvent e )
				{
					if ( e.getKeyCode( ) == KeyEvent.VK_ENTER
							|| e.getKeyCode( ) == KeyEvent.VK_SPACE )
					{
						doOk( );
					}
					else if ( e.getKeyCode( ) == KeyEvent.VK_ESCAPE )
					{
						doExit( );
					}
					else if ( e.getKeyCode( ) == KeyEvent.VK_NUMPAD8 )
					{
						if ( lstInventory.getSelectedIndex( ) > 0 )
							lstInventory.setSelectedIndex(
									lstInventory.getSelectedIndex( ) - 1 );
					}
					else if ( e.getKeyCode( ) == KeyEvent.VK_NUMPAD2 )
					{
						if ( lstInventory
								.getSelectedIndex( ) < lstInventory.getModel( ).getSize( )
										- 1 )
							lstInventory.setSelectedIndex(
									lstInventory.getSelectedIndex( ) + 1 );
					}
				}

				public void keyReleased( KeyEvent e )
				{
				}

				public void keyTyped( KeyEvent e )
				{
				}

			} );

		}

		public Vector getChoice( )
		{
			return choice;
		}
		public void informChoice( Thread who )
		{
			activeThread = who;
		}

		public void setItems( Vector items )
		{
			inventory = (Vector) items.clone( );
			( (DefaultListModel) lstInventory.getModel( ) ).removeAllElements( );
			for ( int i = 0; i < items.size( ); i++ )
			{
				( (DefaultListModel) lstInventory.getModel( ) )
						.addElement( items.elementAt( i ) );
			}
		}

		public void setPrompt( String prompt )
		{
			lblPrompt.setText( prompt );
		}

		public void setVisible( boolean val )
		{
			super.setVisible( val );
			if ( val )
			{
				lstInventory.requestFocus( );
				if ( lstInventory.getModel( ).getSize( ) > 0 )
					lstInventory.setSelectedIndex( 0 );
			}
		}

		private void doExit( )
		{
			if ( activeThread != null )
			{
				choice = null;
				activeThread.interrupt( );
			}
		}

		private void doOk( )
		{
			if ( activeThread != null )
			{
				choice = new Vector( );
				int[ ] indices = lstInventory.getSelectedIndices( );
				for ( int i = 0; i < indices.length; i++ )
				{
					choice.add( ( (Equipment) inventory.elementAt( indices[ i ] ) )
							.getItem( ) );
				}
				activeThread.interrupt( );
			}
		}
	}

	// Components
	public SwingInformBox messageBox;

	public AddornedBorderTextArea persistantMessageBox;
	public Position VP_START = new Position( 0, 0 ), VP_END = new Position( 5, 5 ),
			PC_POS = new Position( 3, 3 );
	private int[ ] additionalKeys = new int[ ]
	{ CharKey.N1, CharKey.N2, CharKey.N3, CharKey.N4, };

	private Color COLOR_LAST_MESSAGE = Color.WHITE, COLOR_OLD_MESSAGE = Color.GRAY;
	private int dimMsg = 0;
	private boolean eraseOnArrival; // Erase the buffer upon the arrival of a new msg

	private boolean flipFacing;
	private Font FNT_MESSAGEBOX;
	// Relations

	private boolean[ ][ ] FOVMask;

	private BufferedImage HEALTH_RED, HEALTH_DARK_RED, HEALTH_MAGENTA, HEALTH_WHITE,
			HEALTH_YELLOW, HEALTH_BROWN, HEALTH_PURPLE,

			HEART_TILE, GOLD_TILE, KEY_TILE,

			TILE_MORNING_TIME, TILE_NOON_TIME, TILE_AFTERNOON_TIME, TILE_DUSK_TIME,
			TILE_NIGHT_TIME, TILE_DAWN_TIME,

			TILE_NO_SHOT, TILE_SHOT_II, TILE_SHOT_III,

			TILE_LINE_STEPS, TILE_LINE_AIM, TILE_SCAN,

			TILE_WEAPON_BACK, TILE_HEALTH_BACK, BORDER1, BORDER2, BORDER3, BORDER4,

			IMG_AXE, IMG_BIBLE, IMG_CROSS, IMG_DAGGER, IMG_HOLY, IMG_CRYSTAL, IMG_FIST,
			IMG_STOPWATCH,

			BLOOD1, BLOOD2, IMG_EXIT_BTN, IMG_OK_BTN, IMG_BUY_BTN, IMG_YES_BTN,
			IMG_NO_BTN,

			IMG_ICON;

	private HelpBox helpBox;
	private int[ ] itemUsageKeys = new int[ ]
	{ CharKey.u, CharKey.e, CharKey.d, CharKey.t, };
	private Monster lockedMonster;
	private Color MAP_NOSOLID = new Color( 86, 77, 65, 150 );

	private Color MAP_NOSOLID_LOS = new Color( 98, 96, 85, 150 );

	private Color MAP_SOLID = new Color( 83, 83, 83 );

	private MerchantBox merchantBox;

	private Vector <String> messageHistory = new Vector <String>( 10 );

	private MultiItemsBox multiItemsBox;

	private transient ApplicationGraphics si;

	private int STANDARD_WIDTH;

	private Action target;
	private Color TRANSPARENT_GRAY = new Color( 20, 20, 20, 180 );

	private Vector <SimpleGFXMenuItem> vecItemUsageChoices = new Vector <SimpleGFXMenuItem>( );
	// Attributes
	private int xrange;

	private int yrange;

	// Get instance of Asset
	protected Asset configuration = ApplicationGraphics.assets;

	private static final String INTERFACE_FILE = "gfx/barrett-interface.gif";

	private final static Color WATERCOLOR_BLOCKED = new Color( 0, 50, 100, 200 ),
			WATERCOLOR = new Color( 0, 70, 120, 200 ),
			RAINCOLOR = new Color( 180, 200, 250, 100 ),
			THUNDERCOLOR = new Color( 180, 200, 200, 150 ),
			FOGCOLOR = new Color( 200, 200, 200, 200 );

	{
		vecItemUsageChoices.add( new SimpleGFXMenuItem( "[u]se", 1 ) );
		vecItemUsageChoices.add( new SimpleGFXMenuItem( "[e]quip", 2 ) );
		vecItemUsageChoices.add( new SimpleGFXMenuItem( "[t]hrow", 4 ) );
		vecItemUsageChoices.add( new SimpleGFXMenuItem( "[d]rop", 3 ) );
		vecItemUsageChoices.add( new SimpleGFXMenuItem( "[ ] Cancel", 5 ) );

	}

	public GFXUserInterface( )
	{
	}

	public void addMessage( Message message )
	{
		Debug.enterMethod( this, "addMessage", message );
		if ( eraseOnArrival )
		{
			messageBox.clear( );
			messageBox.setForeground( COLOR_LAST_MESSAGE );
			eraseOnArrival = false;
		}
		if ( message.getLocation( ).z != player.getPosition( ).z
				|| !insideViewPort( getAbsolutePosition( message.getLocation( ) ) ) )
		{
			Debug.exitMethod( );
			return;
		}
		messageHistory.add( message.getText( ) );
		if ( messageHistory.size( ) > 500 )
			messageHistory.removeElementAt( 0 );
		messageBox.addText( message.getText( ) );
		dimMsg = 0;
		Debug.exitMethod( );
	}

	public void chat( NPC who )
	{
		Debug.enterMethod( this, "chat", who );
		si.saveBuffer( );
		( (GraphicsDisplay) Display.thus ).showTextBox(
				who.getDescription( ) + " says: \n   \"" + who.getTalkMessage( ) + "\"",
				280, 30, 330, 170 );
		si.refresh( );
		// waitKey();
		si.restore( );
		Debug.exitMethod( );
	}

	public void commandSelected( int commandCode )
	{
		switch ( commandCode )
		{
		case CommandListener.PROMPTQUIT:
			processQuit( );
			break;
		case CommandListener.PROMPTSAVE:
			processSave( );
			break;
		case CommandListener.HELP:
			si.saveBuffer( );
			messageBox.setVisible( false );
			helpBox.setVisible( true );
			si.restore( );
			break;
		case CommandListener.LOOK:
			doLook( );
			break;
		case CommandListener.SHOWSTATS:
			showPlayerStats( );
			break;
		case CommandListener.SHOWINVEN:
			try
			{
				actionSelectedByCommand = showInventory( );
			}
			catch ( ActionCancelException ace )
			{
				addMessage( new Message( "- Cancelled", player.getPosition( ) ) );
				eraseOnArrival = true;
				si.refresh( );
				actionSelectedByCommand = null;
			}
			break;
		case CommandListener.SHOWSKILLS:
			try
			{
				if ( !player.isSwimming( ) )
				{
					actionSelectedByCommand = showSkills( );
				}
				else
				{
					player.getLevel( ).addMessage( "You can't do that!" );
					throw new ActionCancelException( );
				}
			}
			catch ( ActionCancelException ace )
			{
				addMessage( new Message( "- Cancelled", player.getPosition( ) ) );
				eraseOnArrival = true;
				si.refresh( );
				actionSelectedByCommand = null;
			}
			break;
			case CommandListener.SHOWMESSAGEHISTORY:
				showMessageHistory();
				break;
			case CommandListener.SHOWMAP:
				Display.thus.showMap(level.getMapLocationKey(), level.getDescription());
				break;
			case CommandListener.SWITCHMUSIC:
				boolean enabled = Service.isEnabled();
				if (enabled) {
					showMessage("Turn off music");
					Service.stopMusic();
					Service.setEnabledMusicManager(false);
				} else {
					showMessage("Turn on music");
					Service.setEnabledMusicManager(true);
					if (!level.isDay() && level.hasNoonMusic())
						Service.playKey(level.getMusicKeyNoon());
					else
						Service.playKey(level.getMusicKeyMorning());
				}
			break;
		case CommandListener.EXAMINELEVELMAP:
			examineLevelMap( );
			break;
		case CommandListener.CHARDUMP:
			GameFiles.saveChardump( player );
			showMessage( "Character File Dumped." );
			break;
		}
	}

	// Interactive Methods
	public void doLook( )
	{
		Position offset = new Position( 0, 0 );

		messageBox.setForeground( COLOR_LAST_MESSAGE );
		si.saveBuffer( );
		Monster lookedMonster = null;
		while ( true )
		{
			int cellHeight = 0;
			Position browser = Position.add( player.getPosition( ), offset );
			String looked = "";
			si.restore( );
			if ( FOVMask[ PC_POS.x + offset.x ][ PC_POS.y + offset.y ] )
			{
				Cell choosen = level.getMapCell( browser );
				if ( choosen != null )
					cellHeight = choosen.getHeight( );
				Feature feat = level.getFeatureAt( browser );
				Vector <Item> items = level.getItemsAt( browser );
				Item item = null;
				if ( items != null )
				{
					item = (Item) items.elementAt( 0 );
				}
				lookedMonster = null;
				Actor actor = level.getActorAt( browser );
				if ( choosen != null )
					looked += choosen.getDescription( );
				if ( level.getBloodAt( browser ) != null )
					looked += "{bloody}";
				if ( feat != null )
					looked += ", " + feat.getDescription( );
				if ( item != null )
					if ( items.size( ) == 1 )
						looked += ", " + item.getDescription( );
					else
						looked += ", " + item.getDescription( ) + " and some items";
				if ( actor != null )
				{
					if ( actor instanceof Monster )
					{
						looked += ", " + actor.getDescription( )
								+ " ['m' for extended info]";
						lookedMonster = (Monster) actor;
					}
					else
					{
						looked += ", " + actor.getDescription( );
					}
				}
			}
			messageBox.setText( looked );
			si.drawImage( ( PC_POS.x + offset.x ) * STANDARD_WIDTH - 2,
					( ( PC_POS.y + offset.y ) * STANDARD_WIDTH - 2 ) - 4 * cellHeight,
					TILE_SCAN );
			si.refresh( );
			CharKey x = new CharKey( CharKey.NONE );
			while ( x.code != CharKey.SPACE && x.code != CharKey.m
					&& x.code != CharKey.ESC && !x.isArrow( ) )
				x = si.inkey( );
			if ( x.code == CharKey.SPACE || x.code == CharKey.ESC )
			{
				si.restore( );
				break;
			}
			if ( x.code == CharKey.m )
			{
				if ( lookedMonster != null )
					Display.thus.showMonsterScreen( lookedMonster, player );
			}
			else
			{
				offset.add( Action
						.directionToVariation( GFXUISelector.toIntDirection( x ) ) );

				if ( offset.x >= xrange )
					offset.x = xrange;
				if ( offset.x <= -xrange )
					offset.x = -xrange;
				if ( offset.y >= yrange )
					offset.y = yrange;
				if ( offset.y <= -yrange )
					offset.y = -yrange;
			}
		}
		messageBox.setText( "Look mode off" );
		si.restore( );
		si.refresh( );

	}

	// Drawing Methods
	public void drawEffect( Effect what )
	{
		if ( what == null )
			return;
		if ( insideViewPort( getAbsolutePosition( what.getPosition( ) ) ) )
		{
			( (GFXEffect) what ).drawEffect( this, si );
		}
	}

	// Setters
	/**
	 * Sets the object which will be informed of the player commands. this
	 * corresponds to the Game object
	 */

	// Getters

	// Smart Getters
	public Position getAbsolutePosition( Position insideLevel )
	{
		Position relative = Position.subs( insideLevel, player.getPosition( ) );
		return Position.add( PC_POS, relative );
	}

	/*
	 * private void drawCursor(){ /*if (isCursorEnabled){ si.restore(); Cell
	 * underlying = player.getLevel().getMapCell(tempCursorPosition);
	 * si.drawImage((PC_POS.x+tempCursorPositionScr.x)*32,(PC_POS.y+
	 * tempCursorPositionScr.y)*32-4*underlying.getHeight(), TILE_SCAN);
	 * si.refresh(); } }
	 */

	public Vector getMessageBuffer() {
		// return new Vector(messageHistory.subList(0,21));
		if (messageHistory.size() > 20)
			return new Vector(messageHistory.subList(messageHistory.size() - 21,
					messageHistory.size()));
		else
			return messageHistory;
	}

	public void init(ApplicationGraphics psi, UserCommands gameCommands, UserActions userActions) {
		Debug.enterMethod(this, "init");
		super.init(gameCommands);
		this.target = userActions.getTargetAction();
		initProperties();
		// GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setDisplayMode(new
		// DisplayMode(800,600,8, DisplayMode.REFRESH_RATE_UNKNOWN));

		/*-- Assign values */
		si = psi;
		FOVMask = new boolean[80][25];
		si.getGraphics2D().setColor(Color.BLACK);
		si.getGraphics2D().fillRect(0, 0, 800, 600);
		si.refresh();

		/*-- Load Fonts */
		try
		{
			FNT_MESSAGEBOX = Font
					.createFont(Font.TRUETYPE_FONT,
							FileLoader.getInputStream("res/v5easter.ttf"))
					.deriveFont( Font.PLAIN, 15 );
		}
		catch ( FontFormatException ffe )
		{
			Game.crash( "Error loading the font", ffe );
		}
		catch ( IOException ioe )
		{
			Game.crash( "Error loading the font", ioe );
		}

		/*-- Load UI Images */
		try
		{
			HEALTH_WHITE = ImageUtils.crearImagen( INTERFACE_FILE, 198, 1, 5, 16 );
			/* HEALTH_BLUE? unneeded */
			HEALTH_RED = ImageUtils.crearImagen( INTERFACE_FILE, 210, 1, 5, 16 );
			HEALTH_DARK_RED = ImageUtils.crearImagen( INTERFACE_FILE, 216, 1, 5, 16 );
			HEALTH_MAGENTA = ImageUtils.crearImagen( INTERFACE_FILE, 222, 1, 5, 16 );

			HEALTH_YELLOW = ImageUtils.crearImagen( INTERFACE_FILE, 228, 1, 5, 16 );
			HEALTH_BROWN = ImageUtils.crearImagen( INTERFACE_FILE, 234, 1, 5, 16 );
			HEALTH_PURPLE = ImageUtils.crearImagen( INTERFACE_FILE, 240, 1, 5, 16 );

			HEART_TILE = ImageUtils.crearImagen( INTERFACE_FILE, 199, 20, 14, 12 );
			GOLD_TILE = ImageUtils.crearImagen( INTERFACE_FILE, 214, 19, 9, 13 );
			KEY_TILE = ImageUtils.crearImagen( INTERFACE_FILE, 224, 20, 13, 13 );

			TILE_MORNING_TIME = ImageUtils.crearImagen( INTERFACE_FILE, 1, 109, 49, 24 );
			TILE_NOON_TIME = ImageUtils.crearImagen( INTERFACE_FILE, 52, 109, 49, 24 );
			TILE_AFTERNOON_TIME = ImageUtils.crearImagen( INTERFACE_FILE, 103, 109, 49,
					24 );
			TILE_DUSK_TIME = ImageUtils.crearImagen( INTERFACE_FILE, 154, 109, 49, 24 );
			TILE_NIGHT_TIME = ImageUtils.crearImagen( INTERFACE_FILE, 205, 109, 49, 24 );
			TILE_DAWN_TIME = ImageUtils.crearImagen( INTERFACE_FILE, 256, 109, 49, 24 );

			// TILE_NO_SHO;
			TILE_SHOT_II = ImageUtils.crearImagen( INTERFACE_FILE, 300, 3, 16, 16 );
			TILE_SHOT_III = ImageUtils.crearImagen( INTERFACE_FILE, 300, 20, 16, 16 );

			TILE_LINE_STEPS = ImageUtils.crearImagen( INTERFACE_FILE, 280, 25, 6, 5 );
			TILE_LINE_AIM = ImageUtils.crearImagen( INTERFACE_FILE, 265, 37, 36, 36 );
			TILE_SCAN = ImageUtils.crearImagen( INTERFACE_FILE, 302, 37, 36, 36 );

			TILE_WEAPON_BACK = ImageUtils.crearImagen( INTERFACE_FILE, 173, 1, 24, 24 );
			TILE_HEALTH_BACK = ImageUtils.crearImagen( INTERFACE_FILE, 3, 34, 261, 24 );

			BORDER1 = ImageUtils.crearImagen( INTERFACE_FILE, 34, 1, STANDARD_WIDTH,
					STANDARD_WIDTH );
			BORDER2 = ImageUtils.crearImagen( INTERFACE_FILE, 1, 1, STANDARD_WIDTH,
					STANDARD_WIDTH );
			BORDER3 = ImageUtils.crearImagen( INTERFACE_FILE, 100, 1, STANDARD_WIDTH,
					STANDARD_WIDTH );
			BORDER4 = ImageUtils.crearImagen( INTERFACE_FILE, 67, 1, STANDARD_WIDTH,
					STANDARD_WIDTH );

			IMG_AXE = ImageUtils.crearImagen( "gfx/crl_features.gif", 48, 0, 16, 16 );
			IMG_BIBLE = ImageUtils.crearImagen( "gfx/crl_features.gif", 96, 0, 16, 16 );
			IMG_CROSS = ImageUtils.crearImagen( "gfx/crl_features.gif", 64, 0, 16, 16 );
			IMG_DAGGER = ImageUtils.crearImagen( "gfx/crl_features.gif", STANDARD_WIDTH,
					0, 16, 16 );
			IMG_HOLY = ImageUtils.crearImagen( "gfx/crl_features.gif", 112, 0, 16, 16 );
			IMG_CRYSTAL = ImageUtils.crearImagen( "gfx/crl_features.gif", 128, 0, 16,
					16 );
			IMG_FIST = ImageUtils.crearImagen( "gfx/crl_features.gif", 136, 0, 16, 16 );
			IMG_STOPWATCH = ImageUtils.crearImagen( "gfx/crl_features.gif", 80, 0, 16,
					16 );

			/*
			 * COLOR_BORDER_IN = new Color(187,161,80); COLOR_BORDER_OUT = new
			 * Color(92,78,36);
			 */

			BLOOD1 = ImageUtils.crearImagen( "gfx/crl_effects.gif", 128, 96,
					STANDARD_WIDTH, STANDARD_WIDTH );
			BLOOD2 = ImageUtils.crearImagen( "gfx/crl_effects.gif", 192, 96,
					STANDARD_WIDTH, STANDARD_WIDTH );

			IMG_EXIT_BTN = ImageUtils.crearImagen( INTERFACE_FILE, 65, 81, 60, 26 );
			IMG_OK_BTN = ImageUtils.crearImagen( INTERFACE_FILE, 2, 81, 60, 26 );
			IMG_BUY_BTN = ImageUtils.crearImagen( INTERFACE_FILE, 128, 81, 60, 26 );
			IMG_YES_BTN = ImageUtils.crearImagen( INTERFACE_FILE, 191, 81, 60, 26 );
			IMG_NO_BTN = ImageUtils.crearImagen( INTERFACE_FILE, 254, 81, 60, 26 );

			IMG_ICON = ImageUtils.createImage( "res/crl_icon.png" );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
			Debug.byebye( e.getMessage( ) );
		}

		si.setIcon( IMG_ICON );
		si.setTitleFrame(
				"CastlevaniaRL v" + Game.getVersion( ) + ", Santiago Zapata 2005-2009" );
		/*-- Init Components*/
		messageBox = new SwingInformBox( );
		/* idList = new ListBox(psi); */
		messageBox.setBounds( 1 * 10, 22 * 24, 78 * 10, 2 * 24 );
		messageBox.setForeground( COLOR_LAST_MESSAGE );
		messageBox.setBackground( Color.BLACK );
		messageBox.setFont( FNT_MESSAGEBOX );
		messageBox.setEditable( false );
		messageBox.setVisible( false );
		messageBox.setOpaque( false );
		messageBox.setLineWrap( true );
		messageBox.setWrapStyleWord( true );

		psi.addComponentToPanel( messageBox );

		merchantBox = new MerchantBox( BORDER1, BORDER2, BORDER3, BORDER4,
				configuration.COLOR_BORDER_INNER, configuration.COLOR_BORDER_OUTER, STANDARD_WIDTH,
				STANDARD_WIDTH );
		merchantBox.setBounds( 150, 60, 500, 410 );
		merchantBox.setVisible( false );
		psi.addComponentToPanel( merchantBox );

		multiItemsBox = new MultiItemsBox( BORDER1, BORDER2, BORDER3, BORDER4,
				configuration.COLOR_BORDER_INNER, configuration.COLOR_BORDER_OUTER, STANDARD_WIDTH,
				STANDARD_WIDTH );
		multiItemsBox.setBounds( 250, 235, 300, 260 );
		multiItemsBox.setVisible( false );
		psi.addComponentToPanel( multiItemsBox );

		helpBox = new HelpBox( BORDER1, BORDER2, BORDER3, BORDER4, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER, STANDARD_WIDTH, STANDARD_WIDTH );
		helpBox.setBounds( 12, STANDARD_WIDTH, 770, 450 );
		helpBox.setVisible( false );
		psi.addComponentToPanel( helpBox );

		persistantMessageBox = new AddornedBorderTextArea( BORDER1, BORDER2, BORDER3,
				BORDER4, configuration.COLOR_BORDER_INNER, configuration.COLOR_BORDER_OUTER, STANDARD_WIDTH,
				STANDARD_WIDTH );
		persistantMessageBox.setBounds( 520, 90, 260, 400 );
		persistantMessageBox.setVisible( false );
		persistantMessageBox.setFont( configuration.FONT_MESSAGE_BOX_PERSISTANT );
		persistantMessageBox.setForeground( Color.WHITE );
		psi.addComponentToPanel( persistantMessageBox );

		si.setVisible( true );

		Debug.exitMethod( );
	}

	/**
	 * Checks if the point, relative to the console coordinates, is inside the
	 * ViewPort
	 */
	public boolean insideViewPort( int x, int y )
	{
		// return (x>=VP_START.x && x <= VP_END.x && y >= VP_START.y && y <= VP_END.y);
		return ( x >= 0 && x < FOVMask.length && y >= 0 && y < FOVMask[ 0 ].length )
				&& FOVMask[ x ][ y ];
	}

	public boolean insideViewPort( Position what )
	{
		return insideViewPort( what.x, what.y );
	}

	public boolean isDisplaying( Actor who )
	{
		return insideViewPort( getAbsolutePosition( who.getPosition( ) ) );
	}

	public boolean isOnFOVMask( int x, int y )
	{
		return FOVMask[ x ][ y ];
	}

	public synchronized void launchMerchant( Merchant who )
	{
		Debug.enterMethod( this, "launchMerchant", who );
		Equipment.eqMode = true;
		Item.shopMode = true;
		Vector merchandise = who.getMerchandiseFor( player );
		if ( merchandise == null || merchandise.size( ) == 0 )
		{
			chat( who );
			Debug.exitMethod( );
			return;
		}
		merchantBox.setMerchandise( merchandise );
		merchantBox.setVisible( true );
		merchantBox.setPrompt( "Greetings " + player.getName( ) + "... I am "
				+ who.getName( ) + ", the " + who.getMerchandiseTypeDesc( )
				+ " merchant. May I interest you in an item?" );
		while ( true )
		{
			merchantBox.setGold( player.getGold( ) );
			merchantBox.informChoice( Thread.currentThread( ) );
			try
			{
				this.wait( );
			}
			catch ( InterruptedException ie )
			{

			}
			Item choice = merchantBox.getSelection( );
			if ( choice == null )
				break;

			if ( player.getGold( ) >= choice.getGoldPrice( ) )
			{
				player.reduceGold( choice.getGoldPrice( ) );
				if ( player.canCarry( ) )
					player.addItem( choice );
				else
					level.addItem( player.getPosition( ), choice );
				merchantBox.setPrompt( "Thanks!, May I interest you in something else?" );
			}
			else
			{
				merchantBox.setPrompt( "I am afraid you don\'t have enough gold" );
			}

		}
		merchantBox.setVisible( false );
		si.recoverFocus( );
		Equipment.eqMode = false;
		Item.shopMode = false;
		Debug.exitMethod( );
	}

	public void levelUp( )
	{

		showMessage( "You gained a level!, [Press Space to continue]" );

		si.waitKey( CharKey.SPACE );
		enterScreen( );
		if ( player.deservesAdvancement( player.getPlayerLevel( ) ) )
		{
			Vector advancements = player.getAvailableAdvancements( );
			if ( advancements.size( ) != 0 )
			{
				Advancement playerChoice = Display.thus.showLevelUp( advancements );
				playerChoice.advance( player );
				player.getGameSessionInfo( )
						.addHistoryItem( "went for " + playerChoice.getName( ) );
			}
		}
		if ( player.deservesStatAdvancement( player.getPlayerLevel( ) ) )
		{
			Vector advancements = player.getAvailableStatAdvancements( );
			if ( advancements.size( ) != 0 )
			{
				Advancement playerChoice = Display.thus.showLevelUp( advancements );
				playerChoice.advance( player );
				player.getGameSessionInfo( )
						.addHistoryItem( "went for " + playerChoice.getName( ) );
			}
		}
		leaveScreen( );
		( (GraphicsDisplay) Display.thus ).showTextBox(
				"LEVEL UP!\n\n [" + player.getLastIncrementString( ) + "]", 40, 60, 300,
				300 );
		// showMessage("You gained a level!, ["+player.getLastIncrementString()+"]");
		player.resetLastIncrements( );
	}

	public void processQuit( )
	{
		messageBox.setForeground( COLOR_LAST_MESSAGE );
		messageBox.setText(
				quitMessages[ Util.rand( 0, quitMessages.length - 1 ) ] + " (y/n)" );
		si.refresh( );
		if ( prompt( ) )
		{
			messageBox.setText(
					"Go away, and let the world flood in darkness... [Press Space to continue]" );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
			enterScreen( );
			// si.refresh();
			player.getGameSessionInfo( ).setDeathCause( GameSessionInfo.QUIT );
			informPlayerCommand( CommandListener.QUIT );
		}
		messageBox.clear( );
		si.refresh( );
	}

	public void processSave( )
	{
		if ( !player.getGame( ).canSave( ) )
		{
			level.addMessage( "You cannot save your game here!" );
			return;
		}
		messageBox.setForeground( COLOR_LAST_MESSAGE );
		messageBox.setText( "Save your game? (y/n)" );
		si.refresh( );
		if ( prompt( ) )
		{
			messageBox.setText(
					"Saving... I will await your return.. [Press Space to continue]" );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
			enterScreen( );
			informPlayerCommand( CommandListener.SAVE );
		}
		messageBox.clear( );
		si.refresh( );
	}

	public boolean prompt( )
	{

		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code != CharKey.Y && x.code != CharKey.y && x.code != CharKey.N
				&& x.code != CharKey.n )
			x = si.inkey( );
		return ( x.code == CharKey.Y || x.code == CharKey.y );
	}

	public boolean promptChat( NPC who )
	{
		si.saveBuffer( );
		boolean ret = ( (GraphicsDisplay) Display.thus )
				.showTextBoxPrompt( who.getTalkMessage( ), 280, 30, 330, 170 );
		si.refresh( );
		// waitKey();
		si.restore( );
		return ret;
	}

	public void refresh( )
	{
		si.cls( );
		// messageBox.setVisible(true);
		/*
		 * if (useMouse) drawCursor();
		 */
		drawLevel( );
		drawPlayerStatus( );
		si.refresh( );
		leaveScreen( );
		if ( dimMsg == 3 )
		{
			messageBox.setForeground( COLOR_OLD_MESSAGE );
			dimMsg = 0;
		}
		dimMsg++;
		if ( !player.getFlag( "KEEPMESSAGES" ) )
			eraseOnArrival = true;
		si.saveBuffer( ); // sz040507

	}

	// Runnable interface
	public void run( )
	{
	}

	public Action selectCommand( CharKey input )
	{
		Debug.enterMethod( this, "selectCommand", input );
		int com = getRelatedCommand( input.code );
		informPlayerCommand( com );
		Action ret = actionSelectedByCommand;
		actionSelectedByCommand = null;
		Debug.exitMethod( ret );
		return ret;
	}

	public void setFlipFacing( boolean val )
	{
		flipFacing = val;
	}

	@Override
	public void setPersistantMessage( String description )
	{
		persistantMessageBox.setText( description );
		persistantMessageBox.setVisible( true );
	}

	public void setPlayer( Player pPlayer )
	{
		super.setPlayer( pPlayer );
		flipFacing = false;
	}

	public void setTargets( Action a ) throws ActionCancelException
	{
		if ( a.needsItem( ) )
			a.setItem( pickItem( a.getPromptItem( ) ) );
		if ( a.needsDirection( ) )
		{
			a.setDirection( pickDirection( a.getPromptDirection( ) ) );
		}
		if ( a.needsPosition( ) )
		{
			if ( a == target )
				a.setPosition( pickPosition( a.getPromptPosition( ), CharKey.f ) );
			else
				a.setPosition( pickPosition( a.getPromptPosition( ), CharKey.SPACE ) );
		}
		if ( a.needsEquipedItem( ) )
			a.setEquipedItem( pickEquipedItem( a.getPromptEquipedItem( ) ) );
		if ( a.needsMultiItems( ) )
		{
			a.setMultiItems( pickMultiItems( a.getPromptMultiItems( ) ) );
		}
		if ( a.needsSpirits( ) )
		{
			// a.setMultiItems(pickSpirits());
			a.setMultiItems( pickMultiItems( a.getPromptMultiItems( ) ) );
		}
		if ( a.needsUnderlyingItem( ) )
		{
			a.setItem( pickUnderlyingItem( a.getPrompUnderlyingItem( ) ) );
		}
	}

	public void showImportantMessage( String x )
	{
		showMessage( x );
		si.waitKey( CharKey.SPACE );
	}

	public Action showInventory( ) throws ActionCancelException
	{
		enterScreen( );
		Equipment.menuDetail = true;
		Vector inventory = player.getInventory( );
		int xpos = 1, ypos = 0;
		BorderedMenuBox menuBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3, BORDER4,
				si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, TILE_WEAPON_BACK );
		menuBox.setGap( 35 );
		menuBox.setItemsPerPage( 10 );
		menuBox.setWidth( 75 );
		menuBox.setPosition( 3, 8 );
		menuBox.setTitle( "Items" );
		menuBox.setMenuItems( inventory );

		MenuBox itemUsageChoices = new MenuBox( si, null );
		itemUsageChoices.setItemsPerPage( 6 );
		itemUsageChoices.setWidth( 20 );
		itemUsageChoices.setPosition( 52, 15 );
		itemUsageChoices.setMenuItems( vecItemUsageChoices );
		si.saveBuffer( 1 );
		// si.saveBuffer();

		JTextArea itemDescription = GraphicsDisplay.createTempArea( 509, 201, 202, 122 );
		itemDescription.setVisible( true );
		si.addComponentToPanel( itemDescription );
		// si.cls();

		int xx = 17;
		int yy = 22;
		int ww = 750;
		int hh = 141;
		si.getGraphics2D( ).setColor( configuration.COLOR_BACKGROUND );
		si.getGraphics2D( ).fillRect( xx + 6, yy + 6, ww - 14, hh - 14 );
		si.getGraphics2D( ).setColor( configuration.COLOR_BORDER_OUTER );
		si.getGraphics2D( ).drawRect( xx + 6, yy + 6, ww - 14, hh - 14 );
		si.getGraphics2D( ).setColor( configuration.COLOR_BORDER_INNER );
		si.getGraphics2D( ).drawRect( xx + 8, yy + 8, ww - 18, hh - 18 );

		si.print( xpos + 2, ypos + 2, "Inventory", Color.BLUE );
		si.print( xpos + 2, ypos + 3, "1. Weapon:", Color.WHITE );
		si.print( xpos + 2, ypos + 4, "2. Readied", Color.WHITE );
		si.print( xpos + 2, ypos + 5, "3. Armor:", Color.WHITE );
		si.print( xpos + 2, ypos + 6, "4. Shield:", Color.WHITE );

		si.print( xpos + 10, ypos + 3, player.getEquipedWeaponDescription( ),
				Color.WHITE );
		si.print( xpos + 10, ypos + 4, player.getSecondaryWeaponDescription( ),
				Color.WHITE );
		si.print( xpos + 10, ypos + 5, player.getArmorDescription( ), Color.WHITE );
		si.print( xpos + 10, ypos + 6, player.getAccDescription( ), Color.WHITE );
		// menuBox.draw();
		// si.print(xpos,24, "[Space] to continue, Up and Down to browse", Color.WHITE);

		si.refresh( );
		si.saveBuffer( );
		// ---
		Item selected = null;

		Action selectedAction = null;
		do
		{
			try
			{
				Equipment eqs = (Equipment) menuBox.getSelectionAKS( additionalKeys );
				if ( eqs == null )
					break;
				selected = eqs.getItem( );
			}
			catch ( AdditionalKeysSignal aks )
			{
				switch ( aks.getKeyCode( ) )
				{
				case CharKey.N1:
					// Unequip Weapon
					if ( player.getWeapon( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getWeapon( ) );
						exitInventory( itemDescription );
						return selectedAction;
					}
					else
					{
						continue;
					}
				case CharKey.N2:
					// Unequip Secondary Weapon
					if ( player.getSecondaryWeapon( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getSecondaryWeapon( ) );
						exitInventory( itemDescription );
						return selectedAction;
					}
					else
					{
						continue;
					}
				case CharKey.N3:
					// Unequip Armor
					if ( player.getArmor( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getArmor( ) );
						exitInventory( itemDescription );
						return selectedAction;
					}
					else
					{
						continue;
					}
				case CharKey.N4:
					// Unequip Shield
					if ( player.getShield( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getShield( ) );
						exitInventory( itemDescription );
						return selectedAction;
					}
					else
					{
						continue;
					}
				}
			}
			if ( selected == null )
			{
				break;
			}
			si.print( 52, 8, selected.getDescription( ), configuration.COLOR_BOLD );
			itemDescription.setText( selected.getDefinition( ).getMenuDescription( ) );
			si.refresh( );

			itemUsageChoices.draw( );

			SimpleGFXMenuItem choice = null;
			try
			{
				choice = (SimpleGFXMenuItem) itemUsageChoices
						.getUnpagedOrdinalSelectionAKS( itemUsageKeys );
			}
			catch ( AdditionalKeysSignal aks )
			{
				switch ( aks.getKeyCode( ) )
				{
				case CharKey.u:
					choice = (SimpleGFXMenuItem) vecItemUsageChoices.elementAt( 0 );
					break;
				case CharKey.e:
					choice = (SimpleGFXMenuItem) vecItemUsageChoices.elementAt( 1 );
					break;
				case CharKey.t:
					choice = (SimpleGFXMenuItem) vecItemUsageChoices.elementAt( 2 );
					break;
				case CharKey.d:
					choice = (SimpleGFXMenuItem) vecItemUsageChoices.elementAt( 3 );
					break;
				}
			}
			if ( choice != null )
			{
				switch ( choice.getValue( ) )
				{
				case 1: // Use
					Use use = new Use( );
					use.setPerformer( player );
					use.setItem( selected );
					exitInventory( itemDescription );
					return use;
				case 2: // Equip
					Equip equip = new Equip( );
					equip.setPerformer( player );
					equip.setItem( selected );
					exitInventory( itemDescription );
					return equip;
				case 3: // Drop
					Drop drop = new Drop( );
					drop.setPerformer( player );
					drop.setItem( selected );
					exitInventory( itemDescription );
					return drop;
				case 4: // Throw
					Throw throwx = new Throw( );
					throwx.setPerformer( player );
					throwx.setItem( selected );
					exitInventory( itemDescription );
					throwx.setPosition( pickPosition( "Throw where?", CharKey.SPACE ) );
					return throwx;
				case 5: // Cancel

					break;
				}
			}
			itemDescription.setText( "" );
			si.restore( );
			si.refresh( );

		}
		while ( selected != null );
		// si.waitKey(CharKey.SPACE);
		si.restore( );
		si.refresh( );
		Equipment.eqMode = false;
		Equipment.menuDetail = false;
		exitInventory( itemDescription );
		leaveScreen( );
		return null;

	}

	/**
	 * Shows a message inmediately; useful for system messages.
	 * 
	 * @param x
	 *            the message to be shown
	 */
	public void showMessage( String x )
	{
		messageBox.setForeground( COLOR_LAST_MESSAGE );
		messageBox.setText( x );
		// si.refresh();
	}
	public void showMessageHistory( )
	{
		enterScreen( );
		si.saveBuffer( );
		si.drawImage( configuration.IMAGE_BACKGROUND );
		si.print( 1, 1, "Message Buffer", configuration.COLOR_BOLD );
		for ( int i = 0; i < 22; i++ )
		{
			if ( i >= messageHistory.size( ) )
				break;
			si.print( 1, i + 2,
					(String) messageHistory.elementAt( messageHistory.size( ) - 1 - i ),
					Color.WHITE );
		}

		si.print( 55, 24, "[ Space to Continue ]", Color.WHITE );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.restore( );
		si.refresh( );
		leaveScreen( );
	}

	public void showPlayerStats( )
	{

		si.saveBuffer( );
		enterScreen( );
		si.drawImage( configuration.IMAGE_BACKGROUND );
		si.print( 1, 1,
				player.getName( ) + " the level " + player.getPlayerLevel( ) + " "
						+ player.getClassString( ) + " " + player.getStatusString( ),
				configuration.COLOR_BOLD );
		si.print( 1, 2, "Sex: " + ( player.getSex( ) == Player.MALE ? "M" : "F" ),
				Color.WHITE );
		si.print( 1, 3,
				"Hits: " + player.getHits( ) + "/" + player.getHitsMax( ) + " Hearts: "
						+ player.getHearts( ) + "/" + player.getHeartsMax( ) + " Gold: "
						+ player.getGold( ) + " Keys: " + player.getKeys( ),
				Color.WHITE );
		si.print( 1, 4,
				"Carrying: " + player.getItemCount( ) + "/" + player.getCarryMax( ),
				Color.WHITE );
		si.print( 1, 6, "Attack: +" + player.getAttack( ), Color.WHITE );
		si.print( 1, 7, "Soul Power: +" + player.getSoulPower( ), Color.WHITE );
		si.print( 1, 8, "Evade: " + player.getEvadeChance( ) + "%", Color.WHITE );
		si.print( 1, 9, "Combat: " + ( 50 - player.getAttackCost( ) ), Color.WHITE );
		si.print( 1, 10, "Invokation: " + ( 50 - player.getCastCost( ) ), Color.WHITE );
		si.print( 1, 11, "Movement: " + ( 50 - player.getWalkCost( ) ), Color.WHITE );

		si.print( 1, 12, "Experience: " + player.getXp( ) + "/" + player.getNextXP( ),
				Color.WHITE );

		/*
		 * si.print(1,2, "Skills", ConsoleSystemInterface.RED); Vector skills =
		 * player.getAvailableSkills(); int cont = 0; for (int i = 0; i < skills.size();
		 * i++){ if (i % 10 == 0) cont++; si.print((cont-1) * 25 + 1, 3 + i - ((cont-1)
		 * * 10), ((Skill)skills.elementAt(i)).getMenuDescription()); }
		 */

		si.print( 1, 14, "Weapon Profficiences", configuration.COLOR_BOLD );
		si.print( 1, 15, "Hand to hand", configuration.COLOR_BOLD );
		si.print( 1, 16, "Daggers", configuration.COLOR_BOLD );
		si.print( 1, 17, "Swords", configuration.COLOR_BOLD );
		si.print( 1, 18, "Spears", configuration.COLOR_BOLD );
		si.print( 22, 15, "Whips", configuration.COLOR_BOLD );
		si.print( 22, 16, "Maces", configuration.COLOR_BOLD );
		si.print( 22, 17, "Pole Combat", configuration.COLOR_BOLD );
		si.print( 22, 18, "Combat Rings", configuration.COLOR_BOLD );
		si.print( 49, 15, "Projectiles", configuration.COLOR_BOLD );
		si.print( 49, 16, "Bows/XBows", configuration.COLOR_BOLD );
		si.print( 49, 17, "Machinery", configuration.COLOR_BOLD );
		si.print( 49, 18, "Shields", configuration.COLOR_BOLD );

		String[ ] wskills = ItemDefinition.CATS;
		int cont = 0;
		for ( int i = 0; i < wskills.length; i++ )
		{
			if ( i % 4 == 0 )
				cont++;
			si.print( ( cont - 1 ) * 23 + 13, 15 + i - ( ( cont - 1 ) * 4 ),
					verboseSkills[ player.weaponSkill( wskills[ i ] ) ], Color.WHITE );
		}

		si.print( 1, 19, "Attack Damage  ", configuration.COLOR_BOLD );
		si.print( 1, 20, "Actual Defense ", configuration.COLOR_BOLD );
		si.print( 1, 21, "Shield Rates   ", configuration.COLOR_BOLD );

		si.print( 16, 19, "" + player.getWeaponAttack( ), Color.WHITE );
		si.print( 16, 20,
				player.getArmorDefense( ) + ( player.getDefenseBonus( ) != 0 ? "+"
						+ player.getDefenseBonus( ) : "" ),
				Color.WHITE );
		si.print( 16, 21, "Block " + player.getShieldBlockChance( ) + "% Coverage "
				+ player.getShieldCoverageChance( ) + "%", Color.WHITE );

		si.print( 1, 23, "[ Press Space to continue ]", Color.WHITE );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.restore( );
		si.refresh( );
		leaveScreen( );
	}

	public Action showSkills( ) throws ActionCancelException
	{
		Debug.enterMethod( this, "showSkills" );
		enterScreen( );
		si.saveBuffer( );
		Vector skills = player.getAvailableSkills( );
		BorderedMenuBox menuBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3, BORDER4,
				si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, null );
		menuBox.setItemsPerPage( 14 );
		menuBox.setWidth( 48 );
		menuBox.setPosition( 6, 4 );
		menuBox.setMenuItems( skills );
		menuBox.setTitle( "Skills" );
		// menuBox.draw();
		si.refresh( );
		Skill selectedSkill = (Skill) menuBox.getSelection( );
		if ( selectedSkill == null )
		{
			si.restore( );
			si.refresh( );
			Debug.exitMethod( "null" );
			leaveScreen( );
			return null;
		}
		si.restore( );
		si.refresh( );
		if ( selectedSkill.isSymbolic( ) )
		{
			Debug.exitMethod( "null" );
			leaveScreen( );
			return null;
		}

		Action selectedAction = selectedSkill.getAction( );
		selectedAction.setPerformer( player );
		if ( selectedAction.canPerform( player ) )
			setTargets( selectedAction );
		else
			level.addMessage( selectedAction.getInvalidationMessage( ) );

		Debug.exitMethod( selectedAction );
		leaveScreen( );
		return selectedAction;
	}

	public void showSystemMessage( String x )
	{
		messageBox.setForeground( COLOR_LAST_MESSAGE );
		messageBox.setText( x );
		// si.refresh();
		si.waitKey( CharKey.SPACE );
	}

	// IO Utility
	public void waitKey( )
	{
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code == CharKey.NONE )
			x = si.inkey( );
	}

	private void drawLevel( )
	{
		Debug.enterMethod( this, "drawLevel" );
		// Cell[] [] cells =
		// level.getCellsAround(player.getPosition().x,player.getPosition().y,
		// player.getPosition().z, range);
		Cell[ ][ ] rcells = level.getMemoryCellsAround( player.getPosition( ).x,
				player.getPosition( ).y, player.getPosition( ).z, xrange, yrange );
		Cell[ ][ ] vcells = level.getVisibleCellsAround( player.getPosition( ).x,
				player.getPosition( ).y, player.getPosition( ).z, xrange, yrange );

		Position runner = new Position( player.getPosition( ).x - xrange,
				player.getPosition( ).y - yrange, player.getPosition( ).z );

		monstersOnSight.removeAllElements( );
		featuresOnSight.removeAllElements( );
		itemsOnSight.removeAllElements( );

		/*
		 * for (int x = 0; x < vcells.length; x++){ for (int y=0; y<vcells[0].length;
		 * y++){
		 */
		for ( int y = 0; y < vcells[ 0 ].length; y++ )
		{
			for ( int x = 0; x < vcells.length; x++ )
			{
				FOVMask[ PC_POS.x - xrange + x ][ PC_POS.y - yrange + y ] = false;
				int cellHeight = 0;
				if ( vcells[ x ][ y ] == null
						|| vcells[ x ][ y ].getID( ).equals( "AIR" ) )
				{
					if ( rcells[ x ][ y ] != null && !rcells[ x ][ y ].getAppearance( )
							.getID( ).equals( "NOTHING" ) )
					{
						GFXAppearance app = (GFXAppearance) rcells[ x ][ y ]
								.getAppearance( );
						try
						{
							if ( level.isDay( ) )
								si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
										( PC_POS.y - yrange + y ) * STANDARD_WIDTH - 17
												- app.getSuperHeight( ),
										app.getDarkImage( ) );
							else
								si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
										( PC_POS.y - yrange + y ) * STANDARD_WIDTH - 17
												- app.getSuperHeight( ),
										app.getDarkniteImage( ) );
						}
						catch ( NullPointerException npe )
						{
							Color c = si.getGraphics2D( ).getColor( );
							si.getGraphics2D( ).setColor( Color.RED );
							si.getGraphics2D( )
									.fillRect( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
											( PC_POS.y - yrange + y ) * STANDARD_WIDTH
													- 17 - app.getSuperHeight( ),
											STANDARD_WIDTH, 49 );
							si.getGraphics2D( ).setColor( c );
						}
					}
					else
					{
						// Draw nothing
						// si.drawImage((PC_POS.x-xrange+x)*32,(PC_POS.y-yrange+y)*32-17,
						// "gfx/black.gif");
						// si.print(PC_POS.x-xrange+x,PC_POS.y-yrange+y,
						// CharAppearance.getVoidAppearance().getChar(),
						// CharAppearance.getVoidAppearance().BLACK);
					}
				}
				else
				{
					cellHeight = vcells[ x ][ y ].getHeight( );
					FOVMask[ PC_POS.x - xrange + x ][ PC_POS.y - yrange + y ] = true;
					String bloodLevel = level.getBloodAt( runner );
					GFXAppearance cellApp = (GFXAppearance) vcells[ x ][ y ]
							.getAppearance( );

					boolean frosty = false;
					if ( level.getFrostAt( runner ) != 0 )
					{
						frosty = true;
						// TODO: Apply a blue tint
					}
					int depthFromPlayer = level.getDepthFromPlayer(
							player.getPosition( ).x - xrange + x,
							player.getPosition( ).y - yrange + y );
					if ( depthFromPlayer != 0 )
					{
						si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
								( PC_POS.y - yrange + y ) * STANDARD_WIDTH - 17
										+ depthFromPlayer * 10,
								cellApp.getDarkImage( ) );
					}
					else
					{
						if ( level.isDay( ) )
							si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH - 17
											- cellApp.getSuperHeight( ),
									cellApp.getImage( ) );
						else
							si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH - 17
											- cellApp.getSuperHeight( ),
									cellApp.getNiteImage( ) );
					}
					if ( bloodLevel != null )
					{
						switch ( Integer.parseInt( bloodLevel ) )
						{
						case 0:
							si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight - cellApp.getSuperHeight( ),
									BLOOD1 );
							break;
						case 1:
							si.drawImage( ( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight - cellApp.getSuperHeight( ),
									BLOOD2 );
							break;
						}
					}
				}
				runner.x++;
			}
			runner.x = player.getPosition( ).x - xrange;
			for ( int x = 0; x < vcells.length; x++ )
			{
				int cellHeight = 0;
				if ( vcells[ x ][ y ] != null )
				{
					cellHeight = vcells[ x ][ y ].getHeight( );
					Feature feat = level.getFeatureAt( runner );
					if ( feat != null )
					{
						if ( feat.isVisible( ) )
						{
							GFXAppearance featApp = (GFXAppearance) feat.getAppearance( );
							si.drawImage(
									( PC_POS.x - xrange + x ) * STANDARD_WIDTH
											- featApp.getSuperWidth( ),
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight - featApp.getSuperHeight( ),
									featApp.getImage( ) );
						}
					}

					SmartFeature sfeat = level.getSmartFeature( runner );
					if ( sfeat != null )
					{
						if ( sfeat.isVisible( ) )
						{
							GFXAppearance featApp = (GFXAppearance) sfeat
									.getAppearance( );
							si.drawImage(
									( PC_POS.x - xrange + x ) * STANDARD_WIDTH
											- featApp.getSuperWidth( ),
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight - featApp.getSuperHeight( ),
									featApp.getImage( ) );
						}
					}

					Vector items = level.getItemsAt( runner );
					Item item = null;
					if ( items != null )
					{
						item = (Item) items.elementAt( 0 );
					}
					if ( item != null )
					{
						if ( item.isVisible( ) )
						{
							GFXAppearance itemApp = (GFXAppearance) item.getAppearance( );
							si.drawImage(
									( PC_POS.x - xrange + x ) * STANDARD_WIDTH
											- itemApp.getSuperWidth( ),
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight - itemApp.getSuperHeight( ),
									itemApp.getImage( ) );
						}
					}

					if ( yrange == y && x == xrange )
					{
						if ( player.isInvisible( ) )
						{
							si.drawImage( PC_POS.x * STANDARD_WIDTH,
									PC_POS.y * STANDARD_WIDTH - 4 * cellHeight,
									( (GFXAppearance) AppearanceFactory
											.getAppearanceFactory( )
											.getAppearance( "SHADOW" ) ).getImage( ) );
						}
						else
						{
							GFXAppearance playerAppearance = (GFXAppearance) player
									.getAppearance( );
							BufferedImage playerImage = (BufferedImage) playerAppearance
									.getImage( );
							if ( flipFacing )
							{
								playerImage = ImageUtils.vFlip( playerImage );
								// flipFacing = false;
							}
							if ( level.getMapCell( player.getPosition( ) ) != null
									&& level.getMapCell( player.getPosition( ) )
											.isShallowWater( ) )
								// si.drawImage(PC_POS.x*32-playerAppearance.getSuperWidth(),PC_POS.y*32-4*cellHeight-playerAppearance.getSuperHeight()+16/,
								// playerImage);
								si.drawImage(
										PC_POS.x * STANDARD_WIDTH
												- playerAppearance.getSuperWidth( ),
										PC_POS.y * STANDARD_WIDTH
												- 4 * player.getStandingHeight( )
												- playerAppearance.getSuperHeight( ) + 16,
										playerImage );
							else
								// si.drawImage(PC_POS.x*32-playerAppearance.getSuperWidth(),PC_POS.y*32-4*cellHeight-playerAppearance.getSuperHeight(),
								// playerImage);
								si.drawImage(
										PC_POS.x * STANDARD_WIDTH
												- playerAppearance.getSuperWidth( ),
										PC_POS.y * STANDARD_WIDTH
												- 4 * player.getStandingHeight( )
												- playerAppearance.getSuperHeight( ),
										playerImage );
						}
					}
					Monster monster = level.getMonsterAt( runner );

					if ( monster != null && monster.isVisible( ) )
					{
						GFXAppearance monsterApp = (GFXAppearance) monster
								.getAppearance( );
						if ( monster.canSwim( ) && level.getMapCell( runner ) != null
								&& level.getMapCell( runner ).isShallowWater( ) )
						{
							si.drawImage(
									( PC_POS.x - xrange + x ) * STANDARD_WIDTH
											- monsterApp.getSuperWidth( ),
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight
											- monsterApp.getSuperHeight( ) + 16,
									monsterApp.getImage( ) );
							// TODO: Overlap water on the monster, draw it lowly
						}
						else if ( monster.hasCounter( Consts.C_MONSTER_FREEZE ) )
						{
							// TODO: Overlay a cyan layer
							si.drawImage(
									( PC_POS.x - xrange + x ) * STANDARD_WIDTH
											- monsterApp.getSuperWidth( ),
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight
											- monsterApp.getSuperHeight( ),
									monsterApp.getImage( ) );
						}
						else
							si.drawImage(
									( PC_POS.x - xrange + x ) * STANDARD_WIDTH
											- monsterApp.getSuperWidth( ),
									( PC_POS.y - yrange + y ) * STANDARD_WIDTH
											- 4 * cellHeight
											- monsterApp.getSuperHeight( ),
									monsterApp.getImage( ) );
					}
					// Draw Masks
					Color mask = null;

					// Water
					if ( vcells[ x ][ y ].isWater( ) )
					{
						if ( level.canFloatUpward( runner ) )
						{
							mask = WATERCOLOR;
						}
						else
						{
							mask = WATERCOLOR_BLOCKED;

						}
					}
					if ( mask != null )
					{
						si.getGraphics2D( ).setColor( mask );
						si.getGraphics2D( ).fillRect(
								( PC_POS.x - xrange + x ) * STANDARD_WIDTH,
								( PC_POS.y - yrange + y ) * STANDARD_WIDTH,
								STANDARD_WIDTH, STANDARD_WIDTH );
					}
				}
				// runner.y++;
				runner.x++;
			}
			/*
			 * runner.y = player.getPosition().y-yrange; runner.x ++;
			 */
			runner.x = player.getPosition( ).x - xrange;
			runner.y++;
		}

		// Overlay
		// Draw Masks
		Color mask = null;
		// Rain
		if ( player.getFlag( Consts.ENV_RAIN ) )
			mask = RAINCOLOR;

		// Thunderstorm
		if ( player.getFlag( Consts.ENV_THUNDERSTORM ) )
			mask = THUNDERCOLOR;
		// Fog
		if ( player.getFlag( Consts.ENV_FOG ) )
			mask = FOGCOLOR;

		if ( mask != null )
		{
			si.getGraphics2D( ).setColor( mask );
			si.getGraphics2D( ).fillRect( 0, 0, this.configuration.SCREEN_WIDTH,
					this.configuration.SCREEN_HEIGHT );
		}

		Debug.exitMethod( );
	}

	private void drawPlayerStatus( )
	{
		Debug.enterMethod( this, "drawPlayerStatus" );
		Image foreColor;
		Image backColor;
		switch ( ( ( player.getHits( ) - 1 ) / 20 ) + 1 )
		{
		case 1:
			foreColor = HEALTH_RED;
			backColor = HEALTH_WHITE;
			break;
		case 2:
			foreColor = HEALTH_DARK_RED;
			backColor = HEALTH_RED;
			break;
		default:
			foreColor = HEALTH_MAGENTA;
			backColor = HEALTH_DARK_RED;
			break;
		}

		Image timeTile = null;
		switch ( level.getDayTime( ) )
		{
		case Level.MORNING:
			timeTile = TILE_MORNING_TIME;
			break;
		case Level.NOON:
			timeTile = TILE_NOON_TIME;
			break;
		case Level.AFTERNOON:
			timeTile = TILE_AFTERNOON_TIME;
			break;
		case Level.DUSK:
			timeTile = TILE_DUSK_TIME;
			break;
		case Level.NIGHT:
			timeTile = TILE_NIGHT_TIME;
			break;
		case Level.DAWN:
			timeTile = TILE_DAWN_TIME;
			break;
		}

		Image shotTile = TILE_NO_SHOT;
		if ( player.getShotLevel( ) == 1 )
			shotTile = TILE_SHOT_II;
		if ( player.getShotLevel( ) == 2 )
			shotTile = TILE_SHOT_III;
		if ( shotTile != null )
			si.drawImage( 18, 80, shotTile );
		int rest = ( ( player.getHits( ) - 1 ) % 20 ) + 1;

		si.printAtPixel( 14, 30,
				player.getName( ) + ", the Lv" + player.getPlayerLevel( ) + " "
						+ player.getClassString( ) + " " + player.getScore( ) + " "
						+ player.getStatusString( ),
				Color.WHITE );
		si.drawImage( 14, 35, TILE_WEAPON_BACK );
		si.drawImage( 38, 35, TILE_HEALTH_BACK );

		for ( int i = 0; i < 20; i++ )
			if ( i + 1 <= rest )
				si.drawImage( 41 + ( i * 6 ), 40, foreColor );
			else
				si.drawImage( 41 + ( i * 6 ), 40, backColor );

		if ( player.getLevel( ).getBoss( ) != null )
		{
			int sixthiedBossHits = (int) Math
					.ceil( ( player.getLevel( ).getBoss( ).getHits( ) * 60.0 )
							/ (double) player.getLevel( ).getBoss( ).getMaxHits( ) );
			Image foreColorB;
			Image backColorB;
			// switch (((player.getLevel().getBoss().getHits()-1) / 20) + 1){
			switch ( ( ( sixthiedBossHits - 1 ) / 20 ) + 1 )
			{
			case 1:
				foreColorB = HEALTH_YELLOW;
				backColorB = HEALTH_WHITE;
				break;
			case 2:
				foreColorB = HEALTH_BROWN;
				backColorB = HEALTH_YELLOW;
				break;
			default:
				foreColorB = HEALTH_PURPLE;
				backColorB = HEALTH_BROWN;
				break;
			}

			int restB = ( ( sixthiedBossHits - 1 ) % 20 ) + 1;

			for ( int i = 0; i < 20; i++ )
				if ( i + 1 <= restB )
					si.drawImage( 665 + ( i * 6 ), 540, foreColorB );
				else
					si.drawImage( 665 + ( i * 6 ), 540, backColorB );
		}

		// TODO: Add the background
		if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
		{
			if ( player.getMysticWeapon( ) != -1 )
				si.drawImage( 18, 38, getImageForMystic( player.getMysticWeapon( ) ) );
		}
		else if ( player.getWeapon( ) != null )
		{
			si.drawImage( 18, 38, ( (GFXAppearance) player.getWeapon( ).getAppearance( ) )
					.getImage( ) );
		}
		if ( player.getLevel( ).getLevelNumber( ) != -1 )
			si.printAtPixel( 524, 50, "STAGE  " + player.getLevel( ).getLevelNumber( )
					+ " " + player.getLevel( ).getDescription( ), Color.WHITE );
		else
			si.printAtPixel( 524, 50, player.getLevel( ).getDescription( ), Color.WHITE );

		// si.drawImage(759, 35, TILE_TIME_BACK);
		si.drawImage( 723, 38, timeTile );
		if ( player.getFlag( Consts.ENV_FOG ) )
			si.printAtPixel( 723, 30, "FOG", Color.GRAY );
		if ( player.getFlag( Consts.ENV_RAIN ) )
			si.printAtPixel( 723, 30, "RAIN", Color.BLUE );
		if ( player.getFlag( Consts.ENV_SUNNY ) )
			si.printAtPixel( 723, 30, "SUNNY", Color.YELLOW );
		if ( player.getFlag( Consts.ENV_THUNDERSTORM ) )
			si.printAtPixel( 723, 30, "STORM", Color.WHITE );

		si.drawImage( 166, 42, HEART_TILE );
		si.printAtPixel( 182, 51, "" + player.getHearts( ), Color.WHITE );
		si.drawImage( 206, 42, GOLD_TILE );
		si.printAtPixel( 219, 51, "" + player.getGold( ), Color.WHITE );
		si.drawImage( 249, 42, KEY_TILE );
		si.printAtPixel( 269, 51, "" + player.getKeys( ), Color.WHITE );
		if ( player.getHostage( ) != null )
		{
			Hostage h = player.getHostage( );
			si.drawImage( 18, 64, ( (GFXAppearance) h.getAppearance( ) ).getImage( ) );
		}

		// si.printAtPixel(18,80,""+player.getHoverHeight(), Color.WHITE);
		Debug.exitMethod( );
	}

	private void drawStepsTo( int x, int y, Image tile, int cellHeight )
	{
		Position target = new Position( x, y );
		Line line = new Line( PC_POS, target );
		Position tmp = line.next( );
		while ( !tmp.equals( target ) )
		{
			tmp = line.next( );
			si.drawImage( tmp.x * STANDARD_WIDTH + 13,
					( tmp.y * STANDARD_WIDTH + 13 ) - 4 * cellHeight, tile );
		}

	}

	private void enterScreen( )
	{
		messageBox.setVisible( false );
	}

	private void examineLevelMap( )
	{
		messageBox.setVisible( false );
		si.saveBuffer( );
		// si.drawImage(GFXDisplay.IMG_FRAME);
		int lw = level.getWidth( );
		int lh = level.getHeight( );
		int remnantx = (int) ( ( 740 - ( lw * 3 ) ) / 2.0d );
		int remnanty = (int) ( ( 480 - ( lh * 3 ) ) / 2.0d );
		Graphics2D g = si.getGraphics2D( );
		g.setColor( TRANSPARENT_GRAY );
		g.fillRect( 0, 0, 800, 600 );
		Color cellColor = null;
		Position runner = new Position( 0, 0, player.getPosition( ).z );
		for ( int x = 0; x < level.getWidth( ); x++, runner.x++, runner.y = 0 )
			for ( int y = 0; y < level.getHeight( ); y++, runner.y++ )
			{
				if ( !level.remembers( x, y ) )
					// cellColor = Color.BLACK;
					continue;
				else
				{
					Cell current = level.getMapCell( runner );
					Feature currentF = level.getFeatureAt( runner );
					if ( level.isVisible( x, y ) )
					{
						if ( current == null )
							// cellColor = Color.BLACK;
							continue;
						else if ( level.getExitOn( runner ) != null )
							cellColor = Color.RED;
						else if ( current.isSolid( )
								|| ( currentF != null && currentF.isSolid( ) ) )
							cellColor = MAP_SOLID;
						else
							cellColor = MAP_NOSOLID_LOS;

					}
					else
					{
						if ( current == null )
							// cellColor = Color.BLACK;
							continue;
						else if ( level.getExitOn( runner ) != null )
							cellColor = Color.RED;
						else if ( current.isSolid( )
								|| ( currentF != null && currentF.isSolid( ) ) )
							cellColor = MAP_SOLID;
						else
							cellColor = MAP_NOSOLID;
					}
					if ( player.getPosition( ).x == x && player.getPosition( ).y == y )
						cellColor = Color.RED;
				}
				g.setColor( cellColor );
				// g.fillOval(30+remnantx+x*5, 30+remnanty+y*5, 5,5);
				g.fillRect( 30 + remnantx + x * 3, 30 + remnanty + y * 3, 3, 3 );
			}
		si.refresh( );

		si.waitKey( CharKey.SPACE );
		messageBox.setVisible( true );
		si.restore( );
		si.refresh( );

	}

	private void exitInventory( JTextArea itemDescription )
	{
		si.remove( itemDescription );
		si.restore( 1 );
		si.refresh( );
	}

	private Image getImageForMystic( int mysticID )
	{
		switch ( mysticID )
		{
		case Player.AXE:
			return IMG_AXE;
		case Player.BIBLE:
			return IMG_BIBLE;
		case Player.CROSS:
			return IMG_CROSS;
		case Player.DAGGER:
			return IMG_DAGGER;
		case Player.HOLY:
			return IMG_HOLY;
		case Player.SACRED_CRYSTAL:
			return IMG_CRYSTAL;
		case Player.SACRED_FIST:
			return IMG_FIST;
		case Player.STOPWATCH:
			return IMG_STOPWATCH;
		}
		return null;
	}

	private Position getNearestMonsterPosition( )
	{
		VMonster monsters = level.getMonsters( );
		Monster nearMonster = null;
		int minDist = 150;
		int maxDist = 15;
		for ( int i = 0; i < monsters.size( ); i++ )
		{
			Monster monster = (Monster) monsters.elementAt( i );
			if ( monster.getPosition( ).z( ) != level.getPlayer( ).getPosition( ).z( ) )
				continue;
			int distance = Position.flatDistance( level.getPlayer( ).getPosition( ),
					monster.getPosition( ) );
			if ( distance < maxDist && distance < minDist && player.sees( monster ) )
			{
				minDist = distance;
				nearMonster = monster;
			}
		}
		if ( nearMonster != null )
			return nearMonster.getPosition( );
		else
			return null;
	}

	private void initProperties( )
	{
		STANDARD_WIDTH = configuration.NORMAL_TILE_WIDTH;

		xrange = configuration.SCREEN_WIDTH_IN_TILES;
		yrange = configuration.SCREEN_HEIGHT_IN_TILES;

		PC_POS = this.configuration.getPlayerLocationOnScreen( );
		FNT_MESSAGEBOX = configuration.FONT_MESSAGE_BOX;

		TILE_LINE_AIM = configuration.IMAGE_AIM_LINE_TILE;
		TILE_SCAN = configuration.IMAGE_SCAN_TILE;
		TILE_LINE_STEPS = configuration.IMAGE_STEPS_TILE;
	}

	private void leaveScreen( )
	{
		messageBox.setVisible( true );
	}

	private int pickDirection( String prompt ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickDirection" );
		// refresh();
		leaveScreen( );
		messageBox.setText( prompt );
		// si.refresh();
		// refresh();

		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code == CharKey.NONE )
			x = si.inkey( );
		if ( x.isArrow( ) )
		{
			int ret = GFXUISelector.toIntDirection( x );
			Debug.exitMethod( ret );
			return ret;
		}
		else
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			si.refresh( );
			throw ret;
		}
	}

	private Item pickEquipedItem( String prompt ) throws ActionCancelException
	{
		enterScreen( );

		Vector <Item> equipped = new Vector <Item>( );
		if ( player.getArmor( ) != null )
			equipped.add( player.getArmor( ) );
		if ( player.getWeapon( ) != null )
			equipped.add( player.getWeapon( ) );
		if ( player.getShield( ) != null )
			equipped.add( player.getShield( ) );
		if ( player.getSecondaryWeapon( ) != null )
			equipped.add( player.getSecondaryWeapon( ) );

		if ( equipped.size( ) == 0 )
		{
			level.addMessage( "Nothing equipped" );
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			throw ret;
		}

		BorderedMenuBox menuBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3, BORDER4,
				si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, TILE_WEAPON_BACK );
		menuBox.setGap( 35 );

		// menuBox.setBounds(26,6,30,11);
		menuBox.setBounds( 6, 4, 70, 12 );
		menuBox.setMenuItems( equipped );
		menuBox.setTitle( prompt );
		si.saveBuffer( );
		// menuBox.draw();
		Item equiped = (Item) menuBox.getSelection( );
		if ( equiped == null )
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			si.restore( );
			si.refresh( );
			throw ret;
		}
		si.restore( );
		si.refresh( );
		leaveScreen( );
		return equiped;
	}

	private Item pickItem( String prompt ) throws ActionCancelException
	{
		enterScreen( );
		Vector inventory = player.getInventory( );
		BorderedMenuBox menuBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3, BORDER4,
				si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, TILE_WEAPON_BACK );
		menuBox.setGap( 35 );
		menuBox.setPosition( 6, 4 );
		menuBox.setWidth( 70 );
		menuBox.setItemsPerPage( 12 );
		menuBox.setMenuItems( inventory );
		menuBox.setTitle( prompt );
		si.saveBuffer( );
		// menuBox.draw();
		Equipment equipment = (Equipment) menuBox.getSelection( );
		si.restore( );
		if ( equipment == null )
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			si.restore( );
			si.refresh( );
			leaveScreen( );
			throw ret;
		}
		si.restore( );
		si.refresh( );
		leaveScreen( );
		return equipment.getItem( );
	}

	private Vector pickMultiItems( String prompt ) throws ActionCancelException
	{
		// Equipment.eqMode = true;
		Vector inventory = player.getInventory( );
		BorderedMenuBox menuBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3, BORDER4,
				si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, TILE_WEAPON_BACK );
		menuBox.setBounds( 25, 3, 40, 18 );
		// menuBox.setPromptSize(2);
		menuBox.setMenuItems( inventory );
		menuBox.setTitle( prompt );
		// menuBox.setForeColor(ConsoleSystemInterface.RED);
		// menuBox.setBorder(true);
		Vector ret = new Vector( );
		BorderedMenuBox selectedBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3,
				BORDER4, si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, TILE_WEAPON_BACK );
		selectedBox.setBounds( 5, 3, 20, 18 );
		// selectedBox.setPromptSize(2);
		selectedBox.setTitle( "Selected Items" );
		selectedBox.setMenuItems( ret );
		// selectedBox.setForeColor(ConsoleSystemInterface.RED);

		si.saveBuffer( );

		while ( true )
		{
			selectedBox.draw( );
			menuBox.draw( );

			Equipment equipment = (Equipment) menuBox.getSelection( );
			if ( equipment == null )
				break;
			if ( !ret.contains( equipment.getItem( ) ) )
				ret.add( equipment.getItem( ) );
		}
		si.restore( );
		// Equipment.eqMode = false;
		return ret;
	}

	private Position pickPosition(	String prompt,
									int fireKeyCode ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickPosition" );
		messageBox.setForeground( COLOR_LAST_MESSAGE );
		messageBox.setText( prompt );
		Position defaultTarget = null;
		Position nearest = getNearestMonsterPosition( );
		if ( nearest != null )
		{
			defaultTarget = nearest;
		}
		else
		{
			defaultTarget = null;
		}

		Position browser = null;
		Position offset = new Position( 0, 0 );
		if ( lockedMonster != null )
		{
			if ( !player.sees( lockedMonster ) || lockedMonster.isDead( ) )
			{
				lockedMonster = null;
			}
			else
				defaultTarget = new Position( lockedMonster.getPosition( ) );
		}

		if ( defaultTarget == null )
		{
			offset = new Position( 0, 0 );
		}
		else
		{
			offset = new Position( defaultTarget.x - player.getPosition( ).x,
					defaultTarget.y - player.getPosition( ).y );
		}

		if ( !insideViewPort( PC_POS.x + offset.x, PC_POS.y + offset.y ) )
		{
			offset = new Position( 0, 0 );
		}

		/*
		 * if (!insideViewPort(offset)) offset = new Position (0,0);
		 */

		si.refresh( );
		si.saveBuffer( );

		while ( true )
		{
			si.restore( );
			int cellHeight = 0;
			browser = Position.add( player.getPosition( ), offset );
			String looked = "";

			if ( FOVMask[ PC_POS.x + offset.x ][ PC_POS.y + offset.y ] )
			{
				Cell choosen = level.getMapCell( browser );
				Feature feat = level.getFeatureAt( browser );
				Vector items = level.getItemsAt( browser );
				if ( choosen != null )
					cellHeight = choosen.getHeight( );
				Item item = null;
				if ( items != null )
				{
					item = (Item) items.elementAt( 0 );
				}
				Actor actor = level.getActorAt( browser );
				if ( choosen != null )
					looked += choosen.getDescription( );
				if ( level.getBloodAt( browser ) != null )
					looked += "{bloody}";
				if ( feat != null )
					looked += ", " + feat.getDescription( );
				if ( actor != null )
					looked += ", " + actor.getDescription( );
				if ( item != null )
					looked += ", " + item.getDescription( );
			}
			messageBox.setText( prompt + " " + looked );
			// si.print(PC_POS.x + offset.x, PC_POS.y + offset.y, '_',
			// ConsoleSystemInterface.RED);
			drawStepsTo( PC_POS.x + offset.x, ( PC_POS.y + offset.y ), TILE_LINE_STEPS,
					cellHeight );

			si.drawImage( ( PC_POS.x + offset.x ) * STANDARD_WIDTH - 2,
					( ( PC_POS.y + offset.y ) * STANDARD_WIDTH - 2 ) - 4 * cellHeight,
					TILE_LINE_AIM );
			si.refresh( );
			CharKey x = new CharKey( CharKey.NONE );
			while ( x.code != CharKey.SPACE && x.code != CharKey.ESC
					&& x.code != fireKeyCode && !x.isArrow( ) )
				x = si.inkey( );
			if ( x.code == CharKey.ESC )
			{
				si.restore( );
				si.refresh( );
				throw new ActionCancelException( );
			}
			if ( x.code == CharKey.SPACE || x.code == fireKeyCode )
			{
				si.restore( );
				if ( level.getMonsterAt( browser ) != null )
					lockedMonster = level.getMonsterAt( browser );
				return browser;
			}
			offset.add(
					Action.directionToVariation( GFXUISelector.toIntDirection( x ) ) );

			if ( offset.x >= xrange )
				offset.x = xrange;
			if ( offset.x <= -xrange )
				offset.x = -xrange;
			if ( offset.y >= yrange )
				offset.y = yrange;
			if ( offset.y <= -yrange )
				offset.y = -yrange;
		}

	}

	private Item pickUnderlyingItem( String prompt ) throws ActionCancelException
	{
		enterScreen( );
		Vector items = level.getItemsAt( player.getPosition( ) );
		if ( items == null )
			return null;
		if ( items.size( ) == 1 )
			return (Item) items.elementAt( 0 );
		BorderedMenuBox menuBox = new BorderedMenuBox( BORDER1, BORDER2, BORDER3, BORDER4,
				si, configuration.COLOR_BACKGROUND, configuration.COLOR_BORDER_INNER,
				configuration.COLOR_BORDER_OUTER,
				STANDARD_WIDTH, TILE_WEAPON_BACK );
		menuBox.setGap( 35 );
		menuBox.setBounds( 6, 4, 70, 12 );
		menuBox.setMenuItems( items );
		menuBox.setTitle( prompt );
		si.saveBuffer( );
		// menuBox.draw();
		Item item = (Item) menuBox.getSelection( );

		if ( item == null )
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			si.restore( );
			si.refresh( );
			leaveScreen( );
			throw ret;
		}
		si.restore( );
		si.refresh( );
		leaveScreen( );
		return item;
	}
}

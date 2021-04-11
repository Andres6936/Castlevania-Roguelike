package co.castle.ui.graphicsUI;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Properties;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.actor.Message;
import co.castle.ai.ActionSelector;
import co.castle.game.Cheat;
import co.castle.main.ApplicationGraphics;
import co.castle.monster.Monster;
import co.castle.npc.NPC;
import co.castle.player.Player;
import co.castle.ui.ActionCancelException;
import co.castle.ui.UISelector;
import co.castle.ui.UserAction;
import sz.csi.CharKey;
import sz.util.Debug;
import sz.util.Position;

public class GFXUISelector extends UISelector
		implements ActionSelector, MouseListener, MouseMotionListener, Serializable
{
	private int mouseDirection = -1;
	private Position mousePosition;

	// Get instance of ApplicationFrame
	private ApplicationGraphics appFrame = ApplicationGraphics.getInstance( );

	private Position tempCursorPosition = new Position( 0, 0 );

	private Position tempCursorPositionScr = new Position( 0, 0 );

	private Position tempRel = new Position( 0, 0 );

	private boolean useMouse = false;

	int[ ] QDIRECTIONS = new int[ ]
	{	Action.UPLEFT, Action.UP, Action.UPRIGHT, Action.LEFT, Action.SELF, Action.RIGHT,
		Action.DOWNLEFT, Action.DOWN, Action.DOWNRIGHT };

	int x1 = (int) Math.round( ( 800.0 / 9.0 ) * 4.0 );

	int x2 = (int) Math.round( ( 800.0 / 9.0 ) * 5.0 );

	int y1 = (int) Math.round( ( 600.0 / 9.0 ) * 4.0 );

	int y2 = (int) Math.round( ( 600.0 / 9.0 ) * 5.0 );
	public static int toIntDirection( Position what )
	{
		switch ( what.x( ) )
		{
		case 1:
			switch ( what.y( ) )
			{
			case 1:
				return Action.DOWNRIGHT;
			case 0:
				return Action.RIGHT;
			case -1:
				return Action.UPRIGHT;
			}
		case 0:
			switch ( what.y( ) )
			{
			case 1:
				return Action.DOWN;
			case -1:
				return Action.UP;
			}
		case -1:
			switch ( what.y( ) )
			{
			case 1:
				return Action.DOWNLEFT;
			case 0:
				return Action.LEFT;
			case -1:
				return Action.UPLEFT;
			}
		}

		return -1;
	}

	public ActionSelector derive( )
	{
		return null;
	}

	public String getID( )
	{
		return "UI";
	}

	public void init(	UserAction[ ] gameActions,
						Action advance, Action target,
						Action attack, GFXUserInterface ui, Properties keyBindings )
	{
		super.init( gameActions, advance, target, attack, ui, keyBindings );

		// NOTE: Asset can container the property, clear this function

		// Get the file properties with configuration of user interface
		final Properties configurationUI = appFrame.configurationUI;

		// Get the valor of property and compare
		if ( configurationUI.getProperty( "useMouse" ).equals( "true" ) )
		{
			appFrame.addMouseListener( this );
			appFrame.addMouseMotionListener( this );
			useMouse = true;
		}

	}

	public void mouseClicked( MouseEvent e )
	{
		// TODO Auto-generated method stub

	}
	public void mouseDragged( MouseEvent e )
	{
		// TODO Auto-generated method stub

	}
	public void mouseEntered( MouseEvent e )
	{
		// TODO Auto-generated method stub

	}
	public void mouseExited( MouseEvent e )
	{
		// TODO Auto-generated method stub

	}

	public void mouseMoved( MouseEvent e )
	{
		switch ( defineQuadrant( e.getPoint( ).x, e.getPoint( ).y ) )
		{
		case 9:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.SE_RESIZE_CURSOR ) );
			break;
		case 6:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.E_RESIZE_CURSOR ) );
			break;
		case 3:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.NE_RESIZE_CURSOR ) );
			break;
		case 8:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.S_RESIZE_CURSOR ) );
			break;
		case 5:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.CROSSHAIR_CURSOR ) );
			break;
		case 2:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.N_RESIZE_CURSOR ) );
			break;
		case 7:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.SW_RESIZE_CURSOR ) );
			break;
		case 4:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.W_RESIZE_CURSOR ) );
			break;
		case 1:
			appFrame.setCursor( Cursor.getPredefinedCursor( Cursor.NW_RESIZE_CURSOR ) );
			break;
		}
		/*
		 * if (isCursorEnabled && updateCursorPosition(e.getPoint().x, e.getPoint().y))
		 * drawCursor();
		 */
	}

	public void mousePressed( MouseEvent e )
	{
		if ( e.getButton( ) == MouseEvent.BUTTON1 )
		{
			int quadrant = defineQuadrant( e.getPoint( ).x, e.getPoint( ).y );
			mouseDirection = QDIRECTIONS[ quadrant - 1 ];
		}
		else if ( e.getButton( ) == MouseEvent.BUTTON3 )
		{
			translatePosition( e.getPoint( ).x, e.getPoint( ).y );
		}
	}

	public void mouseReleased( MouseEvent e )
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Returns the Action that the player wants to perform. It may also forward a
	 * command instead
	 */

	public Action selectAction( Actor who )
	{
		Debug.enterMethod( this, "selectAction", who );
		CharKey input = null;
		Action ret = null;
		while ( ret == null )
		{
			if ( ui( ).gameOver( ) )
				return null;
			input = appFrame.inkey( );
			if ( input.code == CharKey.NONE && !useMouse )
				continue;
			ret = ( (GFXUserInterface) getUI( ) ).selectCommand( input );
			if ( ret != null )
			{
				if ( ret.canPerform( player ) )
					return ret;
				else
					return null;
			}
			if ( input.code == DONOTHING1_KEY )
			{
				Debug.exitMethod( "null" );
				return null;
			}
			if ( input.code == DONOTHING2_KEY )
			{
				Debug.exitMethod( "null" );
				return null;
			}

			if ( Cheat.cheatConsole( player, input.code ) )
			{
				continue;
			}

			if ( useMouse && mousePosition != null )
			{
				mouseDirection = -1;
				if ( level.isValidCoordinate( mousePosition ) )
				{
					// if (level.getMonsterAt(mousePosition) != null){
					if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
					{
						ret = player.getMysticAction( );
						try
						{
							if ( ret != null )
							{
								ret.setPerformer( player );
								if ( ret.canPerform( player ) )
									ret.setPosition( mousePosition );
								else
								{
									level.addMessage( ret.getInvalidationMessage( ) );
									throw new ActionCancelException( );
								}
								Debug.exitMethod( ret );
								mousePosition = null;
								return ret;
							}
						}
						catch ( ActionCancelException ace )
						{
							ui( ).addMessage(
									new Message( "- Cancelled", player.getPosition( ) ) );
							appFrame.refresh( );
							ret = null;
						}
					}
					else
					{
						ret = target;
						try
						{
							ret.setPerformer( player );
							if ( ret.canPerform( player ) )
								ret.setPosition( mousePosition );
							else
							{
								level.addMessage( ret.getInvalidationMessage( ) );
								throw new ActionCancelException( );
							}
							Debug.exitMethod( ret );
							mousePosition = null;
							return ret;
						}
						catch ( ActionCancelException ace )
						{
							ui( ).addMessage(
									new Message( "- Cancelled", player.getPosition( ) ) );
							appFrame.refresh( );
							ret = null;
						}

					}
					// }
				}
				mousePosition = null;
			}

			if ( isArrow( input )
					|| ( useMouse && mousePosition == null && mouseDirection != -1 ) )
			{
				int direction = -1;
				if ( useMouse && mouseDirection != -1 )
				{
					direction = mouseDirection;
					mouseDirection = -1;
				}
				else
				{
					direction = toIntDirection( input );
				}

				Monster vMonster = player.getLevel( )
						.getMonsterAt( Position.add( player.getPosition( ),
								Action.directionToVariation( direction ) ) );
				if ( vMonster != null
						&& vMonster.getStandingHeight( ) == player.getStandingHeight( )
						&& ( !( vMonster instanceof NPC ) || ( vMonster instanceof NPC
								&& ( (NPC) vMonster ).isHostile( ) ) ) )
				{
					if ( attack.canPerform( player ) )
					{
						attack.setDirection( direction );
						Debug.exitMethod( attack );
						return attack;
					}
					else
					{
						level.addMessage( attack.getInvalidationMessage( ) );
						appFrame.refresh( );
					}
				}
				else
				{
					advance.setDirection( direction );
					Debug.exitMethod( advance );
					switch ( direction )
					{
					case Action.UPLEFT:
					case Action.LEFT:
					case Action.DOWNLEFT:
						ui( ).setFlipFacing( true );
						break;
					case Action.UPRIGHT:
					case Action.RIGHT:
					case Action.DOWNRIGHT:
						ui( ).setFlipFacing( false );
						break;
					}
					return advance;
				}
			}
			else if ( input.code == WEAPON_KEY )
			{
				if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
				{
					ret = player.getMysticAction( );
					try
					{
						if ( ret != null )
						{
							ret.setPerformer( player );
							if ( ret.canPerform( player ) )
								ui( ).setTargets( ret );
							else
							{
								level.addMessage( ret.getInvalidationMessage( ) );
								throw new ActionCancelException( );
							}
							Debug.exitMethod( ret );
							return ret;
						}
					}
					catch ( ActionCancelException ace )
					{
						ui( ).addMessage(
								new Message( "- Cancelled", player.getPosition( ) ) );
						appFrame.refresh( );
						// si.cls();
						// refresh();
						ret = null;
					}
				}
				else
				{
					ret = target;
					try
					{
						ret.setPerformer( player );
						if ( ret.canPerform( player ) )
							ui( ).setTargets( ret );
						else
						{
							level.addMessage( ret.getInvalidationMessage( ) );
							throw new ActionCancelException( );
						}
						Debug.exitMethod( ret );
						return ret;
					}
					catch ( ActionCancelException ace )
					{
						ui( ).addMessage(
								new Message( "- Cancelled", player.getPosition( ) ) );
						appFrame.refresh( );
						ret = null;
					}

				}
			}
			else
			{
				ret = getRelatedAction( input.code );
				/*
				 * if (ret == target){ defaultTarget = player.getNearestMonsterPosition();
				 * }
				 */
				try
				{
					if ( ret != null )
					{
						ret.setPerformer( player );
						if ( ret.canPerform( player ) )
							ui( ).setTargets( ret );
						else
						{
							level.addMessage( ret.getInvalidationMessage( ) );
							throw new ActionCancelException( );
						}
						Debug.exitMethod( ret );
						return ret;
					}

				}
				catch ( ActionCancelException ace )
				{
					// si.cls();
					// refresh();
					ui( ).addMessage(
							new Message( "- Cancelled", player.getPosition( ) ) );
					ret = null;
				}
				// refresh();
			}
		}
		Debug.exitMethod( "null" );
		return null;
	}

	public GFXUserInterface ui( )
	{
		return (GFXUserInterface) getUI( );
	}
	private int defineQuadrant( int x, int y )
	{
		if ( x > x2 )
			if ( y > y2 )
				return 9;
			else if ( y > y1 )
				return 6;
			else
				return 3;
		else if ( x > x1 )
			if ( y > y2 )
				return 8;
			else if ( y > y1 )
				return 5;
			else
				return 2;
		else if ( y > y2 )
			return 7;
		else if ( y > y1 )
			return 4;
		else
			return 1;
	}

	private void translatePosition( int x, int y )
	{
		int bigx = (int) Math.ceil( x / 32.0 );
		int bigy = (int) Math.ceil( y / 32.0 );
		tempRel.x = bigx - ui( ).PC_POS.x - 1;
		tempRel.y = bigy - ui( ).PC_POS.y - 1;
		mousePosition = Position.add( player.getPosition( ), tempRel );
	}

	private boolean updateCursorPosition( int x, int y )
	{
		int bigx = (int) Math.ceil( x / 32.0 );
		int bigy = (int) Math.ceil( y / 32.0 );
		tempRel.x = bigx - ui( ).PC_POS.x - 1;
		tempRel.y = bigy - ui( ).PC_POS.y - 1;
		if ( tempCursorPosition != null )
		{
			if ( tempCursorPosition.x == player.getPosition( ).x + bigx - ui( ).PC_POS.x
					- 1
					&& tempCursorPosition.y == player.getPosition( ).y + bigy
							- ui( ).PC_POS.y - 1 )
			{
				return false;
			}
			tempCursorPosition.x = player.getPosition( ).x + bigx - ui( ).PC_POS.x - 1;
			tempCursorPosition.y = player.getPosition( ).y + bigy - ui( ).PC_POS.y - 1;
		}
		if ( tempCursorPositionScr != null )
		{
			tempCursorPositionScr.x = tempRel.x;
			tempCursorPositionScr.y = tempRel.y;
		}
		return true;
	}

}
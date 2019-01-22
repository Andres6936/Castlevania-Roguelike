package co.castle.ui.consoleUI;

import java.util.Properties;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.actor.Message;
import co.castle.ai.ActionSelector;
import co.castle.game.Cheat;
import co.castle.monster.Monster;
import co.castle.npc.NPC;
import co.castle.player.Player;
import co.castle.ui.ActionCancelException;
import co.castle.ui.UISelector;
import co.castle.ui.UserAction;
import sz.csi.CharKey;
import sz.csi.ConsoleSystemInterface;
import sz.util.Debug;
import sz.util.Position;

public class ConsoleUISelector extends UISelector
{
	private ConsoleSystemInterface si;

	public ActionSelector derive( )
	{
		return null;
	}

	public String getID( )
	{
		return "UI";
	}

	public void init(	ConsoleSystemInterface csi, UserAction[ ] gameActions,
						Action advance, Action target, Action attack,
						ConsoleUserInterface ui, Properties keyBindings )
	{
		super.init( gameActions, advance, target, attack, ui, keyBindings );
		this.si = csi;
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
			input = si.inkey( );
			ret = ui( ).selectCommand( input );
			if ( ret != null )
				return ret;
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
			if ( isArrow( input ) )
			{
				int direction = toIntDirection( input );
				Monster vMonster = player.getLevel( )
						.getMonsterAt( Position.add( player.getPosition( ),
								Action.directionToVariation( direction ) ) );
				if ( vMonster != null
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
						si.refresh( );
					}
				}
				else
				{
					advance.setDirection( direction );
					Debug.exitMethod( advance );
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
						ret = null;
					}
				}
			}
			else
			{
				ret = getRelatedAction( input.code );
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
					ui( ).addMessage( new Message( "Cancelled", player.getPosition( ) ) );
					ret = null;
				}
				// refresh();
			}
		}
		Debug.exitMethod( "null" );
		return null;
	}

	public ConsoleUserInterface ui( )
	{
		return (ConsoleUserInterface) getUI( );
	}

}

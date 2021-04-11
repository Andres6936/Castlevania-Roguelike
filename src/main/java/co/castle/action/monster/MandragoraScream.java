package co.castle.action.monster;

import java.util.Vector;

import co.castle.action.Action;
import co.castle.game.SFXManager;
import co.castle.monster.Monster;
import co.castle.monster.VMonster;
import co.castle.npc.Hostage;
import co.castle.npc.NPC;
import co.castle.player.Damage;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class MandragoraScream extends Action
{
	public final static int SCREAM_DAMAGE = 20;
	public final static int SCREAM_RANGE = 8;
	public final static String SCREAM_WAV = "wav/scream.wav";

	@Override
	public void execute( )
	{
		if ( performer.getFlag( "MANDRAGORA_PULLED" ) )
		{
			SFXManager.play( SCREAM_WAV );
			performer.getLevel( ).addMessage(
					"* The Mandragora emits an earth-shattering scream!!! *" );
			performer.getLevel( )
					.addEffect( EffectFactory.getSingleton( ).createLocatedEffect(
							performer.getPosition( ), "SFX_MANDRAGORA_SCREAM" ) );

			VMonster monsters = performer.getLevel( ).getMonsters( );
			Vector <Monster> removables = new Vector <Monster>( );
			for ( int i = 0; i < monsters.size( ); i++ )
			{
				Monster monster = monsters.elementAt( i );
				if ( monster == performer )
					continue;
				if ( Position.flatDistance( performer.getPosition( ),
						monster.getPosition( ) ) < SCREAM_RANGE )
				{
					if ( monster instanceof NPC || monster instanceof Hostage )
					{

					}
					else
					{
						StringBuffer messages = new StringBuffer(
								"The " + monster.getDescription( )
										+ " hears the mandragora scream!" );
						// targetMonster.damage(player.getWhipLevel());
						monster.damage( messages, SCREAM_DAMAGE );
						if ( monster.wasSeen( ) )
						{
							performer.getLevel( ).addMessage( messages.toString( ) );
						}
						if ( monster.isDead( ) )
						{
							if ( monster.wasSeen( ) )
							{
								performer.getLevel( )
										.addMessage( "The " + monster.getDescription( )
												+ " explodes in pain!" );
							}
							removables.add( monster );
						}
					}
				}
			}
			monsters.removeAll( removables );

			if ( Position.flatDistance( performer.getPosition( ),
					performer.getLevel( ).getPlayer( ).getPosition( ) ) < SCREAM_RANGE )
			{
				StringBuffer messages = new StringBuffer(
						"You hear the mandragora scream!" );
				performer.getLevel( ).getPlayer( ).damage( messages, (Monster) performer,
						new Damage( SCREAM_DAMAGE, true ) );
				performer.getLevel( ).addMessage( messages.toString( ) );
			}

			performer.die( );

		}
		else
		{
			performer.getLevel( )
					.addMessage( "* A skeleton pulls out a mandragora root!!! *" );
			performer.setFlag( "MANDRAGORA_PULLED", true );
		}

	}

	@Override
	public String getID( )
	{
		return "MANDRAGORA_SCREAM";
	}
}

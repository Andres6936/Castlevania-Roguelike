package co.castle.npc;

import co.castle.ai.ActionSelector;
import co.castle.ai.SelectorFactory;
import co.castle.ai.npc.VillagerAI;
import co.castle.monster.Monster;
import co.castle.monster.MonsterDefinition;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import sz.csi.textcomponents.ListItem;

//public class NPC extends Actor{
public class NPC extends Monster
{
	private String defID;
	private transient NPCDefinition definition;
	private String npcID;

	// private final static MonsterDefinition NPC_MONSTER_DEFINITION = new
	// MonsterDefinition("NPC", "NPC", "VOID", "NULL_SELECTOR", 0, 2, 0, 5, 0,
	// false, false, true, false, 0, 0, 0, 0);

	private ActionSelector selector;
	private String talkMessage = null;

	public final static MonsterDefinition NPC_MONSTER_DEFINITION = new MonsterDefinition(
			"NPC" );

	static
	{
		NPC_MONSTER_DEFINITION.setDescription( "Innocent Being" );
		NPC_MONSTER_DEFINITION.setAppearance(
				AppearanceFactory.getAppearanceFactory().getAppearance("VOID"));
		NPC_MONSTER_DEFINITION.setDefaultSelector(
				selectorFactory.getSelector("NULL_SELECTOR"));
		NPC_MONSTER_DEFINITION.setScore(-100);
		NPC_MONSTER_DEFINITION.setMaxHits( 2 );
		NPC_MONSTER_DEFINITION.setAttack( 0 );
		NPC_MONSTER_DEFINITION.setSightRange( 5 );
		NPC_MONSTER_DEFINITION.setBloodContent( 30 );
	}

	public NPC( NPCDefinition def )
	{
		super( NPC_MONSTER_DEFINITION );
		definition = def;
		defID = def.getID( );
		npcID = def.getID( );
		selector = getNDefinition( ).getDefaultSelector( ).derive( );
		hits = def.getHits( );
	}

	@Override
	public void damage( StringBuffer buff, int dam )
	{
		try
		{
			( (VillagerAI) getSelector( ) ).setOnDanger( true );
			if ( getHits( ) > 1 )
				( (VillagerAI) getSelector( ) ).setAttackPlayer( true );
			level.signal( getPosition( ), 8, "EVT_MURDERER" );
		}
		catch ( ClassCastException cce )
		{

		}
		super.damage( buff, dam );
	}

	public String getAngryMessage( )
	{
		return getNDefinition( ).getAngryMessage( );
	}

	public Appearance getAppearance( )
	{

		return getNDefinition( ).getAppearance( );
	}

	public int getAttack( )
	{
		return getNDefinition( ).getAttack( );
	}

	public String getDangerMessage( )
	{
		return getNDefinition( ).getDangerMessage( );
	}

	public String getDescription( )
	{
		return getNDefinition( ).getDescription( );
	}

	public String getNPCID( )
	{
		return npcID;
	}

	public ActionSelector getSelector( )
	{
		return selector;
	}

	public ListItem getSightListItem( )
	{
		return getNDefinition( ).getSightListItem( );
	}

	public String getTalkMessage( )
	{
		if ( talkMessage == null )
			return getNDefinition( ).getTalkMessage( );
		else
			return talkMessage;
	}

	public boolean isHostile( )
	{
		return ( (VillagerAI) getSelector( ) ).isHostile( );
	}

	public boolean isPriest( )
	{
		return getNDefinition( ).isPriest( );
	}
	public void message( String mess )
	{
		try
		{
			if ( mess.equals( "ATTACK_PLAYER" ) )
				( (VillagerAI) getSelector( ) ).setAttackPlayer( true );
			else if ( mess.equals( "EVT_MURDERER" ) )
			{
				if ( getHits( ) > 1 )
					( (VillagerAI) getSelector( ) ).setAttackPlayer( true );
				else
					( (VillagerAI) getSelector( ) ).setOnDanger( true );
			}
		}
		catch ( ClassCastException cce )
		{

		}
	}

	public void setTalkMessage( String message )
	{
		talkMessage = message;
	}

	private NPCDefinition getNDefinition( )
	{
		if ( definition == null )
		{
			definition = NPCFactory.getFactory( ).getDefinition( defID );
		}
		return definition;
	}
}

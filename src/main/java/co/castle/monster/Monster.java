package co.castle.monster;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.monster.boss.DraculaAI;
import co.castle.feature.FeatureFactory;
import co.castle.item.Item;
import co.castle.item.ItemDefinition;
import co.castle.level.Emerger;
import co.castle.level.EmergerAI;
import co.castle.npc.NPC;
import co.castle.player.Consts;
import co.castle.player.Player;
import co.castle.ui.Appearance;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;
import sz.util.Util;

public class Monster extends Actor implements Cloneable
{
	private String defID;
	// Attributes
	private transient MonsterDefinition definition;

	private Monster enemy;
	private String featurePrize;
	private int maxHits;
	private boolean visible = true;

	private boolean wasSeen = false;

	protected int hits;

	public Monster( MonsterDefinition md )
	{
		definition = md;
		defID = md.getID( );
		// selector = md.getDefaultSelector();
		selector = md.getDefaultSelector( ).derive( );

		hits = md.getMaxHits( );
		maxHits = md.getMaxHits( );
	}

	public void act( )
	{
		if ( hasCounter( Consts.C_MONSTER_FREEZE )
				|| hasCounter( Consts.C_MONSTER_SLEEP ) )
		{
			setNextTime( 50 );
			updateStatus( );
			return;
		}
		super.act( );
		wasSeen = false;
	}

	public boolean canSwim( )
	{
		return getDefinition( ).isCanSwim( );
	}

	public Object clone( )
	{
		try
		{
			return super.clone( );
		}
		catch ( Exception x )
		{
			return null;
		}

	}

	public void damage( StringBuffer message, int dam )
	{
		if ( getSelector( ) instanceof DraculaAI )
		{
			( (DraculaAI) getSelector( ) ).setOnBattle( true );
		}
		if ( Util.chance( getEvadeChance( ) ) )
		{
			if ( wasSeen( ) )
				level.addMessage( "The " + getDescription( ) + " " + getEvadeMessage( ) );
			return;
		}
		if ( hasCounter( Consts.C_MONSTER_FREEZE ) )
			dam *= 2;
		message.append( " (" + dam + ")" );
		hits -= dam;

		if ( getDefinition( ).getBloodContent( ) > 0 )
		{
			if ( level.getPlayer( ).hasCounter( Consts.C_BLOOD_THIRST )
					&& Position.flatDistance( getPosition( ),
							level.getPlayer( ).getPosition( ) ) < 3 )
			{
				int recover = (int) Math.ceil( getDefinition( ).getBloodContent( ) / 30 );
				level.addMessage(
						"You drink some of the " + getDefinition( ).getDescription( )
								+ " blood! (+" + recover + ")" );
				level.getPlayer( ).recoverHits( recover );
			}
			if ( Util.chance( 40 ) )
			{
				getLevel( ).addBlood( getPosition( ), Util.rand( 0, 1 ) );
			}
		}
		if ( level.getPlayer( ).getFlag( "HEALTH_REGENERATION" ) && Util.chance( 30 ) )
		{
			level.getPlayer( ).recoverHits( 1 );
		}

		if ( isDead( ) )
		{
			if ( this == level.getBoss( ) )
			{
				// if (!level.isWalkable(getPosition())){
				// level.addMessage("You get a castle key!");
				level.getPlayer( ).addKeys( 1 );
				/*
				 * } else setFeaturePrize("KEY");
				 */
				// level.addEffect(new DoubleSplashEffect(getPosition(),
				// "O....,,..,.,.,,......", Appearance.RED, ".,,,,..,,.,.,..,,,,,,",
				// Appearance.WHITE));
				level.addEffect( EffectFactory.getSingleton( )
						.createLocatedEffect( getPosition( ), "SFX_BOSS_DEATH" ) );
				level.addMessage( "The whole level trembles with holy energy!" );
				level.removeBoss( );
				level.getPlayer( ).addHistoricEvent( "vanquished the "
						+ this.getDescription( ) + " on the " + level.getDescription( ) );
				level.anihilate( );
				level.removeRespawner( );
				// level.getPlayer().addSoulPower(Util.rand(10,20)*level.getLevelNumber());
			}
			else
			{
				level.getPlayer( ).increaseMUpgradeCount( );
				setPrize( );
			}
			if ( featurePrize != null && !level.getMapCell( getPosition( ) ).isSolid( ) )
				if ( level.getMapCell( getPosition( ) ).isShallowWater( ) )
				{
					level.addMessage( "A "
							+ FeatureFactory.getFactory( ).getDescriptionForID(
									featurePrize )
							+ " falls into the "
							+ level.getMapCell( getPosition( ) ).getDescription( ) );
					level.addFeature( featurePrize, getPosition( ) );
				}
				else
					level.addFeature( featurePrize, getPosition( ) );

			if ( getDefinition( ).isBleedable( ) )
			{
				Position runner = new Position( -1, -1, getPosition( ).z );
				for ( runner.x = -1; runner.x <= 1; runner.x++ )
					for ( runner.y = -1; runner.y <= 1; runner.y++ )
						if ( Util.chance( 70 ) )
							getLevel( ).addBlood( Position.add( getPosition( ), runner ),
									Util.rand( 0, 1 ) );
			}

			die( );
			level.getPlayer( ).addScore( getDefinition( ).getScore( ) );
			level.getPlayer( ).addXP( getDefinition( ).getScore( ) );
			// level.getPlayer().addSoulPower(Util.rand(0,3));
			level.getPlayer( ).getGameSessionInfo( ).addDeath( getDefinition( ) );
		}
	}

	public void damageWithWeapon( StringBuffer message, int dam )
	{
		Item wep = level.getPlayer( ).getWeapon( );
		if ( wep != null )
			level.getPlayer( )
					.increaseWeaponSkill( wep.getDefinition( ).getWeaponCategory( ) );
		else
			level.getPlayer( ).increaseWeaponSkill( ItemDefinition.CAT_UNARMED );
		damage( message, dam );
	}

	public void die( )
	{
		super.die( );
		level.removeMonster( this );
		if ( getAutorespawncount( ) > 0 )
		{
			Emerger em = new Emerger(
					MonsterFactory.getFactory( )
							.buildMonster( getDefinition( ).getID( ) ),
					getPosition( ), getAutorespawncount( ) );
			level.addActor( em );
			em.setSelector( new EmergerAI( ) );
			em.setLevel( getLevel( ) );
		}
	}

	public void freeze( int cont )
	{
		setCounter( Consts.C_MONSTER_FREEZE, cont );
	}

	public Appearance getAppearance( )
	{
		return getDefinition( ).getAppearance( );
	}

	public int getAttack( )
	{
		return getDefinition( ).getAttack( );
	}

	/*
	 * public boolean playerInRow(){ Position pp = level.getPlayer().getPosition();
	 * /*if (!playerInRange()) return false; //Debug.say("pp"+pp);
	 * //Debug.say(getPosition()); if (pp.x == getPosition().x || pp.y ==
	 * getPosition().y) return true; if (pp.x - getPosition().x == pp.y -
	 * getPosition().y) return true; return false; }
	 */

	public int getAttackCost( )
	{
		return getDefinition( ).getAttackCost( );
	}

	public int getAutorespawncount( )
	{
		return getDefinition( ).getAutorespawnCount( );
	}

	public String getDescription( )
	{
		// This may be flavored with specific monster daya

		return getDefinition( ).getDescription( )
				+ ( hasCounter( Consts.C_MONSTER_CHARM ) ? " C " : "" );
	}

	public Monster getEnemy( )
	{
		return enemy;
	}

	public int getEvadeChance( )
	{
		return getDefinition( ).getEvadeChance( );
	}

	public String getEvadeMessage( )
	{
		return getDefinition( ).getEvadeMessage( );
	}

	public String getFeaturePrize( )
	{
		return featurePrize;
	}

	public int getFreezeResistance( )
	{
		return 0; // placeholder
	}

	public int getHits( )
	{
		return hits;
	}

	public String getID( )
	{
		return getDefinition( ).getID( );
	}

	public int getLeaping( )
	{
		return getDefinition( ).getLeaping( );
	}

	public String getLongDescription( )
	{
		return getDefinition( ).getLongDescription( );

	}

	/*
	 * public ActionSelector getSelector(){ return selector; //return
	 * definition.getDefaultSelector(); }
	 */

	public int getMaxHits( )
	{
		return getDefinition( ).getMaxHits( );
	}

	public Monster getNearestMonster( )
	{
		VMonster monsters = level.getMonsters( );
		Monster nearMonster = null;
		int minDist = 150;
		for ( int i = 0; i < monsters.size( ); i++ )
		{
			Monster monster = (Monster) monsters.elementAt( i );
			int distance = Position.flatDistance( getPosition( ),
					monster.getPosition( ) );
			if ( monster != this && distance < minDist )
			{
				minDist = distance;
				nearMonster = monster;
			}
		}
		return nearMonster;
	}

	public int getScore( )
	{
		return getDefinition( ).getScore( );

	}

	public int getWalkCost( )
	{
		return getDefinition( ).getWalkCost( );
	}

	public String getWavOnHit( )
	{
		return getDefinition( ).getWavOnHit( );
	}

	/*
	 * public ListItem getSightListItem(){ return definition.getSightListItem(); }
	 */

	public void increaseHits( int i )
	{
		hits += i;
	}

	public boolean isDead( )
	{
		return hits <= 0;
	}

	public boolean isEthereal( )
	{
		return getDefinition( ).isEthereal( );
	}

	public boolean isFlying( )
	{
		return getDefinition( ).isCanFly( );
	}

	public boolean isInWater( )
	{
		if ( level.getMapCell( getPosition( ) ) != null )
			return level.getMapCell( getPosition( ) ).isShallowWater( );
		else
			return false;
	}

	public boolean isUndead( )
	{
		return getDefinition( ).isUndead( );
	}

	public boolean isVisible( )
	{
		return visible;
	}

	public void recoverHits( )
	{
		hits = maxHits;
	}

	public boolean seesPlayer( )
	{
		if ( wasSeen( ) )
		{
			Line sight = new Line( getPosition( ), level.getPlayer( ).getPosition( ) );
			Position point = sight.next( );
			while ( !point.equals( level.getPlayer( ).getPosition( ) ) )
			{
				if ( level.getMapCell( point ) != null
						&& level.getMapCell( point ).isOpaque( ) )
				{
					return false;
				}
				point = sight.next( );
				if ( !level.isValidCoordinate( point ) )
					return false;
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public void setEnemy( Monster enemy )
	{
		this.enemy = enemy;
	}

	public void setFeaturePrize( String value )
	{
		featurePrize = value;
	}

	public void setVisible( boolean value )
	{
		visible = value;
	}

	public void setWasSeen( boolean value )
	{
		wasSeen = true;
	}

	/** Returns the direction in which the nearest monster is seen */
	public int stareMonster( )
	{
		Monster nearest = getNearestMonster( );
		if ( nearest == null )
			return -1;
		else
			return stareMonster( getNearestMonster( ) );
	}

	public int stareMonster( Monster who )
	{
		if ( who.getPosition( ).z != getPosition( ).z )
			return -1;
		if ( Position.flatDistance( who.getPosition( ),
				getPosition( ) ) <= getDefinition( ).getSightRange( ) )
		{
			Position pp = who.getPosition( );
			if ( pp.x == getPosition( ).x )
			{
				if ( pp.y > getPosition( ).y )
				{
					return Action.DOWN;
				}
				else
				{
					return Action.UP;
				}
			}
			else if ( pp.y == getPosition( ).y )
			{
				if ( pp.x > getPosition( ).x )
				{
					return Action.RIGHT;
				}
				else
				{
					return Action.LEFT;
				}
			}
			else if ( pp.x < getPosition( ).x )
			{
				if ( pp.y > getPosition( ).y )
					return Action.DOWNLEFT;
				else
					return Action.UPLEFT;
			}
			else
			{
				if ( pp.y > getPosition( ).y )
					return Action.DOWNRIGHT;
				else
					return Action.UPRIGHT;
			}
		}
		return -1;
	}

	public int starePlayer( )
	{
		/** returns the direction in which the player is seen */
		if ( level.getPlayer( ) == null || level.getPlayer( ).isInvisible( )
				|| level.getPlayer( ).getPosition( ).z != getPosition( ).z )
			return -1;
		if ( Position.flatDistance( level.getPlayer( ).getPosition( ),
				getPosition( ) ) <= getDefinition( ).getSightRange( ) )
		{
			Position pp = level.getPlayer( ).getPosition( );
			if ( pp.x == getPosition( ).x )
			{
				if ( pp.y > getPosition( ).y )
				{
					return Action.DOWN;
				}
				else
				{
					return Action.UP;
				}
			}
			else if ( pp.y == getPosition( ).y )
			{
				if ( pp.x > getPosition( ).x )
				{
					return Action.RIGHT;
				}
				else
				{
					return Action.LEFT;
				}
			}
			else if ( pp.x < getPosition( ).x )
			{
				if ( pp.y > getPosition( ).y )
					return Action.DOWNLEFT;
				else
					return Action.UPLEFT;
			}
			else
			{
				if ( pp.y > getPosition( ).y )
					return Action.DOWNRIGHT;
				else
					return Action.UPRIGHT;
			}
		}
		return -1;
	}

	public boolean tryHit( Monster attacker )
	{
		setEnemy( attacker );
		int evasion = getEvadeChance( );
		// level.addMessage("Evasion "+evasion);
		if ( hasCounter( "SLEEP" ) )
			evasion = 0;
		// level.addMessage("Evasion "+evasion);
		// see if evades it
		int weaponAttack = attacker.getDefinition( ).getAttack( );
		if ( Util.chance( evasion ) )
		{
			level.addMessage( "The " + getDescription( ) + " dodges the "
					+ attacker.getDescription( ) + " attack!" );
			return false;
		}
		else
		{
			if ( hasCounter( Consts.C_MONSTER_SLEEP ) )
			{
				level.addMessage( "The " + attacker.getDescription( ) + " wakes up the "
						+ getDescription( ) + "!" );
				setCounter( Consts.C_MONSTER_SLEEP, 0 );
			}
			int baseDamage = weaponAttack;
			double damageMod = 1;

			StringBuffer hitDesc = new StringBuffer( );
			int damage = (int) ( baseDamage * damageMod );
			double percent = (double) damage / (double) getDefinition( ).getMaxHits( );
			if ( percent > 1.0d )
				hitDesc.append( "The " + attacker.getDescription( ) + " whacks the "
						+ getDescription( ) + " apart!!" );
			else if ( percent > 0.7d )
				hitDesc.append( "The " + attacker.getDescription( ) + " smashes the "
						+ getDescription( ) + "!" );
			else if ( percent > 0.5d )
				hitDesc.append( "The " + attacker.getDescription( )
						+ " grievously hits the " + getDescription( ) + "!" );
			else if ( percent > 0.3d )
				hitDesc.append( "The " + attacker.getDescription( ) + " hits the "
						+ getDescription( ) + "." );
			else
				hitDesc.append( "The " + attacker.getDescription( )
						+ " barely scratches the " + getDescription( ) + "..." );

			damage( hitDesc, (int) ( baseDamage * damageMod ) );
			level.addMessage( hitDesc.toString( ) );
			return true;
		}
	}

	public boolean tryMagicHit(	Player attacker, int magicalDamage, int magicalHit,
								boolean showMsg, String attackDesc,
								boolean isWeaponAttack, Position attackOrigin )
	{
		int hitChance = 100 - getEvadeChance( );
		hitChance = (int) Math.round( ( hitChance + magicalHit ) / 2.0d );
		int penalty = 0;
		if ( isWeaponAttack )
		{
			penalty = (int) ( Position.distance( getPosition( ), attackOrigin ) / 4 );
			if ( attacker.getWeapon( ).isHarmsUndead( ) && isUndead( ) )
				magicalDamage *= 2;
			attacker.increaseWeaponSkill(
					attacker.getWeapon( ).getDefinition( ).getWeaponCategory( ) );

		}

		magicalDamage -= penalty;
		int evasion = 100 - hitChance;

		if ( evasion < 0 )
			evasion = 0;

		if ( hasCounter( Consts.C_MONSTER_CHARM ) )
			setCounter( Consts.C_MONSTER_CHARM, 0 );
		if ( hasCounter( "SLEEP" ) )
			evasion = 0;
		// see if evades it
		if ( Util.chance( evasion ) )
		{
			if ( showMsg )
				level.addMessage(
						"The " + getDescription( ) + " evades the " + attackDesc + "!" );
			// moveRandomly();
			return false;
		}
		else
		{
			if ( hasCounter( "SLEEP" ) )
			{
				level.addMessage( "You wake up the " + getDescription( ) + "!" );
				setCounter( "SLEEP", 0 );
			}
			int baseDamage = magicalDamage;
			double damageMod = 1;
			StringBuffer hitDesc = new StringBuffer( );
			int damage = (int) ( baseDamage * damageMod );
			double percent = (double) damage / (double) getDefinition( ).getMaxHits( );
			if ( percent > 1.0d )
				hitDesc.append( "The " + attackDesc + " whacks the " + getDescription( )
						+ " apart!!" );
			else if ( percent > 0.7d )
				hitDesc.append(
						"The " + attackDesc + " smashes the " + getDescription( ) + "!" );
			else if ( percent > 0.5d )
				hitDesc.append( "The " + attackDesc + " grievously hits the "
						+ getDescription( ) + "!" );
			else if ( percent > 0.3d )
				hitDesc.append(
						"The " + attackDesc + " hits the " + getDescription( ) + "." );
			else
				hitDesc.append( "The " + attackDesc + " barely scratches the "
						+ getDescription( ) + "..." );

			damage( hitDesc, (int) ( baseDamage * damageMod ) );
			if ( showMsg )
				level.addMessage( hitDesc.toString( ) );
			// attacker.setLastWalkingDirection(Action.SELF);
			return true;
		}
	}

	public boolean waitsPlayer( )
	{
		return false;
	}

	public boolean wasSeen( )
	{
		return wasSeen;
	}

	private MonsterDefinition getDefinition( )
	{
		if ( definition == null )
		{
			if ( this instanceof NPC )
				definition = NPC.NPC_MONSTER_DEFINITION;
			else
				definition = MonsterFactory.getFactory( ).getDefinition( defID );
		}
		return definition;
	}

	private void setPrize( )
	{
		Player p = level.getPlayer( );
		String[ ] prizeList = null;

		if ( p.deservesMUpgrade( ) )
		{
			setFeaturePrize( "MUPGRADE" );
			return;
		}

		if ( p.deservesUpgrade( ) && Util.chance( 50 ) )
			setFeaturePrize( "UPGRADE" );

		if ( Util.chance( 60 ) )
			return;

		if ( p.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
		{
			if ( Util.chance( 20 ) )
			{
				// Will get a mystic weapon
				if ( p.getFlag( "MYSTIC_CRYSTAL" ) && Util.chance( 20 ) )
					prizeList = new String[ ]
					{ "CRYSTALWP" };
				else if ( p.getFlag( "MYSTIC_FIST" ) && Util.chance( 20 ) )
					prizeList = new String[ ]
					{ "FISTWP" };
				else if ( p.getFlag( "MYSTIC_CROSS" ) && Util.chance( 20 ) )
					prizeList = new String[ ]
					{ "CROSSWP" };
				else if ( p.getFlag( "MYSTIC_STOPWATCH" ) && Util.chance( 20 ) )
					prizeList = new String[ ]
					{ "STOPWATCHWP" };
				else if ( p.getFlag( "MYSTIC_HOLY_WATER" ) && Util.chance( 20 ) )
					prizeList = new String[ ]
					{ "HOLYWP" };
				else if ( p.getFlag( "MYSTIC_HOLY_BIBLE" ) && Util.chance( 20 ) )
					prizeList = new String[ ]
					{ "BIBLEWP" };
				else
					prizeList = new String[ ]
					{ "AXEWP", "DAGGERWP" };
			}
			else if ( Util.chance( 50 ) )
				if ( Util.chance( 40 ) )
					if ( Util.chance( 10 ) )
						if ( Util.chance( 10 ) )
							if ( Util.chance( 10 ) )
								if ( Util.chance( 10 ) )
									prizeList = new String[ ]
									{ "WHITE_MONEY_BAG" };
								else
									prizeList = new String[ ]
									{ "POT_ROAST" };
							else
								prizeList = new String[ ]
								{ "INVISIBILITY_POTION", "ROSARY", "BLUE_MONEY_BAG" };
						else
							prizeList = new String[ ]
							{ "RED_MONEY_BAG" };
					else
						prizeList = new String[ ]
						{ "BIGHEART" };
				else
					prizeList = new String[ ]
					{ "SMALLHEART", "COIN" };
		}
		else
		{
			if ( Util.chance( 50 ) )
				if ( Util.chance( 50 ) )
					if ( Util.chance( 10 ) )
						if ( Util.chance( 10 ) )
							if ( Util.chance( 10 ) )
								prizeList = new String[ ]
								{ "WHITE_MONEY_BAG" };
							else
								prizeList = new String[ ]
								{ "POT_ROAST" };
						else
							prizeList = new String[ ]
							{ "INVISIBILITY_POTION", "ROSARY", "BLUE_MONEY_BAG" };
					else
						prizeList = new String[ ]
						{ "RED_MONEY_BAG" };
				else
					prizeList = new String[ ]
					{ "BIGHEART" };
			else
				prizeList = new String[ ]
				{ "SMALLHEART", "COIN" };
		}

		if ( prizeList != null )
			setFeaturePrize( Util.randomElementOf( prizeList ) );
	}

}
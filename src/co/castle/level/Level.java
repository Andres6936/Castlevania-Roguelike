package co.castle.level;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import co.castle.actor.Actor;
import co.castle.actor.Message;
import co.castle.cuts.Unleasher;
import co.castle.feature.Feature;
import co.castle.feature.FeatureFactory;
import co.castle.feature.SmartFeature;
import co.castle.feature.SmartFeatureFactory;
import co.castle.feature.VFeatures;
import co.castle.game.Game;
import co.castle.item.Item;
import co.castle.levelgen.LevelMetaData;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.monster.Monster;
import co.castle.monster.MonsterFactory;
import co.castle.monster.VMonster;
import co.castle.npc.NPC;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.Effect;
import sz.fov.FOVMap;
import sz.util.Counter;
import sz.util.Debug;
import sz.util.Position;
import sz.util.SZQueue;
import sz.util.Util;

public class Level implements FOVMap, Serializable {
	private final Hashtable bloods = new Hashtable();
	private Monster boss;
	// private Position startPosition, endPosition;
	private String description;
	private Dispatcher dispatcher;
	private final Vector<Feature> doomedFeatures = new Vector<>();
	private MonsterSpawnInfo[] dwellersInfo;
	private final Hashtable<String, String> exitPositions = new Hashtable<>();
	private final Hashtable<String, Position> exits = new Hashtable<>();
	private final VFeatures features;
	private final Hashtable<String, Counter> frosts = new Hashtable<>();
	private final Hashtable<String, Counter> hashCounters = new Hashtable<>();
	private final Hashtable<String, Boolean> hashFlags = new Hashtable<>();
	private boolean haunted;
	private String ID;
	private MonsterSpawnInfo[] inhabitants;
	private boolean isCandled;
	private boolean isDay;

	private boolean isHostageSafe;
	private boolean isRutinary = true;

	private final Hashtable<String, Vector<Item>> items = new Hashtable<>();
	private int levelNumber;
	private final Position lightRunner = new Position(0, 0);
	private final Vector<Feature> lightSources = new Vector<>();
	private boolean[][][] lit;

	private Cell[][][] map;

	private String mapLocationKey;

	private SZQueue messagesneffects;
	private LevelMetaData metaData;

	private final VMonster monsters;

	private String musicKeyMorning;

	private String musicKeyNoon;

	private Respawner nightRespawner;

	private Player player;

	private boolean[][][] remembered;

	private Respawner respawner;
	// private int keyCounter;
	/*
	 * public void keyInminent(){ keyCounter = Util.rand(1,5); }
	 */

	private final Hashtable<String, SmartFeature> smartFeatures = new Hashtable<>();

	private Vector tempActors;

	// private VEffect effects;

	private final Position tempSeen = new Position(0, 0);

	private int timeCounter;

	private Unleasher[] unleashers = new Unleasher[]
			{};

	private boolean[][][] visible;

	Position tempFeaturePosition = new Position(0, 0);

	public final static int MORNING = 1, NOON = 2, AFTERNOON = 3, DUSK = 4, NIGHT = 5,
			DAWN = 6;

	public Level( )
	{
		monsters = new VMonster( 20 );
		features = new VFeatures( 20 );
		// effects = new VEffect(10);
		messagesneffects = new SZQueue( 50 );
	}

	public void addActor( Actor what )
	{
		Debug.doAssert( what != null, "Tried to add a null actor to the world" );
		dispatcher.addActor( what, true );
		if ( what instanceof Monster )
			monsters.addMonster( (Monster) what );
		what.setLevel( this );
	}

	public void addBlood( Position where, int bloodness )
	{
		if ( getBloodAt( where ) != null )
			bloods.remove( where );
		if ( !isValidCoordinate( where ) )
			return;
		if ( getMapCell( where ) == null || getMapCell( where ).isSolid( ) )
			return;
		if ( !getMapCell( where ).isWater( ) )
			bloods.put( where.toString( ), bloodness + "" );
	}

	public void addCounter( String id, int count )
	{
		hashCounters.put( id, new Counter( count ) );
	}

	public void addEffect( Effect what )
	{
		UserInterface.getUI( ).drawEffect( what );
	}

	public void addExit( Position where, String levelID )
	{
		exits.put( levelID, where );
		exitPositions.put( where.toString( ), levelID );
	}

	public void addFeature( Feature what )
	{
		features.addFeature( what );
		if ( what.getFaint( ) > 0 )
		{
			doomedFeatures.add( what );
		}
		if ( what.getLight( ) > 0 )
		{
			lightSources.add( what );
			lightAt( what.getPosition( ), what.getLight( ), true );
		}
	}

	public void addFeature( Feature what, boolean doom )
	{
		features.addFeature( what );
		if ( doom && what.getFaint( ) > 0 )
		{
			doomedFeatures.add( what );
		}

		if ( what.getLight( ) > 0 )
		{
			lightSources.add( what );
			lightAt( what.getPosition( ), what.getLight( ), true );
		}
	}

	public void addFeature( String featureID, Position location )
	{
		// Debug.say("Add"+featureID);
		Feature x = FeatureFactory.getFactory( ).buildFeature( featureID );
		x.setPosition( location.x, location.y, location.z );
		addFeature( x );
		if ( x.getFaint( ) > 0 )
		{
			doomedFeatures.add( x );
		}
		if ( x.getLight( ) > 0 )
		{
			lightSources.add( x );
			lightAt( x.getPosition( ), x.getLight( ), true );
		}
	}

	public void addFrost( Position where, int frostness )
	{
		if ( getFrostAt( where ) != 0 )
			frosts.remove( where );
		frosts.put( where.toString( ), new Counter( frostness ) );
	}

	public void addItem( Position where, Item what ) {
		Vector<Item> stack = items.get(where.toString());
		if (stack == null) {
			stack = new Vector<>(5);
			items.put(where.toString(), stack);
		}
		stack.add(what);
	}

	public void addMessage( Message what )
	{
		/*
		 * what = null; what.getText();
		 */
		UserInterface.getUI( ).addMessage( what );
		// dispatcher.addActor(what, true, what);
	}

	public void addMessage( String what )
	{
		addMessage( new Message( what, player.getPosition( ) ) );
	}

	public void addMessage( String what, Position where )
	{
		addMessage( new Message( what, where ) );
	}

	public void addMonster( Monster what )
	{
		monsters.addMonster( what );
		dispatcher.addActor( what );
		what.setLevel( this );
	}

	public void addSmartFeature( SmartFeature what )
	{

		smartFeatures.put( what.getPosition( ).toString( ), what );
		what.setLevel( this );
		dispatcher.addActor( what );
	}

	public void addSmartFeature( String featureID, Position location )
	{
		SmartFeature x = SmartFeatureFactory.getFactory( ).buildFeature( featureID );
		x.setPosition( location.x, location.y, location.z );
		addSmartFeature( x );
	}

	public void anihilate( ) {
		smartFeatures.clear();
		Vector<Feature> mounds = features.getAllOf("MOUND");
		for (int i = 0; i < mounds.size(); i++)
			features.removeFeature(mounds.elementAt(i));
		monsters.removeAll();
		dispatcher.removeAll();
		dispatcher.addActor(player);
	}

	public void anihilateMonsters( )
	{
		for ( int i = 0; i < monsters.size( ); i++ ) {
			if (boss != null && monsters.elementAt(i) == boss)
				continue;
			if (monsters.elementAt(i).getID().equals("IGOR"))
				continue;
			if (!(monsters.elementAt(i) instanceof NPC)) {
				dispatcher.removeActor(monsters.elementAt(i));
				monsters.remove(monsters.elementAt(i));
				i--;
			}
		}
		dispatcher.addActor( player ); // Added again as it is removed (not an NPC)
	}

	public boolean blockLOS( int x, int y )
	{
		if ( !isValidCoordinate( x, y ) )
			return true;
		if ( map[ player.getPosition( ).z ][ x ][ y ] == null )
			return false;
		else
			return map[ player.getPosition( ).z ][ x ][ y ].isOpaque( );
		// return map[player.getPosition().z][x][y] == null ||
		// map[player.getPosition().z][x][y].isSolid();
	}

	public boolean canFloatUpward( Position where )
	{
		if ( where.z != 0 ) {
			Position deep = new Position(where);
			deep.z--;
			return getMapCell(deep).isShallowWater();
		}
		return false;
	}

	public void checkUnleashers( Game game ) {
		for (Unleasher unleasher : unleashers) {
			if (unleasher.enabled())
				unleasher.unleash(this, game);
		}
	}

	public void createMonster( String who, Position where/* , String feat */ )
	{
		Monster x = MonsterFactory.getFactory( ).buildMonster( who );
		x.setPosition( where );
		/*
		 * if (!feat.equals("")) x.setFeaturePrize(feat);
		 */
		addMonster( x );
	}

	public void darken( )
	{
		for ( int x = 0; x < getWidth( ); x++ )
			for ( int y = 0; y < getHeight( ); y++ )
				darken( x, y );
	}

	public void darken( int x, int y )
	{
		if ( !isValidCoordinate( x, y ) )
			return;
		visible[ player.getPosition( ).z ][ x ][ y ] = false;
	}

	public void destroyCandles( ) {
		Vector<Feature> candles = features.getAllOf("CANDLE");
		for (int i = 0; i < candles.size(); i++)
			destroyFeature(candles.elementAt(i));
	}

	public void destroyFeature( Feature what )
	{
		// if (what.getLight()>0){
		if ( lightSources.contains( what ) )
		{
			lightSources.remove( what );
			lightAt( what.getPosition( ), what.getLight( ), false );
			for ( int i = 0; i < lightSources.size( ); i++ ) {
				Feature lightSource = lightSources.elementAt(i);
				if (Position.distance(what.getPosition(),
						lightSource.getPosition()) < 10) {
					lightAt(lightSource.getPosition(), lightSource.getLight(), true);
				}
			}
		}
		features.removeFeature( what );
	}

	public void disableTriggers( ) {
		for (Unleasher unleasher : unleashers) {
			unleasher.disable();
		}
	}

	public Actor getActorAt( Position x )
	{
		Vector actors = dispatcher.getActors( );
		for ( int i = 0; i < actors.size( ); i++ )
		{
			Actor a = (Actor) actors.elementAt( i );
			/*
			 * Debug.say(a); Debug.say(a.getPosition());
			 */
			if ( a.getPosition( ).equals( x ) )
				return (Actor) actors.elementAt( i );
		}
		return null;
	}

	public String getBloodAt( Position where )
	{
		return (String) bloods.get( where.toString( ) );
	}

	public Monster getBoss( )
	{
		return boss;
	}

	public Cell[ ][ ][ ] getCells( )
	{
		return map;
	}

	public Counter getCounter( String id )
	{
		return (Counter) hashCounters.get( id );
	}

	public int getDayTime( )
	{
		if ( isDay( ) )
		{
			switch ( (int) Math.round( timeCounter / ( Game.DAY_LENGTH / 3.0D ) ) ) {
				case 0:
					return AFTERNOON;
				case 1:
					return NOON;
				case 2:
				case 3:
					return MORNING;
			}
		}
		else
		{
			switch ( (int) Math.round( timeCounter / ( Game.DAY_LENGTH / 3.0D ) ) ) {
				case 0:
					return DAWN;
				case 1:
					return NIGHT;
				case 2:
				case 3:
					return DUSK;
			}
		}
		return -1;
	}

	public Position getDeepPosition( Position where )
	{
		Position ret = new Position( where );
		if ( !isValidCoordinate( where ) )
			return null;
		if ( map[ ret.z ][ ret.x ][ ret.y ] != null
				&& !map[ ret.z ][ ret.x ][ ret.y ].isEthereal( ) )
			return where;
		while ( ( map[ ret.z ][ ret.x ][ ret.y ] == null
				|| map[ ret.z ][ ret.x ][ ret.y ].isEthereal( ) )
				&& ret.z < getDepth( ) - 1 )
		{
			if ( map[ ret.z + 1 ][ ret.x ][ ret.y ] != null
					&& !map[ ret.z + 1 ][ ret.x ][ ret.y ].isEthereal( ) )
			{
				ret.z++;
				return ret;
			}
			ret.z++;
		}
		return null;
	}

	/*
	 * public java.util.Vector getMonsters(){ return monsters; }
	 */

	public int getDepth( )
	{
		return map.length;
	}

	public int getDepthFromPlayer( int x, int y )
	{
		int ret = 0;
		int zrunner = player.getPosition( ).z;
		while ( map[ zrunner ][ x ][ y ] == null
				|| map[ zrunner ][ x ][ y ].getID( ).equals( "AIR" ) )
		{
			ret++;
			zrunner--;
			if ( zrunner == -1 )
				break;
		}
		return ret;
	}

	public String getDescription( )
	{
		return description;
	}

	public Dispatcher getDispatcher( )
	{
		Debug.enterMethod( this, "getDispatcher" );
		Debug.exitMethod( dispatcher );
		return dispatcher;

	}

	public MonsterSpawnInfo[ ] getDwellersInfo( )
	{
		return dwellersInfo;
	}

	public Position getExitFor( String levelID ) {
		return exits.get(levelID);
	}

	public String getExitOn( Position pos ) {
		return exitPositions.get(pos.toString());
	}

	public Feature getFeatureAt( int x, int y, int z )
	{
		tempFeaturePosition.x = x;
		tempFeaturePosition.y = y;
		tempFeaturePosition.z = z;
		return features.getFeatureAt( tempFeaturePosition );
	}

	public Feature getFeatureAt( Position x )
	{
		return features.getFeatureAt( x );
	}

	public Feature[ ] getFeaturesAt( Position x )
	{
		return features.getFeaturesAt( x );
	}

	public boolean getFlag( String flagID ) {
		Boolean flag = (Boolean) hashFlags.get(flagID);
		return flag != null && flag;
	}

	public int getFrostAt( Position where )
	{
		Counter x = (Counter) frosts.get( where.toString( ) );
		if ( x != null )
			return 1;
		else
			return 0;
	}

	public int getHeight( ) {
		return map[0][0].length;

	}

	public String getID() {
		return ID;
	}

	public Vector<Item> getItemsAt(Position where) {
		return items.get(where.toString());
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public Cell getMapCell(int x, int y, int z)
	{
		if ( z < map.length && x < map[ 0 ].length && y < map[ 0 ][ 0 ].length && x >= 0
				&& y >= 0 && z >= 0 )
			return map[ z ][ x ][ y ];
		else
			return null;
	}

	public Cell getMapCell( Position where )
	{
		return getMapCell( where.x, where.y, where.z );
	}

	/*
	 * public void setPositions(Position start, Position end) { startPosition =
	 * start; endPosition = end; }
	 */

	public String getMapLocationKey( )
	{
		return mapLocationKey;
	}

	public Cell[ ][ ] getMemoryCellsAround( int x, int y, int z, int xspan, int yspan )
	{
		int xstart = x - xspan;
		int ystart = y - yspan;
		int xend = x + xspan;
		int yend = y + yspan;
		Cell[ ][ ] ret = new Cell[ 2 * xspan + 1 ][ 2 * yspan + 1 ];
		int px = 0;
		for ( int ix = xstart; ix <= xend; ix++ )
		{
			int py = 0;
			for ( int iy = ystart; iy <= yend; iy++ )
			{
				if ( ix >= 0 && ix < map[ 0 ].length && iy >= 0
						&& iy < map[ 0 ][ 0 ].length && remembers( ix, iy ) )
				{
					ret[ px ][ py ] = map[ z ][ ix ][ iy ];
				}
				/* Las celdas de abajo */
				// if (isValidCoordinate(ix,iy,z) && (map[z][ix][iy] == null ||
				// map[z][ix][iy].getID().equals("AIR"))){
				if ( isValidCoordinate( ix, iy, z ) && ( map[ z ][ ix ][ iy ] == null
						|| map[ z ][ ix ][ iy ].getID( ).equals( "AIR" ) ) )
				{
					int pz = z;
					while ( pz < getDepth( ) - 1 && remembers( ix, iy, pz + 1 ) )
					{
						if ( map[ pz + 1 ][ ix ][ iy ] == null
								|| map[ pz + 1 ][ ix ][ iy ].getID( ).equals( "AIR" ) )
						{
							pz++;
						}
						else
						{
							ret[ px ][ py ] = map[ pz + 1 ][ ix ][ iy ];
							break;
						}
					}
				}
				py++;
			}
			px++;
		}
		return ret;
	}
	/*
	 * public Cell[][] getCellsAround(int x, int y, int z, int xspan, int yspan){
	 * int xstart = x - xspan; int ystart = y - yspan; int xend = x + xspan; int
	 * yend = y + yspan; Cell [][] ret = new Cell [2 * xspan + 1][2 * yspan + 1];
	 * int px = 0; for (int ix = xstart; ix <=xend; ix++){ int py = 0; for (int iy =
	 * ystart ; iy <= yend; iy++){ //Debug.say("px " + px+" py"+py);
	 * //Debug.say("ix " +ix+" iy"+iy+"z"+z); if (ix >= 0 && ix < map[0].length &&
	 * iy >= 0 && iy<map[0][0].length) ret[px][py] = map[z][ix][iy]; py++; } px++; }
	 * return ret; }
	 */

	public SZQueue getMessagesAndEffects( )
	{
		return messagesneffects;
	}

	public LevelMetaData getMetaData( )
	{
		return metaData;
	}

	public Monster getMonsterAt( int x, int y, int z )
	{
		return monsters.getMonsterAt( new Position( x, y, z ) );
	}

	public Monster getMonsterAt( Position x )
	{
		return monsters.getMonsterAt( x );
	}

	public Monster getMonsterByID( String monsterID )
	{
		VMonster monsters = getMonsters( );
		for ( int i = 0; i < monsters.size( ); i++ )
			if ( monsters.elementAt( i ).getID( ).equals( monsterID ) )
				return monsters.elementAt( i );
		return null;
	}

	public VMonster getMonsters( )
	{
		return monsters;
	}

	public String getMusicKeyMorning( )
	{
		return musicKeyMorning;
	}

	public String getMusicKeyNoon( )
	{
		return musicKeyNoon;
	}

	public NPC getNPCByID( String id )
	{
		Vector actors = dispatcher.getActors( );
		for ( int i = 0; i < actors.size( ); i++ )
		{
			if ( actors.elementAt( i ) instanceof NPC )
			{
				NPC npc = (NPC) actors.elementAt( i );
				if ( npc.getNPCID( ).equals( id ) )
				{
					return npc;
				}
			}
		}
		return null;
	}

	public Player getPlayer( )
	{
		return player;
	}

	public SmartFeature getSmartFeature( Position where )
	{
		return (SmartFeature) smartFeatures.get( where.toString( ) );
	}
	/*
	 * public Position getStartPosition() { return startPosition; } public Position
	 * getEndPosition() { return endPosition; }
	 */

	public MonsterSpawnInfo[ ] getSpawnInfo( )
	{
		return inhabitants;
	}

	public Cell[ ][ ] getVisibleCellsAround( int x, int y, int z, int xspan, int yspan )
	{
		int xstart = x - xspan;
		int ystart = y - yspan;
		int xend = x + xspan;
		int yend = y + yspan;
		Cell[ ][ ] ret = new Cell[ 2 * xspan + 1 ][ 2 * yspan + 1 ];
		int px = 0;
		for ( int ix = xstart; ix <= xend; ix++ )
		{
			int py = 0;
			for ( int iy = ystart; iy <= yend; iy++ )
			{
				if ( ix >= 0 && ix < map[ 0 ].length && iy >= 0
						&& iy < map[ 0 ][ 0 ].length && isVisible( ix, iy ) )
				{
					// darken(ix, iy);
					ret[ px ][ py ] = map[ z ][ ix ][ iy ];
					/* Las celdas de abajo */
					if ( isValidCoordinate( ix, iy, z ) && ( map[ z ][ ix ][ iy ] == null
							|| map[ z ][ ix ][ iy ].getID( ).equals( "AIR" ) ) )
					{
						int pz = z;
						while ( pz < getDepth( ) - 1 )
						{
							if ( map[ pz + 1 ][ ix ][ iy ] == null
									|| map[ pz + 1 ][ ix ][ iy ].getID( )
											.equals( "AIR" ) )
							{
								pz++;
							}
							else
							{
								ret[ px ][ py ] = map[ pz + 1 ][ ix ][ iy ];
								// remembered[pz+1][ix][iy]= true;
								break;
							}
						}
					}
				}
				py++;
			}
			px++;
		}
		return ret;
	}

	public int getWidth( )
	{
		return map[ 0 ].length;
	}

	public boolean hasNoonMusic( )
	{
		return musicKeyNoon != null && !musicKeyNoon.equals( "" );
	}

	public boolean isAir( Position where )
	{
		return getMapCell( where ).getID( ).equals( "AIR" );
	}

	public boolean isCandled( )
	{
		return isCandled;
	}

	public boolean isDay( )
	{
		return isDay;
	}

	public boolean isExit( Position pos )
	{
		return getExitOn( pos ) != null;
	}

	public boolean isExitPlaceable( Position where )
	{
		return !getMapCell( where ).isSolid( );
	}
	public boolean isHaunted( )
	{
		return haunted;
	}

	public boolean isHostageSafe( )
	{
		return isHostageSafe;
	}

	public boolean isItemPlaceable( Position where )
	{
		return isWalkable( where ) && getFeatureAt( where ) == null
				&& !getMapCell( where ).isShallowWater( )
				&& !getMapCell( where ).isWater( ) && !getMapCell( where ).isEthereal( );
	}

	public boolean isRutinary( )
	{
		return isRutinary;
	}

	public boolean isSolid( Position where )
	{
		return getMapCell( where ) == null || getMapCell( where ).isSolid( )
				|| ( getFeatureAt( where ) != null && getFeatureAt( where ).isSolid( ) );
	}

	public boolean isValidCoordinate( int x, int y )
	{
		return !( x < 0 || y < 0 || x > getWidth( ) - 1 || y > getHeight( ) - 1 );
	}

	public boolean isValidCoordinate( int x, int y, int z )
	{
		return z >= 0 && z < getDepth( ) && isValidCoordinate( x, y );
	}

	public boolean isValidCoordinate( Position what )
	{
		return isValidCoordinate( what.x, what.y, what.z );
	}

	public boolean isVisible( int x, int y )
	{
		if ( !isValidCoordinate( x, y ) )
			return false;
		return visible[ player
				.getPosition( ).z ][ x ][ y ] /* || lit[player.getPosition().z][x][y] */;
	}

	public boolean isWalkable( Position where )
	{
		return getMapCell( where ) != null && !getMapCell( where ).isSolid( );

		// &&(!getMapCell(where).isWater() || getFrostAt(where) != 0);

	}

	public void lightLights( )
	{
		for ( int i = 0; i < lightSources.size( ); i++ ) {
			Feature f = lightSources.elementAt(i);
			lightAt(f.getPosition(), f.getLight(), true);
		}
	}

	public void loadPop( )
	{
		for ( int i = 0; i < tempActors.size( ); i++ )
		{
			dispatcher.addActor( (Actor) tempActors.elementAt( i ) );
			if ( tempActors.elementAt( i ) instanceof Monster )
			{
				monsters.addMonster( (Monster) tempActors.elementAt( i ) );
			}
		}
	}

	public void populate( )
	{
		if ( getDwellersInfo( ) == null || getDwellersInfo( ).length == 0 )
			return;
		int enemies = Util.rand( 10, 15 ) * getDepth( );
		Position spawnPosition = new Position( 0, 0 );
		for ( int i = 0; i < enemies; i++ )
		{
			MonsterSpawnInfo random = (MonsterSpawnInfo) Util
					.randomElementOf( getDwellersInfo( ) );
			if ( !Util.chance( random.getFrequency( ) ) )
			{
				i--;
				continue;
			}
			Monster monster = MonsterFactory.getFactory( )
					.buildMonster( random.getMonsterID( ) );
			int tries = 0;
			while ( tries < 200 )
			{
				spawnPosition.x = Util.rand( 1, getWidth( ) - 2 );
				spawnPosition.y = Util.rand( 1, getHeight( ) - 2 );
				spawnPosition.z = Util.rand( 0, getDepth( ) - 1 );
				if ( random.getSpawnLocation( ) == MonsterSpawnInfo.UNDERGROUND
						|| random.getSpawnLocation( ) == MonsterSpawnInfo.BORDER )
				{
					if ( !isWalkable( spawnPosition )
							|| getMapCell( spawnPosition ).isWater( )
							|| getMapCell( spawnPosition ).isShallowWater( ) )
					{
						tries++;
						continue;
					}
					else
					{
						break;
					}
				}
				else if ( random.getSpawnLocation( ) == MonsterSpawnInfo.WATER )
				{
					if ( !getMapCell( spawnPosition ).isWater( )
							&& !getMapCell( spawnPosition ).isShallowWater( ) )
					{
						tries++;
						continue;
					}
					else
					{
						break;
					}
				}
			}
			if ( tries < 200 )
			{
				monster.setPosition( spawnPosition.x, spawnPosition.y, spawnPosition.z );
				addMonster( monster );
			}
		}
	}

	public boolean remembers( int x, int y )
	{
		if ( !isValidCoordinate( x, y ) )
			return false;
		return remembered[ player.getPosition( ).z ][ x ][ y ];
	}

	public boolean remembers( int x, int y, int z )
	{
		if ( !isValidCoordinate( x, y, z ) )
			return false;
		return remembered[ z ][ x ][ y ];
	}

	public void removeActor( Actor what )
	{
		Debug.doAssert( what != null, "Tried to remove a null actor to the world" );
		dispatcher.removeActor( what );
	}

	public void removeBoss( )
	{
		monsters.remove( boss );
		dispatcher.removeActor( boss );
		boss = null;
	}

	public void removeCounter( String id )
	{
		hashCounters.remove( id );
	}

	public void removeExit( String exitID ) {
		Position where = exits.get(exitID);
		exitPositions.remove(where.toString());
		exits.remove(exitID);
	}

	public void removeItemFrom( Item what, Position where ) {
		Vector<Item> stack = items.get(where.toString());
		if (stack != null) {
			stack.remove(what);
			if (stack.size() == 0)
				items.values().remove(stack);
		}
	}

	public void removeMonster( Monster what )
	{
		monsters.remove( what );
		dispatcher.removeActor( what );
		what.setLevel( this );
	}

	public void removeRespawner( )
	{
		dispatcher.removeActor( respawner );
		respawner = null;
	}

	public void removeSmartFeature( SmartFeature what )
	{
		smartFeatures.remove( what.getPosition( ).toString( ) );
		dispatcher.removeActor( what );
	}

	public void respawn( )
	{
		Monster monster = MonsterFactory.getFactory( ).getMonsterForLevel( this );
		int spawnPosition = MonsterFactory.getFactory( ).getLastSpawnPosition( );
		if ( monster == null )
			return;
		Position nearPlayer = null;
		boolean ok = false;
		Position add = new Position( 0, 0 );
		switch ( spawnPosition )
		{
		case MonsterSpawnInfo.UNDERGROUND:
		case MonsterSpawnInfo.BORDER:
			for ( int i = 0; i < 30; i++ )
			{
				add.x = Util.rand( -10, 10 );
				add.y = Util.rand( -10, 10 );
				nearPlayer = Position.add( player.getPosition( ), add );
				validate( nearPlayer );
				if ( getMapCell( nearPlayer ) == null
						|| getMapCell( nearPlayer ).isEthereal( )
						|| ( ( getMapCell( nearPlayer ).isWater( )
								|| getMapCell( nearPlayer ).isShallowWater( ) )
								&& !monster.canSwim( ) )
						|| getMapCell( nearPlayer ).isSolid( ) )
					continue;
				ok = true;
				break;
			}
			break;
		case MonsterSpawnInfo.WATER:
			for ( int i = 0; i < 30; i++ )
			{
				add.x = Util.rand( -10, 10 );
				add.y = Util.rand( -10, 10 );
				nearPlayer = Position.add( player.getPosition( ), add );
				validate( nearPlayer );
				if ( getMapCell( nearPlayer ) == null
						|| ( !getMapCell( nearPlayer ).isWater( ) ) )
					continue;
				ok = true;
				break;
			}
			break;
		}

		if ( ok )
		{
			Feature f = FeatureFactory.getFactory( ).buildFeature( "MOUND" );
			f.setPosition( nearPlayer.x, nearPlayer.y, nearPlayer.z );
			addFeature( f );
			Emerger em = new Emerger( monster, nearPlayer, Util.rand( 2, 5 ), f );
			dispatcher.addActor( em );
			em.setSelector( new EmergerAI( ) );
			em.setLevel( this );
		}
	}

	public void savePop( )
	{
		tempActors = new Vector( dispatcher.getActors( ) );
	}
	public void setBoss( Monster what )
	{
		boss = what;
		addMonster( what );
	}

	public void setCells( Cell[ ][ ][ ] what )
	{
		map = what;
		visible = new boolean[ what.length ][ what[ 0 ].length ][ what[ 0 ][ 0 ].length ];
		lit = new boolean[ what.length ][ what[ 0 ].length ][ what[ 0 ][ 0 ].length ];
		remembered = new boolean[ what.length ][ what[ 0 ].length ][ what[ 0 ][ 0 ].length ];
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public void setDispatcher( Dispatcher value )
	{
		Debug.enterMethod( this, "setDispatcher", value );
		dispatcher = value;
		Debug.exitMethod( );
	}

	public void setDwellersInfo( MonsterSpawnInfo[ ] dwellerIDs )
	{
		this.dwellersInfo = dwellerIDs;
	}

	public void setFlag( String flagID, boolean value ) {
		hashFlags.remove(flagID);
		hashFlags.put(flagID, value);
	}

	public void setHaunted( boolean haunted )
	{
		this.haunted = haunted;
	}

	public void setHostageSafe( boolean isHostageSafe )
	{
		this.isHostageSafe = isHostageSafe;
	}

	public void setID( String id )
	{
		ID = id;
	}

	public void setInhabitants( MonsterSpawnInfo[ ] value )
	{
		inhabitants = value;
	}

	public void setIsCandled( boolean value )
	{
		isCandled = value;
	}

	public void setIsDay( boolean newDay )
	{
		if ( haunted )
		{
			if ( isDay )
			{
				if ( !newDay )
				{
					savePop( );
					anihilate( );
					setRespawner( nightRespawner );
				}
			}
			else
			{
				if ( newDay )
				{
					anihilate( );
					removeRespawner( );
					loadPop( );
				}
			}
		}
		isDay = newDay;
	}

	public void setLevelNumber( int value )
	{
		levelNumber = value;
	}

	public void setMapLocationKey( String mapLocationKey )
	{
		this.mapLocationKey = mapLocationKey;
	}

	public void setMetaData( LevelMetaData metaData )
	{
		this.metaData = metaData;
	}

	public void setMusicKeyMorning( String musicKeyMorning )
	{
		this.musicKeyMorning = musicKeyMorning;
	}

	public void setMusicKeyNoon( String musicKeyNoon )
	{
		this.musicKeyNoon = musicKeyNoon;
	}

	public void setNightRespawner( Respawner ap )
	{
		nightRespawner = ap;
	}

	public void setPlayer( Player what )
	{
		player = what;
		if ( !dispatcher.contains( what ) )
			dispatcher.addActor( what, true );
		player.setLevel( this );
	}

	public void setRespawner( Respawner what )
	{
		Debug.enterMethod( this, "setRespawner", what );
		if ( respawner != null )
			dispatcher.removeActor( respawner );
		respawner = what;
		if ( respawner != null )
		{
			dispatcher.addActor( what );
			what.setLevel( this );
		}
		Debug.exitMethod( );
	}

	public void setRutinary( boolean isRutinary )
	{
		this.isRutinary = isRutinary;
	}

	public void setSeen( int x, int y )
	{
		if ( !isValidCoordinate( x, y ) )
			return;
		tempSeen.x = x;
		tempSeen.y = y;
		tempSeen.z = player.getPosition( ).z;
		if ( Position.distance( tempSeen, player.getPosition( ) ) <= player
				.getSightRange( ) || lit[ tempSeen.z ][ tempSeen.x ][ tempSeen.y ] )
		{
			visible[ player.getPosition( ).z ][ x ][ y ] = true;
			remembered[ player.getPosition( ).z ][ x ][ y ] = true;
			Monster m = getMonsterAt( x, y, player.getPosition( ).z );
			if ( m != null )
			{
				m.setWasSeen( true );
			}
		}
	}

	public void setTimecounter( int timeCounter )
	{
		this.timeCounter = timeCounter;
	}

	public void setUnleashers( Unleasher[ ] pUnleashers )
	{
		unleashers = pUnleashers;
	}

	public void signal( Position center, int range, String message )
	{
		Vector actors = dispatcher.getActors( );
		for ( int i = 0; i < actors.size( ); i++ )
		{
			if ( Position.flatDistance( center,
					( (Actor) actors.elementAt( i ) ).getPosition( ) ) <= range )
				( (Actor) actors.elementAt( i ) ).message( message );
		}
	}

	public void spawnTreasure( )
	{
		Position nearPlayer = null;
		while ( true )
		{
			nearPlayer = Position.add( player.getPosition( ),
					new Position( Util.rand( -5, 5 ), Util.rand( -5, 5 ), 0 ) );
			validate( nearPlayer );
			if ( getMapCell( nearPlayer ) == null )
				continue;
			if ( getMapCell( nearPlayer ).getHeight( ) == 0
					&& !getMapCell( nearPlayer ).isWater( )
					&& !getMapCell( nearPlayer ).isShallowWater( )
					&& !getMapCell( nearPlayer ).isSolid( ) )
			{
				String treasure = "";
				switch ( Util.rand( 1, 4 ) )
				{
				case 1:
					treasure = "CROWN";
					break;
				case 2:
					treasure = "CHEST";
					break;
				case 3:
					treasure = "RAINBOW_MONEY_BAG";
					break;
				case 4:
					if ( Util.chance( 70 ) )
						treasure = "MOAUI_HEAD";
					else
						treasure = "CHEST";
				}

				addFeature( treasure, nearPlayer );
				return;
			}
		}
	}

	public void stopTime( int turns )
	{
		dispatcher.setFixed( player, turns );
	}

	public void updateLevelStatus( )
	{
		/*
		 * if (boss != null && boss.isDead())
		 * player.informPlayerEvent(Player.EVT_FORWARD);
		 */
		if ( hashCounters.size( ) > 0 )
		{
			for ( int i = 0; i < hashCounters.size( ); i++ ) {
				hashCounters.elements().nextElement().reduce();
			}
		}
		reduceFrosts( );
		/*
		 * for (int i = 0; i < doomedFeatures.size(); i++){ Feature f = (Feature)
		 * doomedFeatures.elementAt(i); f.setFaint(f.getFaint()-1); if (f.getFaint() <=
		 * 0){ doomedFeatures.remove(f); destroyFeature(f); } }
		 */
		for ( int i = 0; i < doomedFeatures.size( ); i++ ) {
			Feature f = doomedFeatures.elementAt(i);
			f.setFaint(f.getFaint() - 1);
		}
		for ( int i = 0; i < doomedFeatures.size( ); i++ ) {
			Feature f = doomedFeatures.elementAt(i);
			if (f.getFaint() <= 0) {
				doomedFeatures.remove(f);
				destroyFeature(f);
				i--;
			}
		}
	}

	private void lightAt( Position where, int intensity, boolean light )
	{

		lightRunner.z = where.z;
		for ( int x = where.x - intensity; x <= where.x + intensity; x++ )
		{
			for ( int y = where.y - intensity; y <= where.y + intensity; y++ )
			{
				lightRunner.x = x;
				lightRunner.y = y;
				if ( !isValidCoordinate( lightRunner ) )
					continue;
				if ( Position.distance( where, lightRunner ) <= intensity )
				{
					lit[ where.z ][ x ][ y ] = light;
				}
			}
		}
	}

	private void reduceFrosts( ) {
		Enumeration<Counter> counters = frosts.elements();
		while (counters.hasMoreElements()) {
			Counter counter = counters.nextElement();
			counter.reduce();
		}

		Enumeration<String> keys = frosts.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (frosts.get(key).isOver()) {
				addMessage("The ice melts away!");
				player.land();
				frosts.remove(key);
			}
		}
	}

	private void validate( Position what )
	{
		if ( what.x < 0 )
			what.x = 0;
		if ( what.y < 0 )
			what.y = 0;
		if ( what.x > getWidth( ) - 1 )
			what.x = getWidth( ) - 1;
		if ( what.y > getHeight( ) - 1 )
			what.y = getHeight( ) - 1;
	}

}
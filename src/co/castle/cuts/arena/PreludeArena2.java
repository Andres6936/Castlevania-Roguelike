package co.castle.cuts.arena;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.main.Service;

public class PreludeArena2 extends Unleasher
{
	private int counterLimit = 400;
	int counter;

	int superCounter;

	public void unleash( Level level, Game game )
	{
		counter++;
		if ( counter > counterLimit )
		{

			counterLimit = counter * 2;
			counter = 0;
			superCounter++;
			switch ( superCounter )
			{
			case 1:
				level.setInhabitants( new MonsterSpawnInfo[ ]
				{	new MonsterSpawnInfo( "SKELETON_PANTHER", MonsterSpawnInfo.UNDERGROUND,
						100 ),
					new MonsterSpawnInfo( "BLACK_KNIGHT", MonsterSpawnInfo.UNDERGROUND,
							100 ),
					new MonsterSpawnInfo( "SKULL_HEAD", MonsterSpawnInfo.UNDERGROUND,
							100 ) } );
				level.setLevelNumber( 2 );
				level.setMusicKeyMorning( "ARENA2" );
				level.setMusicKeyNoon( "ARENA2" );
				Service.musicManager.playKey( "ARENA2" );
				break;
			case 2:
				level.setInhabitants( new MonsterSpawnInfo[ ]
				{	new MonsterSpawnInfo( "SPEAR_KNIGHT", MonsterSpawnInfo.UNDERGROUND,
						100 ),
					new MonsterSpawnInfo( "WEREBEAR", MonsterSpawnInfo.UNDERGROUND, 100 ),
					new MonsterSpawnInfo( "BONE_ARCHER", MonsterSpawnInfo.UNDERGROUND,
							100 ),
					new MonsterSpawnInfo( "SKELETON_PANTHER",
							MonsterSpawnInfo.UNDERGROUND, 100 ),
					new MonsterSpawnInfo( "AXE_KNIGHT", MonsterSpawnInfo.UNDERGROUND,
							100 ) } );
				level.setLevelNumber( 3 );
				level.setMusicKeyMorning( "ARENA3" );
				level.setMusicKeyNoon( "ARENA3" );
				Service.musicManager.playKey( "ARENA3" );
				break;
			case 3:
				level.setInhabitants( doInhabitants( new String[ ]
				{ "DURGA", "BLADE_SOLDIER", "BONE_HALBERD", "CROW" } ) );
				level.setLevelNumber( 4 );
				level.setMusicKeyMorning( "ARENA4" );
				level.setMusicKeyNoon( "ARENA4" );
				Service.musicManager.playKey( "ARENA4" );
				break;
			case 4:
				level.setInhabitants( doInhabitants( new String[ ]
				{ "COCKATRICE", "COOPER_ARMOR", "SALOME" } ) );
				level.setLevelNumber( 5 );
				level.setMusicKeyMorning( "ARENA5" );
				level.setMusicKeyNoon( "ARENA5" );
				Service.musicManager.playKey( "ARENA5" );
				break;
			case 5:
				level.setInhabitants( doInhabitants( new String[ ]
				{ "LILITH", "BONE_MUSKET", "VAMPIRE_BAT" } ) );
				level.setLevelNumber( 6 );
				level.setMusicKeyMorning( "ARENA6" );
				level.setMusicKeyNoon( "ARENA6" );
				Service.musicManager.playKey( "ARENA6" );
				break;
			case 6:
				level.setInhabitants( doInhabitants( new String[ ]
				{ "ZELDO", "CAGNAZOO", "KNIFE_MERMAN" } ) );
				level.setLevelNumber( 7 );
				level.setMusicKeyMorning( "ARENA7" );
				level.setMusicKeyNoon( "ARENA7" );
				Service.musicManager.playKey( "ARENA7" );
				break;
			case 7:
				level.setInhabitants( doInhabitants( new String[ ]
				{ "GIANTBAT" } ) );
				level.setLevelNumber( 8 );
				level.setMusicKeyMorning( "ARENA8" );
				level.setMusicKeyNoon( "ARENA8" );
				Service.musicManager.playKey( "ARENA8" );
				break;
			case 8:
				level.addMessage(
						"Congratulations! You survived the arena!! But you must keep fighting..." );
			}
		}
	}

	private MonsterSpawnInfo[ ] doInhabitants( String[ ] ids )
	{
		MonsterSpawnInfo[ ] ret = new MonsterSpawnInfo[ ids.length ];
		for ( int i = 0; i < ids.length; i++ )
		{
			ret[ i ] = new MonsterSpawnInfo( ids[ i ], MonsterSpawnInfo.UNDERGROUND,
					100 );
		}
		return ret;
	}
}
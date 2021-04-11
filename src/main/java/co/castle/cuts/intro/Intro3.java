package co.castle.cuts.intro;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.main.Service;
import co.castle.monster.Monster;
import co.castle.monster.MonsterFactory;
import co.castle.ui.UserInterface;
import sz.util.Position;
import sz.util.Util;

public class Intro3 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		if ( level.getFlag( "INTRO2" )
				&& level.getCounter( "COUNTBACK_INTRO_2" ).isOver( ) )
		{
			level.addMessage(
					"A monstruous Warg appears out of nowhere! Remember to 'j'ump and 'a'ttack from safe distance!" );
			Position playerFloor = new Position( level.getPlayer( ).getPosition( ) );
			playerFloor.z = 2;
			int skeletons = Util.rand( 1, 2 );
			for ( int i = 0; i < skeletons + 1; i++ )
			{
				int xpos = Util.rand( -5, 5 );
				int ypos = Util.rand( -5, 5 );
				Position wargPosition = Position.add( playerFloor,
						new Position( xpos, ypos ) );
				if ( level.isWalkable( wargPosition ) )
				{
					String monsterId = "WHITE_SKELETON";
					if ( i == 0 )
						monsterId = "WARG";
					Monster warg = MonsterFactory.getFactory().buildMonster(monsterId);
					warg.setPosition(wargPosition);
					level.addMonster(warg);
				} else {
					i--;
				}
			}
			Service.playKey("WRECKAGE");
			level.setMusicKeyMorning("WRECKAGE");
			if (level.getNPCByID("MELDUCK") != null)
				level.getNPCByID("MELDUCK").setTalkMessage(
						"On your way! I will take care of anything you leave here");
			enabled = false;
			level.removeCounter("COUNTBACK_INTRO_2");
			level.getPlayer().see();
			UserInterface.getUI().refresh();
		}
	}

}
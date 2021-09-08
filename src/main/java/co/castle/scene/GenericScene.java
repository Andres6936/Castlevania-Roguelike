package co.castle.scene;

import co.castle.actor.Actor;
import co.castle.game.MusicManager;
import co.castle.game.PlayerGenerator;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.RepositoryLevelMetadata;
import co.castle.player.Consts;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import sz.util.Util;

import java.util.Hashtable;

public class GenericScene {
    protected final static int DAY_LENGTH = 500;
    protected final Player player;
    protected final UISelector selector;
    protected final UserInterface userInterface;
    protected final Hashtable<String, Level> storedLevels = new Hashtable<>();
    protected final RepositoryLevelMetadata levelMetadata = new RepositoryLevelMetadata();

    protected int timeSwitch;
    protected long turns;
    protected boolean isDay = true;
    protected Level currentLevel;
    protected Dispatcher dispatcher;

    protected GenericScene(UISelector selector) {
        this.selector = selector;
        userInterface = UserInterface.getUI();
        player = PlayerGenerator.thus.generatePlayer();
        player.setGameSessionInfo(new GameSessionInfo());
        player.setSelector(selector);
        player.setPlayerEventListener(new PlayerEventListener() {
            @Override
            public void informEvent(int code) {

            }

            @Override
            public void informEvent(int code, Object param) {

            }
        });
        selector.setPlayer(player);
        userInterface.setPlayer(player);
        userInterface.setGameOver(false);
        userInterface.addCommandListener(pCommand -> {

        });
    }

    protected void checkTimeSwitch() {
        timeSwitch--;
        currentLevel.setTimecounter(timeSwitch);
        if (timeSwitch <= 0) {
            // Environmental Effects, random
            boolean rain = Util.chance(20);
            boolean thunderstorm = !rain && Util.chance(10);
            boolean fog = !rain && !thunderstorm && Util.chance(10);
            boolean sunnyDay = !isDay && !fog && !rain && !thunderstorm
                    && Util.chance(20);

            player.setFlag(Consts.ENV_RAIN, rain);
            player.setFlag(Consts.ENV_THUNDERSTORM, thunderstorm);
            player.setFlag(Consts.ENV_FOG, fog);
            player.setFlag(Consts.ENV_SUNNY, sunnyDay);

            if (isDay) {
                if (currentLevel.hasNoonMusic()) {
                    MusicManager.stopMusic();
                    Display.thus.showTimeChange(!isDay, fog, rain, thunderstorm, false);
                    MusicManager.playKey(currentLevel.getMusicKeyNoon());
                } else {
                    Display.thus.showTimeChange(!isDay, fog, rain, thunderstorm, false);
                }
            } else {

                if (currentLevel.hasNoonMusic()) {
                    MusicManager.stopMusic();
                    Display.thus.showTimeChange(!isDay, fog, rain, thunderstorm,
                            sunnyDay);
                    MusicManager.playKey(currentLevel.getMusicKeyMorning());
                } else {
                    Display.thus.showTimeChange(!isDay, fog, rain, thunderstorm,
                            sunnyDay);
                }
            }
            isDay = !isDay;
            currentLevel.setIsDay(isDay);
            timeSwitch = DAY_LENGTH;
            currentLevel.setTimecounter(timeSwitch);
        }
    }

    protected void update() {
        Actor actor = dispatcher.getNextActor();
        if (actor == player) {
            player.darken();
            player.see();
            if (!player.justJumped())
                userInterface.refresh();
            player.getGameSessionInfo().increaseTurns();
            player.checkDeath();
//            player.getLevel( ).checkUnleashers( this );

        }
        actor.act();
        actor.getLevel().getDispatcher().returnActor(actor);

        if (actor == player) {
            if (currentLevel != null)
                currentLevel.updateLevelStatus();
            // ui.refresh();
            turns++;
            // player.addScore(1);
            checkTimeSwitch();
        }
    }
}

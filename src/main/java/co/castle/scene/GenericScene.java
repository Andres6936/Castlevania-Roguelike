package co.castle.scene;

import co.castle.actor.Actor;
import co.castle.game.CRLException;
import co.castle.game.MusicManager;
import co.castle.game.PlayerGenerator;
import co.castle.item.Item;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.RepositoryLevelMetadata;
import co.castle.levelgen.LevelMaster;
import co.castle.npc.Hostage;
import co.castle.player.Consts;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.CommandListener;
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

    /**
     * True if the user executes an action that as a consequence of a conscious
     * action ends the game, e.g. pressing a button to end the game or a key
     * combination such as CTRL + Q.
     */
    protected boolean processQuit = false;
    protected Level currentLevel;
    protected Dispatcher dispatcher;

    protected GenericScene(UISelector selector, String serial) {
        this.selector = selector;
        userInterface = UserInterface.getUI();
        player = serial == null ? PlayerGenerator.thus.generatePlayer() : PlayerGenerator.thus.createSpecialPlayer(serial);
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
        userInterface.addCommandListener(commandCommand -> {
            if (commandCommand == CommandListener.QUIT) {
                // The user want to exit app.
                processQuit = true;
            }
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

    protected void loadLevel(String levelID) {
        String formerLevelID;
        if (currentLevel != null) {
            if (currentLevel.getBoss() != null && !currentLevel.getBoss().isDead())
                return;
            formerLevelID = currentLevel.getID();
            Level storedLevel = storedLevels.get(formerLevelID);
            if (storedLevel == null) {
                storedLevels.put(formerLevelID, currentLevel);
            }
        } else {
            formerLevelID = "_BACK";
        }
        Level storedLevel = storedLevels.get(levelID);
        if (storedLevel != null) {
            currentLevel = storedLevel;
            player.setPosition(currentLevel.getExitFor(formerLevelID));
            currentLevel.setIsDay(isDay);
            currentLevel.setTimecounter(timeSwitch);
            if (currentLevel.isCandled()) {
                currentLevel.destroyCandles();
                LevelMaster.lightCandles(currentLevel);
            }
        } else {
            try {
                currentLevel = LevelMaster.createLevel(
                        levelMetadata.get(levelID), player);
                currentLevel.setPlayer(player);
                userInterface.setPlayer(player);
                selector.setPlayer(player);
                currentLevel.setIsDay(isDay);
                currentLevel.setTimecounter(timeSwitch);
                if (currentLevel.getPlayer() != null)
                    currentLevel.getPlayer().addHistoricEvent(
                            "got to the " + currentLevel.getDescription());
            } catch (CRLException crle) {
                System.err.println("Error while creating level ");
                crle.printStackTrace();
                System.exit(-100);
            }
        }
        // currentLevel.setLevelNumber(targetLevelNumber);
        player.setLevel(currentLevel);

        if (currentLevel.getExitFor(formerLevelID) != null) {
            player.setPosition(currentLevel.getExitFor(formerLevelID));
        } else if (currentLevel.getExitFor("_START") != null) {
            player.setPosition(currentLevel.getExitFor("_START"));
        }

        if (currentLevel.isHostageSafe()) {
            if (player.hasHostage()) {
                // player.setPosition(currentLevel.getExitFor("_NEXT"));
                Hostage h = player.getHostage();
                player.setHostage(null);
                h.setPosition(player.getPosition().x - 3, player.getPosition().y,
                        player.getPosition().z);
                h.setRescued(true);
                currentLevel.addMonster(h);
                player.addHistoricEvent(
                        "brought " + h.getDescription() + " to safety");
                Display.thus.showHostageRescue(h);
                player.addGold(h.getReward());
                Item reward = h.getItemReward();
                if (reward != null)
                    if (player.canCarry())
                        player.addItem(reward);
                    else
                        player.getLevel().addItem(player.getPosition(), reward);
            }
        }
        dispatcher = currentLevel.getDispatcher();
        if (currentLevel.hasNoonMusic() && !currentLevel.isDay()) {
            MusicManager.playKey(currentLevel.getMusicKeyNoon());
        } else {
            MusicManager.playKey(currentLevel.getMusicKeyMorning());
        }
        if (currentLevel.isRutinary()) {
            currentLevel.anihilateMonsters();
            currentLevel.populate();
        }

        if (currentLevel.getBoss() != null) {
            currentLevel.getBoss().recoverHits();
        }
        if (!dispatcher.contains(player)) {
            dispatcher.addActor(player);
        }
        userInterface.levelChange();
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

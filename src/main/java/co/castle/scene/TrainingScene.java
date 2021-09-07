package co.castle.scene;

import co.castle.game.CRLException;
import co.castle.game.MusicManager;
import co.castle.game.PlayerGenerator;
import co.castle.item.Item;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.RepositoryLevelMetadata;
import co.castle.levelgen.LevelMaster;
import co.castle.levelgen.LevelMetaData;
import co.castle.npc.Hostage;
import co.castle.player.Consts;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import sz.fov.FOV;
import sz.util.Util;

import java.util.Hashtable;

public class TrainingScene implements IScene {
    public final static int DAY_LENGTH = 500;
    private final Player player;
    private final UISelector selector;
    private final UserInterface userInterface;
    private final Hashtable<String, Level> storedLevels = new Hashtable<>();
    private final RepositoryLevelMetadata levelMetadata = new RepositoryLevelMetadata();

    private int timeSwitch;
    private long turns;
    private boolean isDay = true;
    private Level currentLevel;
    private Dispatcher dispatcher;

    public TrainingScene(UISelector selector) {
        this.selector = selector;
        userInterface = UserInterface.getUI();
        player = PlayerGenerator.thus.createSpecialPlayer("SOLIEYU_KID");
        player.setGameSessionInfo(new GameSessionInfo());
        player.setSelector(selector);
        player.setDoNotRecordScore(true);
        selector.setPlayer(player);
        userInterface.setPlayer(player);
        userInterface.setGameOver(false);
        userInterface.addCommandListener(e -> {

        });
        player.setPlayerEventListener(new PlayerEventListener() {
            @Override
            public void informEvent(int code) {

            }

            @Override
            public void informEvent(int code, Object param) {

            }
        });

        LevelMetaData md = new LevelMetaData();
        md.setLevelID("TRAINING");
        levelMetadata.put("TRAINING", md);

        String levelID = "TRAINING";
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
        turns = 0;
        timeSwitch = DAY_LENGTH;

        player.setFOV(new FOV());
        player.getLevel().addMessage("Greetings " + player.getName()
                + ", welcome to the game... Press '?' for Help");
        userInterface.refresh();
        checkTimeSwitch();
    }

    private void checkTimeSwitch() {
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

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }

    @Override
    public TypeScene process() {
        return null;
    }
}

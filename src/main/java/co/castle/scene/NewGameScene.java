package co.castle.scene;

import co.castle.game.CRLException;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.game.PlayerGenerator;
import co.castle.item.Item;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.RepositoryLevelMetadata;
import co.castle.levelgen.LevelMaster;
import co.castle.npc.Hostage;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;

import java.util.Hashtable;

public class NewGameScene implements IScene {

    public final static int DAY_LENGTH = 500;
    private final UISelector selector;
    private final UserInterface userInterface;
    private final Hashtable<String, Level> storedLevels = new Hashtable<>();
    private final RepositoryLevelMetadata levelMetadata = new RepositoryLevelMetadata();

    private int timeSwitch;
    private long turns;
    private boolean isDay = true;
    private Game currentGame;
    private Level currentLevel;
    private Dispatcher dispatcher;

    public NewGameScene(UISelector selector) {
        this.selector = selector;
        userInterface = UserInterface.getUI();
        currentGame = new Game();
        currentGame.setCanSave(false);
        currentGame.setInterfaces(userInterface, selector);
        Player player = PlayerGenerator.thus.generatePlayer();
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
        Display.thus.showIntro(player);

        String levelID = "CHARRIOT_W";
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
        timeSwitch = (int) (DAY_LENGTH / 2.0);
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

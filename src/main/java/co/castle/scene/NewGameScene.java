package co.castle.scene;

import co.castle.actor.Actor;
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
import co.castle.player.Consts;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import sz.csi.CharKey;
import sz.fov.FOV;
import sz.util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class NewGameScene implements IScene {

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

    public NewGameScene(UISelector selector) {
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

        renderer.drawImage(renderer.assets.IMAGE_PROLOGUE);
        renderer.setFontToPanel(renderer.assets.FONT_TITLE);
        renderer.printAtPixel(156, 136, "prologue", Color.WHITE);
        // si.drawImage(311,64, IMG_GBAT);
        renderer.setFontToPanel(renderer.assets.FONT_TEXT);
        renderer.setColor(Color.GRAY);
        JTextArea t1 = createTempArea(150, 170, 510, 300);
        t1.setForeground(Color.LIGHT_GRAY);
        t1.setText(
                "In the year of 1691, a dark castle emerges from the cursed soils of the plains of Transylvannia. "
                        + "Chaos and death spread along the land, as the evil count Dracula unleases his powers, "
                        + "turning its forests and lakes into a pool of blood. \n\n"
                        + "The trip to the castle was long and harsh, after enduring many challenges through all Transylvannia, "
                        + "you are close to the castle of chaos. You are almost at Castlevania, and you are here on business: "
                        + "To destroy forever the Curse of the Evil Count.\n\n" +

                        player.getPlot() + ", " + player.getDescription()
                        + " stands on a forest near the town of Petra and the cursed castle; "
                        + player.getPlot2()
                        + " and the fate running through his veins being the sole hope for mankind.");

        renderer.addComponentToPanel(t1);
        renderer.printAtPixel(156, 490, "[Space] to continue", renderer.assets.COLOR_BOLD);
        renderer.refresh();
        renderer.waitKey(CharKey.SPACE);
        renderer.remove(t1);

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

    private static JTextArea createTempArea(int xpos, int ypos, int w, int h) {
        JTextArea ret = new JTextArea();
        ret.setOpaque(false);
        ret.setForeground(Color.WHITE);
        ret.setVisible(true);
        ret.setEditable(false);
        ret.setFocusable(false);
        ret.setBounds(xpos, ypos, w, h);
        ret.setLineWrap(true);
        ret.setWrapStyleWord(true);
        ret.setFont(renderer.getFont());
        return ret;
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {
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

    @Override
    public TypeScene process() {
        return null;
    }
}

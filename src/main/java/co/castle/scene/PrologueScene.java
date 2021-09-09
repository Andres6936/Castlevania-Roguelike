package co.castle.scene;

import co.castle.levelgen.LevelMetaData;
import co.castle.ui.UISelector;
import sz.fov.FOV;

public class PrologueScene extends GenericScene implements IScene {

    public PrologueScene(UISelector selector) {
        super(selector, "CHRIS");

        LevelMetaData md = new LevelMetaData();
        md.setLevelID("PROLOGUE_KEEP");
        levelMetadata.put("PROLOGUE_KEEP", md);

        loadLevel("PROLOGUE_KEEP");

        currentLevel.setIsDay(false);
        turns = 0;
        timeSwitch = (int) (DAY_LENGTH / 2.0);

        player.setFOV(new FOV());
        player.getLevel().addMessage("Greetings " + player.getName()
                + ", welcome to the game... Press '?' for Help");
        userInterface.refresh();
        checkTimeSwitch();
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public TypeScene process() {
        return super.process();
    }
}

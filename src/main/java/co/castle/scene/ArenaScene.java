package co.castle.scene;

import co.castle.levelgen.LevelMetaData;
import co.castle.ui.UISelector;
import sz.fov.FOV;

public class ArenaScene extends GenericScene implements IScene {

    public ArenaScene(UISelector selector) {
        super(selector, "SONIA");

        LevelMetaData md = new LevelMetaData();
        md.setLevelID("PRELUDE_ARENA");
        levelMetadata.put("PRELUDE_ARENA", md);

        loadLevel("PRELUDE_ARENA");

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
